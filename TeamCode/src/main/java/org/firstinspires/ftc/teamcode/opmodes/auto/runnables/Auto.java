package org.firstinspires.ftc.teamcode.opmodes.auto.runnables;

import static com.pedropathing.ivy.Scheduler.schedule;

import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.Scheduler;

import org.firstinspires.ftc.teamcode.data.Alliance;
import org.firstinspires.ftc.teamcode.opmodes.auto.commands.AutoCommands;
import org.firstinspires.ftc.teamcode.opmodes.auto.commands.Routines;
import org.firstinspires.ftc.teamcode.opmodes.auto.paths.PathsAndPoses;
import org.firstinspires.ftc.teamcode.robot.HazmatRobot;

import dev.nextftc.robot.opmode.NextAutonomous;
import dev.nextftc.robot.opmode.NextOpMode;

@NextAutonomous(name = "Auto")
public class Auto extends NextOpMode {
    private final HazmatRobot hazmatRobot;
    private final Routines routines;
    private final AutoCommands commands;
    private final PathsAndPoses paths;
    private Command selectedRoutine;
    private Alliance selectedAlliance;

    public Auto(HazmatRobot hazmatRobot) {
        super(hazmatRobot);
        this.hazmatRobot = hazmatRobot;
        Scheduler.reset();

        paths = new PathsAndPoses();
        commands = new AutoCommands(hazmatRobot.getFollower(), paths);
        routines = new Routines(hazmatRobot, paths, commands);

    }

    @Override
    public void start() {
//        telemetry.addLine("Select an alliance");
//        telemetry.addLine("BLUE is D-pad left");
//        telemetry.addLine("RED is D-pad right");
//        if(gamepad1.dpadLeftWasPressed()){
//            selectedAlliance = Alliance.BLUE;
//        }
//        else if(gamepad1.dpadRightWasPressed()){
//            selectedAlliance = Alliance.RED;
//        }
//        hazmatRobot.setAlliance(selectedAlliance);
//        telemetry.addLine("Pick an auto");
//        telemetry.addLine("Right bumper for LeaveAuto");
//        telemetry.addLine("Left bumper for PreloadAuto");
//        telemetry.addLine("Cross for CycleAuto");
//        if(gamepad1.rightBumperWasPressed()){
//            selectedRoutine = routines.leaveAuto(hazmatRobot.getAlliance());
//        }
//        else if(gamepad1.leftBumperWasPressed()){
//            selectedRoutine = routines.preloadAuto(hazmatRobot.getAlliance());
//        }
//        else if(gamepad1.crossWasPressed()){
//            selectedRoutine = routines.fullCycleAuto(hazmatRobot.getAlliance());
//        }
//        hazmatRobot.getFollower().setPose(paths.startPos);
//        schedule(selectedRoutine);
        schedule(routines.preloadAuto(Alliance.BLUE));
    }

    @Override
    public void periodic() {
        hazmatRobot.getFollower().update();
        Scheduler.execute();
    }
}
