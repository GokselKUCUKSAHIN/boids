import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.shape.Polygon;

public class Triangle
{

    Polygon body;

    public Triangle(Point2D p1, Point2D p2, Point2D p3)
    {
        body = new Polygon(
                p1.getX(), p1.getY(),
                p2.getX(), p2.getY(),
                p3.getX(), p3.getY()
        );
    }

}
