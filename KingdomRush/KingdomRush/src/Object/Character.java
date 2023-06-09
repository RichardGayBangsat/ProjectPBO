/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Object;

import MainPackage.GamePanel;

public class Character{
    protected GamePanel gamepanel;
    protected int posX,posY,height,width;
    protected int characterSpeed;
    
    // CHARACTER STATUS
    protected boolean alive;
    protected int level;
    protected int characterMaxHp, characterHp; // HP
    protected int attackDamage; // ATTACK
}
