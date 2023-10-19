// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.ModuleConstants;
import frc.robot.subsystems.SwerveModule;

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
  public static AHRS m_gyro;

  @Override
  public void robotInit() {
    module = new SwerveModule(ModuleConstants.kMOD_1_Constants);
    m_gyro = new AHRS();
  }

  @Override
  public void autonomousPeriodic() {
    module.setDesiredState(new SwerveModuleState(0.0, new Rotation2d(0.0)));
  }

  @Override
  public void robotPeriodic() {
    SwerveModuleState state = module.getState();
    // SmartDashboard.putNumber("Drive Encoder",
    // module.m_driveMotorEncoder.getPosition());
    // SmartDashboard.putNumber("Turning Motor Encoder",
    SmartDashboard.putNumber("Module State - Velocity: ", state.speedMetersPerSecond);
    SmartDashboard.putNumber("Module State - Angle: ",state.angle.getDegrees());

    //module.setPIDposition(0.0, 0.0);
  }
}
