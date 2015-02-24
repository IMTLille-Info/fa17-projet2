package telecom.fa17.game;

import java.util.LinkedList;
import java.util.List;

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
	
	public int getTileDimension()
	{
		return this.map.getTileHeight();
	}
	
	public int getWidth()
	{
		int tileSize = this.getTileDimension();
		return (this.map.getWidth() * tileSize) - tileSize;
	}
	
	public int getHeight()
	{
		int tileSize = getTileDimension();
		return (this.map.getHeight() * tileSize) - tileSize;
	}
	
	public void renderBackground()
	{
		this.map.render(0, 0, 0);
	}
	
	public void renderForeground()
	{
		this.map.render(0, 0, 1);
	}
	
	public int getTileId(int x, int y, String name)
	{
		return map.getTileId(x, y, this.map.getLayerIndex(name));
	}
	
	public void addExit(Exit prm)
	{
		obstacles.add(prm);
	}
	
	public void removeExit(int prm)
	{
		obstacles.remove(prm);
	}
	
	// Return an Exit object if the next Cooridnate of the player are an Exit
	public Exit getExitByCoordinate(float x, float y)
	{
		int i = 0;
		boolean found = false;
		
		while((i < obstacles.size() && !found))
		{
			if((obstacles.get(i).getAbsciss() == x) && (obstacles.get(i).getOrdinate() == y))
			{
				found = true;
			} else {
				i++;
			}
		}
		return (found) ? (Exit) obstacles.get(i) : null;
	}
	
	public TiledMap getMap() {
		return map;
	}

	public void setMap(TiledMap map) {
		this.map = map;
	}

}
