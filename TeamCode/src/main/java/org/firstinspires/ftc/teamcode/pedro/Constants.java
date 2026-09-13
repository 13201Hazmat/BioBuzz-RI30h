package org.firstinspires.ftc.teamcode.pedro;

import com.pedropathing.algorithm.Foresight;
import com.pedropathing.algorithm.ForesightConfig;
import com.pedropathing.controllers.Controller;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Matrix;
import com.pedropathing.math.Vector2D;
import com.pedropathing.revhub.drivetrains.Mecanum;
import com.pedropathing.revhub.drivetrains.MecanumConfig;
import com.pedropathing.revhub.localizers.OctoQuadConfig;
import com.pedropathing.revhub.localizers.OctoQuadLocalizer;
import com.pedropathing.revhub.localizers.PinpointLocalizer;
import com.qualcomm.hardware.digitalchickenlabs.OctoQuad;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Constants {

    public static MecanumConfig driveConfig = new MecanumConfig(c -> {
        c.frontLeftName.set("fL");
        c.frontRightName.set("fR");
        c.backLeftName.set("bL");
        c.backRightName.set("bR");
        c.frontLeftDirection.set(DcMotorSimple.Direction.REVERSE);
        c.frontRightDirection.set(DcMotorSimple.Direction.FORWARD);
        c.backLeftDirection.set(DcMotorSimple.Direction.REVERSE);
        c.backRightDirection.set(DcMotorSimple.Direction.FORWARD);
    });



    public static OctoQuadConfig octoQuadConfig = new OctoQuadConfig(c -> {
        c.name.set("oq");
        c.xPodPort.set(1);
        c.yPodPort.set(0);
        c.ticksPerUnit.set(505.316944406);
        c.xPodOffset.set(-0.45275590551181105);
        c.yPodOffset.set(-1.3188976377952757);
        c.xPodDirection.set(OctoQuad.EncoderDirection.FORWARD);
        c.yPodDirection.set(OctoQuad.EncoderDirection.FORWARD);
        c.globalDistanceUnit.set(DistanceUnit.INCH);
        c.offsetUnits.set(DistanceUnit.INCH);
        c.i2cRecoveryMode.set(OctoQuad.I2cRecoveryMode.MODE_1_PERIPH_RST_ON_FRAME_ERR);
        c.headingScalar.set(1.333819966281649);
    });

    public static ForesightConfig foresightConfig = new ForesightConfig(
            c -> {
                Controller primaryTranslationalForward = Controller.proportional(0.4486106842387971);
                Controller secondaryTranslationalForward = Controller.proportional(0.16574966072798314);
                Controller primaryTranslationalLateral = Controller.proportional(0.737962736233596);
                Controller secondaryTranslationalLateral = Controller.proportional(0.27265751231083657);

                c.forwardTranslational.set(Controller.piecewise(secondaryTranslationalForward).put(2.5, primaryTranslationalForward));
                c.strafeTranslational.set(Controller.piecewise(secondaryTranslationalLateral).put(2.5, primaryTranslationalLateral));

                c.coast.set(Controller.proportionalFeedforward(0.012015294951088266));
                c.brake.set(Controller.proportionalFeedforward(0.010213000708425027));

                c.headingFeedback.set(Controller.proportional(6.812630896365188));
                c.headingBrakeCoefficients.set(Vector2D.cartesian(0.0667362485542638, 0.006377537615189557));

                c.linearBrakeCoefficients.set(Matrix.diag(0.10766354837774736, 0.06395882753675518));
                c.quadraticBrakeCoefficients.set(Matrix.diag(0.001720521201243515, 0.0019926799320205083));

                c.maxAchievableForwardVelocity.set(79.35213464463074);
                c.maxAchievableStrafeVelocity.set(59.70486403767397);
                c.naturalForwardDeceleration.set(27.97163701435685);
                c.naturalStrafeDeceleration.set(66.54650838060978);
            }
    );


    public static Follower create(HardwareMap h) {
        return new Follower(
                new OctoQuadLocalizer(h, octoQuadConfig),
                new Mecanum(h, driveConfig),
                new Foresight(foresightConfig)
        );
    }
}