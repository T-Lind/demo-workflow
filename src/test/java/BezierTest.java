import com.github.tlind.bezier.BernsteinBezierGenerator;
import com.github.tlind.bezier.BezierGenerator;
import com.github.tlind.bezier.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BezierTest {

    private ArrayList<Point> controlPoints;

    @BeforeEach
    public void setUp() {
        // Initialize with some sample control points, you can adjust these or randomize them
        controlPoints = new ArrayList<>();
        controlPoints.add(new Point(0, 0));
        controlPoints.add(new Point(1, 2));
        controlPoints.add(new Point(2, 2));
        controlPoints.add(new Point(3, 0));
    }

    @Test
    public void testPerformanceAndConsistency() {
        BezierGenerator bezier = new BezierGenerator(controlPoints);
        BernsteinBezierGenerator bernsteinBezier = new BernsteinBezierGenerator(controlPoints);

        int steps = 1_000_000; // Adjust the number of steps as needed
        ArrayList<Point> pointsFromBezier;
        Point[] pointsFromBernstein;

        double epsilon = 1e-6; // The margin of error allowed for double comparisons

        // Measure computation time for BezierGenerator
        long startTimeBezier = System.nanoTime();
        pointsFromBezier = bezier.generate(steps);
        long endTimeBezier = System.nanoTime();

        // Measure computation time for BernsteinBezierGenerator
        long startTimeBernstein = System.nanoTime();
        pointsFromBernstein = bernsteinBezier.generate(steps);
        long endTimeBernstein = System.nanoTime();

        // Print the computation times
        System.out.println("BezierGenerator computation time: " + (endTimeBezier - startTimeBezier) / 1e6 + " ms");
        System.out.println("BernsteinBezierGenerator computation time: " + (endTimeBernstein - startTimeBernstein) / 1e6 + " ms");

        // Check consistency between the two methods
        for (int i = 0; i < steps; i++) {
            Point p1 = pointsFromBezier.get(i);
            Point p2 = pointsFromBernstein[i];

            assertTrue(Math.abs(p1.x - p2.x) < epsilon && Math.abs(p1.y - p2.y) < epsilon,
                    "Points do not match at step " + i + ": " + p1 + " vs " + p2);
        }
    }
}
