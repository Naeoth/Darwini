package model;

public class OutputData {
	
	/*	----- ATTRIBUTES -----	*/
	
		/**
		 * 
		 */
		public static final int OUTPUT_NEURONS = 2;
		
		/**
		 * 
		 */
		private double shoot;
		
		/**
		 * 
		 */
		private double turnRight;
		
	
	/*	----- CONSTRUCTOR -----	*/
		
		/**
		 * 
		 */
		public OutputData(Matrix results) {
			shoot = results.get(0, 0);
			turnRight = results.get(1, 0);
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
		
}
