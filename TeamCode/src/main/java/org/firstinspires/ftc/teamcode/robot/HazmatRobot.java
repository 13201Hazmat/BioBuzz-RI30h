package org.firstinspires.ftc.teamcode.robot;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.data.Alliance;
import org.firstinspires.ftc.teamcode.pedro.Constants;

import dev.nextftc.robot.NextRobot;

public class HazmatRobot implements NextRobot {
    private final Follower follower;
    private Alliance alliance;
    public HazmatRobot(HardwareMap h, Alliance alliance){
        follower = Constants.create(h);
    }

    public void setAlliance(Alliance alliance){
        this.alliance = alliance;
    }

    public Alliance getAlliance(){
        return alliance;
    }

    public Follower getFollower(){
        if(follower != null){
            return follower;
        }
        return null;
    }
}
