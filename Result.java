import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class Result {

    Stage window;
    Scene resultScene;
    ScrollPane scroll;
    BorderPane windowContent;
    VBox centreContent;
    ImageView logo;
    Alert error;
    TableView<ResultDetails> table;
    TableColumn<ResultDetails, String> courseCodeCol, creditUnitCol, gradeCol;
    Label tnuLabel, tcpLabel, gpaLabel, remarkLabel;
    Button backButton;

    Result() {
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

        //   result table
        table = new TableView<>();
        courseCodeCol = new TableColumn<>("Course Code");
        courseCodeCol.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
        creditUnitCol = new TableColumn<>("Credit Unit");
        creditUnitCol.setCellValueFactory(new PropertyValueFactory<>("creditUnit"));
        gradeCol = new TableColumn<>("Grade");
        gradeCol.setCellValueFactory(new PropertyValueFactory<>("grade"));
        table.getColumns().addAll(courseCodeCol, creditUnitCol, gradeCol);
        table.setItems(getResult());

        //  result labels
        tnuLabel = new Label("Total Number of Units (TNU) = " + Calculator.tnu);
        tcpLabel = new Label("Total Credit Points (TCP) = " + Calculator.tcp);
        gpaLabel = new Label("Grade Point Average (GPA) = " + String.format("%.2f", Calculator.gpa));
        remarkLabel = new Label("Remark: " + getRemark(Calculator.gpa));

        //  back button
        backButton = new Button("Back");

        centreContent.getChildren().addAll(logo, table, tnuLabel, tcpLabel, gpaLabel, remarkLabel, backButton);
        windowContent.setCenter(centreContent);

        //  resultScene
        resultScene = new Scene(scroll);
        resultScene.getStylesheets().add("LightStyle.css");

        //  create window
        window.setTitle("GPA Calculator - RESULT");
        window.setScene(resultScene);
        window.setMaximized(true);
        window.show();

        //  Error dialog
        error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("Credit Unit Error");
        error.setHeaderText(null);
        error.setContentText("Please enter valid integer(s) in the credit unit text field(s)");

        Home.previousWindow = window;

        //  Action events

        Home.homeButton.setOnAction(e -> {
            Home.goHome(window);
        });

        Home.helpButton.setOnAction(e -> {
            Home.goHelp(window);
        });

        Home.aboutButton.setOnAction(e -> {
            Home.goAbout(window);
        });

        backButton.setOnAction(e -> {
            Calculator.window.show();
            Calculator.windowContent.setTop(Home.bar);
            this.window.hide();
        });


    }  // end of constructor

    public ObservableList<ResultDetails> getResult() {
        ObservableList<ResultDetails> result = FXCollections.observableArrayList();

        for (int i = 0; i < Home.courseNumber; i++) {
            result.add(new ResultDetails(
                    Calculator.courseCodeTexts[i],
                    Calculator.cu[i],
                    Calculator.g[i]));
        }

        return result;
    } //  end of getResult()

    public String getRemark(Double gpa) {
        String remark = "";

        if (gpa >= 4.50) {
            remark = "First Class";
        }

        else if (gpa >= 3.50 && gpa <= 4.49) {
            remark = "Second Class Upper";
        }

        else if (gpa >= 2.40 && gpa <= 3.49) {
            remark = "Second Class Lower";
        }

        else if (gpa >= 1.50 && gpa <= 2.39) {
            remark = "Third Class";
        }

        return remark;
    }  //  end of getRemark()

}  //  end of class