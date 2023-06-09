/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Object;

import MainPackage.GamePanel;
import Object.Tower;
import java.awt.Graphics2D;
import java.util.ArrayList;
/**
 *
 * @author USER
 */
public class TowerManager {
    protected GamePanel gamePanel;
    
    protected  ArrayList<Tower> towers;

    public TowerManager(GamePanel gamePanel) {
        this.towers=new ArrayList<>();
        this.gamePanel = gamePanel;
    }
    public void addTower(){
        towers.add(new Tower(gamePanel));
    }
    public void setWorldxTower(int x){
        towers.get(towers.size()-1).posX=x;
    }
    public void setWorldyTower(int y){
        towers.get(towers.size()-1).posY=y;
    }
    public void draw(Graphics2D g2){
        int code;
        EnemyManager enemymanager=gamePanel.getEnemyManager();
        for (Tower tower : towers) {
            tower.draw(g2, gamePanel);
            boolean shootable = false;
            code = 0;
            for (int j = 0; j < enemymanager.getEnemiesSize(); j++) {
                Enemy enemy = enemymanager.getEnemyAt(j);
                if (enemy.getPosX() > tower.getPosX() && enemy.getPosX() < tower.getPosX() + gamePanel.tileSize* 3) {
                    if (enemy.getPosY()<tower.getPosY()+gamePanel.tileSize && enemy.getPosY()>tower.getPosY()) {
                        tower.turnOn();
                        code = 1; // Right
                        shootable = true;
                    }
                } else if (enemy.getPosX() < tower.getPosX() && enemy.getPosX() > tower.getPosX() - gamePanel.tileSize * 3) {
                    if (enemy.getPosY()<tower.getPosY()+gamePanel.tileSize && enemy.getPosY()>tower.getPosY()) {
                        tower.turnOn();
                        code = 2; // Left
                        shootable = true;
                    }
                }else if (enemy.getPosX() <= tower.getPosX()+gamePanel.tileSize && enemy.getPosX()>=tower.getPosX()) {
                    if (tower.getPosY()<enemy.getPosY() ) {
                        tower.turnOn();
                        code = 3; // Down
                        shootable = true;
                    }else {
                        tower.turnOn();
                        code = 4; // up
                        shootable = true;
                    }
                } 
                if (shootable) {
                    break;
                }
            }
            if (shootable) {
                if (code == 1) {
                    tower.drawProjectile(g2, gamePanel);
                } else if (code == 2) {
                    tower.drawProjectileBackward(g2, gamePanel);
                } else if (code == 3){
                    tower.drawProjectileDownward(g2, gamePanel);
                } else if (code == 4){
                    tower.drawProjectileUpward(g2, gamePanel);
                }
                tower.turnOff();
            }
            for (int j = 0; j < tower.getMagazineSize(); j++) {
                if(code!=3){
                    tower.setProjectileAttack(enemymanager, j);
                }else{
                    tower.setProjectileAttack2(enemymanager, j);
                }
            }
        }
    }
    public ArrayList<Tower> getTowerArrayList(){
        return this.towers;
    }
    
    
}
