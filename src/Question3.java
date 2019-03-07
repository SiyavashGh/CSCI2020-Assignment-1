import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Question3 extends Application {
    //The following variables are set to private and declared outside of start method so they can be used anywhere
    private Pane pane = new Pane();
    private double[] pointsx = new double[3];//Array for double variables stores x components of the 3 corners
    private double[] pointsy = new double[3];//Array for double variables stores y components of the 3 corners
    private Circle[] points = new Circle[3];//Array for the 3 corners of the triangle
    private Line[] lines = new Line[3];//Array for the lines
    private Text[] angles = new Text[3];//Text array for the 3 angles


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Question 3");//Sets title of the UI

        Circle bigCircle = new Circle(150, 150, 100.0);//Creates the main circle
        bigCircle.setFill(Color.TRANSPARENT);//Sets color to transparent
        bigCircle.setStroke(Color.BLACK);//Colors the outline of the shape black so it's visible
        pane.getChildren().addAll(bigCircle);//adds it to the Pane so it can be displayed later

        //The starting points for the three corners of the triangles
        pointsx[0] = 50.0;
        pointsy[0] = 150.0;
        pointsx[1] = 150.0;
        pointsy[1] = 50.0;
        pointsx[2] = 250.0;
        pointsy[2] = 150.0;

        for(int i = 0; i < 3; i++){//for loop that runs 3 times for the 3 corners and 3 lines of the triangle
           points[i] = new Circle(pointsx[i], pointsy[i], 8, Color.RED);//creates the circle and colors it red
           if(i != 2) {//if it's the not the end of the loop
               //sets the start and finish of the line
               lines[i] = new Line(pointsx[i], pointsy[i], pointsx[i + 1], pointsy[i + 1]);
           }
           else{
               //if it's the last line, it goes from corner 3 to corner 1
               lines[i] = new Line(pointsx[i], pointsy[i], pointsx[i - 2], pointsy[i - 2]);
           }
           pane.getChildren().addAll(points[i], lines[i]);//adds the lines and red circles to the pane
        }

        angle();//calls for the angle method to calculate the angles

        /*
        The dragged methods ahead are programmed to find the angle from the center of the big circle to where
         the mouse is located. Then use that angle to find the x(the center of big circle + radius of Circle*cos(angle))
         and y(the center of the big circle + radius of the Circle*sin(angle))

        (Sin and Cos methods for y and x are from the equation of the circle)

         Then we use x and y and assign them to the corner we're trying to move
         At the end, the method line is called to reset the lines and the angles

        */
        points[0].setOnMouseDragged(event -> {
            double r = Math.atan2(event.getY()-bigCircle.getCenterY(), event.getX()-bigCircle.getCenterX());
            double x = bigCircle.getCenterX() + bigCircle.getRadius()*Math.cos(r);
            double y = bigCircle.getCenterY() + bigCircle.getRadius()*Math.sin(r);
            points[0].setCenterX(x);
            points[0].setCenterY(y);
            line();
        });

        points[1].setOnMouseDragged(event -> {
            double r= Math.atan2(event.getY()-bigCircle.getCenterY(), event.getX()-bigCircle.getCenterX());
            double x = bigCircle.getCenterX() + bigCircle.getRadius()*Math.cos(r);
            double y = bigCircle.getCenterY() + bigCircle.getRadius()*Math.sin(r);
            points[1].setCenterX(x);
            points[1].setCenterY(y);
            line();
        });

        points[2].setOnMouseDragged(event -> {
            double r = Math.atan2(event.getY()-bigCircle.getCenterY(), event.getX()-bigCircle.getCenterX());
            double x = bigCircle.getCenterX() + bigCircle.getRadius()*Math.cos(r);
            double y = bigCircle.getCenterY() + bigCircle.getRadius()*Math.sin(r);
            points[2].setCenterX(x);
            points[2].setCenterY(y);
            line();
        });




        Scene scene = new Scene(pane, 300, 300);//Create a scene to display pane at 300x300
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);//It doesn't allow the user to resize the window
        primaryStage.show();
    }

    private void line(){
        //This is a method used to reposition the lines of the triangle to their respective corners
        update();//update method is used to update the arrays pointsx and pointsy

        lines[0].setStartX(pointsx[0]);
        lines[0].setStartY(pointsy[0]);
        lines[2].setEndX(pointsx[0]);
        lines[2].setEndY(pointsy[0]);

        lines[1].setStartX(pointsx[1]);
        lines[1].setStartY(pointsy[1]);
        lines[0].setEndX(pointsx[1]);
        lines[0].setEndY(pointsy[1]);

        lines[2].setStartX(pointsx[2]);
        lines[2].setStartY(pointsy[2]);
        lines[1].setEndX(pointsx[2]);
        lines[1].setEndY(pointsy[2]);

        angle();//At last call angle to update the angles
    }

    private void update(){
        for(int i = 0; i < 3; i++){
        //update is used to update the x components and y components of each corner into the pointsx and pointy arrays
            pointsx[i] = points[i].getCenterX();
            pointsy[i] = points[i].getCenterY();
        }

    }

    private void angle(){
        pane.getChildren().removeAll(angles[0], angles[1], angles[2]);//Removes all text variables displaying the angles

        //the following calculates the length of triangles sides
        double b = Math.sqrt(Math.pow(pointsx[2] - pointsx[0], 2) + Math.pow(pointsy[2] - pointsy[0], 2));
        double a = Math.sqrt(Math.pow(pointsx[2] - pointsx[1], 2) + Math.pow(pointsy[2] - pointsy[1], 2));
        double c = Math.sqrt(Math.pow(pointsx[1] - pointsx[0], 2) + Math.pow(pointsy[1] - pointsy[0], 2));

        //Each angle is calculated and rounded down
        angles[0] = new Text(String.valueOf(
                Math.round(Math.toDegrees(Math.acos((a * a - b * b - c * c) / (-2 * b * c)))*100)/100));
        angles[1] = new Text(String.valueOf(
                Math.round(Math.toDegrees(Math.acos((b * b - a * a - c * c) / (-2 * a * c)))*100)/100));
        angles[2] = new Text(String.valueOf(
                Math.round(Math.toDegrees(Math.acos((c * c - b * b - a * a) / (-2 * a * b)))*100)/100));

        for(int i = 0; i < 3; i++){
            angles[i].setX(pointsx[i] + 10.0);//assign each angle to their respective corner plus 10 so they don't overlap
            angles[i].setY(pointsy[i] + 10.0);
        }
        pane.getChildren().addAll(angles[0],angles[1], angles[2]); // add the angles back with their locations assigned

    }

    public static void main(String[] args) { Application.launch(args); }

}