/*
 * Projet Darwini - Étude Pratique
 * 
 * Development of an IA based on genetic algorithms and neural networks.
 *
 * class Darwini.java
 */

package controller;

import java.io.IOException;

import robocode.BattleEndedEvent;
import robocode.Bullet;
import robocode.BulletHitBulletEvent;
import robocode.BulletHitEvent;
import robocode.BulletMissedEvent;
import robocode.RoundEndedEvent;
import robocode.ScannedRobotEvent;
import robocode.SkippedTurnEvent;

import model.DAOData;
import model.LearnedData;

/**
 * A robot based on an existing one, however this one will improve itself over time, by building and following a neural network.
 * Must extend an operational robot extending AdvancedRobot
 *
 * @version 1.0 - 17/11/15
 * @author BOIZUMAULT Romain
 * @author BUSSENEAU Alexis
 * @author GEFFRAULT Luc
 * @author MATHIEU Vianney
 * @author VAILLAND Guillaume
 */
public class Darwini extends SuperClass {

	/*	----- ATTRIBUTE -----	*/

		/**
		 *
		 */
		private DAOData knowledge;
		
		/**
		 * 
		 */
		private LearnedData ld;

	/*	----- CONSTRUCTOR -----	*/
	
		/**
		 *
		 */
		public Darwini() {
			super();
		}
		
		
	/*	----- OTHER METHODS -----	*/
		
		// This method is called when a battle starts
		@Override
		public void run() {		
			super.run();
		}
		
		/**
		 * Moves the robot to a certain point on the map, using Robocode's axes.
		 * Returns when the robot has arrived. (possibly before if there has been an error while moving)
		 * @param to the coordinate where it should move
		 */
		/*private void moveTo(Coordinates to) {
			turnRight(Coordinates.getVectorDirection(new Coordinates(getX(), getY()), to) - getHeading());
			ahead(Math.sqrt(Math.pow(to.getX() - getX(), 2) + Math.pow(to.getY() - getY(), 2)));
		}*/
		
		@Override
		public void fire(double power) {
			super.fire(power);
		}
		
		@Override
		public Bullet fireBullet(double power) {
			return super.fireBullet(power);
		}
		
		@Override
		public void setFire(double power) {
			super.setFire(power);
		}
		
		@Override
		public Bullet setFireBullet(double power) {
			return super.fireBullet(power);
		}
		
		@Override
		public void onScannedRobot(ScannedRobotEvent e) {
			super.onScannedRobot(e);
		}
	
		@Override
		public void onBulletHit(BulletHitEvent e) {
			super.onBulletHit(e);
		}
		
		@Override
		public void onBulletMissed(BulletMissedEvent e) {
			super.onBulletMissed(e);
		}
		
		@Override
		public void onBulletHitBullet(BulletHitBulletEvent e) {		
			super.onBulletHitBullet(e);
		}
	
		@Override
		public void onBattleEnded(BattleEndedEvent e) {				
			super.onBattleEnded(e);
		}
		
		@Override
		public void onSkippedTurn(SkippedTurnEvent e) {
			System.err.println("Taking too long to compute stuff, another turn was skipped :(");
			super.onSkippedTurn(e);
		} 
		
		@Override
		public void onRoundEnded(RoundEndedEvent event) {
			super.onRoundEnded(event);
		}
		
}