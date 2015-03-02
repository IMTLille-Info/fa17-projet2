package telecom.fa17.game;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
 
public class Sounds {

	Music backgroundMusic;
	
	public Sounds(){}
	
	/********************** Monsters & Players Sounds
	** Peut être utilisée 
	** Lorsqu'un joueur/monstre meurt
	** Lorsqu'un joueur/ monstre attaque - différent selon arme
	** Lorsqu'un joueur ramasse un objet
	** Sons différents possibles selon monstres
	**
	** format String play : "resources/music/exemple.ogg"*/
	public void playSound(String play) throws SlickException {
		Music playSound = new Music(play);
		playSound.play();
	}
	
	/********************** Winner/Loser Sounds */
		public void  WinSound() throws SlickException {
		StopBackgroundMusic(this.backgroundMusic);
		Music WinSound = new Music("resources/music/exemple.ogg");
		WinSound.play();
	}
	
	public void  GameOverSound() throws SlickException {
		StopBackgroundMusic(this.backgroundMusic);
		Music GameOverSound = new Music("resources/music/exemple.ogg");
 		GameOverSound.play();
	}
	
	/********************** Background Music */
		public void StopBackgroundMusic(Music currentMusic) {
		if(currentMusic.playing()){
			currentMusic.stop();
		}
	}

	// son Outdoor/Indoor différent - jouer sur ambiance - exemple String backgroundMusic : resources/music/exemple.ogg
	public void setBackgroundMusic(String backgroundMusic) throws SlickException{
		StopBackgroundMusic(this.backgroundMusic);
		this.backgroundMusic = new Music (backgroundMusic);
		this.backgroundMusic.loop();
	}	
}