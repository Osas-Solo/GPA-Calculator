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

    //  main container
    static Stage window;
    static Scene homeScene;
    static BorderPane windowContent;
    ScrollPane scroller;

    //  north content
    static HBox bar;
    static Button homeButton;
    static Button helpButton;
    static Button aboutButton;

    //  centre content
    VBox centreContent;
    ImageView logo;
    Label courseNumberLabel;
    static TextField courseNumberInput;
    Button proceedButton;

    //  other variables
    String courseNumberText;
    static int courseNumber;
    Alert error;
    static Stage previousWindow;

    @Override
    public void start(Stage primaryStage) {

        //  main container
        window = primaryStage;
        windowContent = new BorderPane();
        scroller = new ScrollPane(windowContent);
        scroller.setFitToWidth(true);
        scroller.setFitToHeight(true);

        //  top content
        bar = new HBox();
        bar.setId("bar");
        homeButton = new Button("_Home");
        homeButton.getStyleClass().add("bar-button");
        helpButton = new Button("H_elp");
        helpButton.getStyleClass().add("bar-button");
        aboutButton = new Button("_About");
        aboutButton.getStyleClass().add("bar-button");
        bar.getChildren().addAll(homeButton, helpButton, aboutButton);
        windowContent.setTop(bar);

        //  centre content
        centreContent = new VBox(10);
        centreContent.setPadding(new Insets(10, 10, 10,10));
        centreContent.setAlignment(Pos.CENTER);
        logo = new ImageView("FUPRE_LOGO.png");
        courseNumberLabel = new Label("Enter number of courses:");
        courseNumberInput = new TextField();
        courseNumberInput.setPromptText("Number of courses");
        proceedButton = new Button("Proceed");
        centreContent.getChildren().addAll(logo, courseNumberLabel, courseNumberInput, proceedButton);
        windowContent.setCenter(centreContent);

        //  homeScene
        homeScene = new Scene(scroller);
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

        helpButton.setOnAction(e -> {
            goHelp(window);
        });

        aboutButton.setOnAction(e -> {
            goAbout(window);
        });

        proceedButton.setOnAction(e -> {
            courseNumberText = courseNumberInput.getText();
            try {
                courseNumber = Integer.parseInt(courseNumberText);
                if (courseNumber <= 0) {
                    throw  new NumberFormatException();
                }
                new Calculator();
                window.hide();
            } catch (NumberFormatException e1) {
                error.setContentText("Please enter a valid integer (integer > 0) in the text field");
                error.show();
            }
        });

    }  //  end of start

    public static void goHome(Stage window) {
        Home.windowContent.setTop(Home.bar);
        Home.courseNumberInput.setText("");
        Home.window.show();
        window.hide();
    }  //  end of goHome

    public static void goHelp(Stage window) {
        previousWindow = window;
        new Help();
        window.hide();
    }  //  end of goHelp

    public static void goAbout(Stage window) {
        previousWindow = window;
        new About();
        window.hide();
    }  //  end of goAbout

    public static void goBack(Stage previousWindow, Stage currentWindow) {
        previousWindow.show();
        currentWindow.hide();
    }  //  end of goBack()

}  // end of class