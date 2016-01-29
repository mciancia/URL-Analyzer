package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by mateusz on 28.01.2016.
 */

public class GoBackErrorPopup extends Stage {

    public GoBackErrorPopup(Stage owner){

        this.initModality(Modality.APPLICATION_MODAL);
        this.initOwner(owner);
        this.setTitle("Error");

        VBox dialogVbox = new VBox(20);
        dialogVbox.setAlignment(Pos.CENTER);
        Scene dialogScene = new Scene(dialogVbox, 200, 100);
        dialogVbox.getChildren().addAll(new Text("Nothing to go back to"));
        this.setScene(dialogScene);
    }
}
