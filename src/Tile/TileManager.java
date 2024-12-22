package Tile;
import Main.GamePanel;
import Main.UltilityTool;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
// import main.GamePanel;
public final class TileManager {
    
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];


    public TileManager(GamePanel gp){
        this.gp = gp;

        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/maps/world01.txt");
    }

    public void getTileImage(){
        //try {
            //tile[0] = new Tile();
            //tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));    
            setup(0, "snow", false);

            /*tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));    
            tile[1].collision = true;*/
            setup(1, "wall", true);  

            /*tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));   
            tile[2].collision = true;*/   
            setup(2, "gift", true);
        
            /*tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png")); 
            tile[3].collision = true; */
            setup(3, "sand", true);   

            /*tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/catus.png")); 
            tile[4].collision = true;*/
            setup(4, "tree", true);    

            /*tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/pathway.png")); */
            setup(5, "pathway", false);
            setup(6, "water", true); 
            
            try {
                tile[7] = new Tile();
                tile[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles_interactive/trunk.png"));
                tile[7].collision = true;
            } catch (IOException e) {
                System.out.println("Error loading tile image: trunk");
                e.printStackTrace();
            }

   
        /* } catch (IOException e) {
            e.printStackTrace();
        } */
    }

    public void setup(int index, String imageName, boolean collision)
    {
        UltilityTool uTool = new UltilityTool();
        try{
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;

        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    
    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            if (is == null) {
                System.out.println("Cannot find map01.txt!");
                return;
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
    
            int col = 0;
            int row = 0;
    
            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();
                if (line == null) break;
    
                String[] numbers = line.split(" ");
                for (col = 0; col < gp.maxWorldCol; col++) {
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                   
                }
                if(col == gp.maxWorldCol){
                col = 0;
                row++;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    public void draw(Graphics2D g2){

        int worldCol = 0;
        int worldRow = 0;


        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if(worldX + gp.tileSize > gp.player.worldX -gp.player.screenX &&
               worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
               worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
               worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){

                        g2.drawImage(tile[tileNum].image ,screenX,screenY,null);
                    }
           
            worldCol++;
    

            if(worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }
        }
    }


}
