/*
 * Projet Darwini - Étude Pratique
 * 
 * Development of an IA based on genetic algorithms and neural networks.
 *
 * class Darwini.java
 */

package controller;

import model.AcquisitionData;
import model.Perceptron;
import robocode.BattleEndedEvent;
import robocode.Bullet;
import robocode.BulletHitBulletEvent;
import robocode.BulletHitEvent;
import robocode.BulletMissedEvent;
import robocode.RoundEndedEvent;
import robocode.ScannedRobotEvent;
import robocode.SkippedTurnEvent;

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

	/*	----- ATTRIBUTES -----	*/
		
		/**
		 * 
		 */
		private AcquisitionData acquiData;
	
		/**
		 * 
		 */
		private Perceptron perceptronShoot;
		
		
	/*	----- CONSTRUCTOR -----	*/
		
		/**
		 * 
		 */
		public Darwini() {
			acquiData = new AcquisitionData(this);
		}
			
		
	/*	----- OTHER METHODS -----	*/
		
		@Override
		public void run() {		
			super.run();
			
			perceptronShoot = new Perceptron( getDataFile("perceptron.xml") );
		}
		
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
			if (perceptronShoot.decision( acquiData.acquisition(e).toMatrix() ) > 0)
				fire(3);
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