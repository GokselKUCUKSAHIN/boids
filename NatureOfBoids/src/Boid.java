import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

import java.util.ArrayList;

public class Boid extends Group
{

    private static final double PERCEPTION_RADIUS = 50;
    private static final double PERCEPTION_RADIUS_SQ = PERCEPTION_RADIUS * PERCEPTION_RADIUS;

    // VECTORS
    private Vec2D pos = new Vec2D(Utils.getRandom(Main.width), Utils.getRandom(Main.height));
    private Vec2D vel = Vec2D.random2D().multiply(Utils.getRandom(0.5, 1.5));
    private Vec2D acc = new Vec2D();

    // PROPERTIES
    private DoubleProperty angle = new SimpleDoubleProperty();
    private double maxSpeed = 3.1;

    // EXTRAS
    public Group fov = new Group();

    public Boid()
    {
        this.rotateProperty().bind(angle);
        draw();
    }

    private Vec2D align(Boid[] boids)
    {
        // Create new Vector.
        Vec2D steering = new Vec2D();
        // if boids array not null
        if (boids.length != 0)
        {
            // And calculate average Vector.
            for (Boid other : boids)
            {
                // Arithmetic Mean
                steering.add(other.vel);
            }
            steering.divide(boids.length);
            steering.subtract(this.vel).divide(11); //7
            //this.acc = steering;
        }
        return steering;
    }

    private Vec2D cohesion(Boid[] boids)
    {
        // Create new Vector.
        Vec2D steering = new Vec2D();
        // if boids array not null
        if (boids.length != 0)
        {
            // And calculate average Vector.
            for (Boid other : boids)
            {
                // Arithmetic Mean
                steering.add(other.pos);
            }
            steering.divide(boids.length);
            steering.subtract(this.pos);
            steering.setMagnitude(1.7);
            steering.subtract(this.vel).divide(11);
            //this.acc = steering;
        }
        return steering;
    }

    private Vec2D seperation(Boid[] boids)
    {
        // Create new Vector.
        Vec2D steering = new Vec2D();

        // if boids array not null
        if (boids.length != 0)
        {
            // And calculate average Vector.
            for (Boid other : boids)
            {
                // Arithmetic Mean
                Vec2D diff = Vec2D.subtract(this.pos, other.pos);
                diff.divide(this.pos.distance(other.pos)); //slow code
                steering.add(diff);
            }
            steering.divide(boids.length);
            steering.multiply(6);
            steering.subtract(this.vel).divide(11);
        }
        return steering;
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
                // Check for angles to FOV
                //double difAngle = this.pos.getAngle() - flockmate.get
                //if(difAngle )
                //{
                // Add list named flockmates
                flockmates.add(flockmate);
                //}
            }
        }
        // and Return as Static Array :)
        return flockmates.toArray(new Boid[flockmates.size()]);
    }

    public void update()
    {
        Boid[] flockMates = getFlockmates();
        Vec2D alignForce = align(flockMates);
        Vec2D cohesionForce = cohesion(flockMates);
        Vec2D seperationForce = seperation(flockMates);
        this.acc.add(alignForce);
        this.acc.add(cohesionForce);
        this.acc.add(seperationForce);
        //System.out.println(Arrays.toString(acc.getComponents()));
        angle.set(0);
        angle.set(360 - (vel.getAngleDegree() - 90));
        //
        pos.add(vel);
        vel.add(acc);
        this.acc.setZero();
        vel.setMagnitude(maxSpeed);
        stayInStage(Main.width, Main.height);
    }

    private void draw()
    {
        // FIELD OF VIEW
        Circle circle = new Circle(0, 0, PERCEPTION_RADIUS, Color.color(1, 1, 0.0, 0.3));
        //Arc circle = new Arc(0,0,PERCEPTION_RADIUS, PERCEPTION_RADIUS,315,270);
        //circle.setFill(Color.color(1,1,0,0.1));
        //circle.setType(ArcType.ROUND);
        circle.setStrokeWidth(1.2);
        circle.setStroke(Color.GRAY);
        this.fov.getChildren().add(circle);
        this.fov.layoutXProperty().bind(pos.x);
        this.fov.layoutYProperty().bind(pos.y);
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
        double tolerance = 35;
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