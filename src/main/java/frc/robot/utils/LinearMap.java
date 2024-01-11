package frc.robot.utils;

public class LinearMap {
    /**
     * Linear data translation. Copied (with appropriate license) from the Arduino
     * source.
     * 
     * @param Input   Variable
     * @param in_min  expected minimum input value
     * @param in_max  expected maximum input value
     * @param out_min desired minimum output value
     * @param out_max desired maximum output value
     * @return
     */

    //A function that ensures an angle is between -PI and PI
    public static double boundAngle(double angle) {
        while (angle > Math.PI) {
            angle -= 2 * Math.PI;
        }
        while (angle < -Math.PI) {
            angle += 2 * Math.PI;
        }
        return angle;
    }

    //This function maps a value from one range to another
    public static double rescale(double x, double in_min, double in_max, double out_min, double out_max) {
        //This is the equation for mapping a value from one range to another
        double in_range = in_max - in_min;
        double out_range = out_max - out_min;
        double in_scale = (x - in_min) / in_range;
        double output = in_scale * out_range;
        return output;
        //return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
    }
}
