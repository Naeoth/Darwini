/*
 * Projet Darwini - Étude Pratique
 * 
 * Development of an IA based on genetic algorithms and neural networks.
 *
 * class Perceptron.java
 */

package model.perceptron;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
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
public class NeuralNetwork {
	
	/*	----- ATTRIBUTES -----	*/
	
		/**
		 * 
		 */
		private static final int HIDDEN_NEURONS = 200;

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
		public NeuralNetwork() {
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
		 * @param f a
		 */
		public NeuralNetwork(File f) {
			try {
			    // Get an input factory and instantiate a reader
				XMLStreamReader xmlReader = XMLInputFactory.newInstance().createXMLStreamReader( new FileInputStream(f) );

				// Skip the first lines
				for (int i = 0; i < 4; i++)
					xmlReader.nextTag();

				// Get matrix values from the other lines
				inputWeights = initMatrix(xmlReader);
				// Skip the end of the inputWeights
				xmlReader.nextTag();
				xmlReader.nextTag();
				outputWeights = initMatrix(xmlReader);
				// Skip the end of the outputWeights
				xmlReader.nextTag();
				xmlReader.nextTag();
				bias = initMatrix(xmlReader);

				// Close the reader
				xmlReader.close();
			}
			catch (FileNotFoundException e) {
                System.out.println(f.getAbsolutePath() + " is not found.");
                System.out.println("The perceptron is not initialized, please put a perceptron in the correct directory.");
			}
            catch (XMLStreamException e) {
                System.out.println(f.getAbsolutePath() + " is invalid.");
                System.out.println("The perceptron is not initialized, please put a valid perceptron in the directory.");
            }
		}


    /*	----- MUTATORS -----	*/

        /**
         *
         * @return a
         */
        public Matrix getInputWeights() {
            return inputWeights;
        }

        /**
         *
         * @return a
         */
        public Matrix getOutputWeights() {
            return outputWeights;
        }

        /**
         *
         * @return a
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
		 * @param matrix a
		 */
		private void randomizeIOMatrix(Matrix matrix) {
			for (int i = 0; i < matrix.getRowCount(); i++)
				for (int j = 0; j < matrix.getColumnCount(); j++)
					matrix.set(i, j, Math.random() * 2 - 1);
		}

		/**
		 *
		 * @param matrix a
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
			Matrix vcouche = entries.toMatrix().mult(inputWeights);
			//Ajout du neurone de biais et application de la fonction sigmoïde
			for (int i = 0; i < inputWeights.getColumnCount(); i++)
				vcouche.set(0, i, 1 / (1 + Math.exp(-vcouche.get(0, i) - bias.get(i, 0))));
			
			// Second Treatment	
			//Multiplication du vecteur de couche avec la seconde matrice de poids pour obtenir le vecteur de sortie
			return new OutputData( vcouche.mult(outputWeights) );
		}
		
		/**
		 *
		 *
		 * @param f a
		 *
		 * @throws FileNotFoundException
         * @throws XMLStreamException
		 */
		public void printToXML(File f) throws FileNotFoundException, XMLStreamException {
            XMLStreamWriter xmlWriter = XMLOutputFactory.newInstance().createXMLStreamWriter( new FileOutputStream(f) );

			xmlWriter.writeStartElement("meta");
			xmlWriter.writeAttribute("NbOutputNeurons", Integer.toString(outputWeights.getColumnCount()));
			xmlWriter.writeAttribute("Learners", "1");
			xmlWriter.writeCharacters("\n");
			xmlWriter.writeCharacters("\t");

			xmlWriter.writeStartElement("learner");
			xmlWriter.writeAttribute("accuracy", "");
			xmlWriter.writeAttribute("nbInputNeurons", Integer.toString(inputWeights.getRowCount()));
			xmlWriter.writeAttribute("features_used", "All");
			xmlWriter.writeCharacters("\n");
			xmlWriter.writeCharacters("\t\t");

			xmlWriter.writeStartElement("perceptron");
			xmlWriter.writeAttribute("InputNeurons", Integer.toString(inputWeights.getRowCount()));
			xmlWriter.writeAttribute("HiddenNeurons", Integer.toString(inputWeights.getColumnCount()));
			xmlWriter.writeAttribute("OutputNeurons", Integer.toString(outputWeights.getColumnCount()));
			xmlWriter.writeAttribute("Kernel", "sigmoid");
			xmlWriter.writeCharacters("\n");
			xmlWriter.writeCharacters("\t\t\t");

			xmlWriter.writeEmptyElement("InputWeights");
			xmlWriter.writeAttribute("Rows", Integer.toString(inputWeights.getRowCount()));
			xmlWriter.writeAttribute("Cols", Integer.toString(inputWeights.getColumnCount()));
			xmlWriter.writeAttribute("Matrix", inputWeights.toString());
			xmlWriter.writeCharacters("\n");
			xmlWriter.writeCharacters("\t\t\t");

			xmlWriter.writeEmptyElement("OutputWeights");
			xmlWriter.writeAttribute("Rows", Integer.toString(outputWeights.getRowCount()));
			xmlWriter.writeAttribute("Cols", Integer.toString(outputWeights.getColumnCount()));
			xmlWriter.writeAttribute("Matrix", outputWeights.toString());
			xmlWriter.writeCharacters("\n");
			xmlWriter.writeCharacters("\t\t\t");

			xmlWriter.writeEmptyElement("Bias");
			xmlWriter.writeAttribute("Rows", Integer.toString(bias.getRowCount()));
			xmlWriter.writeAttribute("Cols", Integer.toString(bias.getColumnCount()));
			xmlWriter.writeAttribute("Matrix", bias.toString());
			xmlWriter.writeCharacters("\n");
			xmlWriter.writeCharacters("\t\t");
			xmlWriter.writeEndElement();
			xmlWriter.writeCharacters("\n");
			xmlWriter.writeCharacters("\t");
			xmlWriter.writeEndElement();
			xmlWriter.writeCharacters("\n");
			xmlWriter.writeEndElement();

		    xmlWriter.close();
		}
		
}