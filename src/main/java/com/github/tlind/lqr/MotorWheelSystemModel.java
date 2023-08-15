package com.github.tlind.lqr;

public class MotorWheelSystemModel {
    private final double[][] A;
    private final double[][] B;
    private final double[][] C;

    public MotorWheelSystemModel(double motorConstant, double momentOfInertia, double wheelRadius, double motorResistance) {
        // State-space matrices
        A = new double[][] {
                {-motorResistance / momentOfInertia, 0},
                {1 / wheelRadius, 0}
        };

        B = new double[][] {
                {motorConstant / momentOfInertia},
                {0}
        };

        C = new double[][] {
                {0, 1}
        };
    }

    public double[] getStateUpdate(double[] state, double motorPower, double output) {
        double[] controlInput = {motorPower};

        // Feedback mechanism: Adjust the state update based on the output
        double[] feedback = {output * 0.01, output * 0.01}; // Feedback scaling factor (adjust as needed)
        double[] nextState = matrixVectorMultiply(A, state);
        nextState = vectorAdd(nextState, matrixVectorMultiply(B, controlInput));
        nextState = vectorAdd(nextState, feedback);

        return nextState;
    }

    public double[] getOutput(double[] state) {
        return matrixVectorMultiply(C, state);
    }

    private double[] matrixVectorMultiply(double[][] matrix, double[] vector) {
        int numRows = matrix.length;
        int numCols = matrix[0].length;

        if (numCols != vector.length) {
            throw new IllegalArgumentException("Matrix and vector dimensions are not compatible.");
        }

        double[] result = new double[numRows];

        for (int i = 0; i < numRows; i++) {
            double sum = 0.0;
            for (int j = 0; j < numCols; j++) {
                sum += matrix[i][j] * vector[j];
            }
            result[i] = sum;
        }

        return result;
    }

    private double[] vectorAdd(double[] vector1, double[] vector2) {
        if (vector1.length != vector2.length) {
            throw new IllegalArgumentException("Vector dimensions are not compatible for addition.");
        }

        double[] result = new double[vector1.length];

        for (int i = 0; i < vector1.length; i++) {
            result[i] = vector1[i] + vector2[i];
        }

        return result;
    }
}
