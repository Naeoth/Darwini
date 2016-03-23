/*
 * Projet Darwini - Étude Pratique
 * 
 * Development of an IA based on genetic algorithms and neural networks.
 *
 * class GeneticAlgorithm.java
 */

package model;

import java.io.File;

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
	
	/*	----- ATTRIBUTES -----	*/

        /**
         *
         */
        private static final String DATA_DIRECTORY = "data/";

        /**
         *
         */
        private static final String FILENAME = "Individual";

		/**
		 *
		 */
		private static final String TEST_DIRECTORY = "bin/controller/Darwini.data/";

        /**
         *
         */
        private static final int NB_THREADS = 5;

        /**
         *
         */
		private static final int POPULATION_SIZE = 205;

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
		private static final double MUTATION_PROBABILITY = 0.01;
		
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
            new File(DATA_DIRECTORY).mkdir();

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
						f = new File(DATA_DIRECTORY + FILENAME + (j + 1) + ".xml");

                        // If the file exist, loading the perceptron
						if ( f.exists() )
							population[j] = new Perceptron(f);
                        // Else create a random perceptron
						else {
                            population[j] = new Perceptron();
                            population[j].printToXML(f);
						}

                        scores[j] = fitness(population[j]);
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
		private void mutation(Perceptron child) {
            if (Math.random() < MUTATION_PROBABILITY) {
                mutate(child.getInputWeights());
                mutate(child.getOutputWeights());
                mutate(child.getBias());
            }
		}

		/**
		 * 
		 */
		private Score fitness(Perceptron individual) {
			return null;
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
                newScores[i] = fitness(children[0]);
                newPopulation[i] = children[1];
                newScores[i] = fitness(children[1]);
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
        private void mutate(Matrix m) {

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
                        population[j].printToXML( new File(DATA_DIRECTORY + FILENAME + (j + 1) + ".xml") );

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
