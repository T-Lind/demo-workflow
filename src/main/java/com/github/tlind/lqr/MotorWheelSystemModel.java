package com.github.tlind.lqr;

/**
 * Represents a simplified model of a motor-wheel system for control applications.
 */
public class MotorWheelSystemModel {
    private final double[][] A;
    private final double[][] B;
    private final double[][] C;

    /**
     * Constructs a MotorWheelSystemModel with specified parameters.
     *
     * @param motorConstant   The motor constant of the system.
     * @param momentOfInertia The moment of inertia of the system.
     * @param wheelRadius     The radius of the wheel.
     * @param motorResistance The resistance of the motor.
     */
    public MotorWheelSystemModel(double motorConstant, double momentOfInertia, double wheelRadius, double motorResistance) {
        // State-space matrices
        A = new double[][]{
                {-motorResistance / momentOfInertia, 0},
                {1 / wheelRadius, 0}
        };

        B = new double[][]{
                {motorConstant / momentOfInertia},
                {0}
        };

        C = new double[][]{
                {0, 1}
        };
    }

    /**
     * Computes the next state of the system given the current state, motor power, and output.
     *
     * @param state      The current state of the system.
     * @param motorPower The motor power applied to the system.
     * @param output     The output of the system (angle, encoder ticks,etc.).
     * @return The updated state of the system.
     */
    public double[] getStateUpdate(double[] state, double motorPower, double output) {
        double[] controlInput = {motorPower};

        // Feedback mechanism: Adjust the state update based on the output
        double[] feedback = {output * 0.3, output * 0.3}; // Feedback scaling factor (adjust as needed)
        double[] nextState = matrixVectorMultiply(A, state);
        nextState = vectorAdd(nextState, matrixVectorMultiply(B, controlInput));
        nextState = vectorAdd(nextState, feedback);

        return nextState;
    }

    /**
     * Computes the output of the system given the current state.
     *
     * @param state The current state of the system.
     * @return The output of the system.
     */
    public double[] getOutput(double[] state) {
        return matrixVectorMultiply(C, state);
    }

    /*
     * The following methods are helper methods for matrix and vector operations.
     */

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
