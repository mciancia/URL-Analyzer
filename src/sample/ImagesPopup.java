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
public class ImagesPopup extends Stage {
    public ImagesPopup(Stage owner, Integer numOfImages, Integer sizeOfImages, Integer numOfLinks){
        this.initModality(Modality.APPLICATION_MODAL);
        this.initOwner(owner);
        this.setTitle("URL Info");

        VBox dialogVbox = new VBox(20);
        dialogVbox.setAlignment(Pos.CENTER);
        Scene dialogScene = new Scene(dialogVbox, 300, 200);

        dialogVbox.getChildren().add(new Text("Unique images: " + numOfImages));
        dialogVbox.getChildren().add(new Text("Size of images: " + sizeOfImages + "B"));
        dialogVbox.getChildren().add(new Text("Unique links: " + numOfLinks));
        this.setScene(dialogScene);
    }
}
