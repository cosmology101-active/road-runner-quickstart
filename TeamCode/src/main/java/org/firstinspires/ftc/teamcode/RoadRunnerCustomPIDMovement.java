package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "RoadRunner PID Movement", group = "Autonomous")
public class RoadRunnerPIDMovement extends LinearOpMode {

    @Override
    public void runOpMode() {
        // Initialize the drive with starting pose at origin (0, 0) facing forward (0 radians)
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));

        // Build trajectory actions using Road Runner's built-in PID control
        // Move forward 24 inches (convert to meters if needed - check your drive class units)
        // Road Runner typically uses inches for FTC, but verify in your MecanumDrive class
        Action moveForward = drive.actionBuilder(new Pose2d(0, 0, 0))
                .lineToY(24) // Move forward 24 inches in Y direction
                .build();

        // Turn left 90 degrees (π/2 radians)
        Action turnLeft = drive.actionBuilder(new Pose2d(0, 24, 0))
                .turn(Math.PI / 2) // Turn left 90 degrees (counterclockwise)
                .build();

        // Combine both actions into a sequential action
        Action combinedMovement = new SequentialAction(
                moveForward,
                turnLeft
        );

        // Wait for the game to start (driver presses PLAY)
        telemetry.addData("Status", "Initialized");
        telemetry.addData("Action", "Ready to move forward 24 inches and turn left 90 degrees");
        telemetry.update();

        waitForStart();

        if (opModeIsActive()) {
            // Execute the combined movement using Road Runner's action system
            // This automatically handles PID control for both linear and angular movement
            Actions.runBlocking(combinedMovement);

            // Provide feedback
            telemetry.addData("Status", "Movement Complete");
            telemetry.addData("Final Position", "24 inches forward, turned left 90 degrees");
            telemetry.update();

            // Keep the program running to see telemetry
            while (opModeIsActive()) {
                idle();
            }
        }
    }
}

// Alternative version with more explicit PID control if you need custom tuning
@Autonomous(name = "RoadRunner Custom PID Movement", group = "Autonomous")
class RoadRunnerCustomPIDMovement extends LinearOpMode {

    @Override
    public void runOpMode() {
        // Initialize the drive
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));

        // Build more complex trajectory with custom constraints if needed
        TrajectoryActionBuilder trajectoryBuilder = drive.actionBuilder(new Pose2d(0, 0, 0))
                // Move forward 24 inches with custom velocity and acceleration constraints
                .lineToY(24)
                // Turn left 90 degrees
                .turn(Math.PI / 2);

        Action trajectory = trajectoryBuilder.build();

        telemetry.addData("Status", "Initialized - Custom PID Movement");
        telemetry.update();

        waitForStart();

        if (opModeIsActive()) {
            // Run the trajectory
            Actions.runBlocking(trajectory);

            telemetry.addData("Status", "Custom PID Movement Complete");
            telemetry.update();

            while (opModeIsActive()) {
                // Update pose estimate
                drive.updatePoseEstimate();

                // Display current pose
                Pose2d currentPose = drive.pose;
                telemetry.addData("X Position", currentPose.position.x);
                telemetry.addData("Y Position", currentPose.position.y);
                telemetry.addData("Heading (degrees)", Math.toDegrees(currentPose.heading.toDouble()));
                telemetry.update();

                idle();
            }
        }
    }
}
