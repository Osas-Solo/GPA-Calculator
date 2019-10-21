import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.net.URL;

public class About {

    static Stage window;
    Scene aboutScene;
    BorderPane windowContent;
    VBox centreContent;
    WebView aboutPage;
    VBox bottomContent;
    Button backButton;

    About() {

        //  main container
        window = new Stage();
        windowContent = new BorderPane();

        //  centre content
        centreContent = new VBox(10);
        centreContent.setAlignment(Pos.CENTER);
        aboutPage = new WebView();
        URL url = getClass().getResource("about.html");
        aboutPage.getEngine().load(url.toExternalForm());
        centreContent.getChildren().add(aboutPage);
        windowContent.setCenter(centreContent);

        //  bottom content
        bottomContent = new VBox();
        bottomContent.setPadding(new Insets(10, 10, 10,10));
        bottomContent.setAlignment(Pos.CENTER);
        backButton = new Button("Back");
        bottomContent.getChildren().addAll(backButton);
        windowContent.setBottom(bottomContent);

        //  resultScene
        aboutScene = new Scene(windowContent);
        aboutScene.getStylesheets().add("LightStyle.css");

        //  create window
        window.setTitle("GPA Calculator - About");
        window.setScene(aboutScene);
        window.setMaximized(true);
        window.show();

        //  Action events

        backButton.setOnAction(e -> {
            Home.goBack(Home.previousWindow, window);
        });

    }  // end of constructor

}  //  end of class