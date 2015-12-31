/*
 * Projet Darwini - Étude Pratique
 * 
 * Development of an IA based on genetic algorithms and neural networks.
 *
 * class DOShootData.java
 */

package model;

/**
 *
 *
 * @version 1.0 - 17/11/15
 * @author BOIZUMAULT Romain
 * @author BUSSENEAU Alexis
 * @author GEFFRAULT Luc
 * @author MATHIEU Vianney
 * @author VAILLAND Guillaume
 */
public class DODataShoot extends AbstractDOData {

	/*	----- CONSTRUCTOR -----	*/

		/**
		 *
		 */
		public DODataShoot(double myBearing, double distance, double myEnergy, double opponentVelocity, double myVelocity, double opponentHeading) {
			super(myBearing, distance, myEnergy, opponentVelocity, myVelocity, opponentHeading);
		}

}