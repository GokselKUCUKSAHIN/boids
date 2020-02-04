import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeType;

public class Boid
{

    // constant speed and variable steering;
    //

    private Group body = new Group();
    private Point2D pos = new Point2D(0, 0);
    private Point2D acc = new Point2D(0, 0);
      private Point2D vel = new Point2D(0, 0);
    private double angle = 90;

    public Boid()
    {
        pos = new Point2D(400, 400); // default spawn point
        body.setLayoutX(400);
        body.setLayoutY(400);
        draw();
    }

    public Node getNode()
    {
        return this.body;
    }

    public void applyForce()
    {
        double xComponent = Math.cos(angle);
        double yComponent = Math.sin(angle);
        this.acc = new Point2D(xComponent, -yComponent);
    }

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
        // PHYSICS
        vel = vel.add(acc);
        pos = pos.add(vel);
        vel = vel.multiply(0.9); // decay multiplier
        acc = acc.multiply(0);
        this.body.setLayoutX(pos.getX());
        this.body.setLayoutY(pos.getY());

        body.setRotate(0);
        body.setRotate(360 - (angle - 90));
        System.out.println("Angle = " + angle);
    }

    public void turnLeft()
    {
        angle += 2;
    }

    public void turnRight()
    {
        angle -= 2;
    }

}
