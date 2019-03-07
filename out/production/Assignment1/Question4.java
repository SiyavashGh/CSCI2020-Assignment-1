import java.io.File;
import java.lang.*;
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
public class Part4 extends Application {
    //public static int [] arr=new int[26];
    private String fileName;
    private TextField nameInput = new TextField(fileName);
    private Stage primaryStage;
    private File file;
    @Override
    public void start(Stage primaryStage) throws IOException {
        chooseFile(); //function allows user to choose file to open
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8); //Set vertical gap for grid
        grid.setHgap(10); //Set Horizontal gap for grid
        HBox paths = new HBox();
        Label namef = new Label("Choose file Path");
        nameInput.setMinWidth(300);
        Button view = new Button("Pick File");
        paths.getChildren().addAll(namef, nameInput, view);
        view.setOnAction(event -> { //When button is clicked, calls open file function
            chooseFile();
        });
        nameInput.setOnAction(event->{ //When enter is clicked, calls open File function
            chooseFile();
                });
        grid.addRow(10, paths);
        char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray(); //Character Array of alphabet
        String contents = new String(Files.readAllBytes(Paths.get(file.toURI()))); //Creates String from file
        contents = contents.toUpperCase(); //Changes String to all uppercase characters
        HashMap<Character, Integer> charCount = new HashMap<>(); //Create Hashmap for characters/count of each char
        char[] strArray = contents.toCharArray(); //Creates array of elemts from file string
        for (char c : strArray) {
            if (charCount.containsKey(c)) {
                charCount.put(c, charCount.get(c) + 1);
            } else {
                charCount.put(c, 1);
            }
        }
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String, Number> bc = new BarChart<>(xAxis, yAxis); //Creates barchart
        bc.setTitle("Alphabet Occurences");
        xAxis.setLabel("Letter");
        yAxis.setLabel("Value");
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("File");
        for(char l : alphabet) //adds XY values to bar chart
            if (charCount.containsKey(l)) {
                series1.getData().add(new XYChart.Data(Character.toString(l), charCount.get(l)));
            } else {
                series1.getData().add(new XYChart.Data(Character.toString(l), 0));
            }
        Scene scene = new Scene(grid);
        bc.getData().addAll(series1);
        grid.getChildren().addAll(bc);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void chooseFile(){
        FileChooser filechooser = new FileChooser();
        filechooser.setTitle("Choose a text file");
        file = filechooser.showOpenDialog(primaryStage);
        nameInput.setText(file.getAbsolutePath());
        fileName = file.getName();
    }
    public static void main (String[]args){
        Application.launch(args);
    }
}