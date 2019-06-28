import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class Help {

    static Stage window;
    Scene helpScene;
    ScrollPane scroll;
    BorderPane windowContent;
    VBox centreContent;
    Text help;
    VBox bottomContent;
    Label productionLabel;

    Help() {
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
        help = new Text("This GPA Calculator is based on FUPRE's grading system.\n\n\n" +
                "How to use this GPA Calculator: \n" +
                "1. Enter the number of courses to be calculated in the text field.\n" +
                "2. Click Proceed.\n" +
                "3. Enter Course Code, Credit Unit and select Grade for each course. Ensure that every " +
                "credit unit text field is filled before continuing.\n" +
                "4. Click Calculate.\n" +
                "5. The result of the calculation is then displayed.\n\n\n" +
                "How to calculate GPA manually: \n\n" +
                "Performance Rating:\n" +
                "Score:   \t Grade:\t   Rating:\n" +
                "70 - 100 \t A     \t   5\n" +
                "60 - 69  \t B     \t   4\n" +
                "50 - 59  \t C     \t   3\n" +
                "45 - 49  \t D     \t   2\n" +
                "0 - 44   \t F     \t           0\n\n" +
                "The Credit Point (CP) of each course is calculated by:\n" +
                "CP = Credit Unit (CU) * Rating\n" +
                "The Total Number of Units (TNU) is gotten by summing the CU of all the courses used in the calculation.\n" +
                "The Total Credit Point (TCP) is gotten by summing the CP of all the courses used in the calculation.\n" +
                "The Grade Point Average (GPA) then calculated:\n" +
                "GPA = TCP / TNU\n\n\n" +
                "For Cumulative GPA (CGPA) calculation:\n" +
                "The summation of every TNU is gotten from every semester " +
                "from beginning to date as Cumulative Number of Units (CNU).\n" +
                "While the summation of every TCP is gotten as Cumulative Credit Point (CCP).\n" +
                "Then the CGPA is calculated:\n" +
                "CGPA = CCP / CNU\n\n\n" +
                "Degree Classification:\n\n" +
                "Class of Degree:   \tCGPA:\n" +
                "4.50 - 5.00        \tFirst Class\n" +
                "3.50 - 4.49        \tSecond Class Upper Division\n" +
                "2.40 - 3.49        \tSecond Class Lower Division\n" +
                "1.50 - 2.39        \tThird Class");

        help.getStyleClass().add("info-text");

        centreContent.getChildren().addAll(help);
        windowContent.setCenter(centreContent);

        //  bottom content
        bottomContent = new VBox();
        bottomContent.setPadding(new Insets(10, 10, 10,10));
        bottomContent.setAlignment(Pos.CENTER);
        productionLabel = new Label("© 2018 OS Tech");
        bottomContent.getChildren().add(productionLabel);
        windowContent.setBottom(productionLabel);

        //  resultScene
        helpScene = new Scene(scroll);
        helpScene.getStylesheets().add("LightStyle.css");

        //  create window
        window.setTitle("GPA Calculator - HELP");
        window.setScene(helpScene);
        window.setMaximized(true);
        window.show();

        //  Action events

        Home.homeButton.setOnAction(e -> {
            Home.goHome(window);
        });

        Home.helpButton.setOnAction(e -> {
            if (!window.isShowing()) {
                Home.goHelp(window);
            }
        });

        Home.aboutButton.setOnAction(e -> {
            Home.goAbout(window);
        });

    }  // end of constructor

}  //  end of class