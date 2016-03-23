/*
 * Projet Darwini - Étude Pratique
 * 
 * Development of an IA based on genetic algorithms and neural networks.
 *
 * class Darwini.java
 */

package controller;

import model.AcquisitionData;
import model.GeneticAlgorithm;
import model.OutputData;
import model.Perceptron;

import robocode.ScannedRobotEvent;
import robocode.control.events.BattleStartedEvent;

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
public class Darwini extends InitialRobot {

	/*	----- ATTRIBUTES -----	*/

		/**
		 *
		 */
		public static final String PERCEPTRON_FILE = "Perceptron.xml";
		
		/**
		 * 
		 */
		private AcquisitionData acquiData;
	
		/**
		 * 
		 */
		private Perceptron perceptronShoot;
		
		/**
		 * 
		 */
		private OutputData decisions;
			
		
	/*	----- OTHER METHODS -----	*/
		
		@Override
		public void run() {
			perceptronShoot = new Perceptron( getDataFile(PERCEPTRON_FILE) );
			//new GeneticAlgorithm( getDataDirectory() );
			
			super.run();
		}
		
		@Override
		public void onScannedRobot(ScannedRobotEvent e) {
			decisions = perceptronShoot.train( acquiData.acquisition(e) );
		}

		@Override
		public void onBattleStarted(BattleStartedEvent event) {
			acquiData = new AcquisitionData(this, event.getBattleRules());
		}
}