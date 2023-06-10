/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ButtonManager;

import PlayerBase.Item;
import PlayerBase.PlayerBase;

/**
 *
 * @author richa
 */
public class ItemsButtonManager extends ButtonManager{
    Item item;
    
    public ItemsButtonManager(int x, int y, int height, int width, Item item, PlayerBase playerbase) {
        super(x, y, height, width, playerbase);
        this.item = item;
    }
    
    @Override
    public void upItemLevel(){
        item.levelUp(playerbase);
    }
}
