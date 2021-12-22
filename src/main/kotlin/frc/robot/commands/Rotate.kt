package frc.robot.commands

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import edu.wpi.first.wpilibj2.command.CommandBase
import frc.robot.subsystems.TurnController
import frc.robot.subsystems.Drivebase
import frc.robot.subsystems.Gyro

class Rotate(drivebase: Drivebase, angle: Double) : CommandBase() {
    val m_drivebase: Drivebase
    val m_gyro: Gyro
    val turnController: TurnController

    init {
        m_drivebase = drivebase
        m_gyro = Gyro.instance!!
        turnController = TurnController()
        turnController.setSetPoint(angle)
        turnController.enableContinuousInput(
            -180.0,
            180.0
        ) // quay liên tục giữa -180 và 180 độ để đảm bảo gần vs setpoint nhất có thể
        turnController.setIntegratorRange(-10.0, 1.0) // set range kI từ -10 đến 1
        turnController.setTolerance()
        addRequirements(m_drivebase)
        addRequirements(m_gyro)
    }

    override fun initialize() {}
    override fun execute() {
        SmartDashboard.putBoolean("start", true)
        SmartDashboard.putNumber("angle", m_gyro.yaw)
        var speed: Double = turnController.calculate(m_gyro.yaw)
        speed += Math.signum(speed) * 0.1
        speed = Math.min(0.6, Math.max(-0.6, speed))
        m_drivebase.drive(-speed, speed)
    }

    override fun end(interrupted: Boolean) {
        m_drivebase.drive(0.0, 0.0)
        SmartDashboard.putBoolean("start", false)
    }

    override fun isFinished(): Boolean {
        return turnController.atSetpoint()
    }
}