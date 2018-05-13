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
	private final SimpleDoubleProperty userScore;
	private final SimpleStringProperty releaseDate;
	
	public VideoGame(Integer metascore, String gameTitle, Double userScore, String releaseDate) {
		this.metascore = new SimpleIntegerProperty(metascore);
		this.gameTitle = new SimpleStringProperty(gameTitle);
		this.userScore = new SimpleDoubleProperty(userScore);
		this.releaseDate = new SimpleStringProperty(releaseDate);
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
	public void setUserScore(Double score) {
		userScore.set(score);
	}
	public Double getUserScore() {
		return userScore.get();
	}
	public DoubleProperty userScoreProperty() {
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
}
