/*
 * Projet Darwini - Étude Pratique
 * 
 * Development of an IA based on genetic algorithms and neural networks.
 *
 * class LearnedData.java
 */

package model;

import controller.SuperClass;
import robocode.ScannedRobotEvent;

public class LearnedData {
	
	/*	----- ATTRIBUTE -----	*/
	
		/**
		 * 
		 */
		private SuperClass robot;
		
	
	/*	----- CONSTRUCTOR -----	*/

		/**
		 * 
		 * 
		 * @param robot
		 */
		public LearnedData(SuperClass robot) {
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
				robot.getEnergy(),
				e.getVelocity(),
				robot.getVelocity(),
				e.getHeading()
			);
		}
		
	
	/*	----- ACQUISITION METHODS -----	*/
		
		
		/**
		 * 
		 */
		public void getGunBearing(){
			
		}
		
		

}
