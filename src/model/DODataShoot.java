/*
 * Projet Darwini - Étude Pratique
 * 
 * Development of an IA based on genetic algorithms and neural networks.
 *
 * class DOShootData.java
 */

package model;

/**
 *
 *
 * @version 1.0 - 17/11/15
 * @author BOIZUMAULT Romain
 * @author BUSSENEAU Alexis
 * @author GEFFRAULT Luc
 * @author MATHIEU Vianney
 * @author VAILLAND Guillaume
 */
public class DODataShoot {

	/*	----- ATTRIBUTES -----	*/
	
		/**
		 * 
		 */
		public static final int NB_ENTRIES = 6; 
	
		/**
		 * 
		 */
		private int hit;
		
		/**
		 * 
		 */
		private double myBearing;
		
		
		/**
		 * 
		 */
		private double distance;
		
		
		/**
		 * 
		 */
		private double myEnergy;
		
		/**
		 * 
		 */
		private double opponentVelocity;
		
		/**
		 * 
		 */
		private double myVelocity;
		
		/**
		 * 
		 */
		private double opponentHeading;
		

	/*	----- CONSTRUCTOR -----	*/

		/**
		 *
		 */
		public DODataShoot(double myBearing, double distance, double myEnergy, double opponentVelocity, double myVelocity, double opponentHeading) {
			hit = 0;
			this.myBearing = myBearing;
			this.distance = distance;
			this.myEnergy = myEnergy;
			this.opponentVelocity = opponentVelocity;
			this.myVelocity = myVelocity;
			this.opponentHeading = opponentHeading;
		}


	/*	----- MUTATORS -----	*/
		
		/**
		 * 
		 */
		public void setHit() {
			hit = 1;
		}

	/*	----- OTHER METHODS -----	*/

		public String toString() {
			return hit + " 1:" + myBearing + " 2:" + distance + " 3:" + myEnergy + " 4:" + opponentVelocity + " 5:" + myVelocity + " 6:" + opponentHeading;
		}
		
		public Matrix toMatrix(){
			Matrix matrix = new Matrix(NB_ENTRIES, 1);
			
			matrix.set(0, 0, myBearing);
			matrix.set(1, 0, distance);
			matrix.set(2, 0, myEnergy);
			matrix.set(3, 0, opponentVelocity);
			matrix.set(4, 0, myVelocity);
			matrix.set(5, 0, opponentHeading);
			
			return matrix;
		}

}