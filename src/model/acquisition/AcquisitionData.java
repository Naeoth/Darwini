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
	private static final double MAX_DEMI_DEGREE = MAX_DEGREE / 2.0;

	/**
	 *
	 */
	private static final double MAX_ENERGY = 100.0 ;

	/**
	 *
	 */
	private static final double MAX_DEMI_ENERGY = MAX_ENERGY / 2.0;

	/**
	 *
	 */
	private double maxWidth;

	/**
	 *
	 */
	private double maxDemiWidth = maxWidth / 2.0;

	/**
	 *
	 */
	private double maxHeight;

	/**
	 *
	 */
	private double maxDemiHeigt = maxHeight / 2.0;

	/**
	 *
	 */
	private double maxDistance;

	/**
	 *
	 */
	private double maxDemiDistance = maxDistance / 2.0;
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
				myRobot.getX(),
				myRobot.getY(),
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
		return opponentRobot.getBearing() / MAX_BEARING;
	}

	/**
	 *
	 *
	 * @return a
	 */
	private double getDistance() {
		return ( opponentRobot.getDistance() - maxDemiDistance ) / maxDistance;
	}

	/**
	 *
	 *
	 * @return a
	 */
	private double getMyEnergy() {
		return ( myRobot.getEnergy() - MAX_DEMI_ENERGY ) / MAX_ENERGY;
	}

	/**
	 *
	 *
	 * @return a
	 */
	private double getOpponentVelocity() {
		return ( opponentRobot.getVelocity() - Rules.MAX_VELOCITY / 2.0 ) / Rules.MAX_VELOCITY;
	}

	/**
	 *
	 *
	 * @return a
	 */
	private double getMyVelocity() {
		return ( myRobot.getVelocity() - Rules.MAX_VELOCITY / 2.0 ) / Rules.MAX_VELOCITY;
	}

	/**
	 *
	 *
	 * @return a
	 */
	private double getOpponentHeading() {
		return ( opponentRobot.getHeading() - MAX_DEMI_DEGREE )/ MAX_DEGREE;
	}

	/**
	 *
	 *
	 * @return a
	 */
	private double getMyHeading(){
		return ( myRobot.getHeading() -MAX_DEMI_DEGREE ) / MAX_DEGREE;
	}

	/**
	 *
	 *
	 * @return a
	 */
	private double getMyRadarHeading(){
		return ( myRobot.getRadarHeading() - MAX_DEMI_DEGREE )/ MAX_DEGREE;
	}

	/**
	 *
	 *
	 * @return a
	 */
	private double getMyGunHeading(){
		return ( myRobot.getGunHeading() - MAX_DEMI_DEGREE ) / MAX_DEGREE;
	}

	/**
	 *
	 *
	 * @return a
	 */
	private double getXDistance() {

		return ( myRobot.getX() + opponentRobot.getDistance() * Math.sin(myRobot.getHeadingRadians() + opponentRobot.getBearingRadians()) - maxDemiDistance ) / maxDistance ;
	}

	/**
	 *
	 *
	 * @return a
	 */
	private double getYDistance() {
		return ( myRobot.getY() + opponentRobot.getDistance() * Math.cos(myRobot.getHeadingRadians() + opponentRobot.getBearingRadians()) - maxDemiDistance ) / maxDistance ;

	}
}
