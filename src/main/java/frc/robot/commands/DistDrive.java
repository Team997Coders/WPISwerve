package frc.robot.commands;

import java.util.Timer;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class DistDrive extends CommandBase {
    DriveSubsystem driveSubsystem;
    double measurement;
    double setpointMeters;
    double effort;
    double startY;
    SwerveModuleState[] swerveModuleStates = {new SwerveModuleState(),new SwerveModuleState(),new SwerveModuleState(),new SwerveModuleState()};
    PIDController pidController = new PIDController(0.01, 0.01, 0.01);

    public DistDrive(DriveSubsystem driveSubsystem, double setpointMeters){
        this.driveSubsystem = driveSubsystem;
        this.setpointMeters = setpointMeters;
    }
    @Override
    public void initialize(){
        startY = driveSubsystem.getPose().getY();
    }
    @Override
    public void execute(){
        measurement = driveSubsystem.getPose().getY() - startY;
        effort = pidController.calculate(measurement, setpointMeters);
        swerveModuleStates[0].speedMetersPerSecond = effort;
        swerveModuleStates[1].speedMetersPerSecond = effort;
        swerveModuleStates[2].speedMetersPerSecond = effort;
        swerveModuleStates[3].speedMetersPerSecond = effort;
        driveSubsystem.setModuleStates(swerveModuleStates);        
    }
}
