package Object;
import Entity.Entity;
import Main.GamePanel;
public class OBJ_Axe extends Entity {
    public OBJ_Axe (GamePanel gp){
        super(gp);
        type = type_axe;
        name = "Woodcutter's Axe";
        down1 = setup ("/objects/axe",gp.tileSize,gp.tileSize);
        attackValue  = 2;
        attackArea.width = 40;
        attackArea.height = 40; 
        description = "[Woodcutter's Axe]\n A bit rusty but still \n can cut some trees.";

    }
}
