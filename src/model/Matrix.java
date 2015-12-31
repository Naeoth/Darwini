/*
 * Projet Darwini - Étude Pratique
 * 
 * Development of an IA based on genetic algorithms and neural networks.
 *
 * class MatrixPerceptron.java
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
public class Matrix {
	
	/*	----- ATTRIBUTE -----	*/

		/**
		 *
		 */
		private double[][] matrix;


	/*	----- CONSTRUCTOR -----	*/

		/**
		 *
		 */
		public Matrix(int numRows, int numColumns) {
			matrix = new double[numRows][numColumns];
		}
		
	
	/*	----- MUTATORS -----	*/
		
		/**
		 * 
		 */
		public void set(int numRows, int numColumns, double value) {
			matrix[numRows][numColumns] = value;
		}
		
		/**
		 * 
		 * @param numRows
		 * @param numColumns
		 * @param value
		 */
		public void add(int numRows, int numColumns, double value) {
			this.set(numRows,numColumns, (this.get(numRows, numColumns) + value));
		}
		
		/**
		 * 
		 * @param 
		 * @return
		 */
		
		/**
		 * EXCEPTION A RAJOUTER
		 * REVOIR L'OPTIMIZATION (Strassen)
		 * 
		 * @param m2
		 * @return
		 */
		public Matrix mult(Matrix m2) {	
			Matrix res = new Matrix( getRowCount(), getRowCount() );

			for (int i = 0; i < matrix.length; i++)
				for (int k = 0; k < m2.matrix.length; k++)
					for (int j = 0; j < m2.matrix[0].length; j++)
	        			res.matrix[i][j] += matrix[i][k] * m2.matrix[k][j];
	                	
			return res;
		}
		
	
	/*	----- ACCESSORS -----	*/
		
		/**
		 * 
		 */
		public double get(int numRows, int numColumns) {
			return matrix[numRows][numColumns];
		}
		
		/**
		 * 
		 */
		public int getRowCount() {
			return matrix.length;
		}
		
		/**
		 * 
		 */
		public int getColumnCount() {
			return matrix[0].length;
		}
		
}
