import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeType;

import java.util.ArrayList;

public class Boid
{

    // constant speed and variable steering;
    //
    public static ArrayList<Boid> boids = new ArrayList<>();
    private Group body = new Group();
    //private Point2D pos = new Point2D(0, 0);
    private double posX;
    private double posY;
    private double speed = 3;
    private double angle = 90;
    //
    private double targetX = -1;
    private double targetY = -1;

    public Boid(double x, double y)
    {
        //pos = new Point2D(400, 400); // default spawn point
        posX = x;
        posY = y;
        body.setLayoutX(posX);
        body.setLayoutY(posY);
        draw();
        boids.add(this);
    }

    public Node getNode()
    {
        return this.body;
    }

    /*
    public void applyForce()
    {
        double xComponent = Math.cos(angle);
        double yComponent = Math.sin(angle);
        this.acc = new Point2D(xComponent, -yComponent);
    }
    */

    private void draw()
    {
        // its a simple triangle object
        // based arround center point 'pos'
        // semi light grey color
        Polygon triangle = new Polygon(new double[]{0, -30, -11, 0, 11, 0});
        triangle.setFill(Color.color(0.76, 0.76, 0.76));
        triangle.setStroke(Color.color(0.17, 0.17, 0.17));
        triangle.setStrokeType(StrokeType.CENTERED);
        triangle.setStrokeWidth(1.4);
        body.getChildren().addAll(triangle);
    }


    public void update()
    {
        /*
        if (targetX != -1 || targetY != -1)
        {
            double tarAngle = Utils.calculateAngle(posX, posY, targetX, targetY);
            if(Math.abs(angle - tarAngle) > 2)
            {

            }
        }*/
        //
        if (targetX != -1)
        {
            angle = Utils.calculateAngle(posX, posY, targetX, targetY);
        }
        //
        Point2D next = Utils.endPoint(posX, posY, angle, speed);
        posX = next.getX();
        posY = next.getY();
        //
        this.body.setLayoutX(posX);
        this.body.setLayoutY(posY);
        //
        body.setRotate(0);
        body.setRotate(360 - (angle - 90));
        System.out.println("Angle = " + angle);
    }

    public void setTarget(double x, double y)
    {
        this.targetX = x;
        this.targetY = y;
    }

    public void turnLeft()
    {
        angle += 7;
    }

    public void turnRight()
    {
        angle -= 7;
    }

}
