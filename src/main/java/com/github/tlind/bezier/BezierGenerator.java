package com.github.tlind.bezier;

import java.util.ArrayList;

public class BezierGenerator {
    private ArrayList<Point> controlPoints;

    private ArrayList<Point> pointsOnCurve;

    public BezierGenerator(ArrayList<Point> controlPoints) {
        this.controlPoints = controlPoints;
        pointsOnCurve = new ArrayList<>();
    }


    /**
     * Performs linear interpolation between two points.
     *
     * @param a the first point to interpolate between
     * @param b the second point to interpolate between
     * @param t the interpolation parameter, from 0->1 (0 will return a, 1 will return b, 0.5 is in the middle)
     * @return the interpolated point
     */
    private static Point lerp(Point a, Point b, double t) {
        return new Point((1 - t) * a.x + t * b.x, (1 - t) * a.y + t * b.y);
    }

    /**
     * Performs recursive linear interpolation between a set of points.
     *
     * @param controlPoints points to perform interpolation between, minimum of 1
     * @param t             the time to evaluate the linear interpolation at
     * @return the interpolated point between the control points at time t
     */
    private static Point recursiveLerp(ArrayList<Point> controlPoints, double t) {
        if (controlPoints.size() == 1) {
            return controlPoints.get(0);
        }
        ArrayList<Point> newPoints = new ArrayList<>();
        for (int i = 0; i < controlPoints.size() - 1; i++) {
            newPoints.add(lerp(controlPoints.get(i), controlPoints.get(i + 1), t));
        }
        return recursiveLerp(newPoints, t);
    }

    /**
     * Create the list of interpolated points based on the # of steps
     *
     * @param steps the number of points on the curve to generate. Note these aren't evenly spaced, there are more points
     *              where the curve is more curved, and fewer in straigher sections.
     * @return the new list of points on the curve. They are also stored in the pointsOnCurve ArrayList & getPointsOnCurve()
     */
    public ArrayList<Point> generate(int steps) {
        pointsOnCurve.clear();
        for (double t = 0; t < 1; t += 1.0 / steps) {
            pointsOnCurve.add(recursiveLerp(controlPoints, t));
        }
        return pointsOnCurve;
    }

    public ArrayList<Point> getPointsOnCurve() {
        return pointsOnCurve;
    }
}
