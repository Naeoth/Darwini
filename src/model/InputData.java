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
public class InputData {
	

	/*	----- ATTRIBUTES -----	*/
	
		/**
		 * 
		 */
		public static final int INPUT_NEURONS = 6;
	
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

		
		
	/*	----- CONSTRUCTOR -----	*/
		
		/**
		 * 
		 * @param myBearing, distance, myEnergy, opponentVelocity, myVelocity, opponentHeading
		 */
		public InputData(double myBearing, double distance, double myEnergy, double opponentVelocity, double myVelocity, double opponentHeading) {
			success = new boolean[OutputData.OUTPUT_NEURONS];
			this.myBearing = myBearing;
			this.distance = distance;
			this.myEnergy = myEnergy;
			this.opponentVelocity = opponentVelocity;
			this.myVelocity = myVelocity;
			this.opponentHeading = opponentHeading;
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
		 * @return
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
			ret += "6:" + opponentHeading;
			
			return ret;
		}
		
		/**
		 * 
		 * @return
		 */
		public Matrix toMatrix() {
			Matrix matrix = new Matrix(INPUT_NEURONS, 1);
			
			matrix.set(0, 0, myBearing);
			matrix.set(1, 0, distance);
			matrix.set(2, 0, myEnergy);
			matrix.set(3, 0, opponentVelocity);
			matrix.set(4, 0, myVelocity);
			matrix.set(5, 0, opponentHeading);
			
			return matrix;
		}

}