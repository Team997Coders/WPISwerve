// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.ModuleConstants;
import frc.robot.subsystems.SwerveModule;
import frc.robot.utils.NavXSwerve;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SerialPort;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the
 * name of this class or
 * the package after creating this project, you must also update the
 * build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public SwerveModule module;
  // The gyro sensor
  public static NavXSwerve m_gyro;

  public XboxController m_driverController = new XboxController(0);

  @Override
  public void robotInit() {
    module = new SwerveModule(ModuleConstants.kMOD_3_Constants);
    m_gyro = new NavXSwerve(SerialPort.Port.kMXP);
  }

  @Override
  public void autonomousPeriodic() {
    module.setDesiredState(new SwerveModuleState(0.0, new Rotation2d(0.0)));
  }

  @Override
  public void teleopPeriodic() {
    double joy_angle = m_driverController.getLeftX() * Math.PI;
    SmartDashboard.putNumber("Joystick Angle", joy_angle);
    module.setDesiredState(new SwerveModuleState(0.0, new Rotation2d(joy_angle)));
    SmartDashboard.putNumber("Raw Joy Angle:", new Rotation2d(joy_angle).getDegrees());
  }

  @Override
  public void robotPeriodic() {
    // swerve module state
    SwerveModuleState state = module.getState();
    SmartDashboard.putNumber("Module State - Velocity: ", state.speedMetersPerSecond);
    SmartDashboard.putNumber("Module State - Angle: ", state.angle.getDegrees());
    SmartDashboard.putNumber("Module Position - Distance: ", module.getPosition().distanceMeters);
    SmartDashboard.putNumber("Module Position - Angle: ", module.getPosition().angle.getDegrees());
    // raw hardware
    SmartDashboard.putNumber("Raw Angle", module.getAngle());
    SmartDashboard.putNumber("Raw Turning Motor Angle", module.getTurningEncoder());
    // turning PID information
    SmartDashboard.putNumber("PID/Setpoint", module.m_turningPIDController.getSetpoint().position);
    SmartDashboard.putNumber("PID/Error", module.m_turningPIDController.getPositionError());
    SmartDashboard.putNumber("PID/Goal", module.getTurnGoal());
    //
    SmartDashboard.putNumber("Raw Drive Position", module.getDriveEncoderPosition());
    SmartDashboard.putNumber("Raw Drive Velocity", module.getDriveEncoderVelocity());
    //
    SmartDashboard.putNumber("Gyro YAW", m_gyro.getYaw());
    SmartDashboard.putNumber("Gyro Robotation", m_gyro.getRotation3d().getAngle());
  }
}
