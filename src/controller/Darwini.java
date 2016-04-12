/*
 * Projet Darwini - Étude Pratique
 * 
 * Development of an IA based on genetic algorithms and neural networks.
 *
 * class Darwini.java
 */

package controller;

import model.AcquisitionData;
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
		 * The Perceptron xml file
		 * <p>
		 * The file which contains the perceptron's weighting coefficients that our Darwini robot will use. 
		 * This file is charged during the perceptron creation 
		 * </p>
		 * 
		 * @see model.Perceptron
		 * @see controller.Darwini#run()
		 */
		public static final String PERCEPTRON_FILE = "Perceptron.xml";
		
		/**
		 * The object "AcquisitionData"
		 * 
		 * <p>
		 * Thanks to this object, we will be able to collect the environment data of the robot (used as entries in
		 * the perceptron) every turn.
		 * </p>
		 * <p>
		 * This object is initialized in Darwini's run function and called every time Darwini has to make a decision
		 * (Every time it scans an enemy)
		 * </p>
		 * 
		 * @see model.AcquisitionData
		 * @see controller.Darwini#run()
		 * @see controller.Darwini#onScannedRobot(ScannedRobotEvent)
		 */
		private AcquisitionData acquiData;
	
		
		/**
		 * The perceptron "perceptronShoot"
		 * 
		 * <p>
		 * This is the perceptron used in the Darwini's decision process.
		 * </p>
		 * 
		 * @see model.Perceptron
		 * @see controller.Darwini#run()
		 * @see controller.Darwini#onScannedRobot(ScannedRobotEvent)
		 * 
		 */
		private Perceptron perceptronShoot;
		
		
		
		/**
		 * The OutputData "decisions"
		 * 
		 * <p>
		 * This object collect all the perceptron output data which and allowed us to get them more simply throw
		 * gets methods
		 * </p>
		 * 
		 * @see model.OutputData
		 * @see controller.Darwini#onScannedRobot(ScannedRobotEvent)
		 * 
		 */
		private OutputData decisions;
			
		
	/*	----- OTHER METHODS -----	*/
	
		/**
		 * The run methods
		 * 
		 * <p>
		 * We initialized the Darwini's perceptron which will take all decisions and the object acquiData which will 
		 * be collecting, every turns, the environment data needed in the decision process.
		 * </p>
		 * 
		 * @see controller.Darwini#perceptronShoot
		 * @see controller.Darwini#acquiData
		 * @see controller.Darwini#PERCEPTRON_FILE
		 * 
		 */
		
		@Override
		public void run() {
			perceptronShoot = new Perceptron( getDataFile(PERCEPTRON_FILE) );
            acquiData = new AcquisitionData(this, null);

			super.run();
		}
		
		/**
		 * The reaction of Darwini when it has scanned an enemy
		 * 
		 * <p>
		 * We load the environment data (thanks to acquiData) in the Darwini's perceptron and collect the different
		 * perceptron decisions to act
		 * </p>
		 * 
		 * @param e
		 * 			the scanned robot
		 * 
		 * @see controller.Darwini#acquiData
		 * @see controller.Darwini#decisions
		 * @see controller.Darwini#perceptronShoot
		 */
		
		@Override
		public void onScannedRobot(ScannedRobotEvent e) {
			decisions = perceptronShoot.train( acquiData.acquisition(e) );

            System.out.println(decisions.getTurnGunLeft());
		}

}