package frc.robot.subsystems

import edu.wpi.first.wpilibj.controller.PIDController
import edu.wpi.first.wpilibj2.command.SubsystemBase
import frc.robot.Constants.PID

class TurnController : SubsystemBase() {
    private val turncontroller: PIDController
    companion object {
        private var tcontroller: TurnController? = null
    }

    init {
        turncontroller = PIDController(PID.kP, PID.kI, PID.kD)
    }

    fun setSetPoint(x: Double) {
        turncontroller.setpoint = x // set góc cần quay (input dương thì quay phải)
    }

    fun reset() {
        turncontroller.reset() // reset the intergral term, the previous error
    }

    fun enableContinuousInput(a: Double, b: Double) {
        turncontroller.enableContinuousInput(a, b)
        // Rather then using the max and min input range as constraints, it considers them to be
        // the same point and automatically calculates the shortest route to the setpoint.
    }

    fun setIntegratorRange(m: Double, n: Double) {
        turncontroller.setIntegratorRange(m, n)
    }

    fun setTolerance() {
        turncontroller.setTolerance(PID.kToleranceDegress, PID.kToleranceAngularVelocity) // angular velocity: rad/s
        // Đặt lỗi được coi là có thể chấp nhận được để sử dụng tại Setpoint().
    }

    fun calculate(c: Double): Double {
        return turncontroller.calculate(c)
        // Trả về đầu ra tiếp theo của bộ điều khiển PID.
    }

    fun atSetpoint(): Boolean {
        return turncontroller.atSetpoint()
        // Return true if the error is within the tolerance of the set point
    }

    override fun periodic() {}

}