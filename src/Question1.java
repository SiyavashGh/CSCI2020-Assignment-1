import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.util.Random;


public class Question1 extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Playing Cards");//sets title for the UI

        Random random = new Random(); //Built-in class Random used to declare to randomize ahead

        ImageView[] cards = new ImageView[54];//ImageView array is used to store all cards' pictures in an array
        for (int i = 1; i < 55; i++){//for loop the size of the array
            cards[i-1] = new ImageView("Cards/" + i + ".png");/*adds card to the ImageView array using
                                                                    the loop as part of the file name
                                                                    The loop goes from 1-54 due to file
                                                                    extensions*/
        }

        HBox pics = new HBox();//Horizontal box declaration to set the pictures next to each other horizontally
        for(int i = 0; i < 3; i++) {//for loop for the 3 pictures
            int number = random.nextInt(54);//random number from 0-53
            while(cards[number] != null) {//checks if card exists
                pics.getChildren().add(cards[number]);//adds picture to the horizontal box
                cards[number] = null;//sets the value of that card to nothing so it doesn't get selected again
            }
        }

        Scene scene = new Scene(pics);//Sets the scene to the horizontal box
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    public static void main(String[] args) {
        Application.launch(args);
    }




}
