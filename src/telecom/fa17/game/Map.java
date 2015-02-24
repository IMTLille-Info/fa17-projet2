package telecom.fa17.game;

import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Map {
	private TiledMap map;
	
	private List<Obstacle> obstacles;
	
	public Map(String name) throws SlickException {
		   this.map = new TiledMap("resources/map/" + name + ".tmx");
		   obstacles = new LinkedList<Obstacle>();
	}
	
	public int getTileDimension() {
		return this.map.getTileHeight();
	}
	
	public int getWidth(){
		int tileSize = this.getTileDimension();
		return (this.map.getWidth() * tileSize) - tileSize;
	}
	
	public int getHeight() {
		int tileSize = getTileDimension();
		return (this.map.getHeight() * tileSize) - tileSize;
	}
	
	public void renderBackground() {
		this.map.render(0, 0, 0);
	}
	
	public void renderForeground() {
		this.map.render(0, 0, 1);
	}
	
	public int getTileId(int x, int y, String name) {
		return map.getTileId(x, y, this.map.getLayerIndex(name));
	}
	
	public void addObstacle(Obstacle prm) {
		obstacles.add(prm);
	}
	
	public void removeObstacle(int index) {
		obstacles.remove(index);
	}
	
	public TiledMap getMap() {
		return map;
	}

	public void setMap(TiledMap map) {
		this.map = map;
	}

}