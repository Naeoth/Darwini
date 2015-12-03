/*
 * Projet Darwini - Étude Pratique
 * 
 * Development of an IA based on genetic algorithms and neural networks.
 *
 * class MatrixPerceptron.java
 */

package model;

import java.io.File;
import java.lang.Math;

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
		private CompDiagMatrix neuronBiais;	
		/**
		 * 
		 */
		private CompDiagMatrix maP1;
		
		/**
		 * 
		 */
		private CompDiagMatrix maP2;
		
		/**
		 * 
		 */
		private int nbrNeuroneEntres; //A definir
		
		/**
		 * 
		 */
		private int nbrNeuroneSortie;
		
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
			maP1 = new CompDiagMatrix(nbrNeuroneCaches, nbrNeuroneEntres);
			maP2 = new CompDiagMatrix(nbrNeuroneSortie, nbrNeuroneCaches);
			neurnBiais = new CompDiagMatrix(nbrNeuroneCaches,1);
			
			
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
		 * Calcule le premiuer vecteur sortant des neurones de couches
		 */
		public CompDiagMatrix firstTreatment(CompDiagMatrix entry) {
			//entry est le vecteur des entrées collectées représenté sous forme de matrice
			//pour plus de simplicité
			CompDiagMatrix vcouche = new CompDiagMatrix(nbrNeuroneCaches, 1);

			entry = scaleAndReduce(entry);
			MaP1.mult(entry, vcouche);
			
			for (int i = 0; i < nbrNeuroneCaches; i++)
				vcouche.add(i, 0, neuronBiais.get(i,0));
			
			return vcouche;
		}
		
		/**
		 * Calcule le vecteur de sortie du perceptron 
		 */
		public CompDiagMatrix secondTreatment(CompDiagMatrix couche) {
			//couche est le vecteur résultant du premier traitement, le vecteur sortant des neurones de couche
			CompDiagMatrix vsortie = new CompDiagMatrix(nbrNeuroneSortie, 1); //encore réfléchir sur la taille de la matrice de sortie
			maP2.mult(couche, vsortie);
			
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
