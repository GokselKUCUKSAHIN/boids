import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeType;

import java.util.ArrayList;

public class Boid extends Group
{

    private static final double PERCEPTION_RADIUS = 100;
    private static final double PERCEPTION_RADIUS_SQ = PERCEPTION_RADIUS * PERCEPTION_RADIUS;

    // VECTORS
    Vec2D pos = new Vec2D(400, 400);
    Vec2D vel = Vec2D.random2D().multiply(Utils.getRandom(0.5, 1.5));
    Vec2D acc = new Vec2D();
    DoubleProperty angle = new SimpleDoubleProperty();

    public Boid()
    {
        this.rotateProperty().bind(angle);


        draw();
    }

    private void align(Boid[] boids)
    {
        // if boids array not null
        if (boids.length != 0)
        {
            // Create new Vector.
            Vec2D avg = new Vec2D();
            // And calculate average Vector.
            for (Boid other : boids)
            {
                avg.add(other.vel);
            }
            avg.divide(boids.length);
            //this.vel = avg;
        }
    }

    private Boid[] getFlockmates()
    {
        ArrayList<Boid> flockmates = new ArrayList<>();
        // For All Boids (Flock)
        for (Boid flockmate : Flock.boids)
        {
            // In distance(squared) lesser or equal perception.
            double distSq = this.pos.distanceSq(flockmate.pos);
            if (flockmate != this && distSq <= PERCEPTION_RADIUS_SQ)
            {
                // Add list named flockmates
                flockmates.add(flockmate);
            }
        }
        // and Return as Static Array :)
        return flockmates.toArray(new Boid[flockmates.size()]);
    }

    public void update()
    {
        align(getFlockmates());
        angle.set(360 - (vel.getAngleDegree() - 90));
        //
        pos.add(vel);
        vel.add(acc);
        stayInStage(Main.width, Main.height);
    }

    private void draw()
    {

        // TRIANGLE
        Polygon triangle = new Polygon(new double[]{0, -30, -11, 0, 11, 0});
        triangle.setFill(Color.color(0.76, 0.76, 0.76));
        triangle.setStroke(Color.color(0.17, 0.17, 0.17));
        triangle.setStrokeType(StrokeType.CENTERED);
        triangle.setStrokeWidth(1.4);
        this.getChildren().add(triangle);
        /*
        Circle pnt = new Circle(0, 0, 10, Color.SNOW);
        this.getChildren().add(pnt);
        */
        //
        this.layoutXProperty().bind(pos.x);
        this.layoutYProperty().bind(pos.y);
    }

    private void stayInStage(double width, double height)
    {
        double tolerance = 55;
        // X
        if (this.pos.x.get() > width + tolerance)
        {
            // Teleport to Left Side
            this.pos.x.set(-tolerance);
        } else if (this.pos.x.get() < -tolerance)
        {
            // Teleport to Right Side
            this.pos.x.set(width + tolerance);
        }

        // Y
        if (this.pos.y.get() > height + tolerance)
        {
            // Teleport to Upper Side
            this.pos.y.set(-tolerance);
        } else if (this.pos.y.get() < -tolerance)
        {
            // Teleport to Down Side
            this.pos.y.set(height + tolerance);
        }
    }
    //

    public void setRandVel()
    {
        this.vel = Vec2D.random2D().multiply(Utils.getRandom(0.5, 1.5));
    }
}