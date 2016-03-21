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
		private AcquisitionData acquiData;
	
		/**
		 * 
		 */
		private Perceptron perceptronShoot;
		
		/**
		 * 
		 */
		private OutputData decisions;
		
		
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
			perceptronShoot = new Perceptron( getDataFile("Modele.xml") );
			//new GeneticAlgorithm( getDataDirectory() );
			
			super.run();
		}
		
		@Override
		public void onScannedRobot(ScannedRobotEvent e) {
			decisions = perceptronShoot.train( acquiData.acquisition(e).toMatrix() );
		}
		
}