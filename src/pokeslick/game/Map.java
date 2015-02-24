package pokeslick.game;

import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Map {
	private TiledMap map;
	
	private List<Exit> exits;
	
	public Map(String name) throws SlickException {
		   this.map = new TiledMap("resources/map/" + name + ".tmx");
		   exits = new LinkedList<Exit>();
	}
	
	public void chargeMap(String name) throws SlickException 
	{
		this.map = new TiledMap("resources/map/" + name + ".tmx");
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
		exits.add(prm);
	}
	
	public void removeExit(int prm)
	{
		exits.remove(prm);
	}
	
	// Return an Exit object if the next Cooridnate of the player are an Exit
	public Exit getExitByCoordinate(float x, float y)
	{
		int i = 0;
		boolean found = false;
		
		while((i < exits.size() && !found))
		{
			if((exits.get(i).getAbsciss() == x) && (exits.get(i).getOrdinate() == y))
			{
				found = true;
			} else {
				i++;
			}
		}
		return (found) ? exits.get(i) : null;
	}
}
