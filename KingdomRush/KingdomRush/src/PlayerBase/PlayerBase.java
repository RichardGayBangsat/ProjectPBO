/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PlayerBase;

import MainPackage.GamePanel;
import Object.Player;
import UI.InformationBoxUI;
import com.sun.java.accessibility.util.AWTEventMonitor;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author richa
 */
public class PlayerBase {
    protected GamePanel gamepanel;
    protected Player player;
    protected int stage;
    protected int live;
    protected int coin;
    protected int onUseItemIndex;
    protected boolean usingItem;
    protected int animationCounter = 0;
    protected int animationIndex = 0;
    protected int useItemCD;
    protected int curUseItemCD;
    protected int curTime;
    
    //ITEMS
    ArrayList<Item> items = new ArrayList<>();
    
    // ICON
    protected Banner coinBanner;
    protected Banner liveBanner;
    
    public PlayerBase(GamePanel gamepanel, Player player){
        this.gamepanel = gamepanel;
        this.player = player;
        this.stage = 1;
        this.live = 20;
        this.coin = 1000;
        int x = 0;
        int y = 0;
        int width = gamepanel.tileSize;
        int height = gamepanel.tileSize;
        useItemCD = 6;
        curUseItemCD = 0;
        curTime = 0;
        
        usingItem = false;
        coinBanner = new Banner("/assets/playerbase/coin.png", width, height, x, y);
        liveBanner = new Banner("/assets/playerbase/live.png", width, height, x, y + gamepanel.tileSize);
        
        // ADD ITEMS
        int size = gamepanel.tileSize - 20;
        addItem("/assets/effect/logo/bomb.png", "/assets/effect/animation/Circle_explosion", "bomb",  10, 10, 100);
        addItem("/assets/effect/logo/bluebomb.png", "/assets/effect/animation/Explosion_blue_circle", "blue bomb", 20, 15, 100);
        addItem("/assets/effect/logo/poisonbomb.png", "/assets/effect/animation/Explosion_gas_circle", "poison bomb", 25, 20, 100);
    }
    public boolean isReadyUseItem(){
        return (curUseItemCD == 0);
    }
    public void addTime(){
        if(!isReadyUseItem()){
            curTime++;
            if(curTime == 60){
                curTime = 0;
                curUseItemCD--;
            }
        }
    }
    public void update(){
        addTime();
        if(usingItem){
            animationCounter++;
            if(animationCounter > 5){
                animationCounter = 0;
                animationIndex++;
                if(animationIndex == 10){
                    animationIndex = 0;
                    usingItem = false;
                }
            }
        }
    }
    public void draw(Graphics2D g2){
        coinBanner.draw(g2, String.valueOf(coin));
        liveBanner.draw(g2, String.valueOf(live));
        if(usingItem){
            int areasize = items.get(onUseItemIndex).getAreaSize() + 200;
            int startX = items.get(onUseItemIndex).getAnimationStartPos('x') - 100;
            int startY = items.get(onUseItemIndex).getAnimationStartPos('y') - 100;
            g2.drawImage(items.get(onUseItemIndex).getAnimation(animationIndex),startX,startY ,areasize, areasize, null);
        }
    }
    public void addItem(String logoPath, String animationPath, String name, int damage, int upgradeCost, int areaSize){
        items.add(new Item(logoPath, animationPath, name, damage, upgradeCost, areaSize));
    }
    public void addItem(Item newitem){
        items.add(newitem);
    }
    
    public String getStage(){
        return ("Stage " + String.valueOf(stage));
    }
    public int getStageNumber(){
        return stage;
    }
    public ArrayList<Item> getAllitems(){
        return items;
    }
    
    // GETTER SETTER
    public int getCoin(){
        return coin;
    }
    public void setCoin(int coin){
        this.coin = coin;
    }
    public void minLive(){
        this.live--;
        if(live == 0){
            gamepanel.gameOver = true;
        }
    }
    public int getCurUseItemCD(){
        return curUseItemCD;
    }
    public int getUseItemCD(){
        return useItemCD;
    }
    public void setOnUseItemIndex(int index){
        onUseItemIndex = index;
    }
    public void setNextStage(){
        stage++;
        coin += stage * 50;
    }
    public int isItemClicked(int x, int y){
        for(int i = 0; i < items.size(); i++){
            if(items.get(i).isPressed(x, y)){
                return i;
            }
        }
        
        return -1;
    }
    
    public void useItem(int x, int y){
        int areasize = items.get(onUseItemIndex).getAreaSize();
        int damage = items.get(onUseItemIndex).getDamage();
        usingItem = true;
        gamepanel.getEnemyManager().checkAttackedArea(x - areasize / 2, y - areasize / 2, areasize, areasize, damage);
        items.get(onUseItemIndex).setAnimationCoord(x - areasize / 2, y - areasize / 2);
        curUseItemCD = useItemCD;
    }
}
