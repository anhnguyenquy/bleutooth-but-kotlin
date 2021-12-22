package frc.robot.subsystems

import edu.wpi.first.wpilibj2.command.SubsystemBase
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX
import frc.robot.Constants.Motors
import com.ctre.phoenix.motorcontrol.NeutralMode
import frc.robot.RobotContainer
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import frc.robot.Constants.Controllers
import frc.robot.Constants.Speed

class Drivebase : SubsystemBase() {
    private val rightMaster: WPI_TalonSRX
    private val leftMaster: WPI_TalonSRX
    private val rightFollow: WPI_TalonSRX
    private val leftFollow: WPI_TalonSRX
    private var baseSpeed = 0.0
    private var pivot = 0.0
    private var leftMotorInput = 0.0
    private var rightMotorInput = 0.0

    // Legacy
    private var leftSpeed = 0.0
    private var rightSpeed = 0.0

    init {
        rightMaster = WPI_TalonSRX(Motors.rightMaster)
        rightMaster.setNeutralMode(NeutralMode.Brake)
        leftMaster = WPI_TalonSRX(Motors.leftMaster)
        leftMaster.setNeutralMode(NeutralMode.Brake)
        leftMaster.inverted = true
        rightFollow = WPI_TalonSRX(Motors.rightFollow)
        rightFollow.setNeutralMode(NeutralMode.Brake)
        rightFollow.follow(rightMaster)
        leftFollow = WPI_TalonSRX(Motors.leftFollow)
        leftFollow.setNeutralMode(NeutralMode.Brake)
        leftFollow.follow(leftMaster)
    }

    fun drive(leftSpeed: Double, rightSpeed: Double) {
        leftMaster.set(leftSpeed)
        rightMaster.set(rightSpeed)
    }

    override fun periodic() {
        if (RobotContainer.useLegacy) {
            val boostLeft: Double = if (RobotContainer.movementController.getRawAxis(2) == 1.0) 1.0 else 0.4
            val boostRight: Double = if (RobotContainer.movementController.getRawAxis(4) == 1.0) 1.0 else 0.4
            leftSpeed = -RobotContainer.movementController.getRawAxis(1) * boostLeft
            rightSpeed = RobotContainer.movementController.getRawAxis(5) * boostRight
            drive(leftSpeed, rightSpeed)
            SmartDashboard.putNumber("Left motor's speed", leftSpeed)
            SmartDashboard.putNumber("Right motor's speed", rightSpeed)
        } else {
            baseSpeed = RobotContainer.movementController.getRawAxis(1)
            baseSpeed = if (Math.abs(baseSpeed) > Controllers.deadzone) baseSpeed else 0.0
            pivot = RobotContainer.movementController.getRawAxis(4)
            pivot = if (Math.abs(pivot) > Controllers.deadzone) pivot else 0.0
            leftMotorInput = baseSpeed
            rightMotorInput = baseSpeed
            if (baseSpeed > 0) {
                leftMotorInput += pivot
                rightMotorInput -= pivot

                // Enforce safety speed cap
                leftMotorInput = Math.min(Speed.safetyThreshold, leftMotorInput)
                rightMotorInput = Math.min(Speed.safetyThreshold, rightMotorInput)

                // Enable quick turning
                if (leftMotorInput * rightMotorInput <= 0) {
                    leftMotorInput = if (leftMotorInput <= 0) -rightMotorInput else leftMotorInput
                    rightMotorInput = if (rightMotorInput <= 0) -leftMotorInput else rightMotorInput
                }
            } else if (baseSpeed < 0) {
                leftMotorInput -= pivot
                rightMotorInput += pivot

                // Enforce speed safety cap
                leftMotorInput = Math.max(-Speed.safetyThreshold, leftMotorInput)
                rightMotorInput = Math.max(-Speed.safetyThreshold, rightMotorInput)

                // Enable quick turning
                if (leftMotorInput * rightMotorInput <= 0) {
                    leftMotorInput = if (leftMotorInput >= 0) -rightMotorInput else leftMotorInput
                    rightMotorInput = if (rightMotorInput >= 0) -leftMotorInput else rightMotorInput
                }
            }

            // For some reason lol
            leftMotorInput = -leftMotorInput

            // Modeset the motors and output to SmartDashboard
            // Has to be negated for some reason
            drive(leftMotorInput, rightMotorInput)
            SmartDashboard.putNumber("Left motor's speed", leftMotorInput)
            SmartDashboard.putNumber("Right motor's speed", rightMotorInput)
        }
        SmartDashboard.putBoolean("Legacy Mode", RobotContainer.useLegacy)
    }
}