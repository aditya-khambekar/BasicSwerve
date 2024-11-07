package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.SwerveDriveSubsystem;

import java.util.function.DoubleSupplier;

public class RotationSwerveCommand extends Command {
    private DoubleSupplier speedSupplier;
    SwerveDriveSubsystem swerveDriveSubsystem = SwerveDriveSubsystem.getInstance();

    public RotationSwerveCommand(DoubleSupplier speedSupplier) {
        this.speedSupplier = speedSupplier;
        addRequirements(swerveDriveSubsystem);
    }

    @Override
    public void initialize(){
        swerveDriveSubsystem.setWheelSpeeds(0.0);
    }

    @Override
    public void execute(){
        swerveDriveSubsystem.setRotation();
        swerveDriveSubsystem.setRotationalSpeeds(speedSupplier.getAsDouble());
    }

    @Override
    public void end(boolean interrupted){
        swerveDriveSubsystem.stop();
    }

}
