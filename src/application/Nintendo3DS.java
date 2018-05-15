package application;

public class Nintendo3DS extends VideoGame{
	
	Nintendo3DS(Integer metascore, String gameTitle, String userScore, String releaseDate, String platform) {
		super(metascore, gameTitle, userScore, releaseDate, platform);
		
		platform = "Nintendo 3DS";
	}
}
