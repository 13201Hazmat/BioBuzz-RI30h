package org.firstinspires.ftc.teamcode.opmodes.auto.commands;

import static com.pedropathing.ivy.groups.Groups.parallel;
import static com.pedropathing.ivy.groups.Groups.sequential;

import com.pedropathing.follower.Follower;
import com.pedropathing.ivy.Command;

import org.firstinspires.ftc.teamcode.data.Alliance;
import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.opmodes.auto.paths.PathsAndPoses;
import org.firstinspires.ftc.teamcode.robot.HazmatRobot;

public class Routines {
    private HazmatRobot robot;
    private final Follower follower;
    private final PathsAndPoses paths;
    private final AutoCommands commands;

    public Routines(HazmatRobot robot, PathsAndPoses paths, AutoCommands commands){
        this.robot = robot;
        follower = robot.getFollower();
        this.paths = paths;
        paths.mirrorPose(robot.getAlliance());
        this.commands = commands;
    }

    public Command leaveAuto(Alliance alliance) {
        paths.mirrorPose(alliance);
        return sequential(
                commands.runPath(paths.startPos_to_leavePos())
        );
    }

    public Command preloadAuto(Alliance alliance) {
        paths.mirrorPose(alliance);
        return sequential(
                robot.launch(),
                parallel(
                        commands.runPath(paths.startPos_to_leavePos()),
                        robot.getIntake().setSpeed(Intake.IntakeState.FORWARD)
                )
        );
    }


    public Command fullCycleAuto(Alliance alliance) {
        paths.mirrorPose(alliance);
        return sequential(
                preloadAuto(alliance),
                commands.runPath(paths.endPos_to_startPos()),
                robot.launch(),
                commands.runPath(paths.endPos_to_startPos())
        );
    }


}
