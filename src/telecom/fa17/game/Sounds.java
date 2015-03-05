package telecom.fa17.game;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
 
public class Sounds {

	Music backgroundMusic;
	
	public Sounds(String fileName) throws SlickException{
		this.backgroundMusic = new Music("resources/music/" + fileName);
		this.backgroundMusic.loop();
	}
	
	public void playSound(String fileName) throws SlickException {
		Music playSound = new Music("resources/music/" + fileName);
		playSound.play();
	}
	
	/* Sound played when the player wins NOT YET IMPLEMENTED 
	public void  WinSound() throws SlickException {
		StopBackgroundMusic();
		Music WinSound = new Music("resources/music/");
		WinSound.play();
	} */
	
	/* Sound played when the player looses NOT YET IMPLEMENTED 
	public void  GameOverSound() throws SlickException {
		StopBackgroundMusic();
		Music GameOverSound = new Music("resources/music/");
 		GameOverSound.play();
	}*/
	
	public void StopBackgroundMusic() {
		if(this.backgroundMusic.playing()){
			this.backgroundMusic.stop();
		}
	}

	public void setBackgroundMusic(String fileName) throws SlickException{
		StopBackgroundMusic();
		this.backgroundMusic = new Music("resources/music/" + fileName);
		this.backgroundMusic.loop();
	}	
}