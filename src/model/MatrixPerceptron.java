/*
 * Projet Darwini - Étude Pratique
 * 
 * Development of an IA based on genetic algorithms and neural networks.
 *
 * class MatrixPerceptron.java
 */

package model;

import java.io.File;
import java.util.Iterator;

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
		private static final int NEURON_V = 4; //A changer !
	
		/**
		 * 
		 */
		private CompDiagMatrix MaP1;
		
		/**
		 * 
		 */
		private CompDiagMatrix MaP2;
		
		/**
		 * 
		 */
		private int nbrNeuroneEntres; //A definir
		
		/**
		 * 
		 */
		private int nbrNeuroneCaches; //A def
	

	/*	----- CONSTRUCTOR -----	*/

		/**
		 * 
		 * 
		 * @param f
		 */
		public MatrixPerceptron(File f) {
			//Ouverture du fichier
			MaP1 = new CompDiagMatrix(nbrNeuroneCaches, nbrNeuroneEntres);
			MaP2 = new CompDiagMatrix(nbrNeuroneCaches, nbrNeuroneEntres);
			
			//remplissage des matrices
			MaP1 = scaleAndReduce(MaP1);
			MaP2 = scaleAndReduce(MaP2);
		}
		
		
	/*	----- MATRIX METHODS -----	*/
	
		/**
		 * Sert à centrer et réduire les matrices
		 * 
		 */
		public CompDiagMatrix scaleAndReduce(CompDiagMatrix matrix) {
			double sum=0;
			int size=matrix.numRows()*matrix.numColumns();
				for(int x=0 ; x<matrix.numRows() ; x++){
					for(int y=0 ; y<matrix.numColumns(); y++){
						sum=sum+matrix.get(x,y);
					}
				double moy = sum/size;
					
			
			
			
			
			return null;
		}
		}
	
		/**
		 * Calcule le premiuer vecteur sortant des neurones de couches
		 */
		public CompDiagMatrix firstTreatment(CompDiagMatrix entry) {
			//entry est le vecteur des entrées collectées représenté sous forme de matrice
			//pour plus de simplicité
			CompDiagMatrix vcouche = new CompDiagMatrix(nbrNeuroneCaches, 1);
			entry = scaleAndReduce(entry);
			MaP1.mult(entry, vcouche);
			
			for (int i = 0; i < nbrNeuroneCaches; i++)
				vcouche.add(i, 1, NEURON_V);
			
			return vcouche;
		}
		
		/**
		 * Calcule le vecteur de sortie du perceptron 
		 */
		public CompDiagMatrix secondTreatment(CompDiagMatrix couche) {
			//couche est le vecteur résultant du premier traitement, le vecteur sortant des neurones de couche
			CompDiagMatrix vsortie = new CompDiagMatrix(nbrNeuroneCaches, 1); //encore réfléchir sur la taille de la matrice de sortie
			couche.mult(MaP2, vsortie);
			
			return vsortie;
		}
		
		/**
		 * 
		 * @param sortie
		 * @return
		 */
		public double Somme(CompDiagMatrix sortie) {
			int res = 0;
			
			for(int i = 0; i < nbrNeuroneCaches; i++)
				res += sortie.get(i, 0);
			
			return res;
		}
	
}