/*
 * Projet Darwini - Étude Pratique
 * 
 * Development of an IA based on genetic algorithms and neural networks.
 *
 * class AcquisitionData.java
 */

package model.acquisition;

import controller.InitialRobot;
import model.perceptron.InputData;
import robocode.Rules;
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
	
	/*	----- ATTRIBUTES -----	*/

        /**
         *
         */
        private static final double MAX_BEARING = 180.0;

        /**
         *
         */
        private static final double MAX_DEGREE = 360.0;

        /**
         *
         */
        private static final double MAX_ENERGY = 100.0;

        /**
         *
         */
        private double maxWidth;

        /**
         *
         */
        private double maxHeight;

        /**
         *
         */
        private double maxDistance;
	
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
		 */
		public AcquisitionData(InitialRobot myRobot) {
			this.myRobot = myRobot;
            maxWidth = myRobot.getBattleFieldWidth();
            maxHeight = myRobot.getBattleFieldHeight();
            maxDistance = Math.sqrt(Math.pow(maxWidth, 2) + Math.pow(maxHeight, 2));
		}

	
	/*	----- OTHER METHODS -----	*/
	
		/**
		 * Launch a InputData creation with all the parameters necessary
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
				getMyX(),
				getMyY()
			);
		}
		
		
	/*	----- ACQUISITION METHODS -----	*/
			
		/**
		 *
         *
		 * @return a
		 */
		private double getMyBearing() {
			return opponentRobot.getBearing() / MAX_BEARING;
		}
		
		/**
		 *
         *
		 * @return a
		 */
		private double getDistance() {
			return opponentRobot.getDistance() / maxDistance;
		}

		/**
		 *
         *
		 * @return a
		 */
		private double getMyEnergy() {
			return myRobot.getEnergy() / MAX_ENERGY;
		}
		
		/**
         *
		 * 
		 * @return a
		 */
		private double getOpponentVelocity() {
			return opponentRobot.getVelocity() / Rules.MAX_VELOCITY;
		}
		
		/**
		 *
         *
		 * @return a
		 */
		private double getMyVelocity() {
			return myRobot.getVelocity() / Rules.MAX_VELOCITY;
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
		private double getMyX() {
            return myRobot.getX() + opponentRobot.getDistance() * Math.sin(myRobot.getHeadingRadians() + opponentRobot.getBearingRadians());
        }

		/**
		 *
         *
		 * @return a
		 */
		private double getMyY(){
            return myRobot.getY() + opponentRobot.getDistance() * Math.cos(myRobot.getHeadingRadians() + opponentRobot.getBearingRadians());
        }
		
}
