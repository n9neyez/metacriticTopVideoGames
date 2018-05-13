package application;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

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
}
