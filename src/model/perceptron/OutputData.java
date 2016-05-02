/*
 * Projet Darwini - Étude Pratique
 * 
 * Development of an IA based on genetic algorithms and neural networks.
 *
 * class OutputData.java
 */

package model.perceptron;

/**
 *	Object which contains the neural network result. Every parameters incarnates a decision's value.
 *	Those values will be tested in the darwini execution. If the the value is superior to a predefined threshold
 *	then Darwini will take this decision.
 *
 * @version 1.0 - 17/11/15
 * @author BOIZUMAULT Romain
 * @author BUSSENEAU Alexis
 * @author GEFFRAULT Luc
 * @author MATHIEU Vianney
 * @author VAILLAND Guillaume
*/

public class OutputData {
	
	/*	----- ATTRIBUTES -----	*/
	
		/**
		 *  Number of output Neurons of the neural network
		 */
		protected static final int OUTPUT_NEURONS = 8;
		
		/**
		 * First output neuron
		 * The shooting decision
		 */
		private double shoot;
		
		/**
		 * Second output neuron
		 * The turn right decision
		 */
		private double turnRight;
		
		/**
		 * Third output neuron
		 * The turn left decision
		 */
		private double turnLeft;
		
		/**
		 * Fourth output neuron
		 * The gun turn right decision
		 */
		private double turnGunRight;
		
		/**
		 * Fifth ouput neuron
		 * The gun turn left decision
		 */
		private double turnGunLeft;
		
		/**
		 * Sixth output neuron
		 * The radar turn right decision
		 */
		private double turnRadarRight;
		
		/**
		 * Seventh output neuron
		 * The radar turn left decision
		 */
		private double turnRadarLeft;
		
		/**
		 * Eighth output neuron
		 * The move ahead decision
		 */
		private double moveAhead;


	/*	----- CONSTRUCTOR -----	*/
		
		/**
		 * 
		 */
		public OutputData(Matrix results) {
			shoot = results.get(0, 0);
			turnRight = results.get(0, 1);
			turnLeft = results.get(0, 2);
			turnRadarRight = results.get(0, 3);
			turnRadarLeft = results.get(0, 4);
			turnGunRight = results.get(0, 5);
			turnGunLeft = results.get(0, 6);
			moveAhead = results.get(0, 7);
		}
		
	
	/*	----- ACCESSORS -----	*/
		
		/**
		 * @return The shoot decision
		 */
		public double getShoot() {
			return shoot;
		}
		
		/**
		 * @return The turn right decision
		 */
		public double getTurnRight() {
			return turnRight;
		}
		
		/**
		 * @return The turn left decision
		 */
		public double getTurnLeft() {
			return turnLeft;
		}
		
		/**
		 * @return The radar turn right decision
		 */
		public double getTurnRadarRight() {
			return turnRadarRight;
		}
		
		/**
		 * @return The radar turn left decision
		 */
		public double getTurnRadarLeft() {
			return turnRadarLeft;
		}
		
		/**
		 * @return The gun turn right decision
		 */
		public double getTurnGunRight() {
			return turnGunRight;
		}
		
		/**
		 * @return The gun turn left decision
		 */
		public double getTurnGunLeft() {
			return turnGunLeft;
		}
		
		/**
		 * @return The move ahead decision
		 */
		public double getMoveAhead() {
			return moveAhead;
		}
		
}
