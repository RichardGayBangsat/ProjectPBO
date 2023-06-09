/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Object;

import PlayerBase.PlayerBase;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author richardwei
 */
public class PlayerBuild {
    protected Player player;
    protected BufferedImage image;
    protected String name;
    protected int upgradeCost;
    protected int level;
    
    // UPGRADE BUTTON POSITION
    protected int height, width;
    protected int x, y;
    
    public PlayerBuild(String name, int upgradeCost, String path, Player player){
        try {
            this.image = ImageIO.read(getClass().getResource(path));
        } catch (IOException ex) {
            Logger.getLogger(PlayerBuild.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.name = name;
        this.upgradeCost = upgradeCost;
        this.level = 1;
        this.player = player;
    }
    
    // GETTER SETTER
    public int getLevel(){
        return level;
    }
    public int getUpgradeCost(){
        return level * upgradeCost;
    }
    public BufferedImage getImage(){
        return image;
    }
    public String getName(){
        return name;
    }
    public void setButtonPosition(int x, int y, int height, int width){
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }
    
    public void levelUp(PlayerBase playerbase){
        if(playerbase.getCoin() < getUpgradeCost()){
            System.out.println("Not enough money");
        }else{
            playerbase.setCoin(playerbase.getCoin() - getUpgradeCost());
            level += 1;
            
            if(name == "ATCK"){
                player.setAttack(player.getAttack() + 5);
            }else if(name == "ASPD"){
                if(player.getAttackSpeed() > 1){
                    player.setAttackSpeed(player.getAttackSpeed() - 1);
                }else{
                    level -= 1;
                    System.out.println("level max");
                }
            }else if(name == "CRIT"){
                player.setCritDamage(player.getCritDamage()+ 10);
            }
        }
    }
}
