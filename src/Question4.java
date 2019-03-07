import java.io.File;
import java.lang.*;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Question4 extends Application {
    //public static int [] arr=new int[26];
    private String fileName;
    private TextField nameInput = new TextField(fileName);
    private Stage primaryStage;
    private File file;
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);

    @Override
    public void start(Stage primaryStage) throws IOException {
        alert.setHeaderText("Choose a text file");
        alert.setContentText("This graph will need a text file");
        alert.showAndWait();

        while(file==null){
            chooseFile();
        }
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);

        HBox paths = new HBox();

        Label namef = new Label("Name of file");
        nameInput.setMinWidth(300);
        Button view = new Button("View");

        paths.getChildren().addAll(namef, nameInput, view);

        view.setOnAction(event -> {
            chooseFile();
        });

        grid.addRow(10, paths);

        char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        int count = 0;
        String contents = new String(Files.readAllBytes(Paths.get(file.toURI())));
        contents = contents.toUpperCase();
        HashMap<Character, Integer> charCount = new HashMap<>();
        char[] strArray = contents.toCharArray();
        for (char c : strArray) {
            if (charCount.containsKey(c)) {
                charCount.put(c, charCount.get(c) + 1);
            } else {
                charCount.put(c, 1);
            }
        }
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String, Number> bc = new BarChart<>(xAxis, yAxis);
        bc.setTitle("Alphabet Occurences");
        xAxis.setLabel("Letter");
        yAxis.setLabel("Value");
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("First File");
        for(char l : alphabet)
            if (charCount.containsKey(l)) {
                series1.getData().add(new XYChart.Data(Character.toString(l), charCount.get(l)));
            } else {
                series1.getData().add(new XYChart.Data(Character.toString(l), 0));
            }
        Scene scene = new Scene(grid);
        grid.getChildren().addAll(bc);
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    private void chooseFile(){
        FileChooser filechooser = new FileChooser();
        filechooser.setTitle("Choose a text file");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "TXT files (*.txt)", "*.txt");
        filechooser.getExtensionFilters().add(extFilter);
        file = filechooser.showOpenDialog(primaryStage);
        if (file == null) {
            alert.setContentText("No file was selected");
            alert.setHeaderText("");
            alert.showAndWait();
        } else{
            nameInput.setText(file.getAbsolutePath());
            fileName = file.getName();
        }
    }

    public static void main (String[]args){
        Application.launch(args);
    }
}