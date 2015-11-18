/*
 * Projet Darwini - Étude Pratique
 * 
 * Development of an IA based on genetic algorithms and neural networks.
 *
 * class LearnedData.java
 */

package model;

import controller.Darwini;
import robocode.ScannedRobotEvent;

public class LearnedData {
	
	/*	----- ATTRIBUTE -----	*/
	
		/**
		 * 
		 */
		private Darwini robot;
		
	
	/*	----- CONSTRUCTOR -----	*/

		/**
		 * 
		 * 
		 * @param robot
		 */
		public LearnedData(Darwini robot) {
			this.robot = robot;
		}
		
	
	/*	----- OTHER METHODS -----	*/
	
		/**
		 * Launch a DOData creation with all the parameters necessary
		 * 
		 * @param e
		 */
		public DOData acquisition(ScannedRobotEvent e) {
			return new DOData(
					e.getBearing(),
					e.getDistance(),
					e.getEnergy(),
					robot.getEnergy(),
					e.getVelocity(),
					robot.getVelocity(),
					getGunHeadingInDegrees(),
					getHeadingInDegrees()
					);
		}

	
	/*	----- ACQUISITION METHODS -----	*/
		
		/**
		 * Convert the robot.getGunHeadingRadians' result in Degrees.
		 * 
		 * @return the robot gun heading in Degrees
		 */
		
		public double getGunHeadingInDegrees(){
			return ( (robot.getGunHeadingRadians() * 180) / Math.PI );
		}
		
		/**
		 * Convert the robot.getHeadingRadians' result in Degrees.
		 * 
		 * @return the robot heading in Degrees
		 */
		
		public double getHeadingInDegrees(){
			return ( (robot.getHeadingRadians() * 180) / Math.PI );
		}
		

}
