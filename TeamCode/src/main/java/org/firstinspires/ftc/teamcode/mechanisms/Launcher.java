package org.firstinspires.ftc.teamcode.mechanisms;

import static dev.nextftc.units.Units.RotationsPerMinute;

import dev.nextftc.control.feedback.PIDCoefficients;
import dev.nextftc.control.feedback.PIDController;
import dev.nextftc.hardware.RobotController;
import dev.nextftc.hardware.actuators.NextMotor;
import dev.nextftc.hardware.actuators.NextServo;
import dev.nextftc.robot.Mechanism;
import dev.nextftc.units.measuretypes.AngularVelocity;

public class Launcher implements Mechanism {
    // TODO UPDATE PORT AND ROBOTCONTROLLER
    private final NextMotor launcherMotor = new NextMotor(RobotController.controlHub(), 3);
    private final NextServo compressionServo = new NextServo(RobotController.controlHub(), 3);
    private final int KP = 0;
    private final int KI = 0;
    private final int KD = 0;
    private final int TOLERANCE = 50;
    private AngularVelocity currentVelocity;
    private AngularVelocity targetVelocity;
    private final PIDController pid = new PIDController(new PIDCoefficients(KP, KI, KD));

    // USE THIS METHOD FOR LAUNCHER
    public void setTargetVelocity(double targetVelocity){
        this.targetVelocity = RotationsPerMinute.of(targetVelocity);
    }

    private void spinToVelocity(double velocity) {
        double error = velocity - currentVelocity.getMagnitude();
        targetVelocity = RotationsPerMinute.of(pid.calculate(error));
        launcherMotor.setVelocitySetpoint(targetVelocity);
    }

    public boolean isAtVelocity(){
        return Math.abs(currentVelocity.getMagnitude() - targetVelocity.getMagnitude()) <= TOLERANCE;
    }

    @Override
    public void periodic() {
        currentVelocity = launcherMotor.getEncoderVelocity();
        spinToVelocity(targetVelocity.getMagnitude());
    }
}
