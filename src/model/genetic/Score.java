/*
 * Projet Darwini - Étude Pratique
 *
 * Development of an IA based on genetic algorithms and neural networks.
 *
 * class Score.java
 */

package model.genetic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 *  <p>
 *  Class called by the Genetic algorithm after the ten games of the individuals. It creates an object Score for every individuals (robots)
 *  generate by the genetic algorithm. It allows us to compare the efficiency of those robots.
 *  </p>
 *
 * @see GeneticAlgorithm
 * @see NaturalSelection
 *
 * @version 1.0 - 17/11/15
 * @author BOIZUMAULT Romain
 * @author BUSSENEAU Alexis
 * @author GEFFRAULT Luc
 * @author MATHIEU Vianney
 * @author VAILLAND Guillaume
 */
public class Score implements Comparable<Score> {

    /*	----- ATTRIBUTES -----	*/

        /**
         *  <p>
         *      The robot's number of victories and its percentage
         *  </p>
         */
        private int victory;

        /**
         * <p>
         * 	The survival score for the robot in the battle
         * </p>
         */
        private int survival;

        /**
         * <p>
         *	The last survivor bonus for the robot in the battle
         * </p>
         */
        private int survivalBonus;

        /**
         *  <p>
         * 	The bullet damage score for the robot in the battle
         *  </p>
         */
        private int bulletDamage;

        /**
         * <p>
         *	The bullet damage bonus for the robot in the battle
         * </p> 
         */
        private int bulletBonus;

        /**
         * <p>
         * 	The ramming damage for the robot in the battle
         * </p>
         */
        private int ramDamage;

        /**
         * <p>
         * 	The ramming damage bonus for the robot in the battle
         * </p>
         */
        private int ramBonus;


	/*	----- CONSTRUCTOR -----	*/

        /**
         * <p>
         *     The construction of this object is based on the file we get when the games are over. 
         *     It represents the different parameters we choose to give a compare robots.
         * </p>
		 *
         * @param fileName
         */
        public Score(String fileName, String robotName) {
            try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
                String[] results = stream.filter(line -> line.contains(robotName)).findFirst().get().split("\t");
                Matcher m = Pattern.compile("\\((.*)\\s").matcher(results[1]);
                m.find();
                victory = Integer.parseInt(m.group(1));
                survival = Integer.parseInt(results[2]);
                survivalBonus = Integer.parseInt(results[3]);
                bulletDamage = Integer.parseInt(results[4]);
                bulletBonus = Integer.parseInt(results[5]);
                ramDamage = Integer.parseInt(results[6]);
                ramBonus = Integer.parseInt(results[7]);
            } catch (IOException e) {
                System.out.println("The results file is not found");
            }
        }


    /*	----- OTHER METHOD -----	*/

        /**
         * <p>
         *     Method used to compare two robots thanks to their score. We choose the percentage of victory as 
	 *	   main selection criterion. Then if two robots have the same percentage, we choose the one which 
	 * 	   made the more damages.
         * </p>
         *
         * @param o the score of the second robot
         */

        @Override
        public int compareTo(Score o) {
            if (victory > o.victory)
                return 1;
            if (victory == o.victory)
                    if (bulletDamage >= o.bulletDamage)
                        return 1;
                    if (bulletDamage == o.bulletDamage)
                        return 0;
            else
                        return -1 ;
        }

}
