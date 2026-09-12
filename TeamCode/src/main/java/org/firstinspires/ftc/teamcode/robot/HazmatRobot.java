package org.firstinspires.ftc.teamcode.robot;

import androidx.annotation.NonNull;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.data.Alliance;
import org.firstinspires.ftc.teamcode.mechanisms.Launcher;
import org.firstinspires.ftc.teamcode.mechanisms.Vision;
import org.firstinspires.ftc.teamcode.pedro.Constants;

import java.util.Set;

import dev.nextftc.robot.Mechanism;
import dev.nextftc.robot.NextRobot;

public class HazmatRobot implements NextRobot {
    private final Follower follower;
    private Alliance alliance;

    private Vision vision;
    private Launcher launcher;
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

    @NonNull
    @Override
    public Set<Mechanism> getMechanisms() {
        return Set.of(launcher);
    }

    public Vision getVision() {
        return vision;
    }

    public Launcher getLauncher() {
        return launcher;
    }
}
