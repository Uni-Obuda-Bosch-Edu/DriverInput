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
	
	private static double limitInput(double value, double upperBound) {
		value = Math.abs(value);
		return value % upperBound;
	}
	
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
		percent = limitInput(percent, MaxSteeringWheelRotationPerDirection * 2);
		double currentRotation = in.getSteeringWheelRotationPercent();
		currentRotation -= Math.min(percent, MaxSteeringWheelRotationPerDirection + currentRotation);
		out.setSteeringWheelRotationPercent(currentRotation);
	}
	
	public void turnSteeringWheelRight(double percent) {
		percent = limitInput(percent, MaxSteeringWheelRotationPerDirection * 2);
		double currentPosition = in.getSteeringWheelRotationPercent();
		currentPosition += Math.min(percent, MaxSteeringWheelRotationPerDirection - currentPosition);
		out.setSteeringWheelRotationPercent(currentPosition);
	}
	
	public void pushGasPedal(double percent) {
		percent = limitInput(percent, MaxPedalPushValue);
		double currentPosition = in.getGasPedalPushPercent();
		currentPosition += Math.min(percent, MaxPedalPushValue - currentPosition);
		out.setGasPedalPushPercent(currentPosition);
	}
	
	public void releaseGasPedal(double percent) {
		percent = limitInput(percent, MaxPedalPushValue);
		double currentPosition = in.getGasPedalPushPercent();
		currentPosition -= Math.min(percent, currentPosition);
		out.setGasPedalPushPercent(currentPosition);
	}
	
	public void pushBrakePedal(double percent) {
		percent = limitInput(percent, MaxPedalPushValue);
		double currentPosition = in.getBrakePedalPushPercent();
		currentPosition += Math.min(percent, MaxPedalPushValue - currentPosition);
		out.setBrakePedalPushPercent(currentPosition);
	}
	
	public void releaseBrakePedal(double percent) {
		percent = limitInput(percent, MaxPedalPushValue);
		double currentPosition = in.getBrakePedalPushPercent();
		currentPosition += Math.min(percent, currentPosition);
		out.setBrakePedalPushPercent(currentPosition);
	}
	
	public void moveShiftLeverTo(GearMode mode) {
		if (in.getBrakePedalPushPercent() != MaxPedalPushValue)
			return;
		out.setShiftLeverPosition(mode);
	}
}
