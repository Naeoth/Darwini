/*
 * Projet Darwini - Étude Pratique
 * 
 * Development of an IA based on genetic algorithms and neural networks.
 *
 * class MatrixPerceptron.java
 */

package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.namespace.QName;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;

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
public class Perceptron {
	
	/*	----- ATTRIBUTES -----	*/
	
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
		private Matrix inputWeights;
		
		/**
		 * 
		 */
		private Matrix outputWeights;
	
		/**
		 * 
		 */
		private Matrix bias;
	

	/*	----- CONSTRUCTOR -----	*/
		
		/**
		 * 
		 * 
		 * @param f
		 */
		public Perceptron(File f) {
			try {
			    // Get an input factory and instantiate a reader
				XMLEventReader xmlEventReader = XMLInputFactory.newInstance().createXMLEventReader( new FileInputStream(f) );
				
				// Skip the header line
				xmlEventReader.nextTag();
				// Skip the first line
				xmlEventReader.nextTag();
				
				// Get the number of neurons from the second line
				StartElement perceptron = xmlEventReader.nextTag().asStartElement();
				nbInputNeurons = Integer.parseInt( perceptron.getAttributeByName( new QName("InputNeurons") ).getValue() );
				nbHiddenNeurons = Integer.parseInt( perceptron.getAttributeByName( new QName("HiddenNeurons") ).getValue() );
				nbOutputNeurons = Integer.parseInt( perceptron.getAttributeByName( new QName("OutputNeurons") ).getValue() );
				
				// Get matrix values from the other lines
				inputWeights = initMatrix( xmlEventReader.nextTag().asStartElement() );
				// End of the inputWeights
				xmlEventReader.nextTag();
				outputWeights = initMatrix( xmlEventReader.nextTag().asStartElement() );
				// End of the outputWeights
				xmlEventReader.nextTag();
				bias = initMatrix( xmlEventReader.nextTag().asStartElement() );
			
				// Close the reader
				xmlEventReader.close();
			}
			catch (FileNotFoundException | XMLStreamException | FactoryConfigurationError e) {
				e.printStackTrace();
				System.out.println("The XML file is not found");
			}
		}
		
		
	/*	----- OTHER METHODS -----	*/
		
		/**
		 * 
		 */
		private Matrix initMatrix(StartElement matrix) {
			int rows = Integer.parseInt( matrix.getAttributeByName( new QName("Rows") ).getValue() );
			int cols = Integer.parseInt( matrix.getAttributeByName( new QName("Cols") ).getValue() );
			String[] values = matrix.getAttributeByName( new QName("Matrix") ).getValue().split(" ");
			
			Matrix matrixInitialized = new Matrix(rows, cols);
			
			// Fill the matrix
			int index = 0;
			for (int i = 0; i < rows; i++)
				for (int j = 0; j < cols; j++)
					matrixInitialized.set(i, j, Double.parseDouble( values[index++] ));
			
			return matrixInitialized;
		}
		
		/**
		 * 
		 */
		public boolean decision(Matrix entries) {
			// First Treatment
			/*Matrix vcouche = new Matrix(nbHiddenNeurons, 1);

			inputWeights.mult(entry, vcouche);
			
			for (int i = 0; i < nbHiddenNeurons; i++){
				vcouche.add(i, 0, bias.get(i,0));
				vcouche.set(i, 0, (1/(1+Math.exp(-vcouche.get(i, 0)))));
			}*/
			
			// Second Treatment
			//couche est le vecteur résultant du premier traitement, le vecteur sortant des neurones de couche
			/*Matrix vsortie = new Matrix(nbOutputNeurons, 1); //encore réfléchir sur la taille de la matrice de sortie
			outputWeights.mult(couche, vsortie);*/
			
			//return vsortie.get(0, 0) > 0;
			return true;
		}
		
}
