import com.github.tlind.lqr.MotorWheelSystemModel;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class MotorWheelSystemModelTest {
    @Test
    public void testMotorWheelSystemModel() {
        // Define system parameters
        double motorConstant = 0.1; // Example motor constant
        double momentOfInertia = 0.05; // Example moment of inertia
        double wheelRadius = 0.1; // Example wheel radius
        double initialAngle = 0.0; // Initial angle of the wheel
        double initialVelocity = 0.0; // Initial angular velocity of the motor
        double motorPower = 0.5; // Example motor power
        double motorResistance = 0.1; // Example motor resistance

        // Create instance of MotorWheelSystemModel
        MotorWheelSystemModel systemModel = new MotorWheelSystemModel(motorConstant, momentOfInertia, wheelRadius, motorResistance);

        // Simulate the system over time steps with random variations
        double[] state = {initialVelocity, initialAngle}; // Initial state
        int numSteps = 10; // Number of simulation steps
        double variationStdDev = 0.02; // Standard deviation for random state variations
        Random random = new Random();

        for (int step = 0; step < numSteps; step++) {
            // Simulate random variations in the state
            double[] variation = {
                    random.nextGaussian() * variationStdDev,
                    random.nextGaussian() * variationStdDev
            };
            state[0] += variation[0];
            state[1] += variation[1];

            // Compute next state and output
            double[] output = systemModel.getOutput(state);
            double[] nextState = systemModel.getStateUpdate(state, motorPower, output[0]); // Pass output to state update

            // Print current state, variations, output, and next state
            System.out.println("Step: " + step);
            System.out.println("State: [" + nextState[0] + ", " + nextState[1] + "]");
            System.out.println("State Variations: [" + variation[0] + ", " + variation[1] + "]");
            System.out.println("Output: " + output[0]);

            // Update state for the next step
            state = nextState;
        }
    }
}
