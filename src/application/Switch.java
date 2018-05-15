package application;

public class Switch extends VideoGame{
	
	Switch(Integer metascore, String gameTitle, String userScore, String releaseDate, String platform) {
		super(metascore, gameTitle, userScore, releaseDate, platform);
		
		platform = "Switch";
	}
}
