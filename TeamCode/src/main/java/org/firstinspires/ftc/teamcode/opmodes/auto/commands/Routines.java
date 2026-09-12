package org.firstinspires.ftc.teamcode.opmodes.auto.commands;

import static com.pedropathing.ivy.commands.Commands.*;
import static com.pedropathing.ivy.groups.Groups.*;

import com.pedropathing.follower.Follower;
import com.pedropathing.ivy.Command;

import org.firstinspires.ftc.teamcode.opmodes.auto.paths.PathsAndPoses;
import org.firstinspires.ftc.teamcode.robot.HazmatRobot;

public class Routines {
    private HazmatRobot hazmatRobot;
    private final Follower follower;
    private final PathsAndPoses paths;
    private final AutoCommands commands;

    public Routines(HazmatRobot hazmatRobot, PathsAndPoses paths, AutoCommands commands){
        this.hazmatRobot = hazmatRobot;
        follower = hazmatRobot.getFollower();
        this.paths = paths;
        paths.mirrorPose(hazmatRobot.getAlliance());
        this.commands = commands;
    }
    public Command doSomething(){
        return sequential(
                commands.runPath(paths.exampleStart_to_exampleEnd())
        );
    }

}
