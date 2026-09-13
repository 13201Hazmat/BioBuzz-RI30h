package org.firstinspires.ftc.teamcode.opmodes.auto.commands;

import static com.pedropathing.ivy.groups.Groups.parallel;
import static com.pedropathing.ivy.groups.Groups.sequential;

import com.pedropathing.follower.Follower;
import com.pedropathing.ivy.Command;

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

    public Command leaveAuto() {
        return sequential(
                commands.runPath(paths.startPos_to_leavePos())
        );
    }

    public Command preloadAuto() {
        return sequential(
                commands.runPath(paths.startPos_to_launchPos()),
//                robot.launch(),
                parallel(
                        commands.runPath(paths.launchPos_to_endPos()),
                        robot.getIntake().setSpeed(Intake.IntakeState.FORWARD)
                )
        );
    }


    public Command fullCycleAuto() {
        return sequential(
                preloadAuto(),
                commands.runPath(paths.endPos_to_startPos())
//                robot.launch()
        );
    }


}
