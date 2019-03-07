import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Question2 extends Application {
    private double investmentAmount, monthlyInterestRate, years;/*Created this private variables outside the start
                                                                  method to be able to use them anywhere in the file*/

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Investment-Value Calculator");//Sets the title of the UI

        GridPane pane = new GridPane();//Gridpane declaration to be able to set location of labels and texfields
        pane.setPadding(new Insets(10,10,10,10));

        Label invsLabel = new Label("Investment Amount");
        TextField investAmount = new TextField();//label and textfield for the investment amount

        Label yearsLabel = new Label("Years");
        TextField yearsValue = new TextField();//label and textfield for the years of investment

        Label annualLabel = new Label("Annual Interest Rate");
        TextField AIR = new TextField();//label and textfield for the annual interest rate

        Label futureValueLabel = new Label("Future Value");
        TextField futureValue = new TextField();//label and textfield for Future value
        futureValue.setDisable(true);//set disabled so it's only there for the answer to appear

        Button calculate = new Button("Calculate");//Button to calculate
        calculate.setOnAction(event -> {//When the button is press...

            investmentAmount = Double.parseDouble(investAmount.getText());//assign values from their respected textbox
            years = Double.parseDouble(yearsValue.getText());
            monthlyInterestRate = Double.parseDouble(AIR.getText())/1200;
            //to convert from anual interest rate to monthly interest rate, AIR is divided by 1200
            //1200 --> 12 months in a year and percentage conversion
            double fv = investmentAmount*(Math.pow((1 + monthlyInterestRate), (years*12)));//Calculation
            fv = (double)Math.round(fv * 100) /100;//Rounding the answer to two decimal points

            futureValue.setText(String.valueOf(fv));//Displaying the answer

        });

        pane.add(invsLabel, 0, 0);
        pane.add(investAmount, 1, 0);
        pane.add(yearsLabel, 0, 1);
        pane.add(yearsValue, 1, 1);
        pane.add(annualLabel, 0, 2);
        pane.add(AIR, 1, 2);
        pane.add(futureValueLabel, 0, 3);
        pane.add(futureValue, 1, 3);
        pane.add(calculate, 0, 4);

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }



    public static void main(String[] args) { Application.launch(args); }
}
