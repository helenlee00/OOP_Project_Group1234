package Object;

import Entity.Entity;
import Main.GamePanel;

public class OBJ_Sword_Normal extends Entity{

    public OBJ_Sword_Normal (GamePanel gp){
	    super(gp);
		type = type_sword;
	    name = "Normal Sword";
	    down1 = setup("/objects/sword_normal", gp.tileSize, gp.tileSize);
	    attackValue = 4;
		attackArea.width = 40;
		attackArea.height = 40;
		description = "[" + name + "]\nAn old sword.";
    }
}
