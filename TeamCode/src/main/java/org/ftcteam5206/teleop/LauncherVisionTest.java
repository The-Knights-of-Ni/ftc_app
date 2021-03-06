package org.ftcteam5206.teleop;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.ftcteam5206.KingArthur;
import org.ftcteam5206.subsystems.RobotConstants;
import org.ftcteam5206.subsystems.vision.VisionCallback;
import org.ftcteam5206.subsystems.vision.VisionSystem;
import org.ftcteam5206.utils.Button.ButtonHandler;

/**
 * Created by tarunsingh on 2/25/17.
 */
@TeleOp(name="Launcher Vision Test")
public class LauncherVisionTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        ElapsedTime runtime = new ElapsedTime();
        VisionCallback callback = null;
        ButtonHandler pad1 = new ButtonHandler();
        waitForStart();
        runtime.reset();
        VisionSystem visionSystem = new VisionSystem(this);
        //visionSystem.detectVortex();
        while (opModeIsActive()){
            visionSystem.setCurrentProcessingMode(VisionSystem.ProcessingMode.NONE);
            telemetry.addData("Working", "");
            telemetry.update();
        }
    }
}
