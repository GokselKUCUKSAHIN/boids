import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class Barrier extends Group
{

    public static ArrayList<Barrier> barriers = new ArrayList<>();
    public Vec2D pos = new Vec2D();
    public double radius = 29;

    public Barrier()
    {
        // Setup
        draw();
        pos.x.set(Utils.getRandom(Main.width));
        pos.y.set(Utils.getRandom(Main.height));
        this.layoutXProperty().bind(pos.x);
        this.layoutYProperty().bind(pos.y);
        barriers.add(this);
    }

    public double[] getPosition()
    {
        return new double[]{pos.x.get(), pos.y.get()};
    }

    public void reposition(double x, double y)
    {
        this.pos.x.set(x);
        this.pos.y.set(y);
    }


    public void draw()
    {
        // RED Circular Barrier for Boids
        Circle circle = new Circle(0, 0, radius);
        circle.setFill(Color.RED);
        circle.setStrokeWidth(1.2);
        circle.setStroke(Color.DARKRED);
        this.getChildren().add(circle);
    }
}
