package frc.robot

object Constants {

    object Buttons {
        var speedUpButton = 7
        var driveRight = 1
        var driveLeft = 3
    }

    object Motors {
        // Mấy cái này cũng sẽ phải đặt lại theo ID mỗi cái Talon trên Driver Station
        var rightMaster = 1
        var leftMaster = 2
        var rightFollow = 3
        var leftFollow = 4
        var intaker1 = 7
        var intaker2 = 8
        var intaker3 = 9
    }

    object Controllers {
        var movementController = 0
        var intakeController = 2
        var sensitivity = 1.0 // 0 -> 1
        var deadzone = 0.01 // 0 -> 1, recommended around .01 -> .05
        var driveSwapButton = 1 // Change between legacy and new driving system
    }

    object Speed {
        var defaultIntakerSpeed = 0.4
        var defaultMoveSpeed = 0.4
        var safetyThreshold = .375 // Maximum manual speed cap
    }

    object Time {
        var timeToMove1MeterAtDefaultSpeed = 12345 // in milliseconds
    }

    object PID {
        var kP = 1.0 / 180
        var kD = 0.0
        var kI = 0.0
        var kCollisionThreshold_DeltaG = 1.0 / 180
        var kToleranceDegress = 2.0
        var kToleranceAngularVelocity = 0.08
        const val kToleranceStraight = 0.1
        const val kStraightConstant = 0.08
    }
}