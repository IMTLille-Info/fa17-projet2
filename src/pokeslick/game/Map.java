package pokeslick.game;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Map {
	private TiledMap map;
	
	public Map(String name) throws SlickException {
		   this.map = new TiledMap("resources/map/" + name + ".tmx");
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
}
