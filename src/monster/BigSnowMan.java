package monster;
import Entity.Entity;
import Main.GamePanel;
import Object.OBJ_Heart;
import Object.OBJ_ManaCrystal;
import Object.OBJ_Rock;
import java.util.Random;

public class BigSnowMan extends Entity {
    GamePanel gp;
    public BigSnowMan ( GamePanel gp){
        super(gp);
        this.gp=gp;
        type =type_monster;
        name = "Big Snow Man";
        speed = 4;
        maxLife = 50;
        life = maxLife;
        attack = 2;
        defense = 0;
        exp = 2;
        projectile = new OBJ_Rock(gp);

        solidArea.x = 3 * 3;       // Nếu x được tính dựa trên hệ tọa độ mới.
        solidArea.y = 18 * 3;      // Nếu y được tính dựa trên hệ tọa độ mới.
        solidArea.width = 42 * 3;  // Kích thước chiều rộng tăng gấp đôi.
        solidArea.height = 30 * 3; // Kích thước chiều cao tăng gấp đôi.
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        
        getBigImage();
    }
    public void getBigImage(){
        up1 = setup("/monster/biscuit_1",gp.tileSize*3,gp.tileSize*3);
        up2 = setup("/monster/biscuit_1",gp.tileSize*3,gp.tileSize*3);
        down1 = setup("/monster/biscuit_1",gp.tileSize*3,gp.tileSize*3);
        down2 = setup("/monster/biscuit_1",gp.tileSize*3,gp.tileSize*3);
        left1 = setup("/monster/biscuit_1",gp.tileSize*3,gp.tileSize*3);
        left2 = setup("/monster/biscuit_1",gp.tileSize*3,gp.tileSize*3);
        right1 = setup("/monster/biscuit_1",gp.tileSize*3,gp.tileSize*3);
        right2 = setup("/monster/biscuit_1",gp.tileSize*3,gp.tileSize*3);
    }
    
    public void setAction()
    {

        actionLockCounter++;
        if(actionLockCounter == 120)
        {
            Random random = new Random();
            int i = random.nextInt(20) + 1;//pick a random no from 1 -> 100
    
            if(i <= 5)
            {
                direction = "up";
            }
            if(i > 5 && i <= 10)
            {
                direction = "down";
            }
            if(i > 10 && i <= 15)
            {
                direction = "left";
            }
            if(i > 15 && i <= 20)
            {
                direction = "right";
            }

            actionLockCounter = 0;
        }
        int i = new Random().nextInt(100)+1;
        if(i > 99 && projectile.alive == false && shotAvailableCounter ==30) {
            projectile.set(worldX, worldY, direction, true, this);
            gp.projectileList.add(projectile);
            shotAvailableCounter = 0;
        }
    }

    public void damageReaction()
    {
        actionLockCounter = 0;
        direction = gp.player.direction;
    }
    public void checkDrop() {
        //CAST A DIE
        int i = new Random().nextInt(100)+1;
        //SET THE MONSTER DROP
        if(i <50) {
            dropItem(new OBJ_Heart(gp));
        }
        if(i >= 50 && i < 100) {
            dropItem(new OBJ_ManaCrystal(gp));
        }

    }

}
