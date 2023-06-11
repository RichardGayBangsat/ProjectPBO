/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HighscoreData;

/**
 *
 * @author richardwei
 */
public class ScoreData implements Comparable<ScoreData>{
    private static int playernumber = 1;
    protected String name;
    protected int score;

    public ScoreData(int score) {
        this.score = score;
        name = "Player " + String.valueOf(playernumber);
        playernumber++;
    }

    @Override
    public int compareTo(ScoreData o) {
        if(o.getScore() > getScore()){
            return -1;
        }else if(o.getScore() == getScore()){
            return 0;
        }else{
            return 1;
        }
    }
    public int getScore(){
        return score;
    }
    public String getName(){
        return name;
    }
    public String toString(){
        return name + " - " + String.valueOf(getScore());
    }
}
