package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SwerveModule{
    private TalonFX rotator;
    private TalonFX driver;
    private CANcoder encoder;

    private PIDController rotatorPID;

    private double wheelSpeed = 0.0;
    private double rotationSetPoint;

    public SwerveModule(int controllerID, int wheelID, int encoderID){
        rotator = new TalonFX(controllerID, "Canivore1");
        driver = new TalonFX(wheelID, "Canivore1");
        encoder = new CANcoder(encoderID, "Canivore1");

        rotatorPID = new PIDController(
               1.2,
               0.0,
               0.0
        );

        rotatorPID.enableContinuousInput(0, 1);


    }

    public void setWheelSpeed(double speed){
        if(speed>0.08){
            wheelSpeed = 0;
        }else{
            wheelSpeed = speed;
        }
        System.out.println("Wheel Speed Set to "+ wheelSpeed + " (parameter s was " + speed + ")");
    }

    public void setControllerSetPoint(double rotations){
        rotationSetPoint = rotations;
    }

    public void setState(double speed, double rotations){
        setWheelSpeed(speed);
        setControllerSetPoint(rotations);
    }

    public double getPosition(){
        return encoder.getPosition().getValue();
    }

    public void updateMotors(){
        driver.set(wheelSpeed);
        rotator.set(rotatorPID.calculate(getPosition(), rotationSetPoint));

        SmartDashboard.putNumber(driver.getDeviceID()+" Desired", rotationSetPoint);
        SmartDashboard.putNumber(driver.getDeviceID()+" Actual", getPosition());
    }

    public void setPID(double kp, double ki, double kd){
        rotatorPID.setPID(kp, ki, kd);
    }
}