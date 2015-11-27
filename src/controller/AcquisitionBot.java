/*
 * Projet Darwini - Étude Pratique
 * 
 * Development of an IA based on genetic algorithms and neural networks.
 *
 * class AcquisitionBot.java
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
public class AcquisitionBot extends SuperClass {

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
		public AcquisitionBot() {
			super();

			knowledge = new DAOData();
			ld = new LearnedData(this);
		}
		
		
	/*	----- OTHER METHODS -----	*/
				
		@Override
		public void onScannedRobot(ScannedRobotEvent e) {
			if ( getGunHeat() < getGunCoolingRate() )
				knowledge.insert( ld.acquisition(e) );

			super.onScannedRobot(e);
		}
	
		@Override
		public void onBulletHit(BulletHitEvent e) {
			super.onBulletHit(e);
			
			knowledge.getLastData().setHit(1);
		}
	
		@Override
		public void onBattleEnded(BattleEndedEvent e) {				
			super.onBattleEnded(e);
		}
		
		@Override
		public void onRoundEnded(RoundEndedEvent event) {
			super.onRoundEnded(event);
			
			writeDataInFile();
		}
		
		/**
		 * 
		 */
		private void writeDataInFile() {
			try {
				knowledge.printData( getDataFile("darwini.ssvm") );
			}
			catch (IOException e) {
				e.printStackTrace();
				System.err.println("Failed to save collected data.");
			}
		}
		
}