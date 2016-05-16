/*
 * Projet Darwini - Étude Pratique
 * 
 * Development of an IA based on genetic algorithms and neural networks.
 *
 * class Darwini.java
 */

package controller;

import model.acquisition.AcquisitionData;
import model.perceptron.OutputData;
import model.perceptron.NeuralNetwork;

import robocode.ScannedRobotEvent;

/**
 * <p>
 * A robot based on an existing one, however this one will improve itself over time, by building and following a neural network.
 * Must extend an operational robot extending AdvancedRobot
 * </p>
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
		 * The NeuralNetwork xml file
		 *
		 * <p>
		 * The file which contains the perceptron's weighting coefficients that our Darwini robot will use. 
		 * This file is charged during the perceptron creation 
		 * </p>
		 * 
		 * @see NeuralNetwork
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
		 * @see AcquisitionData
		 * @see controller.Darwini#run()
		 * @see controller.Darwini#onScannedRobot(ScannedRobotEvent)
		 */
		private AcquisitionData acquisitionData;

		/**
		 * The perceptron "neuralNetworkShoot"
		 * 
		 * <p>
		 * This is the perceptron used in the Darwini's decision process.
		 * </p>
		 * 
		 * @see NeuralNetwork
		 * @see controller.Darwini#run()
		 * @see controller.Darwini#onScannedRobot(ScannedRobotEvent)
		 * 
		 */
		private NeuralNetwork perceptron;
		
		/**
		 * The OutputData "decisions"
		 * 
		 * <p>
		 * This object collect all the perceptron output data which and allowed us to get them more simply throw
		 * gets methods
		 * </p>
		 * 
		 * @see OutputData
		 * @see controller.Darwini#onScannedRobot(ScannedRobotEvent)
		 * 
		 */
		private OutputData decisions;
			
		
	/*	----- OTHER METHODS -----	*/
	
		/**
		 * The run methods
		 * 
		 * <p>
		 * We initialized the Darwini's perceptron which will take all decisions and the object acquisitionData which will
		 * be collecting, every turns, the environment data needed in the decision process.
		 * </p>
		 * 
		 * @see controller.Darwini#perceptron
		 * @see controller.Darwini#acquisitionData
		 * @see controller.Darwini#PERCEPTRON_FILE
		 * 
		 */
		@Override
		public void run() {
			perceptron = new NeuralNetwork( getDataFile(PERCEPTRON_FILE) );
            		acquisitionData = new AcquisitionData(this);
			super.run();
		}
		
		/**
		 * The reaction of Darwini when it has scanned an enemy
		 * 
		 * <p>
		 * We load the environment data (thanks to acquisitionData) in the Darwini's perceptron and collect the different
		 * perceptron decisions to act
		 * </p>
		 * 
		 * @param e the scanned robot
		 * 
		 * @see controller.Darwini#acquisitionData
		 * @see controller.Darwini#decisions
		 * @see controller.Darwini#perceptron
		 */
		@Override
		public void onScannedRobot(ScannedRobotEvent e) {
			decisions = perceptron.train( acquisitionData.acquisition(e) );

           		 System.out.println(decisions.toString());
		}



		public void decisionRobot(OutputData decisions){

            decisions=this.decisions;

			if (decisions.getShoot() > 0 ){
				fire(10);
			}

			if (decisions.getTurnRight() > 0 ){
				turnRightRadians(0);
			}
			if (decisions.getTurnLeft() > 0 ){
				turnLeftRadians(3.14);	//Pi
			}
			
			if (decisions.getTurnRadarRight() > 0 ){
				turnRadarRightRadians(0);
			}

			if (decisions.getTurnRadarLeft() > 0 ){
				turnRadarLeftRadians(3.14);
			}
	

			if (decisions.getTurnGunRight() > 0 ){
				turnGunRightRadians(0);
			}

			if (decisions.getTurnGunLeft() > 0 ){
				turnGunLeftRadians(3.14);
			}

			if (decisions.getMoveAhead() > 0 ){
				ahead(3);
			}


		}


}


















