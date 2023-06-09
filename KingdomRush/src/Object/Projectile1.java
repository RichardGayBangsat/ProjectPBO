/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Object;

import MainPackage.GamePanel;

/**
 *
 * @author USER
 */
public class Projectile1 extends Character{
    
    protected int speed;
    
    public Projectile1(GamePanel gp, Tower a) {
        super(gp);
        this.speed = 10;
        this.posX = a.getPosX(); 
        this.posY = a.getPosY(); 
        this.height=gp.tileSize/4;
        this.width=gp.tileSize;
    }
    

    public void setPosX(int x) {
        this.posX = x;
    }
    public void setPosY(int y){
        this.posY = y;
    }
    public void setProjectileAttack(EnemyManager enemymanager){
        enemymanager.checkAttackedArea(posX, posY, width, height, 20);
    }
}
