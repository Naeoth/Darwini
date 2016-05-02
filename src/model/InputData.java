/*
 * Projet Darwini - Étude Pratique
 * 
 * Development of an IA based on genetic algorithms and neural networks.
 *
 * class InputData.java
 */

package model;

/**
 * Object which contains all the entries values that we will use in the neural network decision process.
 * The parameters are collected when our robot scanned an other which will be the "enemy" for this turn.
 * Thanks to those parameters, our robot will take a decision for this turn until the next scanned.
 *
 * @version 1.0 - 17/11/15
 * @author BOIZUMAULT Romain
 * @author BUSSENEAU Alexis
 * @author GEFFRAULT Luc
 * @author MATHIEU Vianney
 * @author VAILLAND Guillaume
 */
public class InputData {
	

	/*	----- ATTRIBUTES -----	*/
	
		/**
		 * Number of entries
		 */
		public static final int INPUT_NEURONS = 11;
	

		/**
		 * 
		 */
		private boolean success[];
		


		/**
		 * The direction our robot is going relative to the enemy our robot has scanned
		 */
		private double myBearing;
		

		/**
		 * The distance to the enemy our robot has scanned
		 */
		private double distance;
		

		/**
		 * Our robot's energy
		 */
		private double myEnergy;
		

		/**
		 * The enemy's velocity
		 */
		private double opponentVelocity;


		/**
		 * Our robot velocity
		 */
		private double myVelocity;


		/**
		 * The direction the enemy is facing
		 */
		private double opponentHeading;

		/**
		 * The direction our robot is facing
		 */
		private double myHeading;
		
		/**
		 * The direction our radar is facing
		 */
		private double myRadarHeading;
		
		/**
		 * The direction our gun is facing
		 */
		private double myGunHeading;
		
		/**
		 * The x distance to the enemy
		 */
		private double xDistance;
		
		/**
		 * The y distance to our enemy
		 */
		private double yDistance;
		
	/*	----- CONSTRUCTOR -----	*/
		
		/**
		 * 
		 * @param myBearing, distance, myEnergy, opponentVelocity, myVelocity, opponentHeading
		 */
		public InputData(double myBearing, double distance, double myEnergy, double opponentVelocity, double myVelocity, double opponentHeading, double myHeading, double myRadarHeading, double myGunHeading, double xDistance, double yDistance) {
			success = new boolean[OutputData.OUTPUT_NEURONS];
			this.myBearing = myBearing;
			this.distance = distance;
			this.myEnergy = myEnergy;
			this.opponentVelocity = opponentVelocity;
			this.myVelocity = myVelocity;
			this.opponentHeading = opponentHeading;
			this.myHeading = myHeading;
			this.myRadarHeading = myRadarHeading;
			this.myGunHeading = myGunHeading;
			this.xDistance = xDistance;
			this.yDistance = yDistance;
		}
		
		
	/*	----- MUTATOR -----	*/
		
		/**
		 * 
		 */
		public void setSuccess(int index) {
			success[index] = true;
		}
	
		
	/*	----- OTHER METHODS -----	*/

		/**
		 * 
		 * @return a
		 */
		public String toSSVM() {
			String ret = "";
			for (int i = 0; i < OutputData.OUTPUT_NEURONS; i++)
				if (success[i])
					ret += "1 ";
				else
					ret += "-1 ";
			
			ret += "1:" + myBearing + " ";
			ret += "2:" + distance + " ";
			ret += "3:" + myEnergy + " ";
			ret += "4:" + opponentVelocity + " ";
			ret += "5:" + myVelocity + " ";
			ret += "6:" + opponentHeading + " ";
			ret += "7:" + myHeading + " ";
			ret += "8:" + myRadarHeading + " ";
			ret += "9:" + myGunHeading + " ";
			ret += "10:" + xDistance + " ";
			ret += "11:" + yDistance;

			return ret;
		}
		
		/**
		 * 
		 * @return a
		 */
		public Matrix toMatrix() {
			Matrix matrix = new Matrix(1, INPUT_NEURONS);

			matrix.set(0, 0, myBearing);
			matrix.set(0, 1, distance);
			matrix.set(0, 2, myEnergy);
			matrix.set(0, 3, opponentVelocity);
			matrix.set(0, 4, myVelocity);
			matrix.set(0, 5, opponentHeading);
			matrix.set(0, 6, myHeading);
			matrix.set(0, 7, myRadarHeading);
			matrix.set(0, 8, myGunHeading);
			matrix.set(0, 9, xDistance);
			matrix.set(0, 10, yDistance);

			return matrix;
		}

}