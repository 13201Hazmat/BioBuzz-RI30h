package org.firstinspires.ftc.teamcode.mechanisms;

import com.pedropathing.ivy.Command;

import org.firstinspires.ftc.teamcode.data.Config;

import dev.nextftc.hardware.RobotController;
import dev.nextftc.hardware.actuators.NextMotor;
import dev.nextftc.hardware.sensors.NextColorDistanceSensor;
import dev.nextftc.robot.Mechanism;

public class Launcher implements Mechanism {
    // TODO UPDATE PORT AND ROBOTCONTROLLER
    private final NextMotor launcherMotor = new NextMotor(RobotController.expansionHub(), Config.launcherMotor);
//    private final NextServo compressionServo = new NextServo(RobotController.controlHub(), 1);
//    private final NextServo launcherGateServo = new NextServo(RobotController.controlHub(), 1);
    private final NextColorDistanceSensor launcherGateColorSensor = new NextColorDistanceSensor(RobotController.controlHub(), 1);
//    private final int KP = 0;
//    private final int KI = 0;
//    private final int KD = 0;
//    private final int TOLERANCE = 50;
    private final int POLLEN_COMPRESS_POS = 0;
    private final int NECTAR_COMPRESS_POS = 0;
    private double currVel = 0.5;
//    private AngularVelocity currentVelocity;
//    private AngularVelocity targetVelocity;
//    private final PIDController pid = new PIDController(new PIDCoefficients(KP, KI, KD));

    public Launcher(){}

    // USE THIS METHOD FOR LAUNCHER
//    public void setTargetVelocity(double targetVelocity){
//        this.targetVelocity = RotationsPerSecond.of(targetVelocity);
//    }
//
//    private void spinToVelocity(double velocity) {
//        double error = velocity - currentVelocity.getMagnitude();
//        targetVelocity = RotationsPerSecond.of(pid.calculate(error));
//        launcherMotor.setVelocitySetpoint(targetVelocity);
//    }

    public Command setPowerThingy() {
        currVel = 0.5;
        return instant(() -> launcherMotor.setThrottle(0.5));
    }

    public Command incrementPower(){
        currVel+=0.01;
        return instant(() -> launcherMotor.setThrottle(currVel + 0.01));
    }

    public Command decrementPower(){
        currVel-=0.01;
        return instant(() -> launcherMotor.setThrottle(currVel - 0.01));
    }

   /* public Command incrementLauncherGate() {
        double next = 0.01;
        double curr = launcherGateServo.getPosition();
        return instant(() -> launcherGateServo.setPosition(next + curr));
    }*/

    /*public Command decrementLauncherGate() {
        double next = 0.01;
        double curr = launcherGateServo.getPosition();
        return instant(() -> launcherGateServo.setPosition(next - curr));
    }

    public Command zeroLauncherGate() {
        return instant(() -> launcherGateServo.setPosition(0));
    }

    public double getServoPos() {
        return launcherGateServo.getPosition();
    }*/

    public double getMotorSpeed() {
        return launcherMotor.getThrottle();
    }

//    public boolean isAtVelocity(){
//        return Math.abs(currentVelocity.getMagnitude() - targetVelocity.getMagnitude()) <= TOLERANCE;
//    }

   /* public void pollenCompress(){
        compressionServo.setPosition(POLLEN_COMPRESS_POS);
    }

    public void nectarCompress(){
        compressionServo.setPosition(NECTAR_COMPRESS_POS);
    }*/

//    public Command moveGate(){
//
//    }

//    @Override
//    public void periodic() {
//        currentVelocity = launcherMotor.getEncoderVelocity();
//        spinToVelocity(targetVelocity.getMagnitude());
//    }


}
