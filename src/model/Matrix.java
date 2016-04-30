/*
 * Projet Darwini - Étude Pratique
 * 
 * Development of an IA based on genetic algorithms and neural networks.
 *
 * class Matrix.java
 */

package model;

import java.util.Locale;

/**
 *   of a matrix system which will be useful for the neural network representation and training
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
		 * The procedure to set the value of a matrix's compartment.
		 *
		 * @param numRows the compartement's row number that we want to reach
		 * @param numColumns the compartement's column number that we want to reach
		 * @param value the value to set
		 */
		public void set(int numRows, int numColumns, double value) {
			matrix[numRows][numColumns] = value;
		}
		
		/**
		 * Multiplication of two matrix
		 *
		 * @param m2 the matrix B in the matrix's multiplication A * B
		 *
		 * @return the matrix's multiplication's result
		 */
		public Matrix mult(Matrix m2) {
			Matrix res = new Matrix(matrix.length, m2.matrix[0].length);
			
			double value;
			for (int k = 0; k < m2.matrix.length; k++) 
				for (int i = 0; i < matrix.length; i++) {
					value = matrix[i][k];
					for (int j = 0; j < m2.matrix[0].length; j++)
						  res.matrix[i][j] += value * m2.matrix[k][j];
				}
	                	
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
		 * @return the number of rows
		 */
		public int getRowCount() {
			return matrix.length;
		}
		
		/**
		 * @return the number of Column
		 */
		public int getColumnCount() {
			return matrix[0].length;
		}


	/*	----- OTHER METHODS -----	*/

		/**
		 *
		 */
		public String toString() {
			StringBuilder sb = new StringBuilder(matrix.length * matrix[0].length);
			for (int i = 0; i < matrix.length; i++)
				for (int j = 0; j < matrix[0].length; j++)
					sb.append(String.format(Locale.US, "%.6f", matrix[i][j])).append(" ");

			return sb.toString();
		}

}