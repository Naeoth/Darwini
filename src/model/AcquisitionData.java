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
import robocode.control.events.BattleStartedEvent;

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
		 * @param myRobot
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
			double angl=myRobot.getRadarHeading();
			double dist=opponentRobot.getDistance();


			if(angl<90) return Math.sin(angl)*dist;
			else if(angl>90 && angl<180) return Math.sin(180-angl)*dist;
			else if(angl>180 && angl<270) return Math.sin(angl-180)*dist;
			else if(angl>270) return Math.sin(360-angl)*dist;
			else if(angl==90 || angl==270) return dist;
			else return 0;
		}
		
		
		/**
		 * 
		 * @return
		 */
		private double getYDistance() {
			double angl=myRobot.getRadarHeading();
			double dist=opponentRobot.getDistance();


			if(angl<90) return Math.cos(angl)*dist;
			else if(angl>90 && angl<180) return Math.cos(180-angl)*dist;
			else if(angl>180 && angl<270) return Math.cos(angl-180)*dist;
			else if(angl>270) return Math.cos(360-angl)*dist;
			else if(angl==0 || angl==180 || angl==360) return dist;
			else return 0;
		}
		
}
