/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ButtonManager;

import Object.PlayerBuild;
import PlayerBase.PlayerBase;

/**
 *
 * @author richa
 */
public class BuildButtonManager extends ButtonManager{
    PlayerBuild build;
    
    public BuildButtonManager(int x, int y, int height, int width, PlayerBuild build, PlayerBase playerbase) {
        super(x, y, height, width, playerbase);
        this.build = build;
    }
    
    @Override
    public void upItemLevel(){
        build.levelUp(playerbase);
    }
}
