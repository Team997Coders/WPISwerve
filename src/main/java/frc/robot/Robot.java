// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;

import com.revrobotics.CANSparkMax;
import com.revrobotics.REVLibError;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxAbsoluteEncoder;
import com.revrobotics.SparkMaxAlternateEncoder;
import com.revrobotics.SparkMaxAnalogSensor;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxAbsoluteEncoder.Type;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Joystick m_stick;
  private static final int driveMotorDeviceID = 4;
  private static final int turnMotorDeviceID = 5;
  private static int m_moduleID = 3;
  private CANSparkMax m_driveMotor;
  private CANSparkMax m_turnMotor;
  private RelativeEncoder m_driveMotorEncoder;
  private RelativeEncoder m_turnMotorEncoder;
  private SparkMaxAbsoluteEncoder m_turnAngleEncoder;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    m_stick = new Joystick(0);
    m_driveMotor = new CANSparkMax(driveMotorDeviceID, MotorType.kBrushless);
    m_driveMotor.restoreFactoryDefaults();
    m_driveMotor.clearFaults();
    if(m_driveMotor.setIdleMode(IdleMode.kCoast) != REVLibError.kOk){
      SmartDashboard.putString("Drive Motor Idle Mode", "Error");
    }

    m_turnMotor = new CANSparkMax(turnMotorDeviceID, MotorType.kBrushless);
    m_turnMotor.restoreFactoryDefaults();
    m_turnMotor.clearFaults();
    if(m_turnMotor.setIdleMode(IdleMode.kCoast) != REVLibError.kOk){
      SmartDashboard.putString("Turn Motor Idle Mode", "Error");
    }

    m_driveMotorEncoder = m_driveMotor.getEncoder();
    m_turnMotorEncoder = m_turnMotor.getEncoder();

    m_turnAngleEncoder = m_turnMotor.getAbsoluteEncoder(Type.kDutyCycle);
    resetEncoders();
    stopAll();
  }

  public void resetEncoders() {
    m_driveMotorEncoder.setPosition(0);
    m_turnMotorEncoder.setPosition(0);
  }

  public void stopAll() {
    m_driveMotor.set(0);
    m_turnMotor.set(0);
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("Drive Encoder", m_driveMotorEncoder.getPosition());
    SmartDashboard.putNumber("Turning Motor Encoder", m_turnMotorEncoder.getPosition());
    SmartDashboard.putNumber("Turning Angle Encoder", m_turnAngleEncoder.getPosition());
  }
}
