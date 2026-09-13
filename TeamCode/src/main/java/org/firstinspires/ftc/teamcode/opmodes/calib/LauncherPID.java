//package org.firstinspires.ftc.teamcode.opmodes.calib;
//
//import org.firstinspires.ftc.teamcode.mechanisms.Launcher;
//import org.firstinspires.ftc.teamcode.robot.HazmatRobot;
//
//import dev.nextftc.robot.opmode.NextOpMode;
//import dev.nextftc.robot.opmode.NextTeleop;
//
//@Config
//@NextTeleop(name = "Launcher PID + Velocity + Hood Tuner")
//public class LauncherPID extends NextOpMode {
//    public static double targetVelocity = 2000;
//    public static double kP = 0.0015;
//    public static double kI = 0;
//    public static double kD = 0.0005;
//    public static double kV = 0.0004;
//    public static double kS = 0.02;
//    private final HazmatRobot robot;
//    // Subsystems
//    private Launcher launcher;
//    private Telemetry telemetryM;
//    public LauncherPID(HazmatRobot robot) {
//        super(robot);
//        this.robot = robot;
//    }
//
//    @Override
//    public void disabledPeriodic() {
//        telemetry.addLine("Launcher PID + Velocity + Hood Tuner Ready");
//    }
//
//    @Override
//    public void start() {
//        schedule(launcher.setVelocity(targetVelocity));
//    }
//
//    @Override
//    public void periodic() {
//
//        // Update shooter velocity & hood angle
//        launcher.setVelocity(targetVelocity).schedule();
//
//        // Update PID / FF constants
//        launcher.velPIDCoefficients.kP = kP;
//        launcher.velPIDCoefficients.kI = kI;
//        launcher.velPIDCoefficients.kD = kD;
//
//        launcher.basicFFParameters.kV = kV;
//        launcher.basicFFParameters.kS = kS;
//
//        // Telemetry
//        telemetry.debug("Target Velocity", targetVelocity);
//        telemetry.debug("Measured Velocity", launcher.getVelocity());
//
//        telemetry.debug("kP", kP);
//        telemetry.debug("kI", kI);
//        telemetry.debug("kD", kD);
//        telemetry.debug("kV", kV);
//        telemetry.debug("kS", kS);
//
//        telemetry.update(telemetry);
//    }
//}