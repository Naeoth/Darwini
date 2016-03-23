/*
 * Projet Darwini - Étude Pratique
 * 
 * Development of an IA based on genetic algorithms and neural networks.
 *
 * class MatrixPerceptron.java
 */

package model;

import java.util.Locale;

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
			set(numRows, numColumns, get(numRows, numColumns) + value);
		}
		
		/**
		 *
		 * 
		 * @param m2
		 * @return
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


	/*	----- OTHER METHODS -----	*/

		/**
		 *
		 */
		public String toString() {
			String ret = "";
			for (int i = 0; i < matrix.length; i++)
				for (int j = 0; j < matrix[0].length; j++)
					ret += String.format(Locale.US, "%.6f", matrix[i][j]) + " ";

			return ret;
		}
		
}