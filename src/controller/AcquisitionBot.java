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
import model.DODataShoot;
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
		private DAOData<DODataShoot> knowledges;
		
		/**
		 * 
		 */
		private AcquisitionData acquiData;

		
	/*	----- CONSTRUCTOR -----	*/
	
		/**
		 *
		 */
		public AcquisitionBot() {
			super();
			
			knowledges = new DAOData<DODataShoot>();
			acquiData = new AcquisitionData(this);
		}
		
		
	/*	----- OTHER METHODS -----	*/
				
		@Override
		public void onScannedRobot(ScannedRobotEvent e) {
			if ( getGunHeat() < getGunCoolingRate() )
				knowledges.insert( acquiData.acquisition(e) );
			
			super.onScannedRobot(e);
			fire(3);
		}
	
		@Override
		public void onBulletHit(BulletHitEvent e) {
			super.onBulletHit(e);
			
			knowledges.getLastData().setSuccess();
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
				knowledges.printSSVM( getDataFile("data.ssvm") );
			}
			catch (IOException e) {
				e.printStackTrace();
				System.err.println("Failed to save collected data.");
			}
		}
		
}