/*
 * Projet Darwini - Étude Pratique
 * 
 * Development of an IA based on genetic algorithms and neural networks.
 *
 * class DOData.java
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
public class DOData {

	/*	----- ATTRIBUTES -----	*/
	
		/**
		 * 
		 */
		private int hit;
		
		/**
		 * 
		 */
		private double bearing;
		
		
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
		private double advVelocity;
		
		/**
		 * 
		 */
		private double myVelocity;
		
		/**
		 * 
		 */
		private double advHeading;
		
		/**
		 * 
		 */
		
		
		

	/*	----- CONSTRUCTOR -----	*/

		/**
		 *
		 */
		public DOData(double bearing, double distance, double myEnergy, double advVelocity, double myVelocity, double advHeading) {
			hit = 0;
			this.bearing = bearing;
			this.distance = distance;
			this.myEnergy = myEnergy;
			this.advVelocity = advVelocity;
			this.myVelocity = myVelocity;
			this.advHeading = advHeading;
		}


	/*	----- MUTATORS -----	*/
		
		/**
		 * 
		 */
		public void setHit(int hit) {
			this.hit = hit;
		}

	/*	----- OTHER METHODS -----	*/

		public String toString() {
			return hit + " 1:" + bearing + " 2:" + distance + " 3:" + myEnergy + " 4:" + advVelocity + " 5:" + myVelocity + " 6:" + advHeading;
		}

}