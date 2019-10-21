import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.net.URL;

public class Help {

    static Stage window;
    Scene helpScene;
    BorderPane windowContent;
    VBox centreContent;
    WebView helpPage;
    VBox bottomContent;
    Button backButton;

    Help() {
        //  main container
        window = new Stage();
        windowContent = new BorderPane();

        //  centre content
        centreContent = new VBox(10);
        centreContent.setAlignment(Pos.CENTER);
        helpPage = new WebView();
        URL url = getClass().getResource("help.html");
        helpPage.getEngine().load(url.toExternalForm());
        centreContent.getChildren().add(helpPage);
        windowContent.setCenter(centreContent);

        //  bottom content
        bottomContent = new VBox();
        bottomContent.setPadding(new Insets(10, 10, 10,10));
        bottomContent.setAlignment(Pos.CENTER);
        backButton = new Button("Back");
        bottomContent.getChildren().addAll(backButton);
        windowContent.setBottom(bottomContent);

        //  resultScene
        helpScene = new Scene(windowContent);
        helpScene.getStylesheets().add("LightStyle.css");

        //  create window
        window.setTitle("GPA Calculator - Help");
        window.setScene(helpScene);
        window.setMaximized(true);
        window.show();

        //  Action events

        backButton.setOnAction(e -> {
            Home.goBack(Home.previousWindow, window);
        });

    }  // end of constructor

}  //  end of class