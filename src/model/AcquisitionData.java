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
import java.lang.Math.*;

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
		
	
	/*	----- CONSTRUCTOR -----	*/

		/**
		 * 
		 * 
		 * @param myRobot
		 */
		public AcquisitionData(InitialRobot myRobot) {
			this.myRobot = myRobot;
			opponentRobot = null;
		}
		
	
	/*	----- OTHER METHODS -----	*/
	
		/**
		 * Launch a DOData creation with all the parameters necessary
		 * 
		 * @param opponentRobot
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
		 * @return
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
			return convert(360, opponentRobot.getHeading());
		}
		
		/**
		 * 
		 * @return
		 */
		
		private double getMyHeading(){
			return myRobot.getHeading();
		}
	
		
		/**
		 * 
		 * @return
		 */
		
		private double getMyRadarHeading(){
			return myRobot.getRadarHeading();
		}
		
		/**
		 * 
		 * 
		 */
		
		private double getMyGunHeading(){
			return myRobot.getGunHeading();
		}
		
		/**
		 * 
		 * @return
		 */
		private double getXDistance() {
			double angle = myRobot.getRadarHeading();
			double distance = opponentRobot.getDistance();
			double ret = 0;
			
			if (angle < 90)
				ret = Math.cos(angle) * distance;
			else if (angle > 90 && angle < 180)
				ret = Math.cos(180 - angle) * distance;
			else if (angle > 180 && angle < 270)
				ret = Math.cos(angle - 180) * distance;
			else if (angle > 360)
				ret = Math.cos(360 - angle) * distance;
			else if (angle == 90 || angle == 180)
				ret = distance;

			return ret;
		}
		
		
		/**
		 * 
		 * @return
		 */
		private double getYDistance() {
            double angle = myRobot.getRadarHeading();
            double distance = opponentRobot.getDistance();
            double ret = 0;

            if (angle < 90)
                ret = Math.sin(angle) * distance;
            else if (angle > 90 && angle < 180)
                ret = Math.sin(180 - angle) * distance;
            else if (angle > 180 && angle < 270)
                ret =  Math.sin(angle - 180) * distance;
            else if (angle > 360)
                ret = Math.sin(360 - angle) * distance;
            else if (angle == 0 || angle == 180)
                ret = distance;

            return ret;
		}

}
