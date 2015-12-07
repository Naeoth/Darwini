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
import robocode.BulletHitEvent;
import robocode.RoundEndedEvent;
import robocode.ScannedRobotEvent;

import model.DAOData;
import model.AcquisitionData;

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
		private DAOData knowledges;
		
		/**
		 * 
		 */
		private AcquisitionData ld;

		
	/*	----- CONSTRUCTOR -----	*/
	
		/**
		 *
		 */
		public AcquisitionBot() {
			super();

			knowledges = new DAOData();
			ld = new AcquisitionData(this);
		}
		
		
	/*	----- OTHER METHODS -----	*/
				
		@Override
		public void onScannedRobot(ScannedRobotEvent e) {
			super.onScannedRobot(e);
			
			if ( getGunHeat() < getGunCoolingRate() )
				knowledges.insert( ld.acquisition(e) );
			
			fire(3);
		}
	
		@Override
		public void onBulletHit(BulletHitEvent e) {
			super.onBulletHit(e);
			
			knowledges.getLastData().setHit();
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
				knowledges.printData( getDataFile("darwini.ssvm") );
			}
			catch (IOException e) {
				e.printStackTrace();
				System.err.println("Failed to save collected data.");
			}
		}
		
}