package com.github.tlind.bezier;

import java.util.ArrayList;

public class BernsteinBezierGenerator {
    private final Point[] controlPoints;
    private final double[][] binomialCoefficients;

    public BernsteinBezierGenerator(ArrayList<Point> controlPoints) {
        this.controlPoints = controlPoints.toArray(new Point[0]);
        this.binomialCoefficients = generateBinomialTable(this.controlPoints.length - 1);
    }

    private static double[][] generateBinomialTable(int n) {
        double[][] table = new double[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            table[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                table[i][j] = table[i - 1][j - 1] + table[i - 1][j];
            }
        }
        return table;
    }

    public Point[] generate(int steps) {
        Point[] pointsOnCurve = new Point[steps];
        double stepSize = 1.0 / steps;
        int n = controlPoints.length - 1;
        double t = 0;
        for (int step = 0; step < steps; t += stepSize, step++) {
            double x = 0;
            double y = 0;
            double bernsteinValue;
            double tPower = 1;
            double oneMinusTPower = Math.pow(1 - t, n);
            for (int i = 0; i <= n; i++) {
                bernsteinValue = binomialCoefficients[n][i] * oneMinusTPower * tPower;
                x += controlPoints[i].x * bernsteinValue;
                y += controlPoints[i].y * bernsteinValue;

                // Update powers for next iteration
                tPower *= t;
                oneMinusTPower /= (1 - t);
            }
            pointsOnCurve[step] = new Point(x, y);
        }
        return pointsOnCurve;
    }
}