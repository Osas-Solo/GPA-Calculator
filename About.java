import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class About {

    static Stage window;
    Scene aboutScene;
    ScrollPane scroll;
    BorderPane windowContent;
    VBox centreContent;
    Text about;
    VBox bottomContent;
    Label productionLabel;

    About() {
        //  main container
        window = new Stage();
        windowContent = new BorderPane();
        scroll = new ScrollPane(windowContent);
        scroll.setFitToWidth(true);
        scroll.setFitToHeight(true);

        //  top content
        windowContent.setTop(Home.bar);

        //  centre content
        centreContent = new VBox(10);
        centreContent.setPadding(new Insets(10, 10, 10,10));
        centreContent.setAlignment(Pos.CENTER);

        //  aboutButton content
        about = new Text("Version: 1.1.0\n\n" +
                "Coded by Ukpebor Osaremhen, a Computer Science student at the Federal " +
                "University of Petroleum Resources\n\n" +
                "For suggestions and complaints, contact me @:\n" +
                "Osas.Solo25@gmail.com\n" +
                "08154028194 (WhatsApp only)" +
                "\n\nNew in this version:\n" +
                "- Fixed multiple window bug for Help and About window\n" +
                "- Added back button to the result window");
        about.getStyleClass().add("info-text");

        centreContent.getChildren().addAll(about);
        windowContent.setCenter(centreContent);

        //  bottom content
        bottomContent = new VBox();
        bottomContent.setPadding(new Insets(10, 10, 10,10));
        bottomContent.setAlignment(Pos.CENTER);
        productionLabel = new Label("© 2018 OS Tech");
        bottomContent.getChildren().add(productionLabel);
        windowContent.setBottom(productionLabel);

        //  resultScene
        aboutScene = new Scene(scroll);
        aboutScene.getStylesheets().add("LightStyle.css");

        //  create window
        window.setTitle("GPA Calculator - About");
        window.setScene(aboutScene);
        window.setMaximized(true);
        window.show();

        //  Action events

        Home.homeButton.setOnAction(e -> {
            Home.goHome(window);
        });

        Home.helpButton.setOnAction(e -> {
            Home.goHelp(window);
        });

        Home.aboutButton.setOnAction(e -> {
            if (!window.isShowing()) {
                Home.goAbout(window);
            }
        });


    }  // end of constructor

}  //  end of class