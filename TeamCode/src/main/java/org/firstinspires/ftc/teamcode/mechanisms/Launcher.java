package org.firstinspires.ftc.teamcode.mechanisms;

import dev.nextftc.control.feedback.PIDCoefficients;
import dev.nextftc.control.feedback.PIDController;
import dev.nextftc.hardware.RobotController;
import dev.nextftc.hardware.actuators.NextMotor;
import dev.nextftc.hardware.actuators.NextServo;
import dev.nextftc.robot.Mechanism;
import dev.nextftc.units.Units;
import dev.nextftc.units.measuretypes.AngularVelocity;
import dev.nextftc.units.unittypes.AngleUnit;
import dev.nextftc.units.unittypes.AngularVelocityUnit;

public class Launcher implements Mechanism {
    // TODO UPDATE PORT AND ROBOTCONTROLLER
    private final NextMotor launcherMotor = new NextMotor(RobotController.controlHub(), 3);
    private final NextServo compressionServo = new NextServo(RobotController.controlHub(), 3);
    private final int kP = 0;
    private final int kI = 0;
    private final int kD = 0;
    private AngularVelocity currentVelocity;
    private PIDController pid = new PIDController(new PIDCoefficients(0.01, 0.0, 0.001));

    public Launcher(){
//        currentVelocity = new AngularVelocity(0.0, new AngularVelocityUnit());
    }

    public void spinToVelocity(){

    }

    @Override
    public void periodic() {
        launcherMotor.getEncoderVelocity();
    }
}
