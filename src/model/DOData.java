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
		private double bearing;
		
		/**
		 * 
		 */
		private double distance;
		
		/**
		 * 
		 */
		private double advEnergy;
		
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
		private double myGunHeading;
		
		/**
		 * 
		 */
		private double myHeading;



	/*	----- CONSTRUCTOR -----	*/

		/**
		 *
		 */
		public DOData(double bearing, double distance, double advEnergy, double myEnergy, double advVelocity, double myVelocity, double myGunHeading, double myHeading) {
			this.bearing = bearing;
			this.distance = distance;
			this.advEnergy = advEnergy;
			this.myEnergy = myEnergy;
			this.advVelocity = advVelocity;
			this.myVelocity = myVelocity;
			this.myGunHeading = myGunHeading;
			this.myHeading = myHeading;
			
		}


	/*	----- ACCESSORS -----	*/


	/*	----- OTHER METHODS -----	*/

		public String toString() {
			return 0 + " 1:" + bearing + " 2:" + distance + " 3:" + advEnergy + " 4:" + myEnergy + " 5:" + advVelocity + " 6:" + myVelocity + " 7:" + myGunHeading + " 8:" + myHeading;
		}

}