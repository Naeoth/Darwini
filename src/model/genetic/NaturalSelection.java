/*
 * Projet Darwini - Étude Pratique
 *
 * Development of an IA based on genetic algorithms and neural networks.
 *
 * class NaturalSelection.java
 */

package model.genetic;

/**
 * A robot based on an existing one, however this one will improve itself over time, by building and following a neural network.
 * Must extend an operational robot extending AdvancedRobot
 *
 * @version 1.0 - 17/11/15
 * @author BOIZUMAULT Romain
 * @author BUSSENEAU Alexis
 * @author GEFFRAULT Luc
 * @author MATHIEU Vianney
 * @author VAILLAND Guillaume
 */
public class NaturalSelection {

    /**
     *
     */
    private static final int NUMBER_GENERATION = 1;

    public static void main(String[] args) {
        GeneticAlgorithm ga = new GeneticAlgorithm();
        System.out.println("Le meilleur de tous les robots se situe à l'indice n°" +ga.keepBest());

        //ga.generate(1);
        //ga.savePopulation();

    }

}
