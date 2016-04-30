/*
 * Projet Darwini - Étude Pratique
 * 
 * Development of an IA based on genetic algorithms and neural networks.
 *
 * class Perceptron.java
 */

package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

<<<<<<< HEAD
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
=======
>>>>>>> c1447e217ff12909bc67fdde1b6068b2f544571d
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
<<<<<<< HEAD
=======
import javax.xml.stream.XMLOutputFactory;
>>>>>>> c1447e217ff12909bc67fdde1b6068b2f544571d
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
<<<<<<< HEAD
		
	
		/**
=======

        /**
>>>>>>> c1447e217ff12909bc67fdde1b6068b2f544571d
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
		 * @param f a
		 */
		public Perceptron(File f) {
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
<<<<<<< HEAD
					matrix.set(i, j, Math.random() * 2 - 1);
=======
					matrix.set(i, j, Math.random());
>>>>>>> c1447e217ff12909bc67fdde1b6068b2f544571d
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
<<<<<<< HEAD
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
=======
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
>>>>>>> c1447e217ff12909bc67fdde1b6068b2f544571d
		
}