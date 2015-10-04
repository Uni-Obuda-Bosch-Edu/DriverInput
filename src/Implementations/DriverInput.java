package Implementations;

import FrameWork.busInterface.DriverInput_Out;
import FrameWork.busInterface.Public_In;
import FrameWork.virtualDataBus.Container;
import FrameWork.virtualDataBus.Container.GearMode;

public class DriverInput {
	public final static int MaxSteeringWheelRotationPerDirection = 100;
	public final static int MaxPedalPushValue = 100;	
	public final static int ShiftLeverPositionParking = 0;
	public final static int ShiftLeverPositionReverse = 1;
	public final static int ShiftLeverPositionNeutral = 2;
	public final static int ShiftLeverPositionDrive = 3;
	
	private Public_In in;
	private DriverInput_Out out;

	public DriverInput(Public_In in, DriverInput_Out out) {
		this.in = in;
		this.out = out;
		out.setEngineToggleButtonState(false);
		out.setShiftLeverPosition(0);
		out.setSteeringWheelRotationPercent(0.0);
		out.setGasPedalPushPercent(0.0);
		out.setBrakePedalPushPercent(0.0);
	}

	public void pushEngineToggleButton() {
		boolean currentState = in.getEngineToggleButtonState();
		if (currentState == false && in.getShiftLeverPosition() != ShiftLeverPositionParking)
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

	public void moveShiftLeverTo(int position) {
		if (in.getBrakePedalPushPercent() != MaxPedalPushValue)
			return;
		out.setShiftLeverPosition(position);
	}
}
