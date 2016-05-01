/*
 * Projet Darwini - Étude Pratique
 * 
 * Development of an IA based on genetic algorithms and neural networks.
 *
 * class OutputData.java
 */

package model.perceptron;

/**
*	Object which contains the neural network result
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
		 */
		private double shoot;
		
		/**
		 * Second output neuron
		 */
		private double turnRight;
		
		/**
		 * Third output neuron
		 */
		private double turnLeft;
		
		/**
		 * 
		 */
		private double turnGunRight;
		
		/**
		 * 
		 */
		private double turnGunLeft;
		
		/**
		 * 
		 */
		private double turnRadarRight;
		
		/**
		 *
		 */
		private double turnRadarLeft;
		
		/**
		 * 
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
		 * 
		 */
		public double getShoot() {
			return shoot;
		}
		
		/**
		 * 
		 */
		public double getTurnRight() {
			return turnRight;
		}
		
		/**
		 * 
		 */
		public double getTurnLeft() {
			return turnLeft;
		}
		
		/**
		 * 
		 */
		public double getTurnRadarRight() {
			return turnRadarRight;
		}
		
		/**
		 * 
		 */
		public double getTurnRadarLeft() {
			return turnRadarLeft;
		}
		
		/**
		 * 
		 */
		public double getTurnGunRight() {
			return turnGunRight;
		}
		
		/**
		 * 
		 */
		public double getTurnGunLeft() {
			return turnGunLeft;
		}
		
		/**
		 * 
		 */
		public double getMoveAhead() {
			return moveAhead;
		}
		
}
