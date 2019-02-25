import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class Home extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    static Stage window;
    static Scene homeScene;
    static BorderPane windowContent;
    ScrollPane scroll;
    static HBox bar;
    static Button home;
    static Button help;
    static Button about;
    VBox centreContent;
    ImageView logo;
    Label courseNumberLabel;
    static TextField courseNumber;
    Button proceed;
    VBox bottomContent;
    Label productionLabel;
    String num;
    static int n;
    Alert error;

    @Override
    public void start(Stage primaryStage) {

        //  main container
        window = primaryStage;
        windowContent = new BorderPane();
        scroll = new ScrollPane(windowContent);
        scroll.setFitToWidth(true);
        scroll.setFitToHeight(true);

        //  top content
        bar = new HBox();
        bar.setId("bar");
        home = new Button("_Home");
        home.getStyleClass().add("bar-button");
        help = new Button("H_elp");
        help.getStyleClass().add("bar-button");
        about = new Button("_About");
        about.getStyleClass().add("bar-button");
        bar.getChildren().addAll(home, help, about);
        windowContent.setTop(bar);

        //  centre content
        centreContent = new VBox(10);
        centreContent.setPadding(new Insets(10, 10, 10,10));
        centreContent.setAlignment(Pos.CENTER);
        logo = new ImageView("FUPRE_LOGO.png");
        courseNumberLabel = new Label("Enter number of courses:");
        courseNumber = new TextField();
        courseNumber.setPromptText("Number of courses");
        proceed = new Button("Proceed");
        centreContent.getChildren().addAll(logo, courseNumberLabel, courseNumber, proceed);
        windowContent.setCenter(centreContent);

        //  bottom content
        bottomContent = new VBox();
        bottomContent.setPadding(new Insets(10, 10, 10,10));
        bottomContent.setAlignment(Pos.CENTER);
        productionLabel = new Label("© 2018 OS Tech");
        bottomContent.getChildren().add(productionLabel);
        windowContent.setBottom(productionLabel);

        //  homeScene
        homeScene = new Scene(scroll);
        homeScene.getStylesheets().add("LightStyle.css");

        //  create window
        window.setTitle("GPA Calculator - HOME");
        window.setScene(homeScene);
        window.setMaximized(true);
        window.show();

        //  Error dialog
        error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("Number Error");
        error.setHeaderText(null);

        //  Action events

        Home.help.setOnAction(e -> {
            Home.goHelp(window);
        });

        Home.about.setOnAction(e -> {
            Home.goAbout(window);
        });

        proceed.setOnAction( e -> {
            num = courseNumber.getText();
            try {
                n = Integer.parseInt(num);
                new Calculator();
                primaryStage.hide();
            } catch (NumberFormatException e1) {
                error.setContentText("Please enter a valid integer in the text field");
                error.show();
            }
        });

    }  //  end of start

    public static void goHome(Stage window) {
        Home.windowContent.setTop(Home.bar);
        Home.courseNumber.setText("");
        Home.window.show();
        window.hide();
    }  //  end goHome

    public static void goHelp(Stage window) {
        new Help();
        window.hide();
    }  //  end goHelp

    public static void goAbout(Stage window) {
        new About();
        window.hide();
    }  //  end goAbout

}  // end of class