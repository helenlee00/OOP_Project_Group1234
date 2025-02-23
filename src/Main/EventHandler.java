package Main;
public class EventHandler  {
    GamePanel gp;

    EventRect eventRect[][];

    int previousEventX, previousEventY;
    boolean canTouchEvent =true;


    public EventHandler(GamePanel gp) {
        this.gp = gp;

        eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];
        
        int col = 0;
        int row = 0;
        while (col < gp.maxWorldCol && row < gp.maxWorldRow){
            eventRect[col][row] = new EventRect();
            eventRect[col][row].x = 23;
            eventRect[col][row].y = 23;
            eventRect[col][row].width = 2;
            eventRect[col][row].height = 2;
            eventRect[col][row].eventRectDefaultX= eventRect[col][row].x;
            eventRect[col][row].eventRectDefaultY= eventRect[col][row].y;
            col++;
            if (col ==gp.maxWorldCol){
                col =0;
                row++;
            }
        }

    }
    public int worldX,worldY;

    public void checkEvent() {

        // CHECK IF THE PLAYER CHARACTER IS MORE THAN 1 TILE AWAY FROM THE LAST EVENT
        int xDistance = Math.abs(gp.player.worldX - previousEventX);    //horizontal or vertical, take greater one
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance,yDistance);
        if (distance > gp.tileSize) {
            canTouchEvent = true;           //can trigger the event again
        }

        if(canTouchEvent ==true){
            if (hit(23,19,"any") ==true) {damagePit(23,19,gp.dialogueState);}           //1444 /20
            if (hit(34,25,"any") ==true) {healingPool(25,35,gp.dialogueState);}
            if (hit(34,26,"any") ==true) {healingPool(25,35,gp.dialogueState);}
            if (hit(34,24,"any") ==true) {healingPool(25,35,gp.dialogueState);}
            if (hit(35,23,"any") ==true) {healingPool(25,35,gp.dialogueState);}
            if (hit(36,23,"any") ==true) {healingPool(25,35,gp.dialogueState);}
            if (hit(37,23,"any") ==true) {healingPool(25,35,gp.dialogueState);}
            if (hit(38,23,"any") ==true) {healingPool(25,35,gp.dialogueState);}
            if (hit(39,23,"any") ==true) {healingPool(25,35,gp.dialogueState);}
            if (hit(40,24,"any") ==true) {healingPool(25,35,gp.dialogueState);}
            if (hit(40,25,"any") ==true) {healingPool(25,35,gp.dialogueState);}
            if (hit(40,26,"any") ==true) {healingPool(25,35,gp.dialogueState);}


        }

        
    }
    public boolean hit(int col, int row, String reqDirection) {
        boolean hit = false;
        
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect[col][row].x = col * gp.tileSize + eventRect[col][row].x;
        eventRect[col][row].y = row * gp.tileSize + eventRect[col][row].y; // Use eventRow for y position
        
        if (gp.player.solidArea.intersects(eventRect[col][row]) && eventRect[col][row].eventDone == false ) {          // 547,849/20
            if (gp.player.direction.equals(reqDirection) || reqDirection.equals("any")) {
                hit = true;

                previousEventX =gp.player.worldX;
                previousEventY =gp.player.worldY;
            }
        }
    
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
        eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;
    
        return hit;
    }


  
   
   
    public void damagePit(int col, int row,int gameState) {
        gp.gameState = gameState;
        gp.playSE(6);
        gp.ui.currentDialogue = "You fall into a pit!";
        gp.player.life -=1 ;
        canTouchEvent =false;
    }


    public void healingPool(int col, int row,int gameState) {
        if(gp.keyH.enterPressed ==true) {
            gp.gameState = gameState;
            gp.player.attackCanceled = true;
            gp.playSE(2);
            gp.ui.currentDialogue = "You drink the water.\nYour life and mana have been recovered.";
            gp.player.mana = gp.player.maxMana;
            gp.aSetter.setSnowMan();
        }
    }
}
