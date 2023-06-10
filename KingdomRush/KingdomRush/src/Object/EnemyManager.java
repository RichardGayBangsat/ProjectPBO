/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Object;

import MainPackage.GamePanel;
import PlayerBase.PlayerBase;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author richa
 */
public class EnemyManager {
    protected int numsOfEnemy;
    protected int enemyCounter; // total enemy perstage 
    protected PlayerBase playerbase;
    protected GamePanel gamepanel;
    protected int spawnCounter = 1;
    protected int spawnCD = 50; //  spawn cooldown 
    
    protected final int enemyLevelUpCounter = 3;
    protected int enemyLevel;
    protected ArrayList<Enemy> enemies;
    
    public EnemyManager(GamePanel gamepanel, PlayerBase playerbase){
        enemies = new ArrayList<>();
        this.gamepanel = gamepanel;
        this.playerbase = playerbase;
        this.numsOfEnemy = 4;
        this.enemyCounter = numsOfEnemy;
        setEnemyLevel();
    }
    public void setEnemyLevel(){
        enemyLevel = playerbase.getStageNumber() / 3;
    }
    public int getEnemyLevel(){
        return enemyLevel;
    }
    
    
    public void spawnEnemy(){
        if(enemyCounter % 2 == 0){
            enemies.add(new Necremencer(gamepanel, enemyLevel));
        }else{
            enemies.add(new Nightborne(gamepanel, enemyLevel));
        }
    }
    public void update(){
        if(enemyCounter >= 1){
            spawnCounter++;
            if(spawnCounter >= spawnCD){
                spawnEnemy();
                enemyCounter--;
                spawnCounter = 1;
            }
        }
        
        for(int x = 0; x < enemies.size(); x++){
            enemies.get(x).update();
            enemies.get(x).move();
            if(enemies.get(x).posX + gamepanel.tileSize == 0){
                playerbase.minLive();
                enemies.remove(x);
            }
        }
        if(enemies.size() == 0){
            isEnemyCounterEnd();
        }
    }
    public void draw(Graphics2D g2){
        for(int x = 0; x < enemies.size(); x++){
            enemies.get(x).draw(g2);
        }
    }
    public void checkAttackedArea(int x, int y, int width, int height, int damage){
        for(int i = 0; i < enemies.size(); i++){
            while(enemies.size() > i && enemies.get(i).isAttacked(x,y,width,height,damage)){
                enemies.remove(i);
            }
        }
        if(enemies.size() == 0){
            isEnemyCounterEnd();
        }
    }
    public void isEnemyCounterEnd(){
        if(enemyCounter == 0 && enemies.size() == 0){
            setUpNewStage();
        }
    }
    public void setUpNewStage(){
        playerbase.setNextStage();
        numsOfEnemy += 2;
        enemyCounter = numsOfEnemy;
        spawnCounter = 1;
        setEnemyLevel();
    }
}