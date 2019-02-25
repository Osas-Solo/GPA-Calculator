import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class Calculator {

    Stage window;
    Scene calculatorScene;
    ScrollPane scroll;
    BorderPane windowContent;
    VBox centreContent;
    ImageView logo;
    GridPane gradeCollector;
    TilePane[] gradesLayout;
    Label[] courseCodeLabels;
    TextField[] courseCodes;
    Label[] creditUnitLabels;
    TextField[] creditUnits;
    Label[] gradeLabels;
    ChoiceBox<String> grades[];
    Button calculate;
    VBox bottomContent;
    Label productionLabel;
    Alert error;
    static String[] courseCodeTexts;
    String[] creditUnitTexts;
    static int[] cu;
    static String[] g;
    int[] ratings;
    int[] cp;
    static double tnu;
    static double tcp;
    static double gpa;

    Calculator() {
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
        logo = new ImageView("FUPRE_LOGO.png");

        //  initialise gradeCollector
        gradeCollector = new GridPane();
        gradeCollector.setPadding(new Insets(10, 10, 10, 10));
        gradeCollector.setHgap(10);
        gradeCollector.setVgap(10);
        gradesLayout = new TilePane[Home.n];
        courseCodeLabels = new Label[Home.n];
        courseCodes = new TextField[Home.n];
        creditUnitLabels = new Label[Home.n];
        creditUnits = new TextField[Home.n];
        gradeLabels = new Label[Home.n];
        grades = new ChoiceBox[Home.n];

        for (int i = 0; i < Home.n; i++) {  //  initialise each gradeCollector array
            gradesLayout[i] = new TilePane();
            gradesLayout[i].setAlignment(Pos.CENTER);
            gradesLayout[i].setBorder(new Border(new BorderStroke(Color.web("#000033"),
                                                BorderStrokeStyle.SOLID,
                                                CornerRadii.EMPTY,
                                                new BorderWidths(3))));
            gradesLayout[i].setPadding(new Insets(10));
            gradesLayout[i].setOrientation(Orientation.HORIZONTAL);
            gradesLayout[i].setPrefColumns(2);
            courseCodeLabels[i] = new Label("Enter course code:");
            courseCodes[i] = new TextField();
            courseCodes[i].setPromptText("Course code");
            creditUnitLabels[i] = new Label("Enter credit unit:");
            creditUnits[i] = new TextField();
            creditUnits[i].setPromptText("Credit unit");
            gradeLabels[i] = new Label("Select your grade for this course:");
            String[] g = {"A", "B", "C", "D", "F"};
            grades[i] = new ChoiceBox<String>();
            grades[i].getItems().addAll(g);
            grades[i].setValue("A");
            gradesLayout[i].getChildren().addAll(
                    courseCodeLabels[i], courseCodes[i],
                    creditUnitLabels[i], creditUnits[i],
                    gradeLabels[i], grades[i] );
        }

        int c = 0;

        if (Home.n % 5 == 0) {  //  GridPane constraints for 5-multiple number of courses
            for (int row = 0; row < (Home.n / 5); row++) {
                for (int col = 0; col < 5; col++) {
                    GridPane.setConstraints(gradesLayout[c], col, row);
                    c++;
                }
            }
        }

        else if (Home.n % 4 == 0) {  //  GridPane constraints for 4-multiple number of courses
            for (int row = 0; row < (Home.n / 4); row++) {
                for (int col = 0; col < 4; col++) {
                    GridPane.setConstraints(gradesLayout[c], col, row);
                    c++;
                }
            }
        }

        else if (Home.n % 3 == 0) {  //  GridPane constraints for 3-multiple number of courses
            for (int row = 0; row < (Home.n / 3); row++) {
                for (int col = 0; col < 3; col++) {
                    GridPane.setConstraints(gradesLayout[c], col, row);
                    c++;
                }
            }
        }


        else if (Home.n % 2 == 0) {  //  GridPane constraints for even-number of courses
            for (int row = 0; row < (Home.n / 2); row++) {
                for (int col = 0; col < 2; col++) {
                    GridPane.setConstraints(gradesLayout[c], col, row);
                    c++;
                }
            }
        }

        else {  //  GridPane constraints for any other number of courses
            for (int row = 0; row <= (Home.n / 3); row++) {
                for (int col = 0; col < 3; col++) {
                    GridPane.setConstraints(gradesLayout[c], col, row);
                    c++;
                    if (c == Home.n) {  // check if every gradesLayout has been positioned
                        break;
                    }
                }
            }
        }

        for (int i = 0; i < Home.n; i++) {
            gradeCollector.getChildren().add(gradesLayout[i]);
        }

        calculate = new Button("Calculate");
        centreContent.getChildren().addAll(logo, gradeCollector, calculate);
        windowContent.setCenter(centreContent);

        //  bottom content
        bottomContent = new VBox();
        productionLabel = new Label("© 2018 OS Tech");
        bottomContent.getChildren().add(productionLabel);
        windowContent.setBottom(bottomContent);

        //  calculatorScene
        calculatorScene = new Scene(scroll);
        calculatorScene.getStylesheets().add("LightStyle.css");

        //  create window
        window.setTitle("GPA Result - CALCULATOR");
        window.setScene(calculatorScene);
        window.setMaximized(true);
        window.show();

        //  Error dialog
        error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("Credit Unit Error");
        error.setHeaderText(null);
        error.setContentText("Please enter valid integer(s) in the credit unit text field(s)");

        //  Action events

        Home.home.setOnAction(e -> {
            Home.goHome(window);
        });

        Home.help.setOnAction(e -> {
            Home.goHelp(window);
        });

        Home.about.setOnAction(e -> {
            Home.goAbout(window);
        });

        calculate.setOnAction(e -> {
            try {
                calculateGpa();
                new Result();
                window.hide();
            } catch (Exception e1) {
                error.show();
            }
        });

    }  // end of constructor

    public void calculateGpa() {
        //  initialise arrays
        courseCodeTexts = new String[Home.n];
        creditUnitTexts = new String[Home.n];
        cu = new int[Home.n];
        g = new String[Home.n];
        ratings = new int[Home.n];
        cp = new int[Home.n];
        tnu = 0;
        tcp = 0;

        for (int i = 0; i < Home.n; i++) {  //  get entered inputs
            courseCodeTexts[i] = courseCodes[i].getText();
            creditUnitTexts[i] = creditUnits[i].getText();
            cu[i] = Integer.parseInt(creditUnitTexts[i]);
            g[i] = grades[i].getValue();

            //  convert grade to ratings
            if (g[i].equals("A")) {
                ratings[i] = 5;
            }

            if (g[i].equals("B")) {
                ratings[i] = 4;
            }

            if (g[i].equals("C")) {
                ratings[i] = 3;
            }

            if (g[i].equals("D")) {
                ratings[i] = 2;
            }

            if (g[i].equals("F")) {
                ratings[i] = 0;
            }

            cp[i] = cu[i] * ratings[i];

            tnu += cu[i];
            tcp += cp[i];
        }

        gpa = tcp / tnu;
        gpa *= 100;
        gpa = Math.round(gpa);
        gpa /= 100;

    }  //  end of calculateGPA

}  //  end of class
