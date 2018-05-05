package graetap3;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;

import javax.swing.JFrame;
import javax.swing.JPanel;
 
public class Mapa{
 
    /**klasa , kt�ra wczytuje map� z pliku 
     * 
     * 
     * */
 
    private int tileSize;
    private int[][] map;
    private int mapWidth;
    private int mapHeight;
 
    Konfiguracja conf = new Konfiguracja();
    /**
     * konstruktor mapy
     * @param s nazwa mapy
     * @param tileSize wielkosc jednej cyfry
     */
    public Mapa(String s, int tileSize){
         this.tileSize = tileSize;
 
         try{
        	 FileInputStream fstream = new FileInputStream(s+".txt");
             BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
             mapWidth = Integer.parseInt(br.readLine());
             mapHeight = Integer.parseInt(br.readLine());
 
             map = new int[mapHeight][mapWidth];
             
             
             
             String delimiters = " ";
             for(int row = 0; row <mapHeight; row++){
                 String line = br.readLine();
                 String[] tokens = line.split(delimiters);
                 for(int col = 0; col <mapHeight; col++){
                     map[row][col] = Integer.parseInt(tokens[col]);
                 }
             }
 
         }
         catch(Exception e){
        	 System.out.println("error");
         }
    }
 
    public void update(){
 
    }
 
    /** metoda kolorujaca mape 
     * w zale�no�ci jak� cyfr� zawiera mapa
     * 0-czarny
     * 1-niebieski
     * 2-zielony
     * @param g
     */
    public void draw(Graphics g){
        for(int row = 0; row <mapHeight; row++){
            for(int col = 0; col <mapHeight; col++){
                int rc = map[row][col];
                if(rc == 0){
                    g.setColor(Color.BLACK);
                }
                if(rc == 1){
                    g.setColor(Color.BLUE);
                }
                if( rc==2) {
                	g.setColor(Color.GREEN);
                }
                g.fillRect(col*tileSize, row*tileSize, tileSize, tileSize);
            }
        }
    }
    
    public double getUnscaledWidth()
    {
        return mapWidth;
    }
    
    public double getUnscaledHeight()
    {
        return mapHeight;
    }
    
    public void setTileSize(int newTileSize)
    {
        tileSize = newTileSize;
    }
    
    public int getTileSize()
    {
        return tileSize;
    }
}
 