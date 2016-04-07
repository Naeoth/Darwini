/*
 * Projet Darwini - Étude Pratique
 * 
 * Development of an IA based on genetic algorithms and neural networks.
 *
 * class AcquisitionData.java
 */

package model;

import controller.InitialRobot;
import robocode.ScannedRobotEvent;
import robocode.BattleRules;

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
		private InitialRobot myRobot;
		
		/**
		 * 
		 */
		private ScannedRobotEvent opponentRobot;

        /**
         *
         */
        private BattleRules rules;


	
	/*	----- CONSTRUCTOR -----	*/

		/**
		 * 
		 * 
		 * @param myRobot a
		 * @param rules a
		 */
		public AcquisitionData(InitialRobot myRobot, BattleRules rules) {
			this.myRobot = myRobot;
			this.rules = rules;
			opponentRobot = null;
		}

	
	/*	----- OTHER METHODS -----	*/
	
		/**
		 * Launch a DOData creation with all the parameters necessary
		 * 
		 * @param opponentRobot a
		 */
		public InputData acquisition(ScannedRobotEvent opponentRobot) {
			this.opponentRobot = opponentRobot;
			
			return new InputData(
				getMyBearing(),
				getDistance(),
				getMyEnergy(),
				getOpponentVelocity(),
				getMyVelocity(),
				getOpponentHeading(),
				getMyHeading(),
				getMyRadarHeading(),
				getMyGunHeading(),
				getXDistance(),
				getYDistance()
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
         *
		 * @return a
		 */
		private double getMyBearing() {
			return convert(360, opponentRobot.getBearing());
		}
		
		/**
		 *
         *
		 * @return a
		 */
		private double getDistance() {
			return convert(800, opponentRobot.getDistance());
		}

		/**
		 *
         *
		 * @return a
		 */
		private double getMyEnergy() {
			return convert(800, myRobot.getEnergy());
		}
		
		/**
         *
		 * 
		 * @return a
		 */
		private double getOpponentVelocity() {
			return convert(800, opponentRobot.getVelocity());
		}
		
		/**
		 *
         *
		 * @return a
		 */
		private double getMyVelocity() {
			return convert(360, myRobot.getVelocity());
		}
		
		/**
		 *
         *
		 * @return a
		 */
		private double getOpponentHeading() {
			return convert(360, opponentRobot.getHeading());
		}
		
		/**
		 *
         *
		 * @return a
		 */
		private double getMyHeading(){
			return myRobot.getHeading();
		}

		/**
		 *
         *
		 * @return a
		 */
		private double getMyRadarHeading(){
			return myRobot.getRadarHeading();
		}
		
		/**
		 * 
		 *
         * @return a
		 */
		private double getMyGunHeading(){
			return myRobot.getGunHeading();
		}
		
		/**
		 *
         *
		 * @return a
		 */
		private double getXDistance() {
			double angle = myRobot.getRadarHeading();
			double distance = opponentRobot.getDistance();
            double ret = 0;

			if (angle < 90)
                ret = Math.sin(angle) * distance;
			else if (angle > 90 && angle < 180)
                ret = Math.sin(180 - angle) * distance;
			else if (angle > 180 && angle < 270)
                ret = Math.sin(angle - 180) * distance;
			else if (angle > 270)
                ret = Math.sin(360 - angle) * distance;
			else if (angle == 90 || angle == 270)
                ret = distance;

            return ret;
		}

		/**
		 *
         *
		 * @return a
		 */
		private double getYDistance() {
            double angle = myRobot.getRadarHeading();
            double distance = opponentRobot.getDistance();
            double ret = 0;

            if (angle < 90)
                ret = Math.cos(angle) * distance;
            else if (angle > 90 && angle < 180)
                ret = Math.cos(180 - angle) * distance;
            else if (angle > 180 && angle < 270)
                ret = Math.cos(angle - 180) * distance;
            else if (angle > 270)
                ret = Math.cos(360 - angle) * distance;
			else if (angle == 0 || angle == 180 || angle == 360)
                ret = distance;

            return ret;
		}
		
}
