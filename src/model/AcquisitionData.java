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
	
	/*	----- ATTRIBUTES -----	*/

		/**
		 *
		 */
		private static final int MAX_DEGREE = 360;

        /**
         *
         */
        private int mapSize = 800;

        /**
         *
         */
        private int maxEnergy;

        /**
         *
         */
        private int maxVelocity;
	
		/**
		 * 
		 */
		private InitialRobot myRobot;
		
		/**
		 * 
		 */
		private ScannedRobotEvent opponentRobot;

	
	/*	----- CONSTRUCTOR -----	*/

		/**
		 * 
		 * 
		 * @param myRobot a
		 * @param rules a
		 */
		public AcquisitionData(InitialRobot myRobot, BattleRules rules) {
			this.myRobot = myRobot;
			opponentRobot = null;
            maxEnergy = 20;
            mapSize = 800;
            maxVelocity = 20;
		}

	
	/*	----- OTHER METHODS -----	*/
	
		/**
		 * Launch a DOData creation with all the parameters necessary
		 * 
		 * @param opponentRobot a
		 */
		public InputData acquisition(ScannedRobotEvent opponentRobot) {
			this.opponentRobot = opponentRobot;
			System.out.println(opponentRobot.getEnergy());
			
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
		
		
	/*	----- ACQUISITION METHODS -----	*/
			
		/**
		 *
         *
		 * @return a
		 */
		private double getMyBearing() {
			return opponentRobot.getBearing() / MAX_DEGREE;
		}
		
		/**
		 *
         *
		 * @return a
		 */
		private double getDistance() {
			return opponentRobot.getDistance() / mapSize;
		}

		/**
		 *
         *
		 * @return a
		 */
		private double getMyEnergy() {
			return myRobot.getEnergy() / maxEnergy;
		}
		
		/**
         *
		 * 
		 * @return a
		 */
		private double getOpponentVelocity() {
			return opponentRobot.getVelocity() / maxVelocity;
		}
		
		/**
		 *
         *
		 * @return a
		 */
		private double getMyVelocity() {
			return myRobot.getVelocity() / maxVelocity;
		}
		
		/**
		 *
         *
		 * @return a
		 */
		private double getOpponentHeading() {
			return opponentRobot.getHeading() / MAX_DEGREE;
		}
		
		/**
		 *
         *
		 * @return a
		 */
		private double getMyHeading(){
			return myRobot.getHeading() / MAX_DEGREE;
		}

		/**
		 *
         *
		 * @return a
		 */
		private double getMyRadarHeading(){
			return myRobot.getRadarHeading() / MAX_DEGREE;
		}
		
		/**
		 * 
		 *
         * @return a
		 */
		private double getMyGunHeading(){
			return myRobot.getGunHeading() / MAX_DEGREE;
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
