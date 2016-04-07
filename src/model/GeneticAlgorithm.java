/*
 * Projet Darwini - Étude Pratique
 * 
 * Development of an IA based on genetic algorithms and neural networks.
 *
 * class GeneticAlgorithm.java
 */

package model;

import controller.Darwini;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

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
        private static final String DATA_SUBDIRECTORY = "data/population/";

        /**
         *
         */
        private static final String FILES_PATH = DATA_SUBDIRECTORY + "Individual";

		/**
		 *
		 */
		private static final String PERCEPTRON_DIRECTORY = "out/production/Darwini/controller/Darwini.data/";

        /**
         *
         */
        private static final String ROBOCODE_PATH = "libs/robocode.jar";

        /**
         *
         */
        private static final String BATTLE_PATH = "data/test.battle";


	/*	----- GENETIC SETTINGS -----	*/

        /**
         *
         */
        private static final int NB_THREADS = 5;

        /**
         *
         */
		private static final int POPULATION_SIZE = 5;

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
		private Perceptron[] population;

        /**
         *
         */
        private Score[] scores;
		
	
	/*	----- CONSTRUCTOR -----	*/
		
		/**
		 * 
		 */
		public GeneticAlgorithm() {
			population = new Perceptron[POPULATION_SIZE];
            scores = new Score[POPULATION_SIZE];
            new File(DATA_SUBDIRECTORY).mkdir();

            // Number of files to create or load by one thread
            int rangeSize = POPULATION_SIZE / NB_THREADS;
            // Keep the threads to join all of it
            Thread[] threads = new Thread[NB_THREADS];

            // Create all the threads
            for (int i = 0; i < NB_THREADS; i++) {
                int copy = i;

                // Lambda expression to initialize a thread
                threads[i] = new Thread( () -> {

                    // Each thread creates or loads a range of files depending on the its call order
					File f = null;
					for (int j = copy * rangeSize; j < (copy + 1) * rangeSize; j++) {
						f = new File(FILES_PATH + (j + 1) + ".xml");

                        // If the file exist, loading the perceptron
						if (f.exists())
							population[j] = new Perceptron(f);
                        // Else create a random perceptron
						else
                            try {
                                population[j] = new Perceptron();
                                population[j].printToXML(f);
                            } catch (FileNotFoundException | XMLStreamException e) {
                                e.printStackTrace();
                            }
					}

				});
                threads[i].start();
            }

            // Wait the end of all threads previously started
            for (Thread thread : threads)
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            for (int i = 0; i < POPULATION_SIZE; i++)
                scores[i] = fitness(i);
        }


	/*	----- GENETIC METHODS -----	*/

        /**
         *
         */
        private int keepBest() {
            int best = 0;

            int randomIndex;
            for (int i = 1; i < POPULATION_SIZE; i++) {
                randomIndex = random(0, POPULATION_SIZE);

                if (scores[randomIndex].compareTo(scores[best]) > 1)
                    best = randomIndex;
            }

            return best;
        }

        /**
         *
         */
		private Perceptron selection() {
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
		private Perceptron[] crossover(Perceptron mother, Perceptron father) {
			Perceptron[] children = new Perceptron[2];
            children[0] = mother;
            children[1] = father;

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
		private void mutation(Perceptron child) {
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
		private Score fitness(int individual) {
            try {
                new File(PERCEPTRON_DIRECTORY).mkdir();
                // Copy the tested perceptron in the correct directory
                FileInputStream is = new FileInputStream(FILES_PATH + (individual + 1) + ".xml");
                FileOutputStream os = new FileOutputStream(PERCEPTRON_DIRECTORY + Darwini.PERCEPTRON_FILE);
                byte[] buffer = new byte[1024];
                int length;

                while ((length = is.read(buffer)) > 0)
                    os.write(buffer, 0, length);

                is.close();
                os.close();

                // Launch the test in Robocode
                Runtime.getRuntime().exec("java -Xmx512M -cp " + ROBOCODE_PATH + " robocode.Robocode -battle " + BATTLE_PATH + " -nodisplay -nosound -results results.txt").waitFor();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }

            return new Score();
		}
		
		/**
		 * 
		 */
		public void generate() {
            Perceptron[] newPopulation = new Perceptron[POPULATION_SIZE];
            Score[] newScores = new Score[POPULATION_SIZE];

            // Keep the best individual
            int best = keepBest();
            newPopulation[0] = population[best];
            newScores[0] = scores[best];

            Perceptron[] children;
            for (int i = 1; i < POPULATION_SIZE; i = i + 2) {
                children = crossover(selection(), selection());
                mutation(children[0]);
                mutation(children[1]);
                newPopulation[i] = children[0];
                newScores[i] = fitness(i);
                newPopulation[i + 1] = children[1];
                newScores[i + 1] = fitness(i + 1);
            }

            population = newPopulation;
            scores = newScores;
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
        public Perceptron selectBest() {
            return population[keepBest()];
        }

        /**
         *
         */
		public void savePopulation() {
            int rangeSize = POPULATION_SIZE / NB_THREADS;
            Thread[] threads = new Thread[NB_THREADS];

            for (int i = 0; i < NB_THREADS; i++) {
                int copy = i;
                threads[i] = new Thread( () -> {

                    for (int j = copy * rangeSize; j < (copy + 1) * rangeSize; j++)
                        try {
                            population[j].printToXML( new File(FILES_PATH + (j + 1) + ".xml") );
                        } catch (FileNotFoundException | XMLStreamException e) {
                            e.printStackTrace();
                        }

                });
                threads[i].start();
            }

            for (Thread thread : threads)
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
		}
		
}