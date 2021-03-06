package org.ftcteam5206;
import android.app.Activity;
import android.hardware.Sensor;
import android.widget.RadioButton;

import com.qualcomm.hardware.adafruit.BNO055IMU;
import com.qualcomm.hardware.adafruit.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.AnalogSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.hardware.configuration.DeviceConfiguration;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity;
import org.firstinspires.ftc.teamcode.R;
import org.ftcteam5206.subsystems.RobotConstants;

/**
 * Created by Dev on 11/25/2016.
 */

public class KingArthur {
    public DcMotor leftDrive = null;
    public DcMotor rightDrive = null;
    public DcMotor launcher = null;
    public DcMotor launcher2 = null;
    public DcMotor intakeTransport = null;
    public DcMotor capMotor = null;
    public AnalogInput turretPot = null;
    public BNO055IMU imu = null;
    public Servo beaconPusher = null;
    public Servo forkReleaseLeft = null;
    public Servo forkReleaseRight = null;
    public Servo clasp = null;
    public Servo hood = null;
    public Servo capRelease = null;
    public Servo phone = null;
    public DeviceInterfaceModule dim = null;
    public Servo transportServo = null;
    public TouchSensor transportSensor = null;
    public ColorSensor beaconSensor = null;
    public VoltageSensor voltageSensor = null;

    HardwareMap hwMap = null;

    public KingArthur(){}

    public void init(HardwareMap hwMap){
        this.hwMap = hwMap;

        //Init DCs
        leftDrive   = hwMap.dcMotor.get("ldrive");
        rightDrive  = hwMap.dcMotor.get("rdrive");
        launcher    = hwMap.dcMotor.get("launcher");
        launcher2    = hwMap.dcMotor.get("launcher2");
        intakeTransport = hwMap.dcMotor.get("intake");
        capMotor = hwMap.dcMotor.get("cap");
        //Set Directions
        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        leftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        launcher.setDirection(DcMotor.Direction.FORWARD);
        launcher.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        launcher.setDirection(DcMotor.Direction.FORWARD);
        launcher.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        intakeTransport.setDirection(DcMotor.Direction.FORWARD);
        intakeTransport.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        capMotor.setDirection(DcMotor.Direction.REVERSE);
        capMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        // Set all motors to zero power
        leftDrive.setPower(0);
        rightDrive.setPower(0);
        launcher.setPower(0);
        launcher2.setPower(0);
        intakeTransport.setPower(0);
        capMotor.setPower(0);

        // Set all motors to run in open loop.
        leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        launcher.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        launcher2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intakeTransport.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        capMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        // Define and initialize ALL installed servos.
        beaconPusher = hwMap.servo.get("beacon");
        //forkReleaseLeft = hwMap.servo.get("forkleft");
        forkReleaseRight = hwMap.servo.get("forkright");
        clasp = hwMap.servo.get("clasp");
        phone = hwMap.servo.get("phone");
        //forkRelease = hwMap.servo.get("fork");
        //clasp = hwMap.servo.get("clasp");
        hood = hwMap.servo.get("hood");
        //capRelease = hwMap.servo.get("cap");
        dim = this.hwMap.deviceInterfaceModule.get("dim");
        transportServo = hwMap.servo.get("transport");
        //TODO: Change these parameters to things we actually like
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.mode = BNO055IMU.SensorMode.IMU;
        parameters.useExternalCrystal = true;
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        parameters.pitchMode = BNO055IMU.PitchMode.WINDOWS;
        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";


        imu = hwMap.get(BNO055IMU.class, "imu");//Angle 1 is yaw, angle 2 is roll, angle 3 is pitch
        imu.initialize(parameters);
        //turretPot = hwMap.get(AnalogInput.class, "pot");
        transportSensor = hwMap.touchSensor.get("ball");
        beaconSensor = hwMap.colorSensor.get("beaconSensor");
        voltageSensor = hwMap.voltageSensor.get("Launcher Motor Controller");
    }

    public void resetEncoders(){
        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        launcher.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        intakeTransport.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        capMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //Might need an idle/wait here.
        leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        launcher.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intakeTransport.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        capMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
    //TODO: See if we need to wait for tick

    public void imuCalibrate(){
        throw new UnsupportedOperationException();
    }
}
