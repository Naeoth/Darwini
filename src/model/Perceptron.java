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
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLOutputFactory;
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
			inputWeights = new Matrix(InputData.INPUT_NEURONS, HIDDEN_NEURONS);
			randomizeIOMatrix(inputWeights);
			outputWeights = new Matrix(HIDDEN_NEURONS, OutputData.OUTPUT_NEURONS);
			randomizeIOMatrix(outputWeights);
			bias = new Matrix(HIDDEN_NEURONS, 1);
			randomizeBiasMatrix(bias);
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
				inputWeights = initMatrix(xmlEventReader);
				// Skip the end of the inputWeights
				xmlEventReader.nextTag();
				xmlEventReader.nextTag();
				outputWeights = initMatrix(xmlEventReader);
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


    /*	----- MUTATORS -----	*/

        /**
         *
         * @return
         */
        public Matrix getInputWeights() {
            return inputWeights;
        }

        /**
         *
         * @return
         */
        public Matrix getOutputWeights() {
            return outputWeights;
        }

        /**
         *
         * @return
         */
        public Matrix getBias() {
            return bias;
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
		private void randomizeIOMatrix(Matrix matrix) {
			for (int i = 0; i < matrix.getRowCount(); i++)
				for (int j = 0; j < matrix.getColumnCount(); j++)
					matrix.set(i, j, Math.random() * 2 - 1);
		}

		/**
		 *
		 * @param matrix
		 */
		private void randomizeBiasMatrix(Matrix matrix) {
			for (int i = 0; i < matrix.getRowCount(); i++)
				for (int j = 0; j < matrix.getColumnCount(); j++)
					matrix.set(i, j, Math.random());
		}
		
		/**
		 * 
		 */
		public OutputData train(InputData entries) {
			// First Treatment
			//Multiplication du vecteur d'entrée avec la première matrice de poids. On obtient le vecteur de couche sans le neurone de biais
			Matrix vcouche = inputWeights.mult(entries.toMatrix());
			//Ajout du neurone de biais et application de la fonction sigmoïde
			for (int i = 0; i < inputWeights.getRowCount(); i++) {
				vcouche.add( 0, i, bias.get(i, 0) );
				vcouche.set( 0, i, 1 / (1 + Math.exp( -vcouche.get(0, i) )) );
			}
			
			// Second Treatment	
			//Multiplication du vecteur de couche avec la seconde matrice de poids pour obtenir le vecteur de sortie
			return new OutputData( vcouche.mult(outputWeights) );
		}
		
		/**
		 *
		 *
		 * @param
		 */
		public void printToXML(File f) {
			try {
				XMLStreamWriter xmlStreamWriter = XMLOutputFactory.newInstance().createXMLStreamWriter( new FileOutputStream(f) );

				xmlStreamWriter.writeStartElement("meta");
				xmlStreamWriter.writeAttribute("nbOutputNeurons", Integer.toString( outputWeights.getColumnCount() ));
				xmlStreamWriter.writeAttribute("Learners", "1");
				xmlStreamWriter.writeCharacters("\n");
				xmlStreamWriter.writeCharacters("\t");

				xmlStreamWriter.writeStartElement("learner");
				xmlStreamWriter.writeAttribute("accuracy", "");
				xmlStreamWriter.writeAttribute("nbInputNeurons", Integer.toString( inputWeights.getRowCount() ));
				xmlStreamWriter.writeAttribute("features_used", "All");
				xmlStreamWriter.writeCharacters("\n");
				xmlStreamWriter.writeCharacters("\t\t");

				xmlStreamWriter.writeStartElement("perceptron");
				xmlStreamWriter.writeAttribute("InputNeurons", Integer.toString( inputWeights.getRowCount() ));
				xmlStreamWriter.writeAttribute("HiddenNeurons", Integer.toString( inputWeights.getColumnCount() ));
				xmlStreamWriter.writeAttribute("OutputNeurons", Integer.toString( outputWeights.getColumnCount() ));
				xmlStreamWriter.writeAttribute("Kernel", "sigmoid");
				xmlStreamWriter.writeCharacters("\n");
				xmlStreamWriter.writeCharacters("\t\t\t");

				xmlStreamWriter.writeEmptyElement("InputWeights");
				xmlStreamWriter.writeAttribute("Rows", Integer.toString( inputWeights.getRowCount() ));
				xmlStreamWriter.writeAttribute("Cols", Integer.toString( inputWeights.getColumnCount() ));
				xmlStreamWriter.writeAttribute("Matrix", inputWeights.toString());
				xmlStreamWriter.writeCharacters("\n");
				xmlStreamWriter.writeCharacters("\t\t\t");

				xmlStreamWriter.writeEmptyElement("OutputWeights");
				xmlStreamWriter.writeAttribute("Rows", Integer.toString( outputWeights.getRowCount() ));
				xmlStreamWriter.writeAttribute("Cols", Integer.toString( outputWeights.getColumnCount() ));
				xmlStreamWriter.writeAttribute("Matrix", outputWeights.toString());
				xmlStreamWriter.writeCharacters("\n");
				xmlStreamWriter.writeCharacters("\t\t\t");

				xmlStreamWriter.writeEmptyElement("Bias");
				xmlStreamWriter.writeAttribute("Rows", Integer.toString( bias.getRowCount() ));
				xmlStreamWriter.writeAttribute("Cols", Integer.toString( bias.getColumnCount() ));
				xmlStreamWriter.writeAttribute("Matrix", bias.toString());
				xmlStreamWriter.writeCharacters("\n");
				xmlStreamWriter.writeCharacters("\t\t");
				xmlStreamWriter.writeEndElement();
				xmlStreamWriter.writeCharacters("\n");
				xmlStreamWriter.writeCharacters("\t");
				xmlStreamWriter.writeEndElement();
				xmlStreamWriter.writeCharacters("\n");
				xmlStreamWriter.writeEndElement();

				xmlStreamWriter.close();
			}
            catch (FileNotFoundException | XMLStreamException e) {
				e.printStackTrace();
			}
		}
		
}