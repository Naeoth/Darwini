/*
 * Projet Darwini - Étude Pratique
 * 
 * Development of an IA based on genetic algorithms and neural networks.
 *
 * class InputData.java
 */

package model;

/**
 * Object which contains all the entries values that we will use in the neural network decision process
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
		
		/*
		 * 
		 */
		private double myVelocity;
		
		/**
		 * 
		 */
		private double opponentHeading;

		/**
		 * 
		 */
		private double myHeading;
		
		/**
		 * 
		 */
		private double myRadarHeading;
		
		/**
		 * 
		 */
		private double myGunHeading;
		
		/**
		 * 
		 */
		private double xDistance;
		
		/**
		 * 
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
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < OutputData.OUTPUT_NEURONS; i++)
				if (success[i])
					sb.append("1 ");
				else
					sb.append("-1 ");
			
			return sb.toString() + "1:" + myBearing + "2: " + distance + "3: " + myEnergy + "4: " + opponentVelocity + "5: " + myVelocity + "6: " + opponentHeading + "7: " + myHeading + "8: " + myRadarHeading + "9: " + myGunHeading + "10: " + xDistance + "11: " + yDistance;
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