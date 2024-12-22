package Main;

import Entity.NPC_OldMan;
import Object.OBJ_Axe;
import Object.OBJ_Boot;
import Object.OBJ_Chest;
import Object.OBJ_Heart;
import Object.OBJ_ManaCrystal;
import Object.OBJ_Potion_Red;
import monster.BigSnowMan;
import monster.SnowMan;
import tile_interactive.IT_DryTree;
public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    } 
    public void setObject() {
        int i = 0;

        gp.obj[i] = new OBJ_Axe(gp);
        gp.obj[i].worldX = 18 * gp.tileSize;
        gp.obj[i].worldY = 7 * gp.tileSize;
        i++;


        
        gp.obj[i] = new OBJ_Potion_Red(gp);
        gp.obj[i].worldX = 28 * gp.tileSize;
        gp.obj[i].worldY = 47 * gp.tileSize;
        i++;

        gp.obj[i] = new OBJ_Potion_Red(gp);
        gp.obj[i].worldX = 16 * gp.tileSize;
        gp.obj[i].worldY = 11 * gp.tileSize;
        i++;

        gp.obj[4] = new OBJ_Heart(gp);
        gp.obj[4].worldX = 29 * gp.tileSize;
        gp.obj[4].worldY = 40 * gp.tileSize;


        gp.obj[i] = new OBJ_ManaCrystal(gp);
        gp.obj[i].worldX = 7 * gp.tileSize;    
        gp.obj[i].worldY = 47 * gp.tileSize;
        i++;

        
        gp.obj[9] = new OBJ_ManaCrystal(gp);
        gp.obj[9].worldX = 25 * gp.tileSize;
        gp.obj[9].worldY = 35 * gp.tileSize;

        gp.obj[10] = new OBJ_ManaCrystal(gp);
        gp.obj[10].worldX = 11 * gp.tileSize;
        gp.obj[10].worldY = 7 * gp.tileSize;

        gp.obj[11] = new OBJ_ManaCrystal(gp);
        gp.obj[11].worldX = 3 * gp.tileSize;
        gp.obj[11].worldY = 2 * gp.tileSize;

        gp.obj[12] = new OBJ_ManaCrystal(gp);
        gp.obj[12].worldX = 24 * gp.tileSize;
        gp.obj[12].worldY = 44 * gp.tileSize;

        gp.obj[8] = new OBJ_Chest(gp);
        gp.obj[8].worldX = 45 * gp.tileSize;
        gp.obj[8].worldY = 46 * gp.tileSize;

        gp.obj[i] = new OBJ_Boot(gp);
        gp.obj[i].worldX = 41 * gp.tileSize;
        gp.obj[i].worldY = 39 * gp.tileSize;

    }

    public void setNPC()       
    {
        gp.npc[0] = new NPC_OldMan(gp);
        gp.npc[0].worldX = gp.tileSize*21;
        gp.npc[0].worldY = gp.tileSize*21;
    }
    public void setSnowMan(){

        int i = 0;

        gp.snowman[i] = new SnowMan(gp);
        gp.snowman[i].worldX =gp.tileSize*23;
        gp.snowman[i].worldY =gp.tileSize*36;
        i++;

        gp.snowman[i] = new SnowMan(gp);
        gp.snowman[i].worldX =gp.tileSize*42;
        gp.snowman[i].worldY =gp.tileSize*5;
        i++;

        gp.snowman[i] = new SnowMan(gp);
        gp.snowman[i].worldX =gp.tileSize*30;
        gp.snowman[i].worldY =gp.tileSize*42;
        i++;

        gp.snowman[i] = new SnowMan(gp);
        gp.snowman[i].worldX =gp.tileSize*16;
        gp.snowman[i].worldY =gp.tileSize*40;
        i++;

        gp.snowman[i] = new SnowMan(gp);
        gp.snowman[i].worldX =gp.tileSize*3;
        gp.snowman[i].worldY =gp.tileSize*11;
        i++;

        gp.snowman[i] = new SnowMan(gp);        // canh giu chia khoa
        gp.snowman[i].worldX =gp.tileSize*4;
        gp.snowman[i].worldY =gp.tileSize*2;
        i++;

        gp.snowman[i] = new SnowMan(gp);        // canh giu chia khoa
        gp.snowman[i].worldX =gp.tileSize*21;
        gp.snowman[i].worldY =gp.tileSize*10;
        i++;

      

        gp.snowman[i] = new SnowMan(gp);
        gp.snowman[i].worldX =gp.tileSize*24;
        gp.snowman[i].worldY =gp.tileSize*4;
        i++;

        gp.snowman[i] = new SnowMan(gp);
        gp.snowman[i].worldX =gp.tileSize*11;
        gp.snowman[i].worldY =gp.tileSize*6;
        i++;
 }
    public void setBigSnowMan(){
        gp.snowman[1] = new BigSnowMan(gp);
        gp.snowman[1].worldX =gp.tileSize*45;
        gp.snowman[1].worldY =gp.tileSize*35;
    }

    public void setInteractiveTile(){

        int i = 0;
   
        gp.iTile[i] = new IT_DryTree(gp,41,47);
        i++;
        gp.iTile[i] = new IT_DryTree(gp,41,48); //sua add them
        i++;

        gp.iTile[i] = new IT_DryTree(gp,41,46);
        i++;
        gp.iTile[i] = new IT_DryTree(gp,41,45);
        i++;
        gp.iTile[i] = new IT_DryTree(gp,41,44);
        i++;
        gp.iTile[i] = new IT_DryTree(gp,41,43);
        i++;
        gp.iTile[i] = new IT_DryTree(gp,42,43);
        i++;
        gp.iTile[i] = new IT_DryTree(gp,43,43);
        i++;
        gp.iTile[i] = new IT_DryTree(gp,44,43);
        i++;
        gp.iTile[i] = new IT_DryTree(gp,45,43);
        i++;
        gp.iTile[i] = new IT_DryTree(gp,46,43);
        i++;
        gp.iTile[i] = new IT_DryTree(gp,47,43);
        i++;
        gp.iTile[i] = new IT_DryTree(gp,48,43);
        i++;
        // gp.iTile[i] = new IT_DryTree(gp,44,20);
        // i++;
        gp.iTile[i] = new IT_DryTree(gp,47,41);
        i++;
        gp.iTile[i] = new IT_DryTree(gp,46,21);
        i++;
        gp.iTile[i] = new IT_DryTree(gp,47,21);
        i++;
        gp.iTile[i] = new IT_DryTree(gp,48,21);
        i++;
        
    }


}
