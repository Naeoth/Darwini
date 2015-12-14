/*
 * Projet Darwini - Étude Pratique
 * 
 * Development of an IA based on genetic algorithms and neural networks.
 *
 * class DOData.java
 */

package model;

import no.uib.cipr.matrix.sparse.CompDiagMatrix;

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
		public DOData(double myBearing, double distance, double myEnergy, double opponentVelocity, double myVelocity, double opponentHeading) {
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
		
		public CompDiagMatrix toMatrix(){
			CompDiagMatrix shootEntries = new CompDiagMatrix(NB_ENTRIES,1); 
			shootEntries.set(0,0,myBearing);
			shootEntries.set(1,0,distance);
			shootEntries.set(2,0,myEnergy);
			shootEntries.set(3,0,opponentVelocity);
			shootEntries.set(4,0,myVelocity);
			shootEntries.set(5,0,opponentHeading);
			return shootEntries;
		}

}