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
 * This object is called by a robot in order to create a new InputData with reduced input values.
 * The values are reduced thanks to maximums which are the AcquisitionData's attribute.
 * This reduction is necessary in order to get entries values between -1 and 1 for our perceptron.
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
	 *	<p>
	 *	   The maximal angle for the bearing
	 *	</p>
	 *
	 * @see InputData
	 */
	private static final double MAX_BEARING = 180.0;

	/**
	 *	<p>
	 *	   The maximal value for any other angle than the bearing
	 *	</p>
	 *
	 * @see InputData
	 */
	private static final double MAX_DEGREE = 360.0;

	/**
	 *	<p>
	 *	   The half-maximal value for an angle
	 *	</p>
	 *
	 * @see InputData
	 */
	private static final double MAX_DEMI_DEGREE = MAX_DEGREE / 2.0;

	/**
	 *	<p>
	 *	   The maximal value of robot's energy
	 *	</p>
	 *
	 * @see InputData
	 */
	private static final double MAX_ENERGY = 100.0 ;

	/**
	 *	<p>
	 *	   The half-maximal value of robot's energy
	 *	</p>
	 *
	 * @see InputData
	 */
	private static final double MAX_DEMI_ENERGY = MAX_ENERGY / 2.0;

	/**
	 *	<p>
	 *	   The maximal width (corresponding to the battlefield width)
	 *	</p>
	 *
	 * @see InputData
	 */
	private double maxWidth;

	/**
	 * <p>
	 *     The half-maximal width (corresponding to the battlefield width)
	 * </p>
	 *
	 * @see InputData
	 */
	private double maxDemiWidth = maxWidth / 2.0;

	/**
	 * <p>
	 *		The maximal height (corresponding to the battlefield height)
	 * </p>
	 *
	 * @see InputData
	 */
	private double maxHeight;

	/**
	 * <p>
	 * 		The half-maximal height (corresponding to the battlefield height)
	 * </p>
	 *
	 * @see InputData
	 */
	private double maxDemiHeigt = maxHeight / 2.0;

	/**
	 * <p>
	 *     The maximal Distance between two robots
	 * </p>
	 *
	 * @see InputData
	 */
	private double maxDistance;

	/**
	 * <p>
	 *     The half-maximal Distance between two robots
	 * </p>
	 *
	 * @see InputData
	 */
	private double maxDemiDistance = maxDistance / 2.0;

	/**
	 * <p>
	 *     Our robot. The robot which has called the creation of Acquisition Data.
	 * </p>
	 *
	 * @see controller.Darwini
	 * @see controller.AcquisitionBot
	 */
	private InitialRobot myRobot;

	/**
	 * <p>
	 *     The opponent Robot, The robot that our robot has scanned.
	 * </p>
	 *
	 * @see controller.Darwini
	 * @see controller.AcquisitionBot
	 */
	private ScannedRobotEvent opponentRobot;



	/*	----- CONSTRUCTOR -----	*/

		/**
		 * <p>
		 *     We just need to set 3 AcquisitionData attributes. The other maximum are known.
		 * </p>
		 * 
		 * @param myRobot The robot which has called the creation of Acquisition Data; our robot.
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
		 * @param opponentRobot The robot that our robot has scanned.
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
	 * @return The reduced bearing
	 *
	 * @see InputData
	 */
	private double getMyBearing() {
		return opponentRobot.getBearing() / MAX_BEARING;
	}

	/**
	 * @return The reduced distance
	 *
	 * @see InputData
	 */
	private double getDistance() {
		return ( opponentRobot.getDistance() - maxDemiDistance ) / maxDistance;
	}

	/**
	 * @return The reduced energy of our robot
	 *
	 * @see InputData
	 */
	private double getMyEnergy() {
		return ( myRobot.getEnergy() - MAX_DEMI_ENERGY ) / MAX_ENERGY;
	}

	/**
	 * @return The reduced opponent's velocity
	 *
	 * @see InputData
	 */
	private double getOpponentVelocity() {
		return ( opponentRobot.getVelocity() - Rules.MAX_VELOCITY / 2.0 ) / Rules.MAX_VELOCITY;
	}

	/**
	 * @return The reduced velocity of our robot
	 *
	 * @see InputData
	 */
	private double getMyVelocity() {
		return ( myRobot.getVelocity() - Rules.MAX_VELOCITY / 2.0 ) / Rules.MAX_VELOCITY;
	}

	/**
	 * @return The reduced opponent's heading
	 *
	 * @see InputData
	 */
	private double getOpponentHeading() {
		return ( opponentRobot.getHeading() - MAX_DEMI_DEGREE )/ MAX_DEGREE;
	}

	/**
	 * @return The reduced heading of our robot
	 *
	 * @see InputData
	 */
	private double getMyHeading(){
		return ( myRobot.getHeading() -MAX_DEMI_DEGREE ) / MAX_DEGREE;
	}

	/**
	 * @return The reduced heading of our robot's radar
	 *
	 * @see InputData
	 */
	private double getMyRadarHeading(){
		return ( myRobot.getRadarHeading() - MAX_DEMI_DEGREE )/ MAX_DEGREE;
	}

	/**
	 * @return The reduced heading of our robot's gun
	 *
	 * @see InputData
	 */
	private double getMyGunHeading(){
		return ( myRobot.getGunHeading() - MAX_DEMI_DEGREE ) / MAX_DEGREE;
	}

	/**
	 * @return The reduced x distance
	 *
	 * @see InputData
	 */
	private double getXDistance() {

		return ( myRobot.getX() + opponentRobot.getDistance() * Math.sin(myRobot.getHeadingRadians() + opponentRobot.getBearingRadians()) - maxDemiDistance ) / maxDistance ;
	}

	/**
	 * @return The reduced y distance
	 *
	 * @see InputData
	 */
	private double getYDistance() {
		return ( myRobot.getY() + opponentRobot.getDistance() * Math.cos(myRobot.getHeadingRadians() + opponentRobot.getBearingRadians()) - maxDemiDistance ) / maxDistance ;

	}
}
