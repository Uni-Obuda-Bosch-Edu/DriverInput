package Implementations;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import FrameWork.busInterface.DriverInput_Out;
import FrameWork.busInterface.Public_In;
import Tests.InputVisualizer;

/*
 * Controls:
 * 		E: toggle engine
 * 		P/R/N/D: change shift lever position
 * 		Left/Right: turn steering wheel
 * 		Up/Down:
 * 			holding G: push/release gas pedal
 * 			holding B: push/release brake pedal
 */
public class DriverInputHandler implements KeyListener {
	private final static double movementUnit = 1.0;
	private boolean holdingGasModKey = false;
	private boolean holdingBrakeModKey = false;
	private DriverInput input;

	public static void main(String[] args) {
		InputVisualizer visualizer = new InputVisualizer(DriverInput.MaxSteeringWheelRotationPerDirection,
				DriverInput.MaxPedalPushValue,
				new int[] { DriverInput.ShiftLeverPositionParking, DriverInput.ShiftLeverPositionReverse,
						DriverInput.ShiftLeverPositionNeutral, DriverInput.ShiftLeverPositionDrive });
		
		// for the test a single class is responsive for acting as the bus and the input delegate
		new DriverInputHandler(visualizer, visualizer, visualizer);
	}
	
	public DriverInputHandler(Public_In in, DriverInput_Out out, Component component) {
		input = new DriverInput(in, out);
		component.addKeyListener(this);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();

		switch (keyCode) {
		case KeyEvent.VK_E:
			input.pushEngineToggleButton();
			break;
		case KeyEvent.VK_G:
			holdingGasModKey = true;
			break;
		case KeyEvent.VK_B:
			holdingBrakeModKey = true;
			break;
		case KeyEvent.VK_UP:
			if (holdingGasModKey)
				input.pushGasPedal(movementUnit);
			else if (holdingBrakeModKey)
				input.pushBrakePedal(movementUnit);
			break;
		case KeyEvent.VK_DOWN:
			if (holdingGasModKey)
				input.releaseGasPedal(movementUnit);
			else if (holdingBrakeModKey)
				input.releaseBrakePedal(movementUnit);
			break;
		case KeyEvent.VK_RIGHT:
			input.turnSteeringWheelRight(movementUnit);
			break;
		case KeyEvent.VK_LEFT:
			input.turnSteeringWheelLeft(movementUnit);
			break;
		case KeyEvent.VK_P:
			input.moveShiftLeverTo(DriverInput.ShiftLeverPositionParking);
			break;
		case KeyEvent.VK_R:
			input.moveShiftLeverTo(DriverInput.ShiftLeverPositionReverse);
			break;
		case KeyEvent.VK_N:
			input.moveShiftLeverTo(DriverInput.ShiftLeverPositionNeutral);
			break;
		case KeyEvent.VK_D:
			input.moveShiftLeverTo(DriverInput.ShiftLeverPositionDrive);
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();

		switch (keyCode) {
		case KeyEvent.VK_G:
			holdingGasModKey = false;
			break;
		case KeyEvent.VK_B:
			holdingBrakeModKey = false;
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
