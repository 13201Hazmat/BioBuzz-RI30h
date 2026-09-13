package org.firstinspires.ftc.teamcode.mechanisms;

import com.pedropathing.ivy.Command;

import org.firstinspires.ftc.teamcode.data.BallType;
import org.firstinspires.ftc.teamcode.data.Config;

import dev.nextftc.hardware.RobotController;
import dev.nextftc.hardware.actuators.NextServo;
import dev.nextftc.hardware.sensors.NextColorDistanceSensor;
import dev.nextftc.hardware.sensors.colors.ColorProfile;
import dev.nextftc.hardware.sensors.colors.ColorSpace;
import dev.nextftc.hardware.sensors.colors.NextColor;
import dev.nextftc.robot.Mechanism;

public class Transfer implements Mechanism {
    public Transfer(){}

    private final double OPEN_POS = 0.0;
    private final double MID_POS = OPEN_POS / 2;
    private final double CLOSE_POS = 1.0;

    private NextServo rampServo = new NextServo(RobotController.controlHub(), Config.rampServo,0);

    public NextServo getRampServo() {
        return rampServo;
    }

    public Command deltaUp(){
        return instant(() -> rampServo.setPosition(rampServo.getPosition() + 0.01));
    }

    public Command deltaDown(){
        return instant(() -> rampServo.setPosition(rampServo.getPosition() - 0.01));
    }


    private void setRampServoPosition(double x){
        rampServo.setPosition(x);
    }

    public Command setPosition(double pos){
        return instant(()->setRampServoPosition(pos)).requiring(rampServo);
    }

    public Command open(){
        return setPosition(OPEN_POS);
    }

    public Command close(){
        return setPosition(CLOSE_POS);
    }

    public Command mid(){return setPosition(MID_POS);}

}
