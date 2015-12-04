/*
 * Projet Darwini - Étude Pratique
 * 
 * Development of an IA based on genetic algorithms and neural networks.
 *
 * class SuperClass.java
 */

package controller;

import java.awt.Color;

import robocode.AdvancedRobot;
import robocode.HitRobotEvent;
import robocode.ScannedRobotEvent;

/**
 * SpinBot - a sample robot by Mathew Nelson.
 * <p/>
 * Moves in a circle, firing hard when an enemy is detected.
 *
 * @author Mathew A. Nelson (original)
 * @author Flemming N. Larsen (contributor)
 */
public class SuperClass extends AdvancedRobot {
	
	/*	----- OTHER METHODS -----	*/

		/**
		 * SpinBot's run method - Circle
		 */
		public void run() {
			// Set colors
			setBodyColor(Color.blue);
			setGunColor(Color.blue);
			setRadarColor(Color.black);
			setScanColor(Color.yellow);
	
			// Loop forever
			while (true) {
				// Tell the game that when we take move, we'll also want to turn right... a lot.
				setTurnRight(10000);
				// Limit our speed to 5
				setMaxVelocity(5);
				// Start moving (and turning)
				ahead(10000);
				// Repeat.
			}
		}

		/**
		 * onScannedRobot: Fire hard!
		 */
		public void onScannedRobot(ScannedRobotEvent e) {
		}
	
		/**
		 * onHitRobot:  If it's our fault, we'll stop turning and moving,
		 * so we need to turn again to keep spinning.
		 */
		public void onHitRobot(HitRobotEvent e) {
			if (e.getBearing() > -10 && e.getBearing() < 10) {
				fire(3);
			}
			if (e.isMyFault()) {
				turnRight(10);
			}
		}
	
}																																				