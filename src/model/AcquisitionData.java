/*
 * Projet Darwini - Étude Pratique
 * 
 * Development of an IA based on genetic algorithms and neural networks.
 *
 * class AcquisitionData.java
 */

package model;

import controller.SuperClass;
import robocode.ScannedRobotEvent;

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
public class AcquisitionData {
	
	/*	----- ATTRIBUTE -----	*/
	
		/**
		 * 
		 */
		private SuperClass myRobot;
		
		/**
		 * 
		 */
		private ScannedRobotEvent opponentRobot;
		
	
	/*	----- CONSTRUCTOR -----	*/

		/**
		 * 
		 * 
		 * @param robot
		 */
		public AcquisitionData(SuperClass myRobot) {
			this.myRobot = myRobot;
			opponentRobot = null;
		}
		
	
	/*	----- OTHER METHODS -----	*/
	
		/**
		 * Launch a DOData creation with all the parameters necessary
		 * 
		 * @param e
		 */
		public DODataShoot acquisition(ScannedRobotEvent opponentRobot) {
			this.opponentRobot = opponentRobot;
			
			return new DODataShoot(
				getMyBearing(),
				getDistance(),
				getMyEnergy(),
				getOpponentVelocity(),
				getMyVelocity(),
				getOpponentHeading()
			);
		}
		
		/**
		 * 
		 */
		private double convert(double max, double value) {
			return value / max;
		}
		
		
	/*	----- ACQUISITION METHODS -----	*/
		
		/**
		 * 
		 */
		private double getMyBearing() {
			return convert(360, opponentRobot.getBearing());
		}
		
		/**
		 * 
		 * @return
		 */
		private double getDistance() {
			return convert(800, opponentRobot.getDistance());
		}

		/**
		 * 
		 * @return
		 */
		private double getMyEnergy() {
			return convert(800, myRobot.getEnergy());
		}
		
		/**
		 * 
		 * @return
		 */
		private double getOpponentVelocity() {
			return convert(800, opponentRobot.getVelocity());
		}
		
		/**
		 * 
		 * @return
		 */
		private double getMyVelocity() {
			return convert(360, myRobot.getVelocity());
		}
		
		/**
		 * 
		 * @return
		 */
		private double getOpponentHeading() {
			return convert(360, opponentRobot.getBearing());
		}

}
