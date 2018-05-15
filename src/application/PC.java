package application;

public class PC extends VideoGame {
	
	PC(Integer metascore, String gameTitle, String userScore, String releaseDate, String platform) {
		super(metascore, gameTitle, userScore, releaseDate, platform);
		
		platform = "PC";
	}
}
