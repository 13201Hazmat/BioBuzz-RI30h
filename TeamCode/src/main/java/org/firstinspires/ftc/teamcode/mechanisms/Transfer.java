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
    private final double CLOSE_POS = 0.0;

    private final double DISTANCE = 0.0;

    NextServo rampServo = new NextServo(RobotController.controlHub(), Config.rampServo);
    NextColorDistanceSensor bucketSensor = new NextColorDistanceSensor(RobotController.controlHub(), Config.bucketSensor);

    ColorProfile yellow = new ColorProfile(
            ColorSpace.HSV,
            NextColor.Companion.hsv(130f, 0.7f, 0.6f),
            NextColor.Companion.hsv(20f, 0.3f, 1f)
    );

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

    private void updateBallType(){
        if (bucketSensor.isColorWithinDistance(yellow, DISTANCE)){
            BallType.current = BallType.POLLEN;
        } else if (bucketSensor.isWithinDistance(DISTANCE) && !bucketSensor.isColor(yellow)){
            BallType.current = BallType.NECTAR;
        } else {
            BallType.current = BallType.NOTHING;
        }
    }

    public String getResult(){
        return bucketSensor.debug();
    }
    @Override
    public void periodic(){
        bucketSensor.update();

        updateBallType();
    }

}
