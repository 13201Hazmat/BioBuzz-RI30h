package org.firstinspires.ftc.teamcode.opmodes.auto.commands;


import static com.pedropathing.ivy.commands.Commands.instant;
import static com.pedropathing.ivy.groups.Groups.parallel;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;

import com.pedropathing.follower.Follower;
import com.pedropathing.ivy.CommandBuilder;
import com.pedropathing.paths.Path;

import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.opmodes.auto.paths.PathsAndPoses;
import org.firstinspires.ftc.teamcode.robot.HazmatRobot;

public class AutoCommands {
    private final Follower follower;
    private final PathsAndPoses paths;
    private final HazmatRobot robot = new HazmatRobot();

    public AutoCommands(Follower follower, PathsAndPoses paths){
        this.follower = follower;
        this.paths = paths;
    }
    public CommandBuilder runPath(Path path){
        return follow(follower, path);
    }

    public CommandBuilder intakePath(Path path){
        return parallel(runPath(path), robot.getIntake().setSpeed(Intake.IntakeState.FORWARD));
    }
}
