package application;

public class Playstation4 extends VideoGame {
	
	Playstation4(Integer metascore, String gameTitle, String userScore, String releaseDate, String platform) {
		super(metascore, gameTitle, userScore, releaseDate, platform);
		
		platform = "Playstation 4";
	}
}
