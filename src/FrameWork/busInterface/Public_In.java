package FrameWork.busInterface;

import FrameWork.virtualDataBus.Container.GearMode;

public interface Public_In {
	
	public boolean getEngineToggleButtonState();
	public double getSteeringWheelRotationPercent();
	public double getBrakePedalPushPercent();
	public double getGasPedalPushPercent();
	public GearMode getShiftLeverPosition();
	
	public double getGearTorque();    
	public int getGearRevolution();  
	public int getGearMode();   
	
	public double getEngineTorque();
	public int getEngineRevolution();
	public double getWaterTemperature();
	public double getOilTemperature();
	public double getOilPressure(); 
	public int getServiceCode(); 
	
	public double getCenterOfXAxis();
	public double getCenterOfYAxis();
	public double getMotionVectorXWithLengthAsSpeedInKm();
	public double getMotionVectorYWithLengthAsSpeedInKm();

	public double getWheelTorqueInNewton();
	public double getMaximumTorqueInNewton();
	public double getMaximumBrakeTorqueInNewton();
	public double getFrictionalCoefficientOfBrakes();
	public double getDiameterOfDriveAxesInMeters();
	public double getLengthOfAxesInMeters();
	public double getDistanceBetweenAxesInMeters();
	public double getDiameterOfWheelsInMeters();
	public double getWidthOfWheelsInMeters();
	public double getDriveWheelStateZeroBasedDegree();
	public double getMaximumDriveWheelStateZeroBasedDegree();
	public double getMaximumWheelsTurnDegree();
	public double getTotalMassInKg();
	public double getInnerFrictionalCoefficientInNewton();
};
   
                   