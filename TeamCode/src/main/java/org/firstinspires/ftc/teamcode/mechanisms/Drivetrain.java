package org.firstinspires.ftc.teamcode.mechanisms;

import com.pedropathing.ivy.Command;

import org.firstinspires.ftc.teamcode.data.Config;

import dev.nextftc.hardware.RobotController;
import dev.nextftc.hardware.actuators.NextMotor;
import dev.nextftc.hardware.actuators.NextServo;
import dev.nextftc.robot.Mechanism;

public class Drivetrain implements Mechanism {
    public Drivetrain(){}
    public final NextMotor frontLeft = new NextMotor(RobotController.controlHub(), Config.frontLeftMotor);
    public final NextMotor frontRight = new NextMotor(RobotController.expansionHub(), Config.frontRightMotor);
    public final NextMotor backLeft = new NextMotor(RobotController.controlHub(), Config.backLeftMotor);
    public final NextMotor backRight =  new NextMotor(RobotController.expansionHub(), Config.backRightMotor);




}