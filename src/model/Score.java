/*
 * Projet Darwini - Étude Pratique
 *
 * Development of an IA based on genetic algorithms and neural networks.
 *
 * class Score.java
 */

package model;

import java.io.File;

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


    public Score(File f) {
    }

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

}
