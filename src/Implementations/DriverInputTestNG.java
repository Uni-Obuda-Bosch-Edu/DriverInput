package Implementations;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.*;

import FrameWork.virtualDataBus.Container;
import FrameWork.virtualDataBus.Container.GearMode;

public class DriverInputTestNG {

	private Container bus;
	private DriverInput input;
	private final double defaultSteeringWheelRotation = 0.0;
	private final double maxSteeringWheelRotationPerDirection = 100.0;
	private final double minPedalPushPercent = 0.0;
	private final double maxPedalPushPercent = 100.0;
	private final double noMoveValue = 0.0;
	private double randomInRangeValue;
	private double randomOutOfRangeValue;
	
	@BeforeTest
	public void beforeTest() {
		bus = Container.getInstance();
		input = new DriverInput(bus);
		Random rand = new Random();
		randomInRangeValue = rand.nextInt(99) + 1; // required magic num
		randomOutOfRangeValue = rand.nextInt(99) + 100; // required magic num
	}

	@Test
	public void testPushEngineToggleButton_InParking() {
		bus.setEngineToggleButtonState(false);
		bus.setShiftLeverPosition(GearMode.Parking);
		input.pushEngineToggleButton();
		Assert.assertTrue(bus.getEngineToggleButtonState());
	}
	
	@Test
	public void testPushEngineToggleButton_NotInParking() {
		bus.setEngineToggleButtonState(false);
		bus.setShiftLeverPosition(GearMode.Neutral);
		input.pushEngineToggleButton();
		Assert.assertFalse(bus.getEngineToggleButtonState());
	}
	
	@Test
	public void testTurnSteeringWheelLeft_NoMove() {
		final double turnPercent = noMoveValue;
		bus.setSteeringWheelRotationPercent(defaultSteeringWheelRotation);
		input.turnSteeringWheelLeft(turnPercent);
		Assert.assertEquals(bus.getSteeringWheelRotationPercent(), defaultSteeringWheelRotation);
		
	}
	
	@Test
	public void testTurnSteeringWheelLeft_InRange() {
		final double turnPercent = randomInRangeValue;
		bus.setSteeringWheelRotationPercent(defaultSteeringWheelRotation);
		input.turnSteeringWheelLeft(turnPercent);
		Assert.assertEquals(bus.getSteeringWheelRotationPercent(), -turnPercent);
		
	}
	
	@Test
	public void testTurnSteeringWheelLeft_OutOfRange() {
		final double turnPercent = randomOutOfRangeValue;
		bus.setSteeringWheelRotationPercent(defaultSteeringWheelRotation);
		input.turnSteeringWheelLeft(turnPercent);
		Assert.assertEquals(bus.getSteeringWheelRotationPercent(), -maxSteeringWheelRotationPerDirection);
		
	}
	
	@Test
	public void testTurnSteeringWheelRight_NoMove() {
		final double turnPercent = noMoveValue;
		bus.setSteeringWheelRotationPercent(defaultSteeringWheelRotation);
		input.turnSteeringWheelRight(turnPercent);
		Assert.assertEquals(bus.getSteeringWheelRotationPercent(), defaultSteeringWheelRotation);
		
	}
	
	@Test
	public void testTurnSteeringWheelRight_InRange() {
		final double turnPercent = randomInRangeValue;
		bus.setSteeringWheelRotationPercent(defaultSteeringWheelRotation);
		input.turnSteeringWheelRight(turnPercent);
		Assert.assertEquals(bus.getSteeringWheelRotationPercent(), turnPercent);
		
	}
	
	@Test
	public void testTurnSteeringWheelRight_OutOfRange() {
		final double turnPercent = randomOutOfRangeValue;
		bus.setSteeringWheelRotationPercent(defaultSteeringWheelRotation);
		input.turnSteeringWheelRight(turnPercent);
		Assert.assertEquals(bus.getSteeringWheelRotationPercent(), maxSteeringWheelRotationPerDirection);
		
	}
	
	@Test
	public void testPushGasPedal_NoMove() {
		final double pushPercent = noMoveValue;
		bus.setGasPedalPushPercent(minPedalPushPercent);
		input.pushGasPedal(pushPercent);
		Assert.assertEquals(bus.getGasPedalPushPercent(), minPedalPushPercent);
	}
	
	@Test
	public void testPushGasPedal_InRange() {
		final double pushPercent = randomInRangeValue;
		bus.setGasPedalPushPercent(minPedalPushPercent);
		input.pushGasPedal(pushPercent);
		Assert.assertEquals(bus.getGasPedalPushPercent(), pushPercent);
	}
	
	@Test
	public void testPushGasPedal_OutOfRange() {
		final double pushPercent = randomOutOfRangeValue;
		bus.setGasPedalPushPercent(minPedalPushPercent);
		input.pushGasPedal(pushPercent);
		Assert.assertEquals(bus.getGasPedalPushPercent(), maxPedalPushPercent);
	}
	
	@Test
	public void testReleaseGasPedal_NoMove() {
		final double releasePercent = noMoveValue;
		bus.setGasPedalPushPercent(maxPedalPushPercent);
		input.releaseGasPedal(releasePercent);
		Assert.assertEquals(bus.getGasPedalPushPercent(), maxPedalPushPercent);
	}
	
	@Test
	public void testReleaseGasPedal_InRange() {
		final double releasePercent = randomInRangeValue;
		bus.setGasPedalPushPercent(maxPedalPushPercent);
		input.releaseGasPedal(releasePercent);
		Assert.assertEquals(bus.getGasPedalPushPercent(), maxPedalPushPercent - releasePercent);
	}
	
	@Test
	public void testReleaseGasPedal_OutOfRange() {
		final double releasePercent = randomOutOfRangeValue;
		bus.setGasPedalPushPercent(maxPedalPushPercent);	
		input.releaseGasPedal(releasePercent);
		Assert.assertEquals(bus.getGasPedalPushPercent(), minPedalPushPercent);
	}
	
	@Test
	public void testPushBrakePedal_NoMove() {
		final double pushPercent = noMoveValue;
		bus.setBrakePedalPushPercent(minPedalPushPercent);
		input.pushBrakePedal(pushPercent);
		Assert.assertEquals(bus.getBrakePedalPushPercent(), minPedalPushPercent);
	}
	
	@Test
	public void testPushBrakePedal_InRange() {
		final double pushPercent = randomInRangeValue;
		bus.setBrakePedalPushPercent(minPedalPushPercent);
		input.pushBrakePedal(pushPercent);
		Assert.assertEquals(bus.getBrakePedalPushPercent(), pushPercent);
	}
	
	@Test
	public void testPushBrakePedal_OutOfRange() {
		final double pushPercent = randomOutOfRangeValue;
		bus.setBrakePedalPushPercent(minPedalPushPercent);
		input.pushBrakePedal(pushPercent);
		Assert.assertEquals(bus.getBrakePedalPushPercent(), maxPedalPushPercent);
	}
	
	@Test
	public void testReleaseBrakePedal_NoMove() {
		final double releasePercent = noMoveValue;
		bus.setBrakePedalPushPercent(maxPedalPushPercent);
		input.releaseBrakePedal(releasePercent);
		Assert.assertEquals(bus.getBrakePedalPushPercent(), maxPedalPushPercent);
	}
	
	@Test
	public void testReleaseBrakePedal_InRange() {
		final double releasePercent = randomInRangeValue;
		bus.setBrakePedalPushPercent(maxPedalPushPercent);
		input.releaseBrakePedal(releasePercent);
		Assert.assertEquals(bus.getBrakePedalPushPercent(), maxPedalPushPercent - releasePercent);
	}
	
	@Test
	public void testReleaseBrakePedal_OutOfRange() {
		final double releasePercent = randomOutOfRangeValue;
		bus.setBrakePedalPushPercent(maxPedalPushPercent);	
		input.releaseBrakePedal(releasePercent);
		Assert.assertEquals(bus.getBrakePedalPushPercent(), minPedalPushPercent);
	}
	
	@Test
	public void testMoveShiftLeverTo_PushingBrake() {
		final GearMode newGearMode = GearMode.Drive;
		bus.setShiftLeverPosition(GearMode.Parking);
		bus.setBrakePedalPushPercent(maxPedalPushPercent);
		input.moveShiftLeverTo(newGearMode);
		Assert.assertEquals(bus.getShiftLeverPosition(), newGearMode);		
	}
	
	@Test
	public void testMoveShiftLeverTo_NotPushingBrake() {
		final GearMode startGearMode = GearMode.Parking;
		bus.setShiftLeverPosition(startGearMode);
		bus.setBrakePedalPushPercent(minPedalPushPercent);
		input.moveShiftLeverTo(GearMode.Drive);
		Assert.assertEquals(bus.getShiftLeverPosition(), startGearMode);	
	}
}
