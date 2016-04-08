/*
 * Projet Darwini - Étude Pratique
 *
 * Development of an IA based on genetic algorithms and neural networks.
 *
 * class NaturalSelection.java
 */

package model;

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
    private static final int NUMBER_GENERATION = 200;

    public static void main(String[] args) {
        System.out.println("The Perceptron is initializing...");
        GeneticAlgorithm ga = new GeneticAlgorithm();


        for (int i = 0; i < NUMBER_GENERATION; i++) {
            System.out.print("Generation n°" + (i + 1) + "...");
            ga.generate();
            System.out.println("DONE");
        }

        System.out.print("Save of the last generation (may take a while, please wait)...");
        ga.savePopulation();
        System.out.println("DONE");
    }

}
