/*
 * Projet Darwini - Étude Pratique
 *
 * Development of an IA based on genetic algorithms and neural networks.
 *
 * class Score.java
 */

package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

<<<<<<< HEAD
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
         * @param file
         */
        public Score(FileReader file) {
            try {
                BufferedReader br = new BufferedReader(file);
                br.readLine();
                br.readLine();
                String [] tableauResultat = br.readLine().split("\t");

                Matcher m = Pattern.compile("\\((.*)\\s%\\)").matcher(tableauResultat[1]);
                m.find();
                victory = Integer.parseInt(m.group(1));
                survival = Integer.parseInt(tableauResultat[2]);
                survivalBonus = Integer.parseInt(tableauResultat[3]);
                bulletDamage = Integer.parseInt(tableauResultat[4]);
                bulletBonus = Integer.parseInt(tableauResultat[5]);
                ramDamage = Integer.parseInt(tableauResultat[6]);
                ramBonus = Integer.parseInt(tableauResultat[7]);
            } catch (IOException e) {
                System.out.println("The results file is not found");
            }
        }


    /*	----- OTHER METHOD -----	*/

        @Override
        public int compareTo(Score o) {
            return 0;
        }
=======
    @Override
    public int compareTo(Score o) {
        if (this.victory > o.getVictory()) {
            return 1;
        } else {
            if (this.victory == o.getVictory()) {
                if (this.bulletDmg >= o.getBulletDmg()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
        return 0 ;
    }
>>>>>>> tmp

}
