/*
 * Projet Darwini - Étude Pratique
 *
 * Development of an IA based on genetic algorithms and neural networks.
 *
 * class GeneticAlgorithm.java
 */

package model;

import java.io.File;

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
public class Launcher {

    public static void main(String[] args) {
        GeneticAlgorithm ga = new GeneticAlgorithm();

        for (int i = 0; i < 200; i++)
            ga.generate();

        ga.savePopulation();
        ga.selectBest().printToXML( new File("Darwini.xml") );
    }

}
