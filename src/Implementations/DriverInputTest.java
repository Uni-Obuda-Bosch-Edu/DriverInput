package Implementations;

import FrameWork.virtualDataBus.Container;
import FrameWork.virtualDataBus.Container.GearMode;

public class DriverInputTest {
	private static Container bus;
	private static DriverInput input;

	private static String gearModeToString(GearMode mode) {
		switch (mode) {
		case Parking:
			return "PARKING";
		case Neutral:
			return "NEUTRAL";
		case Drive:
			return "DRIVE";
		case Reverse:
			return "REVERSE";
		}
		return null;
	}

	private static void printInputContext() {
		String ETBS = bus.getEngineToggleButtonState() ? "ON" : "OFF";
		double SWRP = bus.getSteeringWheelRotationPercent();
		String DIR = SWRP < 0.0 ? " (LEFT)" : (SWRP > 0.0 ? " (RIGHT)" : "");
		double GPPP = bus.getGasPedalPushPercent();
		double BPPP = bus.getBrakePedalPushPercent();
		String SLP = gearModeToString(bus.getShiftLeverPosition());
		System.out.println("[EngineButton = " + ETBS + "; SteeringWheel = " + Math.abs(SWRP) + "%" + DIR + "; GasPedal = "
				+ GPPP + "%; BrakePedal = " + BPPP + "%; ShiftLever = " + SLP + "]");
	}

	private static void runTest(Runnable funcRef, String actionMessage) {
		System.out.print("\nBefore -> ");
		printInputContext();
		funcRef.run();
		System.out.println("Action -> " + actionMessage + "..");
		System.out.print("After  -> ");
		printInputContext();
	}

	private static void testPushEngineToggleButton() {
		runTest(new Runnable() {
			@Override
			public void run() {
				input.pushEngineToggleButton();
			}
		}, "Pushing the engine toggle button");
	}

	private static void testTurnSteeringWheelLeft(final double value) {
		runTest(new Runnable() {
			@Override
			public void run() {
				input.turnSteeringWheelLeft(value);
			}
		}, "Turning the steering wheel left by " + value + "%");
	}

	private static void testTurnSteeringWheelRight(final double value) {
		runTest(new Runnable() {
			@Override
			public void run() {
				input.turnSteeringWheelRight(value);
			}
		}, "Turning the steering wheel right by " + value + "%");
	}

	private static void testPushGasPedal(final double value) {
		runTest(new Runnable() {
			@Override
			public void run() {
				input.pushGasPedal(value);
			}
		}, "Pushing the gas pedal by " + value + "%");
	}

	private static void testReleaseGasPedal(final double value) {
		runTest(new Runnable() {
			@Override
			public void run() {
				input.releaseGasPedal(value);
			}
		}, "Releasing the gas pedal by " + value + "%");
	}

	private static void testPushBrakePedal(final double value) {
		runTest(new Runnable() {
			@Override
			public void run() {
				input.pushBrakePedal(value);
			}
		}, "Pushing the brake pedal by " + value + "%");
	}

	private static void testReleaseBrakePedal(final double value) {
		runTest(new Runnable() {
			@Override
			public void run() {
				input.releaseBrakePedal(value);
			}
		}, "Releasing the brake pedal by " + value + "%");
	}

	private static void testMoveShiftLeverTo(final GearMode mode) {
		runTest(new Runnable() {
			@Override
			public void run() {
				input.moveShiftLeverTo(mode);
			}
		}, "Moving the shifting lever to position " + gearModeToString(mode));
	}

	public static void main(String[] args) {
		bus = Container.getInstance();
		input = new DriverInput(bus);

		System.out.println("Engine start failure\n");
		testPushBrakePedal(100.0);
		testMoveShiftLeverTo(GearMode.Neutral);
		testPushEngineToggleButton(); // failure, can only start engine while in
										// Parking mode
		System.out.println("\n\n\n");

		System.out.println("Engine start success\n");
		testMoveShiftLeverTo(GearMode.Parking);
		testReleaseBrakePedal(100.0);
		testPushEngineToggleButton(); // OK now, we are in the right mode
		testPushEngineToggleButton(); // off
		testPushEngineToggleButton(); // and on again
		System.out.println("\n\n\n");

		System.out.println("Steering wheel rotation (left)\n");
		testTurnSteeringWheelLeft(20.0); // at -20.0
		testTurnSteeringWheelLeft(120.0); // stops at -100.0
		testTurnSteeringWheelLeft(40.0); // still at -100.0
		testTurnSteeringWheelRight(100);
		System.out.println("\n\n\n");

		System.out.println("Steering wheel rotation (right)\n");
		testTurnSteeringWheelRight(80.0); // at +80.0
		testTurnSteeringWheelRight(30.0); // stops at +100.0
		testTurnSteeringWheelRight(10.0); // still at +100.0
		testTurnSteeringWheelLeft(100);
		System.out.println("\n\n\n");

		System.out.println("Gas pedal pushing\n");
		testPushGasPedal(50.0); // at 50.0
		testPushGasPedal(70.0); // stops at 100.0
		testPushGasPedal(90.0); // still at 100.0
		System.out.println("\n\n\n");

		System.out.println("Gas pedal releasing\n");
		testReleaseGasPedal(60.0); // at 40.0
		testReleaseGasPedal(110.0); // stops at 0.0
		testReleaseGasPedal(130.0); // still at 0.0
		System.out.println("\n\n\n");

		System.out.println("Brake pedal pushing & releasing\n");
		testPushBrakePedal(35.0); // at 35.0
		testReleaseBrakePedal(5.0); // at 30.0
		testPushBrakePedal(80.0); // stops at 100.0
		testReleaseBrakePedal(130.0); // stops at 0.0
		testPushBrakePedal(100.0); // at 100.0
		testReleaseBrakePedal(100.0);
		System.out.println("\n\n\n");

		System.out.println("Shift lever move failure\n");
		testMoveShiftLeverTo(GearMode.Drive); // failure, can only shift when
												// brake if fully pushed
		System.out.println("\n\n\n");

		System.out.println("Shift lever move success\n");
		testPushBrakePedal(100);
		testMoveShiftLeverTo(GearMode.Drive); // OK now, pedal is pushed
												// correctly
		testMoveShiftLeverTo(GearMode.Neutral);
		testMoveShiftLeverTo(GearMode.Reverse);
		testMoveShiftLeverTo(GearMode.Parking);
		testReleaseBrakePedal(100);
		testPushEngineToggleButton();
	}
}
