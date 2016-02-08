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
public class Darwini extends Robot {

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
			perceptronShoot = new Perceptron( getDataFile("Modele.xml") );
			
			super.run();
		}
		
		@Override
		public void onScannedRobot(ScannedRobotEvent e) {
			if (perceptronShoot.decision( acquiData.acquisition(e).toMatrix() ) > 0)
				fire(3);
		}
		
}