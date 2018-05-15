package application;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class VideoGame {
	
	private final SimpleIntegerProperty metascore;
	private final SimpleStringProperty gameTitle;
	private final SimpleStringProperty userScore;
	private final SimpleStringProperty releaseDate;
	private final SimpleStringProperty platform;
	
	public VideoGame(Integer metascore, String gameTitle, String userScore, String releaseDate, String platform) {
		this.metascore = new SimpleIntegerProperty(metascore);
		this.gameTitle = new SimpleStringProperty(gameTitle);
		this.userScore = new SimpleStringProperty(userScore);
		this.releaseDate = new SimpleStringProperty(releaseDate);
		this.platform = new SimpleStringProperty(platform);
	}
	// metascore set/get
	public void setMetascore(Integer metaScore) {
		metascore.set(metaScore);
	}
	public Integer getMetascore() {
		return metascore.get();
	}
	public IntegerProperty metascoreProperty() {
		return metascore;
	}
	
	// gameTitle set/get
	public void setGameTitle(String title) {
		gameTitle.set(title);
	}
	public String getGameTitle() {
		return gameTitle.get();
	}
	public StringProperty gameTitleProperty() {
		return gameTitle;
	}
	
	// userScore set/get
	public void setUserScore(String score) {
		userScore.set(score);
	}
	public String getUserScore() {
		return userScore.get();
	}
	public StringProperty userScoreProperty() {
		return userScore;
	}
	
	// releaseDate set/get
	public void setReleaseDate(String date) {
		releaseDate.set(date);
	}
	public String getReleaseDate() {
		return releaseDate.get();
	}
	public StringProperty releaseDateProperty() {
		return releaseDate;
	}
	
	// platform set/get
	public void setPlatform(String platform) {
		this.platform.set(platform);
	}
	public String getPlatform() {
		return platform.get();
	}
	public StringProperty platformProperty() {
		return platform;
	}
}
