# Swerve Module Tester
This is simple code to test out a single swerve module on our test drivetrain.  This not meant to be a drivable controller, just a simple way to verify (using the real Java development pipeline) that we can talk to the module.  The functions to be tested are:

* Instanciation of both motors
  * turnMotor
  * driveMotor
* Instanciation of the encoders
  * turning motor encoder (turnMotorEncoder)
  * analog sensor on turning axis (turnAngleEncoder)
  * drive motor distance encoder (driveEncoder)
* Control to reset encoders
* Dashboard with all encoders
* Methods to move the motors
  * actual control input from Gamepad left and right joysticks
* Method to select which module to control
* (optional) PID control-to-angle for turning motor

Later we will create further testers to verify the full module inclusion for development.

This might be too much for a simple throw-away code repo.  However, it will be a valuable tester.
