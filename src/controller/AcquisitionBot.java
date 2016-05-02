/*
 * Projet Darwini - Étude Pratique
 * 
 * Development of an IA based on genetic algorithms and neural networks.
 *
 * class AcquisitionBot.java
 */

package controller;

import java.io.IOException;

import robocode.BulletHitEvent;
import robocode.RoundEndedEvent;
import robocode.ScannedRobotEvent;

import model.acquisition.Database;
import model.acquisition.AcquisitionData;

/**
 * A robot based on an existing one, however this one is used to collect data only useful in the creation
 * Must extend an operational robot extending AdvancedRobot
 *
 * @version 1.0 - 17/11/15
 * @author BOIZUMAULT Romain
 * @author BUSSENEAU Alexis
 * @author GEFFRAULT Luc
 * @author MATHIEU Vianney
 * @author VAILLAND Guillaume
 */
public class AcquisitionBot extends InitialRobot {

	/*	----- ATTRIBUTES -----	*/

		/**
		 *
		 */
		private static final String SSVM_FILE = "data.ssvm";

		/**
		 *
		 */
		private Database knowledges;
		
		/**
		 * 
		 */
		private AcquisitionData acquisitionData;

		
	/*	----- CONSTRUCTOR -----	*/
	
		/**
		 *
		 */
        @Override
        public void run() {
			knowledges = new Database();
			acquisitionData = new AcquisitionData(this);

            super.run();
		}
		
		
	/*	----- OTHER METHODS -----	*/
				
		@Override
		public void onScannedRobot(ScannedRobotEvent e) {
			super.onScannedRobot(e);

            // Get all data required for construct an InputData and save it in a database
			knowledges.insert( acquisitionData.acquisition(e) );

            System.out.println(acquisitionData.acquisition(e).toSSVM());
		}
	
		@Override
		public void onBulletHit(BulletHitEvent e) {
			super.onBulletHit(e);

            // Set a success for the first output neuron in OutputData when the robot hits another robot
			knowledges.getLastData().setSuccess(0);
		}
		
		@Override
		public void onRoundEnded(RoundEndedEvent event) {
			super.onRoundEnded(event);

            // At the end, save all the data collected in a SSVM format file
			try {
				knowledges.printToSSVM( getDataFile(SSVM_FILE) );
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		
}