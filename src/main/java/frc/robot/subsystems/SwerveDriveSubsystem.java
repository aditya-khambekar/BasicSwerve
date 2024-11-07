package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import java.util.Objects;

public class SwerveDriveSubsystem extends SubsystemBase {
    private static volatile SwerveDriveSubsystem instance;
    private SwerveModule FrontLeft, FrontRight, BackLeft, BackRight;

    public static synchronized SwerveDriveSubsystem getInstance(){
        return instance = Objects.requireNonNullElseGet(instance, SwerveDriveSubsystem::new);
    }

    public SwerveDriveSubsystem(){
        FrontLeft = new SwerveModule(Constants.CONTROLLER1, Constants.WHEEL1, Constants.ENCODER1);
        FrontRight = new SwerveModule(Constants.CONTROLLER2, Constants.WHEEL2, Constants.ENCODER2);
        BackLeft = new SwerveModule(Constants.CONTROLLER3, Constants.WHEEL3, Constants.ENCODER3);
        BackRight = new SwerveModule(Constants.CONTROLLER4, Constants.WHEEL4, Constants.ENCODER4);
    }

    public void stop(){
        FrontLeft.setWheelSpeed(0.0);
        FrontLeft.setControllerSetPoint(0.0);
        FrontRight.setWheelSpeed(0.0);
        FrontRight.setControllerSetPoint(0.0);
        BackLeft.setWheelSpeed(0.0);
        BackLeft.setControllerSetPoint(0.0);
        BackRight.setWheelSpeed(0.0);
        BackRight.setControllerSetPoint(0.0);
    }

    public void setWheelSpeeds(double speed){
        FrontLeft.setWheelSpeed(speed);
        FrontRight.setWheelSpeed(speed);
        BackLeft.setWheelSpeed(speed);
        BackRight.setWheelSpeed(speed);
    }

    public void setRotationalSpeeds(double speed){
        FrontLeft.setWheelSpeed(-speed);
        FrontRight.setWheelSpeed(speed);
        BackLeft.setWheelSpeed(-speed);
        BackRight.setWheelSpeed(speed);
    }

    public void setLinearAngles(double r){
        FrontLeft.setControllerSetPoint(r);
        FrontRight.setControllerSetPoint(r);
        BackLeft.setControllerSetPoint(r);
        BackRight.setControllerSetPoint(r);
    }

    public void setRotation(){
        FrontLeft.setControllerSetPoint(Rotation2d.fromDegrees(-45).getRotations());
        FrontRight.setControllerSetPoint(Rotation2d.fromDegrees(45).getRotations());
        BackLeft.setControllerSetPoint(Rotation2d.fromDegrees(45).getRotations());
        BackRight.setControllerSetPoint(Rotation2d.fromDegrees(-45).getRotations());
    }

    @Override
    public void periodic(){
        FrontLeft.updateMotors();
        FrontRight.updateMotors();
        BackLeft.updateMotors();
        BackRight.updateMotors();
    }
}
