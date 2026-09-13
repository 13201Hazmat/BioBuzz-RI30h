package org.firstinspires.ftc.teamcode.mechanisms;

import static dev.nextftc.units.Units.RotationsPerMinute;

import com.pedropathing.ivy.Command;

import org.firstinspires.ftc.teamcode.data.Config;

import dev.nextftc.hardware.RobotController;
import dev.nextftc.hardware.actuators.NextMotor;
import dev.nextftc.robot.Mechanism;

public class Launcher implements Mechanism {
    private final NextMotor launcherMotor = new NextMotor(RobotController.expansionHub(), Config.launcherMotor);

    private final double TOLERANCE = 25.0;
    private final double DELTA = 50.0;
    private final double POINT1 = 0.0;

    public double kV = 0;
    public double kS = 0;

    private double curPower = 0.5;
    private double currentVelocity = 0.0;
    private double targetVelocity = 0.0;

    public Launcher(){
        launcherMotor.getVelocityConstants().setKV(kV);
        launcherMotor.getVelocityConstants().setKV(kS);

    }

    public NextMotor getLauncherMotor(){
        return launcherMotor;
    }

    public void setTargetVelocity(double targetVelocity){
         launcherMotor.setVelocitySetpoint(RotationsPerMinute.of(targetVelocity));
    }

    public Command setPoint1(){
        return instant(()->launcherMotor.setVelocitySetpoint(RotationsPerMinute.of(POINT1)));
    }

    public Command stopLauncher(){
        return instant(()-> launcherMotor.setVelocitySetpoint(RotationsPerMinute.of(0)));
    }
    public Command deltaUp(){
        return instant(()->launcherMotor.setVelocitySetpoint(RotationsPerMinute.of(currentVelocity + DELTA)));
    }

    public Command deltaDown(){
        return instant(()->launcherMotor.setVelocitySetpoint(RotationsPerMinute.of(currentVelocity - DELTA)));
    }

    public Command setPowerThingy() {
        curPower = 0.5;
        return instant(() -> launcherMotor.setThrottle(0.5));
    }

    public Command incrementPower(){
        curPower +=0.01;
        return instant(() -> launcherMotor.setThrottle(curPower + 0.01));
    }

    public Command decrementPower(){
        curPower -=0.01;
        return instant(() -> launcherMotor.setThrottle(curPower - 0.01));
    }


//    public double getMotorSpeed() {
//        return launcherMotor.getThrottle();
//    }

    public boolean isAtVelocity(){
        return Math.abs(currentVelocity - targetVelocity) <= TOLERANCE;
    }

    @Override
    public void periodic() {
        currentVelocity = launcherMotor.getEncoderVelocity().into(RotationsPerMinute);

        isAtVelocity();
    }


}
