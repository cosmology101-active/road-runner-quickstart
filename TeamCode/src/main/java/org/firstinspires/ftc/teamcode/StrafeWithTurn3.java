package org.firstinspires.ftc.teamcode.champion.autonomous;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.champion.road.runner.drive.ChampionMecanumDrive;

@Config
@Autonomous(group = "Official")
public class StrafeWithTurn3 extends LinearOpMode {

    // Dashboard Variables for the movement sequence
    // Field is 140" x 140" with center at (0,0), so coordinates range from -70 to +70
    // Robot chassis is 15", so robot center must stay 7.5" from field edges
    public static double START_X = 40;              // Starting position X
    public static double START_Y = 0;               // Starting position Y
    public static double STRAIGHT_DISTANCE = 80;   // Distance to travel straight (right to left)
    public static double FORWARD_DISTANCE = 20;    // Distance to go forward after turn
    public static double TURN_ANGLE = 90;          // Turn angle in degrees (positive = counterclockwise)

    // Calculated positions
    // Note: These will update automatically on the FTC Dashboard when you change the START_X and STRAIGHT_DISTANCE values.
    public static double END_STRAIGHT_X = START_X - STRAIGHT_DISTANCE;  // End of straight movement
    public static double FINAL_X = END_STRAIGHT_X;                      // Final X (no change during forward)
    public static double FINAL_Y = START_Y + FORWARD_DISTANCE;          // Final Y after forward movement

    // Pose definitions using RoadRunner 1.0.1 Pose2d
    public static Pose2d STARTING_POSE = new Pose2d(START_X, START_Y, Math.toRadians(180)); // Face left (180°)

    private ChampionMecanumDrive drive;

    @Override
    public void runOpMode() throws InterruptedException {

        // Initialize the drive chassis with starting pose
        drive = new ChampionMecanumDrive(hardwareMap, STARTING_POSE);

        // Create a single Action sequence for smooth, coordinated movement
        Action movementSequence = drive.actionBuilder(STARTING_POSE)
                // Step 1: Go straight from right to left (80 inches)
                .splineToLinearHeading(new Pose2d(END_STRAIGHT_X, START_Y, Math.toRadians(180)), Math.toRadians(180))

                // Step 2: Turn 90 degrees right (from 180° to 270°)
                // Note: The turn angle is from the current heading, so a +90 turn on a 180° heading results in a 270° heading.
                .turn(Math.toRadians(TURN_ANGLE))

                // Step 3: Go forward 20 inches (in the new direction)
                .lineToY(START_Y + FORWARD_DISTANCE)

                .build();

        // Display initialization telemetry
        telemetry.addLine("READY - 80\" straight + 90° turn + 20\" forward");
        telemetry.addLine("────────────────────────────────");
        telemetry.addData("Start Position", "X: " + START_X + ", Y: " + START_Y + ", θ: 180°");
        telemetry.addData("After Straight", "X: " + END_STRAIGHT_X + ", Y: " + START_Y + ", θ: 180°");
        telemetry.addData("After Turn", "X: " + END_STRAIGHT_X + ", Y: " + START_Y + ", θ: 270°");
        telemetry.addData("Final Position", "X: " + FINAL_X + ", Y: " + FINAL_Y + ", θ: 270°");
        telemetry.addLine("────────────────────────────────");
        telemetry.addData("Straight Distance", STRAIGHT_DISTANCE + " inches");
        telemetry.addData("Turn Angle", TURN_ANGLE + "° right");
        telemetry.addData("Forward Distance", FORWARD_DISTANCE + " inches");
        telemetry.addData("Robot Chassis", "15\" (7.5\" from center to edge)");
        telemetry.update();

        waitForStart();

        if (isStopRequested()) {
            return;
        }

        // Execute the complete movement sequence
        telemetry.addLine("Starting movement sequence...");
        telemetry.addData("Step 1", "Going straight " + STRAIGHT_DISTANCE + " inches");
        telemetry.update();

        Actions.runBlocking(movementSequence);

        // Show completion status
        telemetry.addLine("All movements complete!");
        telemetry.addData("Expected Final", "X: " + FINAL_X + ", Y: " + FINAL_Y + ", θ: " + (180 + TURN_ANGLE) + "°");
        telemetry.update();

        // Keep the robot active for a bit to see final position
        sleep(2000);
    }
}
