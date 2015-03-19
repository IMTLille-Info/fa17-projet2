package telecom.fa17.game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import telecom.fa17.game.Exit;

public class Map {
	private TiledMap map;
	protected List<Exit> exit;
	private List<PNJ> adversaries;
	private Position hitPosition;
	private boolean playerHit;
	private String musicFilename;
	private boolean isArena;

	public Map(String name, String mscFile, boolean isArena) throws SlickException {
		this.map = new TiledMap("resources/map/" + name + ".tmx");
		this.exit = new ArrayList<Exit>();
		this.adversaries = new ArrayList<PNJ>();
		this.hitPosition = new Position(0, 0);
		this.playerHit = false;
		this.musicFilename = mscFile;
		this.isArena = isArena;
	}

	public int getTileDimension() {
		return this.map.getTileHeight();
	}

	public int getWidth() {
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

	public String getMusicFilename() {
		return this.musicFilename;
	}

	public void addExit(Exit prm) {
		this.exit.add(prm);
	}

	public void removeExit(int prm) {
		this.exit.remove(prm);
	}
	
	public void removePNJ(PNJ prm) {
		this.adversaries.remove(prm);
	}

	public int getExitListSize(){
		return this.exit.size();
	}
	
	public Position getNextPosition(int key, Position pos) {
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
	public boolean findCollision(int key, Position pos) {
		Position nextPos = getNextPosition(key, pos);

		if (this.map.getTileId(
				(int) (nextPos.getAbsciss() / getTileDimension()),
				(int) (nextPos.getOrdinate() / getTileDimension()), 2) == 0) {
			if (!adversaries.isEmpty()) {
				for(PNJ el : adversaries) {
					if (el.getPosition().equals(nextPos) && !el.isCrossable()) {
						return true;
					}
				}
			}
			return false;
		}
		return true;
	}

	public Exit findExit(int key, Position pos) {
		Position nextPos = getNextPosition(key, pos);
		for (Exit each : exit) {
			if (each.getPosition().equals(nextPos)) { // each.isCloseTo(nextPos) ?
				return each;
			}
		}
		return null;
	}

	public void addAdversary(PNJ prm) {
		adversaries.add(prm);
	}

	public List<PNJ> getAliveAdversaries() {
		List<PNJ> retour = new ArrayList<PNJ>();

		for(PNJ el : adversaries) {
			if (el.isAlive()) {
				retour.add(el);
			}
		}
		return retour;

	}
	
	public void removeAdversary(PNJ monster){
		this.adversaries.remove(monster);
	}
	
	public boolean allAdversariesKilled(){
		return adversaries.isEmpty();
	}

	public void setHitZone(Position position) {
		hitPosition = position;
		playerHit = true;
	}

	public Position getHitZone() {
		return hitPosition;
	}

	/**
	 * return if a player was hit on the map and change the status if true
	 * 
	 * @return hit state
	 */
	public boolean playerHit() {
		return playerHit;
	}

	public void stopHit() {
		playerHit = false;
	}
	
	void setArena(boolean isArena) {
		this.isArena = isArena;
	}
	
	boolean isArena() {
		return isArena;
	}
}
