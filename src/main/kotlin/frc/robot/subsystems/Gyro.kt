package frc.robot.subsystems

import edu.wpi.first.wpilibj2.command.SubsystemBase
import com.kauailabs.navx.frc.AHRS
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard

class Gyro : SubsystemBase() {
    private val ahrs : AHRS // consist of sensors on three axes : yaw, roll, pitch

    init {
        ahrs = AHRS()
    }

    fun reset() {
        ahrs.reset() // reset cả 3 trục về 0
    }

    // return the yaw value that was reported by the sensor (from -180 to 180)
    val yaw: Double
        get() = ahrs.yaw.toDouble() // return the yaw value that was reported by the sensor (from -180 to 180)

    override fun periodic() {
        SmartDashboard.putNumber("X", ahrs.displacementX.toDouble()) // Liên tục cập nhật giá trị của X lên Dashboard
        SmartDashboard.putNumber("Y", ahrs.displacementY.toDouble()) // Liên tục cập nhật giá trị của Y lên Dashboard
    }

    companion object {
        private var gyro: Gyro? = null
        val instance: Gyro?
            get() {
                if (gyro == null) {
                    gyro = Gyro()
                }
                return gyro
            }
    }
}