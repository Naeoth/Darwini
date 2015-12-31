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
			matrix[numRows][numColumns] += value;
		}
		
		/**
		 * 
		 * @param 
		 * @return
		 */
		
		public Matrix mult(Matrix b) {
			Matrix res = new Matrix( getRowCount(), b.getColumnCount() );

			for(int i = 0; i < getRowCount(); i++)
				for(int j = 0; j < b.getColumnCount(); j++)
					res.set(i, j, res.get(i, j) + get(i, j) * b.get(j, i) );

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
