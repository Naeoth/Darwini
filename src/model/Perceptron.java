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
import java.io.FileOutputStream;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

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
		public static final int HIDDEN_NEURONS = 200;
		
	
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
		 */
		public Perceptron() {
			inputWeights = new Matrix(HIDDEN_NEURONS, InputData.INPUT_NEURONS);
			randomizeMatrix(inputWeights);
			outputWeights = new Matrix(OutputData.OUTPUT_NEURONS, HIDDEN_NEURONS);
			randomizeMatrix(outputWeights);
			bias = new Matrix(HIDDEN_NEURONS, 1);
			randomizeMatrix(bias);
		}
		
		
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
		 * @param matrix
		 */
		private void randomizeMatrix(Matrix matrix) {
			for (int i = 0; i < matrix.getRowCount(); i++)
				for (int j = 0; j < matrix.getColumnCount(); j++)
					matrix.set(i, j, Math.random() * 2 - 1);
		}
		
		/**
		 * 
		 */
		public OutputData train(Matrix entries) {
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
			return new OutputData( outputWeights.mult(vcouche) );
		}
		
		/**
		 *
		 *
		 * @param
		 *
		 * @throws
		 */
		public void printToXML(File f) {
			
		    // Get an input factory and instantiate a writer
			// On part d'un fichier java pour créer un fichier xml
			try {
				XMLStreamWriter xmlStreamWriter = XMLOutputFactory.newInstance().createXMLStreamWriter(new FileOutputStream(f));
				
				xmlStreamWriter.writeStartElement("meta");
				xmlStreamWriter.writeAttribute("NbOutputNeurons", Integer.toString(OutputData.OUTPUT_NEURONS));
				xmlStreamWriter.writeAttribute("Learners", "1");
				xmlStreamWriter.writeCharacters("\n");
				xmlStreamWriter.writeCharacters("\t");
				
				xmlStreamWriter.writeStartElement("learner");
				xmlStreamWriter.writeAttribute("accuracy", "0.722775");
				xmlStreamWriter.writeAttribute("nbInputNeurons", Integer.toString(InputData.INPUT_NEURONS));
				xmlStreamWriter.writeAttribute("features_used", "All");
				xmlStreamWriter.writeCharacters("\n");
				xmlStreamWriter.writeCharacters("\t\t");
				
				xmlStreamWriter.writeStartElement("perceptron");
				xmlStreamWriter.writeAttribute("InputNeurons", Integer.toString(InputData.INPUT_NEURONS));
				xmlStreamWriter.writeAttribute("HiddenNeurons", Integer.toString(HIDDEN_NEURONS));
				xmlStreamWriter.writeAttribute("OoutputNeurons", Integer.toString(OutputData.OUTPUT_NEURONS));
				xmlStreamWriter.writeAttribute("Kernel", "sigmoid");
				xmlStreamWriter.writeCharacters("\n");
				xmlStreamWriter.writeCharacters("\t\t\t");
				
				xmlStreamWriter.writeEmptyElement("InputWeights");
				xmlStreamWriter.writeAttribute("Rows", Integer.toString(InputData.INPUT_NEURONS));
				xmlStreamWriter.writeAttribute("Cols", Integer.toString(HIDDEN_NEURONS));
				xmlStreamWriter.writeAttribute("Matrix", inputWeights.transpose().toString() );
				xmlStreamWriter.writeCharacters("\n");
				xmlStreamWriter.writeCharacters("\t\t\t");
				
				xmlStreamWriter.writeEmptyElement("OutputWeights");
				xmlStreamWriter.writeAttribute("Rows", Integer.toString(HIDDEN_NEURONS));
				xmlStreamWriter.writeAttribute("Cols", Integer.toString(OutputData.OUTPUT_NEURONS));
				xmlStreamWriter.writeAttribute("Matrix", outputWeights.transpose().toString() );
				xmlStreamWriter.writeCharacters("\n");
				xmlStreamWriter.writeCharacters("\t\t\t");
				
				xmlStreamWriter.writeEmptyElement("Bias");
				xmlStreamWriter.writeAttribute("Rows", Integer.toString(HIDDEN_NEURONS));
				xmlStreamWriter.writeAttribute("Cols", Integer.toString(OutputData.OUTPUT_NEURONS));
				xmlStreamWriter.writeAttribute("Matrix", bias.transpose().toString() );		
				xmlStreamWriter.writeCharacters("\n");
				xmlStreamWriter.writeCharacters("\t\t");
				xmlStreamWriter.writeEndElement();
				xmlStreamWriter.writeCharacters("\n");
				xmlStreamWriter.writeCharacters("\t");
				xmlStreamWriter.writeEndElement();
				xmlStreamWriter.writeCharacters("\n");
				xmlStreamWriter.writeEndElement();
				xmlStreamWriter.close();
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.out.println("Impossble d'écire dans le xml");
			}
			catch ( XMLStreamException e) {
				e.printStackTrace();
				System.out.println("Impossible d'écrire dans le xml 2");
			}
			
	}	
	
	public static void main(String[] args) {
		File f = new File("Modele.xml") ;
		File f2 = new File("Test.xml") ;
		new Perceptron(f).printToXML(f2);
	}
		
}