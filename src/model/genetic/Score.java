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
 *
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
         *
         */
        private int victory;

        /**
         *
         */
        private int survival;

        /**
         *
         */
        private int survivalBonus;

        /**
         *
         */
        private int bulletDamage;

        /**
         *
         */
        private int bulletBonus;

        /**
         *
         */
        private int ramDamage;

        /**
         *
         */
        private int ramBonus;


	/*	----- CONSTRUCTOR -----	*/

        /**
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

        @Override
        public int compareTo(Score o) {
            if (victory > o.victory)
                return 1;
            else
            if (victory == o.victory)
                if (bulletDamage >= o.bulletDamage)
                    return 1;
                else
                    return 0;

            return 0 ;
        }

}
