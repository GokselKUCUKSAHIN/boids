import java.util.ArrayList;

public class Flock
{
    public static ArrayList<Boid> boids = new ArrayList<>();
    private static boolean fovState = false;
    //
    public Flock()
    {
        for (int i = 0; i < 200; i++)
        {
            boids.add(new Boid());
        }
    }

    public void setRandomVel()
    {
        for (Boid boid : boids)
        {
            boid.setRandVel();
        }
    }

    public static void showFOV()
    {
        if (!fovState)
        {
            for (Boid boid : boids)
            {
                Main.child.add(boid.fov);
                boid.fov.toBack();
            }
        }
        fovState = true;
    }

    public static void hideFOV()
    {
        if (fovState)
        {
            for (Boid boid : boids)
            {
                Main.child.remove(boid.fov);
            }
        }
        fovState = false;
    }
}