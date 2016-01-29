package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.Stack;

public class Main extends Application {
    private final int width = 500;
    private final int height = 600;
    private final int buttonWidth = 100;
    private final int linkWidth = 300;
    private final int paneHeight = 30;
    private ObservableList<String> items;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Group root = new Group();
        primaryStage.setTitle("URL Analyzer");
        primaryStage.setScene(new Scene(root, width, height));
        primaryStage.setResizable(false);
        primaryStage.setHeight(height);
        primaryStage.setWidth(width);
        primaryStage.show();

        TextField linkTextField = new TextField("http://google.com");
        linkTextField.setPrefWidth(linkWidth);
        linkTextField.setPrefHeight(paneHeight);


        Button analyzeButton = new Button("Analyze");
        analyzeButton.setPrefWidth(100);
        analyzeButton.setTranslateX(width - 200);
        analyzeButton.setPrefHeight(paneHeight);

        Button goBackButton = new Button("Go back");
        goBackButton.setPrefWidth(100);
        goBackButton.setTranslateX(width-100);
        goBackButton.setPrefHeight(paneHeight);

        ListView<String> links = new ListView<>();
        links.setTranslateY(paneHeight);
        links.setPrefHeight(height-paneHeight);
        links.setPrefWidth(width);

        items = FXCollections.observableArrayList();
        links.setItems(items);


        root.getChildren().addAll(analyzeButton, goBackButton, linkTextField, links);

        FetchHistory history = new FetchHistory();
        Stack<String> sessionHistory = new Stack<>();

        analyzeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                items.clear();
                String url = linkTextField.getText();
                System.out.println(url);
                WebAnalyzer webAnalyzer;
                try {
                    webAnalyzer = new WebAnalyzer(url);
                    history.addUrl(url);
                    sessionHistory.push(url);
                    final ImagesPopup dialog = new ImagesPopup(primaryStage, webAnalyzer.getTotalImagesNum(),
                            webAnalyzer.getTotalImagesSize(), webAnalyzer.getLinks().size());
                    items.addAll(webAnalyzer.getLinks());
                    dialog.show();
                } catch (Exception e){
                    final ErrorPopup errorPopup = new ErrorPopup(primaryStage);
                    errorPopup.show();
                    System.out.println("Error while creating webanalyzer on "+ url);
                }


            }
        });
        goBackButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(sessionHistory.size());
                if(sessionHistory.size() <= 1){
                    final GoBackErrorPopup popup = new GoBackErrorPopup(primaryStage);
                    popup.show();
                } else {
                    sessionHistory.pop();
                    linkTextField.setText(sessionHistory.peek());
                }
            }
        });
        links.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                linkTextField.setText(links.getSelectionModel().getSelectedItem());
            }
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
