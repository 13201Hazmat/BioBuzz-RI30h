package org.firstinspires.ftc.teamcode.mechanisms;

import static dev.nextftc.units.Units.RotationsPerSecond;

import com.pedropathing.ivy.Command;

import dev.nextftc.control.feedback.PIDCoefficients;
import dev.nextftc.control.feedback.PIDController;
import dev.nextftc.hardware.RobotController;
import dev.nextftc.hardware.actuators.NextMotor;
import dev.nextftc.hardware.actuators.NextServo;
import dev.nextftc.hardware.sensors.NextColorDistanceSensor;
import dev.nextftc.hardware.sensors.colors.ColorProfile;
import dev.nextftc.hardware.sensors.colors.ColorSpace;
import dev.nextftc.hardware.sensors.colors.NextColor;
import dev.nextftc.robot.Mechanism;
import dev.nextftc.units.measuretypes.AngularVelocity;

public class Launcher implements Mechanism {
    // TODO UPDATE PORT AND ROBOTCONTROLLER
    private final NextMotor launcherMotor = new NextMotor(RobotController.controlHub(), 1);
    private final NextServo compressionServo = new NextServo(RobotController.controlHub(), 1);
    private final NextServo launcherGateServo = new NextServo(RobotController.controlHub(), 1);
    private final NextColorDistanceSensor launcherGateColorSensor = new NextColorDistanceSensor(RobotController.controlHub(), 1);
    private final int KP = 0;
    private final int KI = 0;
    private final int KD = 0;
    private final int TOLERANCE = 50;
    private AngularVelocity currentVelocity;
    private AngularVelocity targetVelocity;
    private final PIDController pid = new PIDController(new PIDCoefficients(KP, KI, KD));

    public Launcher(){}

    // USE THIS METHOD FOR LAUNCHER
    public void setTargetVelocity(double targetVelocity){
        this.targetVelocity = RotationsPerSecond.of(targetVelocity);
    }

    private void spinToVelocity(double velocity) {
        double error = velocity - currentVelocity.getMagnitude();
        targetVelocity = RotationsPerSecond.of(pid.calculate(error));
        launcherMotor.setVelocitySetpoint(targetVelocity);
    }

    @Deprecated
    public Command setPowerThingy() {
        return instant(() -> launcherMotor.setThrottle(0.8));
    }

    public Command incrementLauncherGate() {
        double next = 0.01;
        double curr = launcherGateServo.getPosition();
        return instant(() -> launcherGateServo.setPosition(next + curr));
    }

    public Command decrementLauncherGate() {
        double next = 0.01;
        double curr = launcherGateServo.getPosition();
        return instant(() -> launcherGateServo.setPosition(next - curr));
    }

    public Command zeroLauncherGate() {
        return instant(() -> launcherGateServo.setPosition(0));
    }

    public double getServoPos() {
        return launcherGateServo.getPosition();
    }

    public double getMotorSpeed() {
        return launcherMotor.getThrottle();
    }

    public boolean isAtVelocity(){
        return Math.abs(currentVelocity.getMagnitude() - targetVelocity.getMagnitude()) <= TOLERANCE;
    }

    ColorProfile yellow = new ColorProfile(
            ColorSpace.HSV,
            NextColor.Companion.hsv(130f, 0.7f, 0.6f),
            NextColor.Companion.hsv(20f, 0.3f, 1f)
    );

    @Override
    public void periodic() {
        currentVelocity = launcherMotor.getEncoderVelocity();
        spinToVelocity(targetVelocity.getMagnitude());
    }


}
