import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Vec2D
{

    public DoubleProperty x = new SimpleDoubleProperty(); //x coordinate
    public DoubleProperty y = new SimpleDoubleProperty(); //y coordinate

    public Vec2D()
    {
        this.x.set(0);
        this.y.set(0);
    }

    public Vec2D(Vec2D v)
    {
        this.x = v.x;
        this.y = v.y;
    }

    public Vec2D(double x, double y)
    {
        this.x.set(x);
        this.y.set(y);
    }

    /**
     * Performs addition of two vector.
     *
     * @param addVector
     * @return A vector holding the product
     */
    public Vec2D add(Vec2D addVector)
    {
        this.x.set(this.x.get() + addVector.x.get());
        this.y.set(this.y.get() + addVector.x.get());
        return this;
    }

    /**
     * Add the given integer values
     *
     * @param x coordinate
     * @param y coordinate
     * @return A vector holding the product
     */
    public Vec2D add(double x, double y)
    {
        this.x.set(this.x.get() + x);
        this.y.set(this.y.get() + y);
        return this;
    }

    /**
     * subtracts the given integer values
     *
     * @param x coordinate
     * @param y coordinate
     * @return A vector holding the product
     */

    public Vec2D subtract(double x, double y)
    {
        // Vect2D(this.x -= x, this.y -= y);
        this.x.set(this.x.get() - x);
        this.y.set(this.y.get() - y);
        return this;
    }

    /**
     * Performs subtraction of two vectors
     *
     * @param subVector
     */
    public Vec2D subtract(Vec2D subVector)
    {
        this.x.set(this.x.get() - subVector.x.get());
        this.y.set(this.x.get() - subVector.y.get());
        return this;
    }

    public Vec2D division(double divider)
    {
        this.x.set(this.x.get() / divider);
        this.y.set(this.y.get() / divider);
        return this;
    }

    public double absX()
    {
        return Math.sqrt(Math.pow(this.x.get(), 2));
    }

    public double absY()
    {
        return Math.sqrt(Math.pow(this.y.get(), 2));
    }

    public double abs()
    {
        return Math.sqrt(Math.pow(this.x.get(), 2) + Math.pow(this.y.get(), 2));
    }

    @Override
    public String toString()
    {
        return String.format("%3.3f; %3.3f", this.x.get(), this.y.get());
    }
}

