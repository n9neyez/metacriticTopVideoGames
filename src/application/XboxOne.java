package application;

public class XboxOne extends VideoGame {
	
	XboxOne(Integer metascore, String gameTitle, String userScore, String releaseDate, String platform) {
		super(metascore, gameTitle, userScore, releaseDate, platform);
		
		platform = "Xbox One";
	}
}
