package Implementations;

import FrameWork.busInterface.DriverInput_Out;
import FrameWork.busInterface.Public_In;
import FrameWork.virtualDataBus.Container;
import FrameWork.virtualDataBus.Container.GearMode;

public class DriverInput {
	private final static double MaxSteeringWheelRotationPerDirection = 100.0;
	private final static double MaxPedalPushValue = 100.0;
	private Public_In in;
	private DriverInput_Out out;

	public DriverInput(Container container) {
		this.in = container;
		this.out = container;
		out.setEngineToggleButtonState(false);
		out.setShiftLeverPosition(GearMode.Parking);
		out.setSteeringWheelRotationPercent(0.0);
		out.setGasPedalPushPercent(0.0);
		out.setBrakePedalPushPercent(0.0);
	}

	public void pushEngineToggleButton() {
		boolean currentState = in.getEngineToggleButtonState();
		if (currentState == false && in.getShiftLeverPosition() != GearMode.Parking)
			return;
		out.setEngineToggleButtonState(!currentState);
	}

	public void turnSteeringWheelLeft(double percent) {
		double currentRotation = in.getSteeringWheelRotationPercent();
		currentRotation -= Math.min(Math.abs(percent), MaxSteeringWheelRotationPerDirection + currentRotation);
		out.setSteeringWheelRotationPercent(currentRotation);
	}

	public void turnSteeringWheelRight(double percent) {
		double currentPosition = in.getSteeringWheelRotationPercent();
		currentPosition += Math.min(Math.abs(percent), MaxSteeringWheelRotationPerDirection - currentPosition);
		out.setSteeringWheelRotationPercent(currentPosition);
	}

	public void pushGasPedal(double percent) {
		double currentPosition = in.getGasPedalPushPercent();
		currentPosition += Math.min(Math.abs(percent), MaxPedalPushValue - currentPosition);
		out.setGasPedalPushPercent(currentPosition);
	}

	public void releaseGasPedal(double percent) {
		double currentPosition = in.getGasPedalPushPercent();
		currentPosition -= Math.min(Math.abs(percent), currentPosition);
		out.setGasPedalPushPercent(currentPosition);
	}

	public void pushBrakePedal(double percent) {
		double currentPosition = in.getBrakePedalPushPercent();
		currentPosition += Math.min(Math.abs(percent), MaxPedalPushValue - currentPosition);
		out.setBrakePedalPushPercent(currentPosition);
	}

	public void releaseBrakePedal(double percent) {
		double currentPosition = in.getBrakePedalPushPercent();
		currentPosition -= Math.min(Math.abs(percent), currentPosition);
		out.setBrakePedalPushPercent(currentPosition);
	}

	public void moveShiftLeverTo(GearMode mode) {
		if (in.getBrakePedalPushPercent() != MaxPedalPushValue)
			return;
		out.setShiftLeverPosition(mode);
	}
}
