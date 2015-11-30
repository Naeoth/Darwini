package model;

import no.uib.cipr.matrix.sparse.CompDiagMatrix;

public class MatrixPerceptron {
	
	private CompDiagMatrix MaP1;
	private CompDiagMatrix MaP2;
	private int neuronV = 4; //A changer !
	private int nbrNeuroneEntres; //A definir
	private int nbrNeuroneCaches; //A def
	
	public MatrixPerceptron(fichier f){
		//Ouverture du fichier
		MaP1 = new CompDiagMatrix(nbrNeuroneCaches,nbrNeuroneEntres);
		MaP2 = new CompDiagMatrix(nbrNeuroneCaches,nbrNeuroneEntres);
		
		//remplissage des matrices
		
		MaP1 = centrerEtReduire(MaP1);
		MaP2 = centrerEtReduire(MaP2);
		
	}
	
	/*
	 * Sert à centrer et réduire les matrices
	 * 
	 */
	
	public CompDiagMatrix centrerEtReduire(CompDiagMatrix matrix){
		return null;
	}
	
	/*
	 * Calcule le premiuer vecteur sortant des neurones de couches
	 */
	
	public CompDiagMatrix firstTreatment(CompDiagMatrix entry){
		//entry est le vecteur des entrées collectées représenté sous forme de matrice
		//pour plus de simplicité
		CompDiagMatrix vcouche = new CompDiagMatrix(nbrNeuroneCaches,1);
		entry = centrerEtReduire(entry);
		this.MaP1.mult(entry, vcouche);
		for(int i = 0; i<nbrNeuroneCaches;i++)
			vcouche.add(i,1,this.neuronV);
		return vcouche;
	}
	
	/*
	 * Calcule le vecteur de sortie du perceptron 
	 */
	
	public CompDiagMatrix secondTreatment(CompDiagMatrix couche){
		//couche est le vecteur résultant du premier traitement, le vecteur sortant des neurones de couche
		CompDiagMatrix vsortie = new CompDiagMatrix(nbrNeuroneCaches,1); //encore réfléchir sur la taille de la matrice de sortie
		couche.mult(MaP2, vsortie);
		return vsortie;
	}
	
	public double Somme(CompDiagMatrix sortie){
		int res=0;
		for(int i = 0; i<nbrNeuroneCaches;i++)
			res+=sortie.get(i, 0);
		return res;
	}
	
	
	
}
