package telecom.fa17.game;

import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import telecom.fa17.game.Exit;

public class Map {
	private TiledMap map;
<<<<<<< HEAD
	
	private List<Obstacle> obstacles;
	private List<Mobile> mobiles;
	
	public Map(String name) throws SlickException {
		   this.map = new TiledMap("resources/map/" + name + ".tmx");
		   obstacles = new LinkedList<Obstacle>();
		   mobiles = new LinkedList<Mobile>();
=======
	private List<Element> obstacles;
	private List<PNJ> adversaries;
	
	public Map(String name) throws SlickException {
		   this.map = new TiledMap("resources/map/" + name + ".tmx");
		   obstacles = new LinkedList<Element>();
		   adversaries = new LinkedList<PNJ>();
>>>>>>> origin/master
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
		
	public Position getNextPosition(int key, Position pos){
		float x = 0, y = 0;
		
		switch (key) {
    		case Input.KEY_UP:  
    			y = -getTileDimension();
    			break;
    		case Input.KEY_LEFT:
    			x = -getTileDimension();
    			break;
    		case Input.KEY_DOWN:
    			y = getTileDimension();
    			break;
    		case Input.KEY_RIGHT:
    			x = getTileDimension();
    			break;
		}
		return new Position(pos.getAbsciss() + x, pos.getOrdinate() + y);
	}
	
	// Met à jour les variables pour le mouvement
	public boolean findCollision(int key, Position pos){		
		Position nextPos = getNextPosition(key, pos);

		if (this.map.getTileId((int) (nextPos.getAbsciss() / getTileDimension()), (int) (nextPos.getOrdinate()  / getTileDimension()), 2) == 0){
			int i =0;
			if(!adversaries.isEmpty()){
				while(i < adversaries.size()){
					if(Position.equals(adversaries.get(i).getPosition(), nextPos)&&adversaries.get(i).isCrossable==false){
						return true;
					}
					i++;
				}
			}
			return false;
		}
		return true;
	}
	
<<<<<<< HEAD
	public void addMobile(Mobile mobile){
		this.mobiles.add(mobile);
	}
	
	// Return an Exit object if the next Coordinate of the player are an Exit
	public Exit getExitByCoordinate(float x, float y){
=======
	public Exit findExit(int key, Position pos){	
		Position nextPos = getNextPosition(key, pos);
>>>>>>> origin/master
		int i = 0;
		boolean found = false;
		
		while((i < obstacles.size() && !found)){
			if(Position.equals(obstacles.get(i).getPosition(), nextPos)){
				found = true;
			} else {
				i++;
			}
		}
		return (found) ? (Exit) obstacles.get(i) : null;
	}
	
	public void addAdversary(PNJ prm){
		adversaries.add(prm);
	}
	
	public List<PNJ> getAdversaries(){
		List<PNJ> retour = new LinkedList<PNJ>();
		int i = 0;
		
		while(i < adversaries.size()){
			if(adversaries.get(i).isAlive()){
				retour.add(adversaries.get(i));
			}
			i++;
		}
		return retour;
	}
}
