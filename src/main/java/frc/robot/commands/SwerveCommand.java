package frc.robot.commands;

import com.fasterxml.jackson.databind.ext.SqlBlobSerializer;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.SwerveDriveSubsystem;
import frc.robot.subsystems.SwerveModule;

import java.util.function.DoubleSupplier;

public class SwerveCommand extends Command {
    private DoubleSupplier angleSupplier;
    private DoubleSupplier speedSupplier;

    SwerveDriveSubsystem swerveDriveSubsystem = SwerveDriveSubsystem.getInstance();

    public SwerveCommand(DoubleSupplier angleSupplier, DoubleSupplier speedSupplier){
        this.angleSupplier = angleSupplier;
        this.speedSupplier = speedSupplier;
        addRequirements(swerveDriveSubsystem);
    }

    @Override
    public void initialize(){
        swerveDriveSubsystem.setWheelSpeeds(0.0);
        swerveDriveSubsystem.setLinearAngles(0.0);
    }

    @Override
    public void execute(){
        swerveDriveSubsystem.setLinearAngles(angleSupplier.getAsDouble());
        swerveDriveSubsystem.setWheelSpeeds(speedSupplier.getAsDouble());
    }

    @Override
    public void end(boolean interrupted){

    }

}
