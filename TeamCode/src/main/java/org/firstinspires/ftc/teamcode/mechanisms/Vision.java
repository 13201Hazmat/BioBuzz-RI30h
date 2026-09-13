package org.firstinspires.ftc.teamcode.mechanisms;

import com.pedropathing.math.Pose;
import com.pedropathing.math.Vector;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;

import org.firstinspires.ftc.teamcode.data.Alliance;
import org.firstinspires.ftc.teamcode.data.BallType;

import java.util.ArrayList;
import java.util.List;

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

        double cameraPitch = 15;
        double ballHeight = 3;
        double cameraHeight = 13.5;

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

    public Pose getClosestBallPose(Pose currentPose, Alliance alliance) {
        if (currentPose == null) {
            return null;
        }

        List<LLResultTypes.DetectorResult> detections = getRelevantDetections(alliance);

        if (detections.isEmpty()) {
            return null;
        }

        LLResultTypes.DetectorResult closestBall = null;
        double closestDistance = Double.MAX_VALUE;

        double cameraPitch = 15;
        double ballHeight = 2.8;
        double cameraHeight = 13.5;


        for (LLResultTypes.DetectorResult detection : detections) {
            double tx = detection.getTargetXDegrees();
            double ty = detection.getTargetYDegrees();
            if(detection.getClassName().equals(Alliance.BLUE.getClassName()) || detection.getClassName().equals(Alliance.RED.getClassName())){
                ballHeight = 3.6;
            }

            double heightDifference = cameraHeight - ballHeight;

            double cameraToBallAngle = Math.toRadians(cameraPitch + ty);

            // Avoid impossible math
            if (Math.abs(Math.tan(cameraToBallAngle)) < 0.0001) {
                continue;
            }

            double forwardDistance = heightDifference / Math.tan(cameraToBallAngle);

            double lateralDistance = forwardDistance * Math.tan(Math.toRadians(tx));

            double distance = Math.sqrt(forwardDistance * forwardDistance + lateralDistance * lateralDistance);

            if (distance < closestDistance) {
                closestDistance = distance;
                closestBall = detection;
            }
        }

        if (closestBall == null) {
            return null;
        }

        return getFinalPose(closestBall.getTargetXDegrees(), closestBall.getTargetYDegrees(), currentPose);
    }


    public List<LLResultTypes.DetectorResult> getRelevantDetections(Alliance alliance) {
        LLResult latestResult = limelight.getLatestResult();
        List<LLResultTypes.DetectorResult> detections = latestResult.getDetectorResults();
        List<LLResultTypes.DetectorResult> list = new ArrayList<>();
        for (LLResultTypes.DetectorResult detection : detections) {
            String className = detection.getClassName();
            if (className.equals("pollen")) {
                list.add(detection);
            } else if (alliance == Alliance.BLUE && className.equals("blue_nectar")) {
                list.add(detection);
            } else if (alliance == Alliance.RED && className.equals("red_nectar")) {
                list.add(detection);
            }
        }
        return list;
    }


}
