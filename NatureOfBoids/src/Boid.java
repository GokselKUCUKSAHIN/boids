import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeType;

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
        Polygon triangle = new Polygon(new double[]{0, -30, -11, 0, 11, 0});
        triangle.setFill(Color.color(0.76, 0.76, 0.76));
        triangle.setStroke(Color.color(0.17, 0.17, 0.17));
        triangle.setStrokeType(StrokeType.CENTERED);
        triangle.setStrokeWidth(1.4);
        //Circle pnt = new Circle(0, 0, 25, Color.SNOW);
        this.getChildren().add(triangle); //pnt
        this.layoutXProperty().bind(pos.x);
        this.layoutYProperty().bind(pos.y);
    }
}