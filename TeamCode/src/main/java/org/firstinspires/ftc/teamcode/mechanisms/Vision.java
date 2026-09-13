/*
package org.firstinspires.ftc.teamcode.mechanisms;

import com.pedropathing.ivy.Command;
import com.pedropathing.math.Pose;
import com.pedropathing.math.Vector;

import dev.nextftc.hardware.webcams.NextLimelight;
import dev.nextftc.robot.Mechanism;

public class Vision implements Mechanism {
    private final NextLimelight limelight = new NextLimelight("Limelight");

    public Vision() {
    }

    public void start() {
        limelight.startReading(2, 100);
    }

    public void setTracking() {
        limelight.setPipeline(2);
    }

    public void setCounting() {
        limelight.setPipeline(3);
    }

    public NextLimelight getLimelight() {
        return limelight;
    }

    public Pose getFinalPose(double targetX, double targetY, Pose currentPose) {

        if (currentPose == null) {
            return null;
        }

        double cameraPitch = 10;
        double ballHeight = 3;
        double cameraHeight = 9.5;

        double cameraOffsetY = 7.5;
        double cameraOffsetX = 0;

        double cameraToBallAngle = Math.toRadians(cameraPitch + targetY);

        double heightDifference = cameraHeight - ballHeight;

        // Checks for impossible math
        if (Math.abs(Math.tan(cameraToBallAngle)) < 0.0001) {
            return null;
        }

        double forwardDistance = heightDifference / Math.tan(cameraToBallAngle);
        double lateralDistance = forwardDistance * Math.tan(Math.toRadians(targetX));

        forwardDistance += cameraOffsetY;
        lateralDistance += cameraOffsetX;

        Vector robotToBallVector = new Vector(forwardDistance, lateralDistance);

        robotToBallVector.toVector2D().rotate(currentPose.heading());

        double fieldX = currentPose.x() + robotToBallVector.toVector2D().x();

        double fieldY = currentPose.y() + robotToBallVector.toVector2D().y();

        return new Pose(fieldX, fieldY, currentPose.heading());
    }

    public int getAmountBalls() {
        return limelight.getLatestResult().getDetectorResults().size();
    }

    public Pose getFinalPose(Pose currentPose) {
        return getFinalPose(limelight.getTX(), limelight.getTY(), currentPose);
    }
}*/
