/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Object;
import MainPackage.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;

/**
 *
 * @author richa
 */
public class Player extends Character{
    protected int maxShield, shield; // SHIELD
    // POSITION, IMAGE, DIRECTION
    KeyHandler keyhandler;
    protected ArrayList<BufferedImage> walkright = new ArrayList<>();
    protected ArrayList<BufferedImage> walkleft = new ArrayList<>();
    protected ArrayList<BufferedImage> idle = new ArrayList<>();
    protected ArrayList<BufferedImage> attack = new ArrayList<>();
    protected String direction;
    
    // ANIMATION INDEX & COUNTER
    protected int spriteIndex = 0;
    protected int spriteCounter = 0;
    
    protected ArrayList<PlayerBuild> builds = new ArrayList<>();
    
    protected boolean attacking;
  
    public Player(GamePanel gamepanel, KeyHandler keyhandler){
        this.gamepanel = gamepanel;
        this.keyhandler = keyhandler;
        alive = false;
        this.level = 1;
        this.characterMaxHp = 100;
        this.characterHp = characterMaxHp;
        
        this.maxShield = 50;
        this.shield = maxShield; 
        
        this.attackDamage = 25;
        setupDefaultPlayer();
        getPlayerImage();
    }
    private void setupDefaultPlayer(){
        attacking = false;
        posX = gamepanel.screenWidth / 2 - gamepanel.tileSize / 2;
        posY = gamepanel.screenHeight / 2 - gamepanel.tileSize / 2;
        height = gamepanel.tileSize;
        width = gamepanel.tileSize;
        characterSpeed = 4; 
        
        builds.add(new PlayerBuild("ATK", 10, "/assets/effect/logo/sword.png", this));
        builds.add(new PlayerBuild("HP", 15, "/assets/effect/logo/food.png", this));
        builds.add(new PlayerBuild("DEF", 10, "/assets/effect/logo/shield.png", this));
    }
    public void getPlayerImage() {
        // defautl direction
        direction = "left";
        
        try{
            for(int x = 0; x < 6; x++){
                walkright.add(ImageIO.read(getClass().getResourceAsStream("/assets/player/walk"+(x+1)+".png")));
                walkleft.add(ImageIO.read(getClass().getResourceAsStream("/assets/player/walkleft"+(x+1)+".png")));
                idle.add(ImageIO.read(getClass().getResourceAsStream("/assets/player/idle"+(x+1)+".png")));
                attack.add(ImageIO.read(getClass().getResourceAsStream("/assets/player/attack"+(x+1)+".png")));
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    // PLAYER MOVEMENT
    public void moveUp(){
        direction = "up";
        if(posY-characterSpeed >= 0){
            posY -= characterSpeed;
        }
    }
    public void moveDown(){
        direction = "down";
        if(posY+characterSpeed <= gamepanel.arenaScreenRow * gamepanel.tileSize - gamepanel.tileSize){ 
            posY += characterSpeed;
        }
    }
    public void moveLeft(){
        direction = "left";
        if(posX-characterSpeed >= 0){
            posX -= characterSpeed;
        }
    }
    public void moveRight(){
        direction = "right";
        if(posX+characterSpeed <= gamepanel.arenaScreenCol * gamepanel.tileSize - (gamepanel.tileSize * 2)){ // check if < 1280 (maps dimension) - 80 (tilesize)
            posX += characterSpeed;
        }
    }
    
    // UPDATE DRAW
    public void update(){
        if(keyhandler.up == true){
            moveUp();
        }else if(keyhandler.down == true){
            moveDown();
        }else if(keyhandler.left == true){
            moveLeft();
        }else if(keyhandler.right == true){
            moveRight();
        }else{
            direction = "idle";
        }
        
        spriteCounter++;
        if(spriteCounter > 5){
            if(spriteIndex == 5){
                spriteIndex = 0;
                if(attacking == true){
                    attacking = false;
                }
            }else{
                spriteIndex++;
            }
            spriteCounter = 0;
        }
    }
    
    // DRAW
    public void draw(Graphics2D g2){
        BufferedImage image = null;
        
        if(attacking == true){
            image = attack.get(spriteIndex);
        }else{
            if(direction.equals("up")){
                image = walkright.get(spriteIndex);
            }else if(direction.equals("down")){
                image = walkright.get(spriteIndex);
            }else if(direction.equals("left")){
                image = walkleft.get(spriteIndex);
            }else if(direction.equals("right")){
                image = walkright.get(spriteIndex);
            }else if(direction.equals("idle")){
                image = idle.get(spriteIndex);
            }
        }
        g2.drawImage(image, posX, posY, width, height, null);
        
    }
    
    // GETTER SETTER
    public boolean isAttacking(){
        return attacking;
    }
    public void setAttack(EnemyManager enemymanager){
        attacking = true;
        spriteIndex = 0;
        enemymanager.checkAttackedArea(posX, posY, width, height, attackDamage);
    }
    public ArrayList<BufferedImage> getIdleAnimation(){
        return idle;
    }
    public BufferedImage getIdleImage(){
        return idle.get(6);
    }
    public ArrayList<PlayerBuild> getBuilds(){
        return builds;
    }
    
    public int getMaxShield(){
        return this.maxShield;
    }
    public int getShield(){
        return shield;
    }
    public int getMaxHp(){
        return this.characterMaxHp;
    }
    public int getHp(){
        return characterHp;
    }
    public int getAttack(){
        return attackDamage;
    }
    public int getLevel(){
        return level;
    }
    public String getStatusByString(String value){
        String result = value;
        if(value.equals("ATK")){
            result += " " + String.valueOf(attackDamage);
        }else if(value.equals("DEF")){
            result += " " + String.valueOf(shield);
        }else{
            result += " " + String.valueOf(characterHp);
        }
        
        return result;
    }
}
