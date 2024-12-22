package Object;
import Entity.Entity;
import Entity.Projectile;
import Main.GamePanel;
import java.awt.Color;

public class OBJ_Snowball extends Projectile{
    GamePanel gp;
    public OBJ_Snowball(GamePanel gp) {
        super(gp);
        this.gp= gp;
        name = "Snowball";
        speed = 8;
        maxLife = 80;
        life = maxLife;
        attack = 6;
        useCost = 1;
        alive = false;
        getImage();     
    }
    public void getImage() {
        up1 = setup("/projectile/snowball_up_1",gp.tileSize,gp.tileSize);
        up2 = setup("/projectile/snowball_up_2",gp.tileSize,gp.tileSize);
        down1 = setup("/projectile/snowball_down_1",gp.tileSize,gp.tileSize);
        down2 = setup("/projectile/snowball_down_2",gp.tileSize,gp.tileSize);
        left1 = setup("/projectile/snowball_left_1",gp.tileSize,gp.tileSize);
        left2 = setup("/projectile/snowball_left_2",gp.tileSize,gp.tileSize);
        right1 = setup("/projectile/snowball_right_1",gp.tileSize,gp.tileSize);
        right2 = setup("/projectile/snowball_right_2",gp.tileSize,gp.tileSize);
    }
    public boolean haveResource(Entity user) {
        boolean haveResource = false;
        if(user.mana >= useCost) {
            haveResource = true;
        }
        return haveResource;
    }
    public void subtractResource(Entity user) {
        user.mana -= useCost;
    }

    public Color getParticleColor(){
        Color color = new Color(70, 130, 180);   // chỉnh màu snowball khi ném trúngtrúng nvat   
        
        return color;
   }
   
    public int getParticleSize(){
           int size =4;   
           return size;
       }
   
       public int getParticleSpeed(){
           int speed =1;
           return speed;
       }
   
       public int getParticleMaxLife(){
           int maxLife = 20;
           return maxLife;
       }
}
