/*
 * Projet Darwini - Étude Pratique
 * 
 * Development of an IA based on genetic algorithms and neural networks.
 *
 * class AbstractDOData.java
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
public abstract class AbstractDOData {
	
	/*	----- ATTRIBUTES -----	*/
	
		/**
		 * 
		 */
		private int success;
		
		/**
		 * 
		 */
		private double[] data;
		
		
	/*	----- CONSTRUCTOR -----	*/
		
		/**
		 * 
		 */
		protected AbstractDOData(double... entries) {
			success = 0;
			data = entries;
		}
		
		
	/*	----- MUTATOR -----	*/
		
		/**
		 * 
		 */
		public void setSuccess() {
			success = 1;
		}
	
		
	/*	----- OTHER METHODS -----	*/

		/**
		 * 
		 * @return
		 */
		public String toSSVM() {
			String ret = Integer.toString(success);
			
			for (int i = 1; i <= data.length; i++)
				ret += " " + i + ":" + data[i - 1];
			
			return ret;
		}
		
		/**
		 * 
		 * @return
		 */
		public Matrix toMatrix() {
			Matrix matrix = new Matrix(data.length, 1);
			
			for (int i = 0; i < data.length; i++)
				matrix.set(i, 0, data[i]);
			
			return matrix;
		}
		
}
