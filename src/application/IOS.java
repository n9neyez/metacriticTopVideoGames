package application;

public class IOS extends VideoGame{
	
	IOS(Integer metascore, String gameTitle, String userScore, String releaseDate, String platform) {
		super(metascore, gameTitle, userScore, releaseDate, platform);
		
		platform = "iOS";
	}
}
