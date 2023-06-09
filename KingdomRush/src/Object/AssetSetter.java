/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Object;

import MainPackage.GamePanel;
import MainPackage.GamePanel;
import Object.TowerManager;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp=gp;
    }
    public void setObject(ArrayList<Tower> towerList){
        //pengecekan agar tower tetap 1 tile lebarnya
        int b=gp.getPosxPlayer();
        int ctrx=0;
        int c=gp.getPosyPlayer();
        int ctry=0;
        while(b-gp.tileSize>0){
            ctrx++;
            b-=gp.tileSize;
        }
        while(c-gp.tileSize>0){
            ctry++;
            c-=gp.tileSize;
        }
        //pengecekan lokasi valid apakah sudah ditempati
        int ctr=0;
        for(int i=0;i<towerList.size();i++){
            if(towerList.get(i).getPosX()== ctrx * gp.tileSize && towerList.get(i).getPosY()==ctry*gp.tileSize){
                ctr++;
            }
        }
        if(gp.getTileMapGP(ctrx, ctry+1).equals("14")){
            if(ctr==0){
                gp.setUpTower(ctrx * gp.tileSize, ctry * gp.tileSize);
            }
        }
    }
    
}
