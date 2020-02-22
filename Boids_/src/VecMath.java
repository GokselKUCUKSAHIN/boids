public class VecMath
{

    public static Vec2D endPoint(double x, double y, double angle, double radius)
    {
        x += (radius * Math.cos(angleToRadian(angle)));
        y -= (radius * Math.sin(angleToRadian(angle)));
        return new Vec2D(x, y);
    }

    public static Vec2D endPoint(Vec2D point, double angle, double radius)
    {
        return endPoint(point.x.get(), point.y.get(), angle, radius);
    }

    public static double angleToRadian(double angle)
    {
        return (Math.PI / 180.0) * angle;
    }

    public static double radianToAngle(double radian)
    {
        return radian * (180.0 / Math.PI);
    }

    public static double calculateAngle(double p1X, double p1Y, double p2X, double p2Y)
    {
        double dx = Math.abs(p1X - p2X);
        double dy = Math.abs(p1Y - p2Y);
        //System.out.println(dx+": "+dy);
        double a = radianToAngle(Math.atan(dy / dx));
        if (p1X - p2X >= 0)
        {
            //II - III
            if (p1Y - p2Y >= 0)
            {
                //II Region
                return 180 - a;
            } else
            {
                //III Region
                return 180 + a;
            }
        } else
        {
            // I - IV
            if (p1Y - p2Y >= 0)
            {
                //I Region
                return a;
            } else
            {
                //IV Region
                return 360 - a;
            }
        }
    }

    public static double calculateAngle(Vec2D v1, Vec2D v2)
    {
        return calculateAngle(v1.x.get(), v1.y.get(), v2.x.get(), v2.y.get());
    }


}