/*
 * Projet Darwini - Étude Pratique
 * 
 * Development of an IA based on genetic algorithms and neural networks.
 *
 * class GeneticAlgorithm.java
 */

package model.genetic;

import controller.Darwini;
import model.perceptron.Matrix;
import model.perceptron.NeuralNetwork;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * A robot based on an existing one, however this one will improve itself over time, by building and following a neural network.
 * Must extend an operational robot extending AdvancedRobot
 *
 * @version 1.0 - 17/11/15
 * @author BOIZUMAULT Romain
 * @author BUSSENEAU Alexis
 * @author GEFFRAULT Luc
 * @author MATHIEU Vianney
 * @author VAILLAND Guillaume
 */
public class GeneticAlgorithm {

	/*	----- PATHS -----	*/

        /**
         *
         */
        private static final String POPULATION_DIRECTORY = "data/population/";

        /**
         *
         */
        private static final String ROBOT_DIRECTORY = "out/production/Darwini/controller/Darwini.data/";

        /**
         *
         */
        private static final String INDIVIDUAL_FILENAME = "Individual";

        /**
         *
         */
        private static final String ROBOCODE_PATH = "libs/robocode.jar";

        /**
         *
         */
        private static final String BATTLE_PATH = "data/test.battle";

        /**
         *
         */
        private static final String RESULTS_PATH = "/tmp/results.txt";


	/*	----- GENETIC SETTINGS -----	*/

        /**
         *
         */
        private static final int NB_THREADS = 3 * Runtime.getRuntime().availableProcessors() / 4;

        /**
         *
         */
		private static final int POPULATION_SIZE = 11;

        /**
         *
         */
        private static final int TOURNAMENT_SIZE = 5;

		/**
		 *
		 */
		private static final double CROSSOVER_PROBABILITY = 0.7;

		/**
		 *
		 */
		private static final double MUTATION_PROBABILITY = 0.5;

        /**
         *
         */
        private static final int MUTATION_MIN = 5;

        /**
         *
         */
        private static final int MUTATION_MAX = 10;


	/*	----- ATTRIBUTES -----	*/

		/**
		 *
		 */
		private NeuralNetwork[] population;

        /**
         *
         */
        private Score[] scores;


	/*	----- CONSTRUCTOR -----	*/

		/**
		 *
		 */
		public GeneticAlgorithm() {
			population = new NeuralNetwork[POPULATION_SIZE];
            scores = new Score[POPULATION_SIZE];
            new File(POPULATION_DIRECTORY).mkdir();
            new File(ROBOT_DIRECTORY).mkdir();

            System.out.println(POPULATION_SIZE + " individuals are being initialized...");
            ExecutorService executor = Executors.newFixedThreadPool(NB_THREADS);
            for (int i = 0; i < POPULATION_SIZE; i++) {
                int individual = i;
                executor.submit(() -> {
                    File file = new File(POPULATION_DIRECTORY + INDIVIDUAL_FILENAME + (individual + 1) + ".xml");

                    // If the file exist, loading the perceptron
                    if (file.exists())
                        population[individual] = new NeuralNetwork(file);
                    // Else create a random perceptron
                    else
                        try {
                            population[individual] = new NeuralNetwork();
                            population[individual].printToXML(file);
                        } catch (FileNotFoundException | XMLStreamException e) {
                            e.printStackTrace();
                        }

                    scores[individual] = fitness(individual);
                    System.out.println("\tIndividual n°" + (individual + 1) + "...OK");
                });
            }
            executor.shutdown();
            try {
                executor.awaitTermination(6000, TimeUnit.SECONDS);
            }
            catch (InterruptedException e) {
                System.out.println("Initialization takes too much time, please change your computer");
            }
            System.out.println("DONE");
        }


	/*	----- GENETIC METHODS -----	*/

        /**
         *
         */
        public int keepBest() {
            int best = 0;

            for (int i = 1; i < POPULATION_SIZE; i++) {

                if (scores[i].compareTo(scores[best]) > 0)
                    best = i;
            }

            return best;
        }

        /**
         *
         */
		private NeuralNetwork selection() {
            int chosen = random(0, POPULATION_SIZE);

            int randomIndex;
            for (int i = 0; i < TOURNAMENT_SIZE - 1; i++) {
                randomIndex = random(0, POPULATION_SIZE);
                if (scores[randomIndex].compareTo(scores[chosen]) > 1)
                    chosen = randomIndex;
            }

			return population[chosen];
		}

		/**
		 *
		 */
		private NeuralNetwork[] crossover(NeuralNetwork mother, NeuralNetwork father) {
			NeuralNetwork[] children = {mother, father};

            if (Math.random() < CROSSOVER_PROBABILITY) {
                cross(mother.getInputWeights(), father.getInputWeights());
                cross(mother.getOutputWeights(), father.getOutputWeights());
                cross(mother.getBias(), father.getBias());
            }

			return children;
		}

        /**
         *
         */
        private void cross(Matrix m1, Matrix m2) {
            int size = m1.getRowCount() * m2.getColumnCount();
            int firstCrossing = random(0, size);
            int secondCrossing = random(firstCrossing, size);

            int cols = m1.getColumnCount();
            double tmp;
            for (int i = 0; i < m1.getRowCount(); i++)
                for (int j = 0; j <  m1.getColumnCount(); j++) {
                    tmp = i * cols + j;
                    if (tmp < firstCrossing || tmp >= secondCrossing) {
                        tmp = m1.get(i, j);
                        m1.set(i, j, m2.get(i, j));
                        m2.set(i, j, tmp);
                    }
                }
        }

        /**
         *
         */
		private void mutation(NeuralNetwork child) {
            if (Math.random() < MUTATION_PROBABILITY)
                mutate(child.getInputWeights());
            if (Math.random() < MUTATION_PROBABILITY)
                mutate(child.getOutputWeights());
            if (Math.random() < MUTATION_PROBABILITY)
                mutate(child.getBias());
		}

        /**
         *
         */
        private void mutate(Matrix m) {
            int rowCount = m.getRowCount();
            int columnCount = m.getColumnCount();

            for (int i = 0; i < random(MUTATION_MIN, MUTATION_MAX); i++)
                m.set(random(0, rowCount), random(0, columnCount) , Math.random() * 2 - 1);
        }

		/**
		 *
		 */
		private synchronized Score fitness(int individual) {
            try {
                // Copy the tested perceptron in the correct directory
                FileInputStream is = new FileInputStream(POPULATION_DIRECTORY + INDIVIDUAL_FILENAME + (individual + 1) + ".xml");
                FileOutputStream os = new FileOutputStream(ROBOT_DIRECTORY + Darwini.PERCEPTRON_FILE);
                byte[] buffer = new byte[1024];
                int length;

                while ((length = is.read(buffer)) > 0)
                    os.write(buffer, 0, length);

                is.close();
                os.close();

                // Launch the test in Robocode
                Runtime.getRuntime().exec("java -Xmx512M -DWORKINGDIRECTORY=data -cp " + ROBOCODE_PATH + " robocode.Robocode -nosound -nodisplay -battle " + BATTLE_PATH + " -results " + RESULTS_PATH).waitFor();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }

            return new Score(RESULTS_PATH, "Darwini");
        }

		/**
		 *
		 */
		public void generate(int numberGeneration) throws IllegalArgumentException {
            if (numberGeneration < 1)
                throw new IllegalArgumentException("The number of generation must be greater than 0");

            NeuralNetwork[] newPopulation = new NeuralNetwork[POPULATION_SIZE];
            Score[] newScores = new Score[POPULATION_SIZE];
            NeuralNetwork[] children;
            int best;

            for (int i = 0; i < numberGeneration; i++) {
                System.out.println("Generation n°" + i + 1 + "...");
                // Keep the best individual
                best = keepBest();
                newPopulation[0] = population[best];
                newScores[0] = scores[best];

                for (int j = 1; j < POPULATION_SIZE; j = j + 2) {
                    children = crossover(selection(), selection());
                    mutation(children[0]);
                    newPopulation[j] = children[0];
                    newScores[j] = fitness(j);
                    if (j != POPULATION_SIZE - 1) {
                        mutation(children[1]);
                        newPopulation[j + 1] = children[1];
                        newScores[j + 1] = fitness(j + 1);
                    }
                }

                population = newPopulation;
                scores = newScores;
            }
		}


	/*	----- OTHER METHODS -----	*/

        /**
         *
         */
        private int random(int min, int max) {
            return (int)(Math.random() * (max - min)) + min;
        }

        /**
         *
         */
		public void savePopulation() {
            ExecutorService executor = Executors.newFixedThreadPool(NB_THREADS);
            for (int i = 0; i < POPULATION_SIZE; i++) {
                int individual = i;
                executor.submit(() -> {
                    try {
                        population[individual].printToXML( new File(POPULATION_DIRECTORY + INDIVIDUAL_FILENAME + (individual + 1) + ".xml") );
                    } catch (FileNotFoundException | XMLStreamException e) {
                        e.printStackTrace();
                    }
                });
            }
            executor.shutdown();
            try {
                executor.awaitTermination(6000, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                System.out.println("Saving the population takes too much time, please change your computer");
            }
        }

        public Score[] getScores() {
            return this.scores;
        }

}