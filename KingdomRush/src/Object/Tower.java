/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Object;

import MainPackage.GamePanel;
import Sound.Sound;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author USER
 */
public class Tower  extends Character{
    
    protected int Damage = 20;
    protected int posX,posY;
    protected boolean shoot;
    protected int cooldown;
    protected String name;
    protected boolean collision=true;
    protected BufferedImage image;
    protected Projectile projectile = new Projectile(gamepanel,this);
    protected ArrayList<BufferedImage> fireballright = new ArrayList<>();
    protected ArrayList<BufferedImage> fireballLeft = new ArrayList<>();
    protected ArrayList<BufferedImage> fireballUp = new ArrayList<>();
    protected ArrayList<BufferedImage> fireballDown = new ArrayList<>();
    protected ArrayList<Projectile> magazine = new ArrayList<>();
    public Tower(GamePanel gp) {
        super(gp);
        try{
            image = ImageIO.read(getClass().getResource("/assets/tower/tower1.png"));
            setUpProjectileImage(fireballright, fireballLeft, fireballUp, fireballDown);
            this.height=gp.tileSize*2;
            this.width=gp.tileSize;
            this.shoot = false;
            this.cooldown=0;
        }catch(IOException e){
            e.printStackTrace();
        }
        
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }
    public void setPosY(int a) {
        this.posY = a;
    }
    public void draw(Graphics2D g2,GamePanel gp){
        g2.drawImage(image,posX,posY,gp.tileSize,gp.tileSize*2,null );
    }
    public void drawProjectile(Graphics2D g2,GamePanel gp){
        if(this.cooldown==0){
            shootProjectile();
            this.cooldown=27;
        }else{
            cooldown-=1;
        }
        for (int i = 0; i < magazine.size(); i++) {
            g2.drawImage(fireballright.get(0),magazine.get(i).posX,magazine.get(i).posY+gp.tileSize,
                    magazine.get(i).width,magazine.get(i).height,null );
        }
        
        
        moveProjectile();
    }
    public void drawProjectileBackward(Graphics2D g2,GamePanel gp){
        if(this.cooldown==0){
            shootProjectile();
            this.cooldown=27;
        }else{
            cooldown-=1;
        }
        for (int i = 0; i < magazine.size(); i++) {
            g2.drawImage(fireballLeft.get(0),magazine.get(i).posX,magazine.get(i).posY+gp.tileSize,
                    magazine.get(i).width,magazine.get(i).height,null );
        }
        moveProjectileBackward();
    }
    public void drawProjectileUpward(Graphics2D g2,GamePanel gp){
        if(this.cooldown==0){
            shootProjectile();
            this.cooldown=27;
        }else{
            cooldown-=1;
        }
        for (int i = 0; i < magazine.size(); i++) {
            g2.drawImage(fireballUp.get(0),magazine.get(i).posX+gp.tileSize/2-10,magazine.get(i).posY,
                    magazine.get(i).height,magazine.get(i).width/2,null );
        }
        moveProjectileUpWard();
    }
    public void drawProjectileDownward(Graphics2D g2,GamePanel gp){
        if(this.cooldown==0){
            shootProjectile();
            this.cooldown=27;
        }else{
            cooldown-=1;
        }
        for (int i = 0; i < magazine.size(); i++) {
            g2.drawImage(fireballDown.get(0),magazine.get(i).posX+gp.tileSize/2-10,magazine.get(i).posY+gp.tileSize*2,
                    magazine.get(i).height,magazine.get(i).width/4,null );
        }
        moveProjectileDownWard();
    }
     
    public void shootProjectile(){
        magazine.add(new Projectile(gamepanel,this));
        magazine.get(magazine.size()-1).setPosX(this.posX);
        magazine.get(magazine.size()-1).setPosY(this.posY);
    }
    public int getProjectileX(){
        return projectile.posX;
    }
    public void moveProjectile(){
        if(!magazine.isEmpty()){
            magazine.get(0).posX+=4;
        }
    }
    public void moveProjectileBackward(){
        if(!magazine.isEmpty()){
            magazine.get(0).posX-=4;
        }
    }
    public void moveProjectileUpWard(){
        if(!magazine.isEmpty()){
            magazine.get(0).posY-=4;
        }
    }
    public void moveProjectileDownWard(){
        if(!magazine.isEmpty()){
            magazine.get(0).posY+=4;
        }
    }
    public void setProjectileAttack(EnemyManager enemymanager, int i){
        enemymanager.checkProjectileAttackedArea(magazine.get(i).getPosX(), magazine.get(i).getPosY(), 
                magazine.get(i).width, magazine.get(i).height, 20,this.magazine,i);
    }
    public void setProjectileAttack2(EnemyManager enemymanager, int i){
        enemymanager.checkProjectileAttackedArea2(magazine.get(i).getPosX(), magazine.get(i).getPosY(), 
                magazine.get(i).width, magazine.get(i).height, 20,this.magazine,i);
    }
    public int getMagazineSize(){
        return this.magazine.size();
    }
    public void turnOn(){
        this.shoot = true;
    }
    public void turnOff(){
        this.shoot = false;
    }
    public boolean shootable(){
        return shoot;
    }
    public void setUpProjectileImage(ArrayList<BufferedImage> a,ArrayList<BufferedImage> b,ArrayList<BufferedImage> c,ArrayList<BufferedImage> d){
        try{
            BufferedImage temp1 = ImageIO.read(getClass().getResource("/assets/projectile/1.png"));
            BufferedImage temp2 = ImageIO.read(getClass().getResource("/assets/projectile/2.png"));
            a.add(temp1);
            a.add(temp2);
            BufferedImage temp3 = ImageIO.read(getClass().getResource("/assets/projectile/3.png"));
            BufferedImage temp4 = ImageIO.read(getClass().getResource("/assets/projectile/4.png"));
            b.add(temp3);
            b.add(temp4);
            BufferedImage temp5 = ImageIO.read(getClass().getResource("/assets/projectile/5.png"));
            BufferedImage temp6 = ImageIO.read(getClass().getResource("/assets/projectile/6.png"));
            c.add(temp5);
            c.add(temp6);
            BufferedImage temp7 = ImageIO.read(getClass().getResource("/assets/projectile/7.png"));
            BufferedImage temp8 = ImageIO.read(getClass().getResource("/assets/projectile/8.png"));
            d.add(temp7);
            d.add(temp8);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public int getHeight(){
        return this.height;
    }
    public int getWidth(){
        return this.width;
    }
    
}
