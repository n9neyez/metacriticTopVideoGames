package application;

import java.io.IOException;

import javafx.application.Platform;
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
    private TableView<?> main_table;

    @FXML
    private TableColumn<?, ?> metascore_col;

    @FXML
    private TableColumn<?, ?> gameTitle_col;

    @FXML
    private TableColumn<?, ?> userScore_col;

    @FXML
    private TableColumn<?, ?> releaseDate_col;
    
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
    private Button btn_showTableData;
    
    @FXML
    void show_table(MouseEvent event) {
		// Create a variable for the connection string.
		String connectionUrl = "jdbc:sqlserver://localhost:1433;" +
			"databaseName=MetacriticDB;integratedSecurity=true;";

		// Declare the JDBC objects.
		Connection con = null;
		Statement stmt = null;
		
		
        	try {
        		// Establish the connection.
        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            		con = DriverManager.getConnection(connectionUrl);
            
            		// Create PS4 Table
            		String createPlaystation4 = "CREATE TABLE Playstation4" + 
            				"(GameID int identity not null" + 
            				",GameTitle varchar(50) not null" + 
            				",Metascore int" + 
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
	
        	}
        
		// Handle any errors that may have occurred.
		catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    // initialize the app
    public void initialze() throws IOException {
    	
    	
		// Create a variable for the connection string.
		String connectionUrl = "jdbc:sqlserver://localhost:1433;" +
			"databaseName=MetacriticDB;integratedSecurity=true;";

		// Declare the JDBC objects.
		Connection con = null;
		Statement stmt = null;
		
    	try {
    		// Establish the connection.
    		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        		con = DriverManager.getConnection(connectionUrl);
        		
        	// drop all tables when starting program
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
        		
        		String drop3DS = "DROP TABLE 3DS;";
        		stmt = con.createStatement();
        		stmt.execute(drop3DS);
        		
        		String dropIOS = "DROP TABLE iOS;";
        		stmt = con.createStatement();
        		stmt.execute(dropIOS);
        }
    	catch (Exception e) {
    		e.printStackTrace();
    	}
		
		
    }
}
