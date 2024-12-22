    package Entity ;

import Main.GamePanel; 
import Main.KeyHandler;
import Object.OBJ_Snowball;
import Object.OBJ_Sword_Normal;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
//import java.io.IOException;
//import javax.imageio.ImageIO;
public final class Player extends Entity {
    KeyHandler keyH; 
    
    public final int screenX;
    public final int screenY;
    // int standCounter = 0;
    public boolean attackCanceled = false;
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int maxInventorySize = 20;


    public Player(GamePanel gp, KeyHandler keyH) {

        super(gp);

        this.keyH = keyH;
        
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();     //p6: chỉnh cho con người k chạm vào tường, đo body tạo size
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;
        //ATTACK AREA

        //attackArea.width = 36;
        //attackArea.height = 36;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
        setItems();
    }
    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        // worldY = gp.tileSize * 27;
        speed = 4;
        direction = "down";

        //PLAYER STATUS
        level =1;
        maxLife = 6;
        life = maxLife;
        maxMana = 5;
        mana = maxMana;
        ammo =10;
        strength=1; 		// The more strength he has, the more damage he gives
        dexterity =1;		// The more dexterity he has, the less damage he receives
        exp=0;
        nextLevelExp=5;
        //coin = 0;

        currentWeapon = new OBJ_Sword_Normal(gp);		
        // currentShield = new OBJ_Shield_Wood(gp);
        projectile = new OBJ_Snowball(gp);
        attack = getAttack();		// The total attack value is decided by strength and weapon
        // defense = getDefense();	// The total defense value is described by dexterity and shield
    }
    public void setDefaultPositions(){
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        direction = "down";
    }
    public void restoreLifeAndMan(){
        life = maxLife;
        mana = maxMana;
        invincible = false;


    }
    public void setItems()
    {   
        inventory.clear();
        inventory.add(currentWeapon);
        // inventory.add(currentShield);
        // inventory.add(new OBJ_Key(gp));
    }

    public int getAttack()	{
        attackArea = currentWeapon.attackArea;
        return attack = strength * currentWeapon.attackValue;
    
    }
    
    // public int getDefense(){
    //     return defense = dexterity * currentShield.defenseValue;
    //  }
    
    
    public void getPlayerImage()
    {
        up1 = setup("/player/up1",gp.tileSize,gp.tileSize*2);
        up2 = setup("/player/up2",gp.tileSize,gp.tileSize*2);   
        down1 = setup("/player/down1",gp.tileSize,gp.tileSize*2);
        down2 = setup("/player/down2",gp.tileSize,gp.tileSize*2);
        left1 = setup("/player/left1",gp.tileSize*2,gp.tileSize);
        left2 = setup("/player/left2",gp.tileSize*2,gp.tileSize);
        right1 = setup("/player/right1",gp.tileSize*2,gp.tileSize);
        right2 = setup("/player/right2",gp.tileSize*2,gp.tileSize);
    }
    public void getPlayerAttackImage()
    {
        if(currentWeapon.type == type_sword){
            attackUp1 = setup("/player/attack_up_1",gp.tileSize,gp.tileSize*2);
            attackUp2 = setup("/player/attack_up_2",gp.tileSize,gp.tileSize*2);
            attackDown1 = setup("/player/attack_down_1",gp.tileSize,gp.tileSize*2);
            attackDown2 = setup("/player/attack_down_2",gp.tileSize,gp.tileSize*2);
            attackLeft1 = setup("/player/attack_left_1",gp.tileSize*2,gp.tileSize);
            attackLeft2 = setup("/player/attack_left_2",gp.tileSize*2,gp.tileSize);
            attackRight1 = setup("/player/attack_right_1",gp.tileSize*2,gp.tileSize);
            attackRight2 = setup("/player/attack_right_2",gp.tileSize*2,gp.tileSize);

        }
        if(currentWeapon.type == type_axe){
            attackUp1 = setup("/player/axe_up_1",gp.tileSize,gp.tileSize*2);
            attackUp2 = setup("/player/axe_up_2",gp.tileSize,gp.tileSize*2);
            attackDown1 = setup("/player/axe_down_1",gp.tileSize,gp.tileSize*2);
            attackDown2 = setup("/player/axe_down_2",gp.tileSize,gp.tileSize*2);
            attackLeft1 = setup("/player/axe_left_1",gp.tileSize*2,gp.tileSize);
            attackLeft2 = setup("/player/axe_left_2",gp.tileSize*2,gp.tileSize);
            attackRight1 = setup("/player/axe_right_1",gp.tileSize*2,gp.tileSize);
            attackRight2 = setup("/player/axe_right_2",gp.tileSize*2,gp.tileSize);

        }
        
    }

    public void update() {
        if(attacking == true)
        {
                attacking();
        }
        else   if(keyH.upPressed==true|| keyH.downPressed ==true || keyH.leftPressed == true|| keyH.rightPressed == true || keyH.enterPressed == true){
                if (keyH.upPressed == true) {
                    direction = "up";
                }
                else if (keyH.downPressed == true) {
                    direction = "down";
                }
                else if (keyH.leftPressed == true) {
                    direction = "left";
                }
                else if (keyH.rightPressed == true) {
                    direction = "right";
                 }

                // CHECK TILE COLLISION
                 collisionOn = false;
                 gp.cChecker.checkTile(this);

                // CHECK OBJECT COLLISION
                int objIndex = gp.cChecker.checkObject(this, true);
                pickUpObject(objIndex);

                //CHECK NPC COLLISION
                int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
                interactNPC(npcIndex);

                //CHECK MONSTER COLLISION
                int monsterIndex = gp.cChecker.checkEntity(this, gp.snowman);
                contactMonster(monsterIndex);

                //CHECK INTERACTIVE TILE COLLISION
                int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);

                // CHECK EVENT 
                gp.eHandler.checkEvent();
                
                 // IF COLLISION IS FALSE, PLAYER CAN MOVE
                 if(collisionOn == false && keyH.enterPressed == false){
                    
                    switch(direction){
                        case"up":      worldY -= speed; break;
                            
                        case "down":   worldY += speed;  break;
                    
                        case "left":   worldX -= speed;  break;
                        
                        case "right":  worldX += speed;  break;
                    }
                 }

                 if(keyH.enterPressed == true  && attackCanceled == false){
                    gp.playSE(6);
                    attacking = true;
                    spriteCounter = 0;
                }
                
                attackCanceled = false;
                

                gp.keyH.enterPressed = false;
                spriteCounter++;
                if(spriteCounter >12){
                    if(spriteNum==1){
                        spriteNum=2;
                }
                    else if (spriteNum==2){
                        spriteNum =1;
                }
                    spriteCounter = 0;
                }
            }
            if(gp.keyH.shotKeyPressed == true && projectile.alive == false
            && shotAvailableCounter == 30 && projectile.haveResource(this) == true) {
                // SET DEFAULT COORDINATES DIRECTION AND USER
                projectile.set(worldX, worldY, direction,true, this);
                //SUBTRACT THE COST (MANA, AMMO ETC.)
                projectile.subtractResource(this);
                // ADD IT TO THE LEFT
                gp.projectileList.add(projectile);
                shotAvailableCounter =0;
                gp.playSE(10);
            }
        
           //This needs to be outside if key if statement !
           if ( invincible == true ){
                invincibleCounter++;
                if(invincibleCounter >60){
                    invincible = false;
                    invincibleCounter = 0;

                }
           }
           if (shotAvailableCounter < 30) {
            shotAvailableCounter++;
            }
            if(life > maxLife) {
                life = maxLife;
            }
            if(mana > maxMana) {
                mana = maxMana;
            }
            if(life <= 0){
                gp.gameState = gp.gameOverState;
                gp.playSE(11);
            }
                
    }
    public void attacking(){
        spriteCounter++;
        if ( spriteCounter <= 5){
            spriteNum = 1;
        }
        if(spriteCounter >5 && spriteCounter <= 25){
            spriteNum=2;

            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeith = solidArea.height;
            //Adjust player's world x/y for the attackarea 
            switch ( direction){
                case "up" : worldY -= attackArea.height ;break;
                case "down" : worldY += attackArea.height ;break;
                case "left" : worldX -= attackArea.width ;break;
                case "right" : worldX += attackArea.width ;break;
            }
            // attack area becomes solid area
            solidArea.width = attackArea.width;
            solidArea.height= attackArea.height;
            // check monster collision with the updated world
            int monsterIndex = gp.cChecker.checkEntity(this, gp.snowman);
            damageMonster (monsterIndex, attack);

            int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
            damageInteractiveTile(iTileIndex);

            // after checking collision, restore the original data
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.height = solidAreaHeith;
            solidArea.width = solidAreaWidth;
        }
        if(spriteCounter >25){
            spriteNum =1;
            spriteCounter = 0;
            attacking = false;
        }

    }
    public void pickUpObject (int i) {
        if(i != 999) {
        String objectName = gp.obj[i].name;
        
        switch (objectName) {
            case "Chest":
                    gp.ui.gameFinished = true ;
                    gp.stopMusic();
                    
                    break;
            }
        //PICKUP ONLY ITEMS
        if(gp.obj[i].type == type_pickupOnly) {
            gp.obj[i].use(this);
            gp.obj[i] = null;
        }
        // INVENTORY ITEMS
        else {
            String text;
        if( inventory.size() != maxInventorySize){
            inventory.add(gp.obj[i]);
            gp.playSE(i);
            text = "Got a "+ gp.obj[i].name +"!";
            
    
            switch (objectName) {
            case "Chest":
                text = null; // Không hiển thị thông báo nếu là Chest
            }
        }
        else {
            text = "You cannot carry any more";
            
        }
        gp.ui.addMessage(text);
        gp.obj[i] = null;
        }
        }
    }

    public void interactNPC(int i)
    {
        if(gp.keyH.enterPressed == true){
            if(i != 999){    
            attackCanceled = true;
            gp.gameState = gp.dialogueState;
            gp.npc[i].speak();
                         
            }
        }
    }
    public void contactMonster(int i){
        if( i != 999){
            if ( invincible == false && gp.snowman[i].dying == false){

                gp.playSE(5);

                int damage = gp.snowman[i].attack - defense;
                if(damage < 0)
                {
                    damage = 0;
                }

                life -= damage;
                invincible = true;
            }
            
        }
    }
    public  void damageMonster(int i, int attack){
        if( i!= 999){
            if(gp.snowman[i].invincible == false){

                gp.playSE(4);

                int damage = attack - gp.snowman[i].defense;
                if(damage < 0)
                {
                    damage = 0;
                }

                gp.snowman[i].life -= damage;
                gp.ui.addMessage(damage + " damage!");

                gp.snowman[i].invincible = true;
                gp.snowman[i].damageReaction();

                if(gp.snowman[i].life <=0 ){
                    gp.snowman[i].dying = true;
                    gp.ui.addMessage("killed the " + gp.snowman[i].name + "!");
                    gp.ui.addMessage("Exp + " + gp.snowman[i].exp);
                    exp += gp.snowman[i].exp;
                    checkLevelUp();
                }
            }
        }
    }
    
    //đặt đkien để mỗi axe có thể chặt cây
    public void damageInteractiveTile(int i){

        if (i != 999 && gp.iTile[i].destructible == true && gp.iTile[i].isCorrectItem(this) == true
                && gp.iTile[i].invincible == false){
            gp.iTile[i].playSE();       // nghe sound khi chat cay
            gp.iTile[i].life--; 
            gp.iTile[i].invincible=true;

        generateParticle(gp.iTile[i], gp.iTile[i]);

        if(gp.iTile[i].life ==0){
            gp.iTile[i]= gp.iTile[i].getDestroyedForm();
        }

        }
    }

    public void checkLevelUp()

    {
        if(exp >= nextLevelExp)
        {
            level++;
            nextLevelExp = nextLevelExp*2;
            maxLife += 2;
            strength++;
            dexterity++;
            attack = getAttack();
            // defense = getDefense();
            gp.playSE(7);
            gp.gameState = gp.dialogueState;
            gp.ui.currentDialogue = "You are level" + level + "now\n" + "you feel stronger!";
        }
    }
    public void selectItem(){
        int itemIndex = gp.ui.getItemIndexOnSlot();
        if ( itemIndex < inventory.size()){
            Entity selectedItem = inventory.get(itemIndex);
            if(selectedItem.type == type_sword || selectedItem.type == type_axe ){
                currentWeapon = selectedItem;
                attack = getAttack();
                getPlayerAttackImage();;
            }
            // if(selectedItem.type == type_shield){
            //     currentShield = selectedItem;
            //     defense = getDefense();

            // }
            if(selectedItem.type == type_consumable){
                selectedItem.use (this);
                inventory.remove(itemIndex);

            }
        }
    }
    public void draw( Graphics2D g2) {
          //g2.setColor(Color.white);
          //g2.fillRect( worldX,worldY, gp.tileSize, gp.tileSize);
          BufferedImage image = null;
          int tempScreenX = screenX ;
          int tempScreenY = screenY;
          switch ( direction){
          case "up":
            if (attacking == false){
                if ( spriteNum ==1 ){image = up1;}
                if ( spriteNum ==2 ){image = up2;}}
            if (attacking == true){
                    tempScreenY = screenY - gp.tileSize ;
                    if ( spriteNum ==1 ){image = attackUp1;}
                    if ( spriteNum ==2 ){image = attackUp2;}}    
                break ;
          case "down":
            if (attacking == false){
                if ( spriteNum ==1 ){image = down1;}
                if ( spriteNum ==2 ){image = down2;}}
            if (attacking == true){
                if ( spriteNum ==1 ){image = attackDown1;}
                if ( spriteNum ==2 ){image = attackDown2;}} 
                break;
          case "left":
            if (attacking == false){
                if ( spriteNum ==1 ){image = left1;}
                if ( spriteNum ==2 ){image = left2;}}
            if (attacking == true){
                tempScreenX = screenX - gp.tileSize;
                if ( spriteNum ==1 ){image = attackLeft1;}
                if ( spriteNum ==2 ){image = attackLeft2;}}
                break;
          case "right":
            if (attacking == false){
                if ( spriteNum ==1 ){image = right1;}
                if ( spriteNum ==2 ){image = right2;}}
            if (attacking == true){
                if ( spriteNum ==1 ){image = attackRight1;}
                if ( spriteNum ==2 ){image = attackRight2;}}
                break;
          }
          if ( invincible == true ){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));

          }
          g2.drawImage(image,tempScreenX,tempScreenY,null);
          // Reset alpha
          g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        //DEBUG
          /*g2.setFont(new Font("Arial", Font.PLAIN,26));
          g2.setColor(Color.WHITE);
          g2.drawString("Invincible "+ invincibleCounter, 10,400);*/
    }
}






