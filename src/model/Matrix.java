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
		
	
	/*	----- MUTATOR -----	*/
		
		/**
		 * 
		 */
		public void set(int numRows, int numColumns, double value) {
			matrix[numRows][numColumns] = value;
		}
		
		public Matrix mult(Matrix A, Matrix B){
			    Matrix C= new Matrix(A.getRowCount(),B.getColumnCount());
			    int i,j;
			    for(i=0;i<A.getRowCount();i++){
			        for(j=0;j<B.getColumnCount();j++){
			        	//C[i][j]+=(A[i][j]*B[j][i]);
			            C.set(i, j, (C.get(i, j) + (A.get(i, j)*B.get(j, i))));
			        }
			    }
			    return C;
		}
		
	
	/*	----- ACCESSOR -----	*/
		
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
