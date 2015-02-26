package telecom.fa17.game;

import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import telecom.fa17.game.Exit;

public class Map {
	private TiledMap map;
	private List<Obstacle> obstacles;
	
	public Map(String name) throws SlickException {
		   this.map = new TiledMap("resources/map/" + name + ".tmx");
		   obstacles = new LinkedList<Obstacle>();
	}
	
	public int getTileDimension(){
		return this.map.getTileHeight();
	}
	
	public int getWidth(){
		int tileSize = this.getTileDimension();
		return (this.map.getWidth() * tileSize) - tileSize;
	}
	
	public int getHeight(){
		int tileSize = getTileDimension();
		return (this.map.getHeight() * tileSize) - tileSize;
	}
	
	public void renderBackground(){
		this.map.render(0, 0, 0);
	}
	
	public void renderForeground(){
		this.map.render(0, 0, 1);
	}
	
	public int getTileId(int x, int y, String name){
		return map.getTileId(x, y, this.map.getLayerIndex(name));
	}
	
	public void addExit(Exit prm){
		obstacles.add(prm);
	}
	
	public void removeExit(int prm){
		obstacles.remove(prm);
	}
		
	private Position getNextPosition(int key, Position pos)
	{
		int x = 0, y = 0;
		
		switch (key) {
    		case Input.KEY_UP:  
    			y = -getTileDimension();
    		case Input.KEY_LEFT:
    			x = -getTileDimension();
    		case Input.KEY_DOWN:
    			y = getTileDimension();
    		case Input.KEY_RIGHT:
    			x = getTileDimension();
		}
		return new Position(pos.getAbsciss() + x, pos.getOrdinate() + y);
	}
	
	// Met à jour les variables pour le mouvement
	public boolean findCollision(int key, Position pos) 
	{		
		Position nextPos = getNextPosition(key, pos);
		return (this.map.getTileId((int) (nextPos.getAbsciss() / getTileDimension()), (int) (nextPos.getOrdinate()  / getTileDimension()), 2) == 0) 
			? false : true;	
	}
	
	public Exit findExit(int key, Position pos)
	{	
		Position nextPos = getNextPosition(key, pos);
		int i = 0;
		boolean found = false;
		
		while((i < obstacles.size() && !found)){
			if((obstacles.get(i).getPosition() == nextPos)){
				found = true;
			} else {
				i++;
			}
		}
		return (found) ? (Exit) obstacles.get(i) : null;
	}
}