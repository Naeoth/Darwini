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

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

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
				XMLStreamReader xmlEventReader = XMLInputFactory.newInstance().createXMLStreamReader( new FileInputStream(f) );
	
				// Skip the first lines
				for (int i = 0; i < 4; i++)
					xmlEventReader.nextTag();
				
				// Get matrix values from the other lines
				inputWeights = initMatrix(xmlEventReader).transpose();
				// Skip the end of the inputWeights
				xmlEventReader.nextTag();
				xmlEventReader.nextTag();
				outputWeights = initMatrix(xmlEventReader).transpose();
				// Skip the end of the outputWeights
				xmlEventReader.nextTag();
				xmlEventReader.nextTag();
				bias = initMatrix(xmlEventReader);
				
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
		 * Initialse la matrice dans le sens donné par le XML
		 */
		private Matrix initMatrix(XMLStreamReader xmlEventReader) {
			int rows = Integer.parseInt( xmlEventReader.getAttributeValue(0) );
			int cols = Integer.parseInt( xmlEventReader.getAttributeValue(1) );
			String[] values = xmlEventReader.getAttributeValue(2).split(" ");
			
			Matrix matrix = new Matrix(rows, cols);
			
			// Fill the matrix
			int index = 0;
			for (int i = 0; i < rows; i++)
				for (int j = 0; j < cols; j++)
					matrix.set(i, j, Double.parseDouble( values[index++] ));
			
			return matrix;
		}
		
		/**
		 * 
		 */
		public Matrix decision(Matrix entries) {
			// First Treatment
			//Multiplication du vecteur d'entrée avec la première matrice de poids. On obtient le vecteur de couche sans le neurone de biais
			Matrix vcouche = inputWeights.mult(entries);
			//Ajout du neurone de biais et application de la fonction sigmoïde
			for (int i = 0; i < inputWeights.getRowCount(); i++) {
				vcouche.add( i, 0, bias.get(i, 0) );
				vcouche.set( i, 0, 1 / (1 + Math.exp( -vcouche.get(i, 0) )) );
			}
			
			// Second Treatment	
			//Multiplication du vecteur de couche avec la seconde matrice de poids pour obtenir le vecteur de sortie
			return outputWeights.mult(vcouche);
		}
		
		/**
		 *
		 *
		 * @param
		 *
		 * @throws
		 */
		public void printToXML(File f) {
			// Ecrire le perceptron sous forme XML
		}
		
}