/*
 * Projet Darwini - Étude Pratique
 * 
 * Development of an IA based on genetic algorithms and neural networks.
 *
 * class MatrixPerceptron.java
 */

package model;

import java.io.File;
import java.io.IOException;
import java.lang.Math;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import no.uib.cipr.matrix.sparse.CompDiagMatrix;

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
public class MatrixPerceptron {
	
	/*	----- ATTRIBUTES -----	*/
	
		/**
		 * 
		 */
		private Document matrixXML;
	
		/**
		 * 
		 */
		private int nbInputNeurons;
		
		/**
		 * 
		 */
		private int nbHiddenNeurons;
		
		/**
		 * 
		 */
		private int nbOutputNeurons;
	
		/**
		 * 
		 */
		private CompDiagMatrix inputWeights;
		
		/**
		 * 
		 */
		private CompDiagMatrix outputWeights;
	
		/**
		 * 
		 */
		private CompDiagMatrix bias;
	

	/*	----- CONSTRUCTOR -----	*/

		/**
		 * 
		 * 
		 * @param f
		 */
		public MatrixPerceptron(File f) {
			try {
				SAXBuilder sxb = new SAXBuilder();
				matrixXML = sxb.build(f);
			}
			catch (IOException e) {
				System.out.println("File not found.\n" + e.getMessage());
			}
			catch (JDOMException e) {
				System.out.println("XML file is invalid.\n" + e.getMessage());
			}
			
			Element perceptron = matrixXML.getRootElement().getChild("learner").getChild("perceptron");
			
			// Get the number of neurons
			nbInputNeurons = Integer.parseInt( perceptron.getAttributeValue("InputNeurons") );
			nbHiddenNeurons = Integer.parseInt( perceptron.getAttributeValue("HiddenNeurons") );
			nbOutputNeurons = Integer.parseInt( perceptron.getAttributeValue("OutputNeurons") );
			
			// Get the matrix
			inputWeights = initMatrix( perceptron.getChild("InputWeights") );
			outputWeights = initMatrix( perceptron.getChild("OutputWeights") );
			bias = initMatrix( perceptron.getChild("Bias") );
		}
		
		
	/*	----- MATRIX METHODS -----	*/
		
		/**
		 * 
		 */
		private CompDiagMatrix initMatrix(Element matrix) {
			int rows = Integer.parseInt( matrix.getAttributeValue("Rows") );
			int cols = Integer.parseInt( matrix.getAttributeValue("Cols") );
			String[] values = matrix.getAttributeValue("Matrix").split(" ");
			
			CompDiagMatrix matrixInitialized = new CompDiagMatrix(rows, cols);
			
			// Fill the matrix
			int index = 0;
			
			for (int j = 0; j < cols; j++)
				for (int i = 0; i < rows; i++)
					matrixInitialized.set( i, j, Double.parseDouble( values[index++] ) );
			
			return matrixInitialized;
		}
	
		/**
		 * Sert à centrer et réduire les matrices
		 * 
		 */
		public CompDiagMatrix scaleAndReduce(CompDiagMatrix matrix) {
			double sum=0;
			double pvar=0;
			int size=matrix.numRows()*matrix.numColumns();
			for(int x=0 ; x<matrix.numRows() ; x++){
				for(int y=0 ; y<matrix.numColumns(); y++){
					sum+=matrix.get(x,y);
				}
			}

			double moy = sum/size;

			for(int i=0 ; i<matrix.numRows() ; i++){
				for(int j=0 ; j<matrix.numColumns(); j++){
					pvar+=(matrix.get(i, j)-moy)*(matrix.get(i, j)-moy);
				}
			}

			double var = pvar/size;
			double ectType = Math.sqrt(var);
			for(int k=0 ; k<matrix.numRows() ; k++){
				for(int l=0 ; l<matrix.numColumns(); l++){
					double coeffScaled=(matrix.get(k,l)-moy)/ectType;
					matrix.set(k, l, coeffScaled); 
				}
			}
			
			return matrix;
		}

		/**
		 * Calcule le premier vecteur sortant des neurones de couches
		 */
		public CompDiagMatrix firstTreatment(CompDiagMatrix entry) {
			//entry est le vecteur des entrées collectées représenté sous forme de matrice
			//pour plus de simplicité
			CompDiagMatrix vcouche = new CompDiagMatrix(nbHiddenNeurons, 1);

			entry = scaleAndReduce(entry);
			inputWeights.mult(entry, vcouche);
			
			for (int i = 0; i < nbHiddenNeurons; i++){
				vcouche.add(i, 0, bias.get(i,0));
				vcouche.set(i, 0, (1/(1+Math.exp(-vcouche.get(i, 0)))));
			}
			
			return vcouche;
		}
		
		/**
		 * Calcule le vecteur de sortie du perceptron 
		 */
		public CompDiagMatrix secondTreatment(CompDiagMatrix couche) {
			//couche est le vecteur résultant du premier traitement, le vecteur sortant des neurones de couche
			CompDiagMatrix vsortie = new CompDiagMatrix(nbOutputNeurons, 1); //encore réfléchir sur la taille de la matrice de sortie
			outputWeights.mult(couche, vsortie);
			
			return vsortie;
		}
		
}
