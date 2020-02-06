import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Boid extends Group
{

    // VECTORS
    Vec2D pos = new Vec2D(400, 400);
    Vec2D vel = new Vec2D();
    Vec2D acc = new Vec2D();
    //DoubleProperty px = new SimpleDoubleProperty(0);
    //DoubleProperty py = new SimpleDoubleProperty(0);

    // GRAPHICS
    // Group body = new Group();

    public Boid()
    {
        draw();
    }
    public void update(double x, double y)
    {
        this.pos.x.set(x);
        this.pos.y.set(y);
    }
    public void test()
    {
        this.pos.subtract(100,10);
    }
    private void draw()
    {
        Circle pnt = new Circle(0, 0, 25, Color.SNOW);
        this.getChildren().add(pnt);
        this.layoutXProperty().bind(pos.x);
        this.layoutYProperty().bind(pos.y);
    }
}