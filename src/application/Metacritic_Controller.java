package application;

import java.io.IOException;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.sql.*;
import com.microsoft.sqlserver.jdbc.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
//import org.jsoup.nodes.Attribute;
//import org.jsoup.nodes.Attributes;
//import org.jsoup.nodes.Entities;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Arrays;

public class Metacritic_Controller {
	private static final String CONNECTION_URL = "jdbc:sqlserver://localhost:1433;" +
			"databaseName=MetacriticDB;integratedSecurity=true;";
	
	// Create ObservableList to display in the tableView for JavaFX
	ObservableList<VideoGame> videoGameList = FXCollections.observableArrayList();
	ObservableList<VideoGame> playstationList = FXCollections.observableArrayList();
	ObservableList<VideoGame> xboxList = FXCollections.observableArrayList();
	ObservableList<VideoGame> switchList = FXCollections.observableArrayList();
	ObservableList<VideoGame> pcList = FXCollections.observableArrayList();
	ObservableList<VideoGame> nintendo3DSList = FXCollections.observableArrayList();
	ObservableList<VideoGame> iOSGameList = FXCollections.observableArrayList();

	// Exit Icon, handle hover effects and closing app
    @FXML
    private ImageView exit_icon;
    @FXML
    void exit_app(MouseEvent event) {
    	Platform.exit();
    	System.exit(0);
    }
    @FXML
    void exit_onhover(MouseEvent event) {
    	ColorAdjust color = new ColorAdjust();
    	color.setBrightness(1.0);
    	exit_icon.setEffect(color);
    }
    @FXML
    void exit_onleave(MouseEvent event) {
    	ColorAdjust color = new ColorAdjust();
    	color.setBrightness(0.0);
    	exit_icon.setEffect(color);
    }
    //END Exit Icon
    
    @FXML
    private TableView<VideoGame> main_table;

    @FXML
    private TableColumn<VideoGame, Number> metascore_col;

    @FXML
    private TableColumn<VideoGame, String> gameTitle_col;

    @FXML
    private TableColumn<VideoGame, String> userScore_col;

    @FXML
    private TableColumn<VideoGame, String> releaseDate_col;
    
    @FXML
    private TableColumn<VideoGame, String> platform_col;
    
    @FXML
    private RadioButton radio_playstation4;

    @FXML
    private ToggleGroup toggle_platform;

    @FXML
    private RadioButton radio_xboxOne;

    @FXML
    private RadioButton radio_switch;

    @FXML
    private RadioButton radio_pc;

    @FXML
    private RadioButton radio_3DS;

    @FXML
    private RadioButton radio_iOS;
    
    @FXML
    private RadioButton radio_showAll;
    
    @FXML
    private Button btn_showTableData;
    
    @FXML
    void show_table(MouseEvent event) throws IOException {

		// Declare the JDBC objects.
		Connection con = null;
		Statement stmt = null;
		
		// Create ObservableList to display in the tableView for JavaFX
		ObservableList<VideoGame> videoGameList = FXCollections.observableArrayList();
		ObservableList<VideoGame> playstationList = FXCollections.observableArrayList();
		ObservableList<VideoGame> xboxList = FXCollections.observableArrayList();
		ObservableList<VideoGame> switchList = FXCollections.observableArrayList();
		ObservableList<VideoGame> pcList = FXCollections.observableArrayList();
		ObservableList<VideoGame> nintendo3DSList = FXCollections.observableArrayList();
		ObservableList<VideoGame> iOSGameList = FXCollections.observableArrayList();
		
		metascore_col.setCellValueFactory(cellData -> cellData.getValue().metascoreProperty());
		gameTitle_col.setCellValueFactory(cellData -> cellData.getValue().gameTitleProperty());
		userScore_col.setCellValueFactory(cellData -> cellData.getValue().userScoreProperty());
		releaseDate_col.setCellValueFactory(cellData -> cellData.getValue().releaseDateProperty());
		platform_col.setCellValueFactory(cellData -> cellData.getValue().platformProperty());
		

		
		//Document ps4Document = Jsoup.connect("http://www.metacritic.com/browse/games/score/metascore/all/ps4?sort=desc").get();
		
		//Elements ps4metaScores = ps4Document.select("div[class=metascore_w small game positive]"); // get ps4 metascores
		
		
        	try {
        		// Establish the connection.
        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            		con = DriverManager.getConnection(CONNECTION_URL);
            		
            		/*
            		// Create VideoGame Table
            		String createVideoGameTable = "CREATE TABLE VideoGame" + 
            				"(GameID int identity not null" + 
            				",Metascore int" + 
            				",GameTitle varchar(50) not null" + 
            				",UserScore varchar(10)" + 
            				",ReleaseDate varchar(50)" + 
            				",Platform varchar(20)" +
            				",CONSTRAINT PK_VideoGame_GameID PRIMARY KEY (GameID)" + 
            				");";
            		stmt = con.createStatement();
            		stmt.execute(createVideoGameTable);
            		
            		// Create PS4 Table
            		String createPlaystation4 = "CREATE TABLE Playstation4" + 
            				"(GameID int identity not null" + 
            				",Metascore int" + 
            				",GameTitle varchar(50) not null" + 
            				",UserScore float" + 
            				",ReleaseDate varchar(50)" + 
            				",CONSTRAINT PK_Playstation4_GameID PRIMARY KEY (GameID)" + 
            				");";
            		stmt = con.createStatement();
            		stmt.execute(createPlaystation4);
            
            		// Create Xbox Table
            		String createXboxOne = "CREATE TABLE XboxOne" + 
            				"(GameID int identity not null" + 
            				",GameTitle varchar(50) not null" + 
            				",Metascore int" + 
            				",UserScore float" + 
            				",ReleaseDate varchar(50)" + 
            				",CONSTRAINT PK_XboxOne_GameID PRIMARY KEY (GameID)" + 
            				");";
            		stmt = con.createStatement();
            		stmt.execute(createXboxOne);
            		
            		// Create Switch Table
            		String createSwitch = "CREATE TABLE Switch" + 
            				"(GameID int identity not null" + 
            				",GameTitle varchar(50) not null" + 
            				",Metascore int" + 
            				",UserScore float" + 
            				",ReleaseDate varchar(50)" + 
            				",CONSTRAINT PK_Switch_GameID PRIMARY KEY (GameID)" + 
            				");";
            		stmt = con.createStatement();
            		stmt.execute(createSwitch);
            		
            		// Create PC Table
            		String createPC = "CREATE TABLE PC" + 
            				"(GameID int identity not null" + 
            				",GameTitle varchar(50) not null" + 
            				",Metascore int" + 
            				",UserScore float" + 
            				",ReleaseDate varchar(50)" + 
            				",CONSTRAINT PK_PC_GameID PRIMARY KEY (GameID)" + 
            				");";
            		stmt = con.createStatement();
            		stmt.execute(createPC);
            		
            		// Create 3DS Table
            		String create3DS = "CREATE TABLE Nintendo3DS" + 
            				"(GameID int identity not null" + 
            				",GameTitle varchar(50) not null" + 
            				",Metascore int" + 
            				",UserScore float" + 
            				",ReleaseDate varchar(50)" + 
            				",CONSTRAINT PK_Nintendo3DS_GameID PRIMARY KEY (GameID)" + 
            				");";
            		stmt = con.createStatement();
            		stmt.execute(create3DS);
            		
            		// Create iOS Table
            		String createIOS = "CREATE TABLE iOS" + 
            				"(GameID int identity not null" + 
            				",GameTitle varchar(50) not null" + 
            				",Metascore int" + 
            				",UserScore float" + 
            				",ReleaseDate varchar(50)" + 
            				",CONSTRAINT PK_iOS_GameID PRIMARY KEY (GameID)" + 
            				");";
            		stmt = con.createStatement();
            		stmt.execute(createIOS);
            		*/
            		
            		//	RETRIEVE PS4 DATA
            		// declare ps4 variables
            		String ps4metascoreString, ps4gametitle = null, ps4userscore, ps4releasedate = null;
            		Integer ps4metascoreValue = null;
            		Double ps4userscoreValue = null;
            		// get PS4 elements
            		Document ps4Document = Jsoup.connect("http://www.metacritic.com/browse/games/score/metascore/all/ps4?sort=desc").get();
            		Elements ps4metaScores = ps4Document.select("div[class=metascore_w small game positive]"); // target ps4 metascores
            		Elements ps4gameTitles = ps4Document.select("div[class=product_item product_title] > a"); // target ps4 game titles
            		Elements ps4userScores = ps4Document.getElementsByAttributeValue("class", "product_item product_userscore_txt"); // target ps4 user scores
            		Elements ps4releaseDates = ps4Document.select("div[class=product_item product_date]"); // target ps4 release dates
            		
            		ArrayList<Integer> ps4metaScoreArr = new ArrayList<Integer>();
            		for (Element ps4metascore : ps4metaScores) {
            			ps4metascoreString = ps4metascore.text();
            			ps4metascoreValue = Integer.parseInt(ps4metascoreString);
            			ps4metaScoreArr.add(ps4metascoreValue);
            			
            		}
            		
            		ArrayList<String> ps4TitlesArr = new ArrayList<String>();
        			for (Element ps4title : ps4gameTitles) {
        				ps4gametitle = ps4title.text();
        				ps4TitlesArr.add(ps4gametitle);
        				
        			}
        			
        			ArrayList<String> ps4userScoreArr = new ArrayList<String>();
        			for (Element userscore : ps4userScores) {
        				ps4userscore = userscore.child(1).text();
        				ps4userScoreArr.add(ps4userscore);
        				
        			}
        			
        			ArrayList<String> ps4releaseDateArr = new ArrayList<String>();
        			for (Element releaseDate : ps4releaseDates) {
        				ps4releasedate = releaseDate.text();
        				ps4releaseDateArr.add(ps4releasedate);
        				
        			}
        			// create array to hold data and pass to classes
        			for (int x = 0; x <  ps4metaScores.size(); ++x) {
        				videoGameList.add(new VideoGame(ps4metaScoreArr.get(x), ps4TitlesArr.get(x), ps4userScoreArr.get(x), ps4releaseDateArr.get(x), "Playstation 4"));
        				playstationList.add(new VideoGame(ps4metaScoreArr.get(x), ps4TitlesArr.get(x), ps4userScoreArr.get(x), ps4releaseDateArr.get(x), "Playstation 4"));
        			}
        			
    /* ------------------------------------------------------------------------------------------------- */
            		//	RETRIEVE XBOX DATA
            		// declare xbox variables
        			String xboxMetascoreString, xboxGametitle = null, xboxUserscore, xboxReleasedate = null;
        			Integer xboxMetascoreValue = null;
        			Double xboxUserScoreValue = null;
        			//get XBOX elements
        			Document xboxDocument = Jsoup.connect("http://www.metacritic.com/browse/games/score/metascore/all/xboxone?sort=desc").get();
        			Elements xboxMetascores = xboxDocument.select("div[class=metascore_w small game positive]");
        			Elements xboxGameTitles = xboxDocument.select("div[class=product_item product_title] > a");
        			Elements xboxUserScores = xboxDocument.getElementsByAttributeValue("class", "product_item product_userscore_txt");
        			Elements xboxReleaseDates = xboxDocument.select("div[class=product_item product_date]");
        			
        			ArrayList<Integer> xboxMetascoreArr = new ArrayList<Integer>();
        			for (Element xboxMetascore : xboxMetascores) {
        				xboxMetascoreString = xboxMetascore.text();
        				xboxMetascoreValue = Integer.parseInt(xboxMetascoreString);
        				xboxMetascoreArr.add(xboxMetascoreValue);
        			}
        			    		
        			ArrayList<String> xboxTitleArr = new ArrayList<String>();
        			for (Element xboxGameTitle : xboxGameTitles) {
        				xboxGametitle = xboxGameTitle.text();
        				xboxTitleArr.add(xboxGametitle);
        			}
        			
        			ArrayList<String> xboxUserScoreArr =  new ArrayList<String>();
        			for (Element xboxUserScore : xboxUserScores) {
        				xboxUserscore = xboxUserScore.child(1).text();
        				xboxUserScoreArr.add(xboxUserscore);
        			}
        			ArrayList<String> xboxReleaseDateArr = new ArrayList<String>();
        			for (Element xboxReleaseDate : xboxReleaseDates) {
        				xboxReleasedate = xboxReleaseDate.text();
        				xboxReleaseDateArr.add(xboxReleasedate);
        			}
        			
        			// create array to hold data and pass to classes
        			for (int i = 0; i < xboxMetascores.size(); ++i) {
        				videoGameList.add(new VideoGame(xboxMetascoreArr.get(i), xboxTitleArr.get(i), xboxUserScoreArr.get(i), xboxReleaseDateArr.get(i), "Xbox One"));
        				xboxList.add(new VideoGame(xboxMetascoreArr.get(i), xboxTitleArr.get(i), xboxUserScoreArr.get(i), xboxReleaseDateArr.get(i), "Xbox One"));
        			}
        			
    /* ------------------------------------------------------------------------------------------------- */
            		//	RETRIEVE SWITCH DATA
            		// declare switch variables
        			String switchMetascoreString, switchGametitle = null, switchUserscore, switchReleasedate = null;
        			Integer switchMetascoreValue = null;
        			Double switchUserScoreValue = null;
        			//get XBOX elements
        			Document switchDocument = Jsoup.connect("http://www.metacritic.com/browse/games/score/metascore/all/switch/filtered?sort=desc").get();
        			Elements switchMetascores = switchDocument.select("div[class=metascore_w small game positive]");
        			Elements switchGameTitles = switchDocument.select("div[class=product_item product_title] > a");
        			Elements switchUserScores = switchDocument.getElementsByAttributeValue("class", "product_item product_userscore_txt");
        			Elements switchReleaseDates = switchDocument.select("div[class=product_item product_date]");
        			
        			ArrayList<Integer> switchMetascoreArr = new ArrayList<Integer>();
        			for (Element switchMetascore : switchMetascores) {
        				switchMetascoreString = switchMetascore.text();
        				switchMetascoreValue = Integer.parseInt(switchMetascoreString);
        				switchMetascoreArr.add(switchMetascoreValue);
        			}
        			    		
        			ArrayList<String> switchTitleArr = new ArrayList<String>();
        			for (Element switchGameTitle : switchGameTitles) {
        				switchGametitle = switchGameTitle.text();
        				switchTitleArr.add(switchGametitle);
        			}
        			
        			ArrayList<String> switchUserScoreArr =  new ArrayList<String>();
        			for (Element switchUserScore : switchUserScores) {
        				switchUserscore = switchUserScore.child(1).text();
        				switchUserScoreArr.add(switchUserscore);
        			}
        			ArrayList<String> switchReleaseDateArr = new ArrayList<String>();
        			for (Element switchReleaseDate : switchReleaseDates) {
        				switchReleasedate = switchReleaseDate.text();
        				switchReleaseDateArr.add(switchReleasedate);
        			}
        			
        			// create array to hold data and pass to classes
        			for (int i = 0; i < switchMetascores.size(); ++i) {
        				videoGameList.add(new VideoGame(switchMetascoreArr.get(i), switchTitleArr.get(i), switchUserScoreArr.get(i), switchReleaseDateArr.get(i), "Switch"));
        				switchList.add(new VideoGame(switchMetascoreArr.get(i), switchTitleArr.get(i), switchUserScoreArr.get(i), switchReleaseDateArr.get(i), "Switch"));
        			}

    /* ------------------------------------------------------------------------------------------------- */
            		//	RETRIEVE PC DATA
            		// declare PC variables
        			String PCMetascoreString, PCGametitle = null, PCUserscore, PCReleasedate = null;
        			Integer PCMetascoreValue = null;
        			Double PCUserScoreValue = null;
        			//get XBOX elements
        			Document PCDocument = Jsoup.connect("http://www.metacritic.com/browse/games/score/metascore/all/PC/filtered?sort=desc").get();
        			Elements PCMetascores = PCDocument.select("div[class=metascore_w small game positive]");
        			Elements PCGameTitles = PCDocument.select("div[class=product_item product_title] > a");
        			Elements PCUserScores = PCDocument.getElementsByAttributeValue("class", "product_item product_userscore_txt");
        			Elements PCReleaseDates = PCDocument.select("div[class=product_item product_date]");
        			
        			ArrayList<Integer> PCMetascoreArr = new ArrayList<Integer>();
        			for (Element PCMetascore : PCMetascores) {
        				PCMetascoreString = PCMetascore.text();
        				PCMetascoreValue = Integer.parseInt(PCMetascoreString);
        				PCMetascoreArr.add(PCMetascoreValue);
        			}
        			    		
        			ArrayList<String> PCTitleArr = new ArrayList<String>();
        			for (Element PCGameTitle : PCGameTitles) {
        				PCGametitle = PCGameTitle.text();
        				PCTitleArr.add(PCGametitle);
        			}
        			
        			ArrayList<String> PCUserScoreArr =  new ArrayList<String>();
        			for (Element PCUserScore : PCUserScores) {
        				PCUserscore = PCUserScore.child(1).text();
        				PCUserScoreArr.add(PCUserscore);
        			}
        			ArrayList<String> PCReleaseDateArr = new ArrayList<String>();
        			for (Element PCReleaseDate : PCReleaseDates) {
        				PCReleasedate = PCReleaseDate.text();
        				PCReleaseDateArr.add(PCReleasedate);
        			}
        			
        			// create array to hold data and pass to classes
        			for (int i = 0; i < PCMetascores.size(); ++i) {
        				videoGameList.add(new VideoGame(PCMetascoreArr.get(i), PCTitleArr.get(i), PCUserScoreArr.get(i), PCReleaseDateArr.get(i), "PC"));
        				pcList.add(new VideoGame(PCMetascoreArr.get(i), PCTitleArr.get(i), PCUserScoreArr.get(i), PCReleaseDateArr.get(i), "PC"));
        			}
        			
    /* ------------------------------------------------------------------------------------------------- */
            		//	RETRIEVE DS DATA
            		// declare DS variables
        			String DSMetascoreString, DSGametitle = null, DSUserscore, DSReleasedate = null;
        			Integer DSMetascoreValue = null;
        			Double DSUserScoreValue = null;
        			//get XBOX elements
        			Document DSDocument = Jsoup.connect("http://www.metacritic.com/browse/games/score/metascore/all/3ds?sort=desc").get();
        			Elements DSMetascores = DSDocument.select("div[class=metascore_w small game positive]");
        			Elements DSGameTitles = DSDocument.select("div[class=product_item product_title] > a");
        			Elements DSUserScores = DSDocument.getElementsByAttributeValue("class", "product_item product_userscore_txt");
        			Elements DSReleaseDates = DSDocument.select("div[class=product_item product_date]");
        			
        			ArrayList<Integer> DSMetascoreArr = new ArrayList<Integer>();
        			for (Element DSMetascore : DSMetascores) {
        				DSMetascoreString = DSMetascore.text();
        				DSMetascoreValue = Integer.parseInt(DSMetascoreString);
        				DSMetascoreArr.add(DSMetascoreValue);
        			}
        			    		
        			ArrayList<String> DSTitleArr = new ArrayList<String>();
        			for (Element DSGameTitle : DSGameTitles) {
        				DSGametitle = DSGameTitle.text();
        				DSTitleArr.add(DSGametitle);
        			}
        			
        			ArrayList<String> DSUserScoreArr =  new ArrayList<String>();
        			for (Element DSUserScore : DSUserScores) {
        				DSUserscore = DSUserScore.child(1).text();
        				DSUserScoreArr.add(DSUserscore);
        			}
        			ArrayList<String> DSReleaseDateArr = new ArrayList<String>();
        			for (Element DSReleaseDate : DSReleaseDates) {
        				DSReleasedate = DSReleaseDate.text();
        				DSReleaseDateArr.add(DSReleasedate);
        			}
        			
        			// create array to hold data and pass to classes
        			for (int i = 0; i < DSMetascores.size(); ++i) {
        				videoGameList.add(new VideoGame(DSMetascoreArr.get(i), DSTitleArr.get(i), DSUserScoreArr.get(i), DSReleaseDateArr.get(i), "Nintendo 3DS"));
        				nintendo3DSList.add(new VideoGame(DSMetascoreArr.get(i), DSTitleArr.get(i), DSUserScoreArr.get(i), DSReleaseDateArr.get(i), "Nintendo 3DS"));
        			}
        			
    /* ------------------------------------------------------------------------------------------------- */
            		//	RETRIEVE iOS DATA
            		// declare iOS variables
        			String iOSMetascoreString, iOSGametitle = null, iOSUserscore, iOSReleasedate = null;
        			Integer iOSMetascoreValue = null;
        			Double iOSUserScoreValue = null;
        			//get XBOX elements
        			Document iOSDocument = Jsoup.connect("http://www.metacritic.com/browse/games/score/metascore/all/ios?sort=desc").get();
        			Elements iOSMetascores = iOSDocument.select("div[class=metascore_w small game positive]");
        			Elements iOSGameTitles = iOSDocument.select("div[class=product_item product_title] > a");
        			Elements iOSUserScores = iOSDocument.getElementsByAttributeValue("class", "product_item product_userscore_txt");
        			Elements iOSReleaseDates = iOSDocument.select("div[class=product_item product_date]");
        			
        			ArrayList<Integer> iOSMetascoreArr = new ArrayList<Integer>();
        			for (Element iOSMetascore : iOSMetascores) {
        				iOSMetascoreString = iOSMetascore.text();
        				iOSMetascoreValue = Integer.parseInt(iOSMetascoreString);
        				iOSMetascoreArr.add(iOSMetascoreValue);
        			}
        			    		
        			ArrayList<String> iOSTitleArr = new ArrayList<String>();
        			for (Element iOSGameTitle : iOSGameTitles) {
        				iOSGametitle = iOSGameTitle.text();
        				iOSTitleArr.add(iOSGametitle);
        			}
        			
        			ArrayList<String> iOSUserScoreArr =  new ArrayList<String>();
        			for (Element iOSUserScore : iOSUserScores) {
        				iOSUserscore = iOSUserScore.child(1).text();
        				iOSUserScoreArr.add(iOSUserscore);
        			}
        			ArrayList<String> iOSReleaseDateArr = new ArrayList<String>();
        			for (Element iOSReleaseDate : iOSReleaseDates) {
        				iOSReleasedate = iOSReleaseDate.text();
        				iOSReleaseDateArr.add(iOSReleasedate);
        			}
        			
        			// create array to hold data and pass to classes
        			for (int i = 0; i < iOSMetascores.size(); ++i) {
        				videoGameList.add(new VideoGame(iOSMetascoreArr.get(i), iOSTitleArr.get(i), iOSUserScoreArr.get(i), iOSReleaseDateArr.get(i), "iOS (iPhone)"));
        				iOSGameList.add(new VideoGame(iOSMetascoreArr.get(i), iOSTitleArr.get(i), iOSUserScoreArr.get(i), iOSReleaseDateArr.get(i), "iOS (iPhone)"));
        			}
        			
        			// display the main table content with Observablelist
        			
        			if (radio_playstation4.isSelected()) {
        				main_table.setItems(playstationList);
        			}
        			else if (radio_xboxOne.isSelected()) {
        				main_table.setItems(xboxList);
        			}
        			else if (radio_switch.isSelected()) {
        				main_table.setItems(switchList);
        			}
        			else if (radio_pc.isSelected()) {
        				main_table.setItems(pcList);
        			}
        			else if (radio_3DS.isSelected()) {
        				main_table.setItems(nintendo3DSList);
        			}
        			else if (radio_iOS.isSelected()) {
        				main_table.setItems(iOSGameList);
        			}
        			else if (radio_showAll.isSelected()){
        				main_table.setItems(videoGameList);
        			}
        			else {
        				main_table.setItems(videoGameList);
        			}
        	}
        
		// Handle any errors that may have occurred.
		catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    // initialize the app
    public void initialize() throws IOException {
    	btn_showTableData.setVisible(true);
    	


		// Declare the JDBC objects.
		Connection con = null;
		Statement stmt = null;
		
    	try {
    		// Establish the connection.
    		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        		con = DriverManager.getConnection(CONNECTION_URL);
        		
        	// drop all tables when starting program
        		String dropVideoGames = "DROP TABLE VideoGame;";
        		stmt = con.createStatement();
        		stmt.execute(dropVideoGames);
        		
        		String dropPlaystation = "DROP TABLE Playstation4;";
        		stmt = con.createStatement();
        		stmt.execute(dropPlaystation);
        		
        		String dropXbox = "DROP TABLE XboxOne;";
        		stmt = con.createStatement();
        		stmt.execute(dropXbox);
        		
        		String dropSwitch = "DROP TABLE Switch;";
        		stmt = con.createStatement();
        		stmt.execute(dropSwitch);
        		
        		String dropPC = "DROP TABLE PC;";
        		stmt = con.createStatement();
        		stmt.execute(dropPC);
        		
        		String drop3DS = "DROP TABLE Nintendo3DS;";
        		stmt = con.createStatement();
        		stmt.execute(drop3DS);
        		
        		String dropIOS = "DROP TABLE iOS;";
        		stmt = con.createStatement();
        		stmt.execute(dropIOS);
        		

    			
    			/*
    			Connection conn = null;
    			Statement insertStmt = null;
    			ResultSet results = null;
    			VideoGame gameSelect = videoGameList;
    			
    			try {
    				conn = DriverManager.getConnection(CONNECTION_URL);
    				System.out.println("Connected.");
    				insertStmt = conn.createStatement();
    				
    				String insertDB = "INSERT INTO VideoGame (Metascore, GameTitle, UserScore, ReleaseDate, Platform)" +
    								"VALUES (?,?,?,?,?)";
    				
    				PreparedStatement preparedStatement = conn.prepareStatement(insertDB);
    				
    				preparedStatement.setInt(1, gameSelect.getMetascore());
    				preparedStatement.setString(2, gameSelect.getGameTitle());
    				preparedStatement.setString(3, gameSelect.getUserScore());
    				preparedStatement.setString(4, gameSelect.getReleaseDate());
    				preparedStatement.setString(5, gameSelect.getPlatform());
    				
    				System.out.println("Data has been inserted.");
    				
    			} catch (SQLException e) {
    				System.err.println(e);
    			}finally {
    				if(results != null) {
    					results.close();
    				}
    				if(insertStmt != null) {
    					insertStmt.close();
    				}
    				if(conn != null) {
    					conn.close();
    				}
    			}
    			*/
    			

    			
    			
        }
    	catch (Exception e) {
    		e.printStackTrace();
    	}
		
		
    }
}
