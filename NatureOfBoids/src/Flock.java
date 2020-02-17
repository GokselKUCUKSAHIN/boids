import java.util.ArrayList;

public class Flock
{

    public static ArrayList<Boid> boids = new ArrayList<>();

    public Flock()
    {
        for (int i = 0; i < 100; i++)
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
}
