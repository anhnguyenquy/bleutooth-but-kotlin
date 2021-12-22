package frc.robot

import edu.wpi.first.wpilibj.GenericHID
import edu.wpi.first.wpilibj.XboxController
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.button.JoystickButton
import frc.robot.Constants.Controllers
import frc.robot.commands.*
import frc.robot.subsystems.*

class RobotContainer {

    companion object {
        @JvmStatic var useLegacy = false
        @JvmStatic var drivebase: Drivebase = Drivebase()
        @JvmStatic var movementController = XboxController(Controllers.movementController)
    }


    var driveManual: Command = DriveManual(drivebase)
    var toggleDriveSystem: Command = ToggleDriveSystem()

    init {
        configureButtonBindings()
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be created by
     * instantiating a [GenericHID] or one of its subclasses ([ ] or [XboxController]), and then passing it to a
     * [edu.wpi.first.wpilibj2.command.button.JoystickButton].
     */
    private fun configureButtonBindings() {
        JoystickButton(movementController, Controllers.driveSwapButton).whileActiveOnce(toggleDriveSystem)
    }


    /**
     * Use this to pass the autonomous command to the main [Robot] class.
     *
     * @return the command to run in autonomous
     */
    val autonomousCommand: Command
        get() {
            return driveManual
        }
}
