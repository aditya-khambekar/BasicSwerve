// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.function.DoubleSupplier;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.RotationSwerveCommand;
import frc.robot.commands.SwerveCommand;

import frc.robot.oi.OI;
import frc.robot.subsystems.SwerveDriveSubsystem;
import frc.robot.subsystems.SwerveModule;


public class RobotContainer
{


    //SwerveDriveSubsystem swerveDriveSubsystem = SwerveDriveSubsystem.getInstance();
    OI oi = new OI();
    public RobotContainer()
    {
        System.out.println("Robot Container Init");
        configureBindings();
    }
    
    
    private void configureBindings() {
        SwerveDriveSubsystem.getInstance().setDefaultCommand(new SwerveCommand(
                new DoubleSupplier() {//angle in rotations
                    @Override
                    public double getAsDouble() {
                        var x = oi.driverController().getAxis(OI.Axes.RIGHT_STICK_X);
                        var y = oi.driverController().getAxis(OI.Axes.RIGHT_STICK_Y);

                        SmartDashboard.putNumber("x", x);
                        SmartDashboard.putNumber("y", y);

                        return Rotation2d.fromRadians(Math.atan2(y, x)-Math.PI/2).getRotations();
                    }
                },
                new DoubleSupplier() {//speed
                    @Override
                    public double getAsDouble() {
                        var x = oi.driverController().getAxis(OI.Axes.RIGHT_STICK_X);
                        var y = oi.driverController().getAxis(OI.Axes.RIGHT_STICK_Y);

                        SmartDashboard.putNumber("Supplied Speed",Math.hypot(x, y)/2 );
                        return Math.hypot(x, y)/2;
                    }
                }
        ));

        new Trigger(() -> oi.driverController().getAxis(OI.Axes.LEFT_STICK_X)!=0)
                .whileTrue(new RotationSwerveCommand(
                        new DoubleSupplier() {//speed
                            @Override
                            public double getAsDouble() {
                                return oi.driverController().getAxis(OI.Axes.LEFT_STICK_X)/2;
                            }
                        }
                )
        );
    }
    
    
    public Command getAutonomousCommand()
    {
        return Commands.print("No autonomous command configured");
    }
}
