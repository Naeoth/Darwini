/*
 * Projet Darwini - Étude Pratique
 * 
 * Development of an IA based on genetic algorithms and neural networks.
 *
 * class GeneticAlgorithm.java
 */

package model;

import java.io.File;
import java.util.ArrayList;

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
		private static final String FILENAME = "Individual";

		/**
		 * 
		 */
		private static final int INITIAL_POPULATION = 500;
		
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
		ArrayList<Perceptron> initialPopulation;
		
	
	/*	----- CONSTRUCTOR -----	*/
		
		/**
		 * 
		 */
		public GeneticAlgorithm(File directory) {
			initialPopulation = new ArrayList<>(INITIAL_POPULATION);
			File f;
			
			for (int i = 1; i <= INITIAL_POPULATION; i++) {
				f = new File(directory.getAbsolutePath() + "/" + FILENAME + i + ".xml");
				if ( f.exists() )
					initialPopulation.add( new Perceptron(f) );
				else
					initialPopulation.add( new Perceptron() );
			}
		}
		
	
	/*	----- OTHER METHODS -----	*/
		
		/**
		 * 
		 */
		private Perceptron[] selection() {
			Perceptron[] parents = new Perceptron[2]; 
			
			return parents;
		}
		
		/**
		 * 
		 */
		private Perceptron[] crossover(Perceptron mother, Perceptron father) {
			Perceptron[] children = new Perceptron[2]; 
			
			return children;
		}
		
		/**
		 * 
		 */
		private void mutation(Perceptron child) {
			
		}
		
		/**
		 * 
		 */
		private Perceptron[] terminaison(Perceptron mother, Perceptron father, Perceptron child1, Perceptron child2) {
			Perceptron[] bestIndividuals = new Perceptron[2]; 
			
			return bestIndividuals;
		}
		
		/**
		 * 
		 */
		private double test(Perceptron individual) {
			return 0;
		}
		
		/**
		 * 
		 */
		public void run() {
			Perceptron[] parents = selection();
			Perceptron[] children = crossover(parents[0], parents[1]);
			mutation(children[0]);
			mutation(children[1]);
			terminaison(parents[0], parents[1], children[0], children[1]);
		}
		
		/**
		 * 
		 */
		public void savePopulation() {
			/*for (int i = 1; i <= INITIAL_POPULATION; i++) {
				f = new File(directory.getAbsolutePath() + "/" + FILENAME + i + ".xml");*/
		}
		
}
