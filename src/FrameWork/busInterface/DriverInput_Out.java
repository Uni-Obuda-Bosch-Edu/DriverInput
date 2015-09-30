package FrameWork.busInterface;

import FrameWork.virtualDataBus.Container.GearMode;

public interface DriverInput_Out {

		public void setEngineToggleButtonState(boolean buttonState);
		public void setSteeringWheelRotationPercent(double percent);
		public void setGasPedalPushPercent(double percent);
		public void setBrakePedalPushPercent(double percent);
		public void setShiftLeverPosition(GearMode mode);
	
}
