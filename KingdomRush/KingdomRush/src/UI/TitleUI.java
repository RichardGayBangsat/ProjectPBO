/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI;
import MainPackage.GamePanel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

/**
 *
 * @author richardwei
 */
public class TitleUI {
    private int comNum; // MENU NUMBER
    protected GamePanel gamepanel;
    protected Graphics2D g2;
    protected Font customFont;
    protected Color fontColor;
    protected BufferedImage backgroundImage;
    protected BufferedImage bannerBackground;
    
    public TitleUI (GamePanel gamepanel){
        this.gamepanel = gamepanel;
        comNum = 0;
        
        
        // SET FONT
        try{
            InputStream inputstream = getClass().getResourceAsStream("/assets/font/LuckiestGuy-Regular.ttf");
            customFont = Font.createFont(Font.TRUETYPE_FONT, inputstream);
        }catch(FontFormatException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        // ==================================================================================================
        
        // SET IMAGE
        try{
            backgroundImage = ImageIO.read(getClass().getResourceAsStream("/assets/titleframebackground/titleFrameBackground.png"));
            bannerBackground = ImageIO.read(getClass().getResourceAsStream("/assets/pausebackground/pauseBackground.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(customFont);
        fontColor = new Color(255,255,204);
        
        g2.drawImage(backgroundImage, 0, 0, gamepanel.screenWidth, gamepanel.screenHeight, null);
        // SET BANNER HEIGHT, WIDTH, X , Y
        int bannerWidth = gamepanel.tileSize * 10;
        int bannerHeight = gamepanel.tileSize * 6;
        int screenX = gamepanel.screenWidth / 2 - bannerWidth / 2;
        int screenY = gamepanel.screenHeight / 2 - bannerHeight / 2 - gamepanel.tileSize / 2;
        // ===============================
        g2.drawImage(bannerBackground, screenX, screenY, bannerWidth, bannerHeight, null);
        drawTitle(bannerHeight, bannerWidth, screenX, screenY);
    }
    public void drawTitle(int bannerHeigth, int bannerWidth, int screenX, int screenY){
        
        g2.setFont(g2.getFont().deriveFont(Font.ITALIC, 96F));
        String text = "KELUN RUSH";
        
        // SET POSITION
        int positionX = getXforCenteredText(text);
        int positionY = screenY + gamepanel.tileSize + g2.getFontMetrics().getAscent();
        
        // SET COLOR
        g2.setColor(fontColor);
        
        // DRAW TITLE
        g2.drawString(text, positionX, positionY);
        
        // DRAW MENU TEXT
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN + Font.ITALIC, 48F));
        text = "NEW GAME";
        positionX = getXforCenteredText(text);
        positionY += gamepanel.tileSize; // add position y 
        g2.drawString(text, positionX, positionY);
        drawSideArrow(0,positionX,positionY);
        
        text = "LOAD GAME";
        positionX = getXforCenteredText(text);
        positionY += gamepanel.tileSize; // add position y 
        g2.drawString(text, positionX, positionY);
        drawSideArrow(1,positionX,positionY);
        
        text = "EXIT GAME";
        positionX = getXforCenteredText(text);
        positionY += gamepanel.tileSize; // add position y 
        g2.drawString(text, positionX, positionY);        
        drawSideArrow(2,positionX,positionY);
    }
    public void drawSideArrow(int index, int x, int y){
        if(comNum == index){
            g2.setColor(fontColor);
            g2.drawString(">", x - gamepanel.tileSize, y); // set text start X position - tilesize
        }
    }
    public int getXforCenteredText(String text){
        // GET FONT LENGTH
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        // GENERATE X POSITION FOR CENTERED TEXT
        int x = gamepanel.screenWidth/2 - length/2;
        return x;
    }
    
    // GETTER SETTER - comNum;
    public void setNum(int value){
        this.comNum = value;
    }
    public int getNum(){
        return this.comNum;
    }
}
