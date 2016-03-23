package model;

public class OutputData {
	
	/*	----- ATTRIBUTES -----	*/
	
		/**
		 *
		 */
		public static final int OUTPUT_NEURONS = 8;
		
		/**
		 * 
		 */
		private double shoot;
		
		/**
		 * 
		 */
		private double turnRight;
		
		/**
		 * 
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
			turnRight = results.get(1, 0);
			turnLeft = results.get(2, 0);
			turnRadarRight = results.get(2, 0);
			turnRadarLeft = results.get(2, 0);
			turnGunRight = results.get(2, 0);
			turnGunLeft = results.get(2, 0);
			moveAhead = results.get(2, 0);
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
