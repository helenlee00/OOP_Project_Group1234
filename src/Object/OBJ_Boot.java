package Object;

import Entity.Entity;
import Main.GamePanel;

public class OBJ_Boot extends Entity {

    public OBJ_Boot (GamePanel gp) {
        super(gp);
        name = "Boot";
        down1 = setup("/objects/Boot",gp.tileSize,gp.tileSize);
    
    }
}

