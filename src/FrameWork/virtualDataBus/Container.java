package FrameWork.virtualDataBus;

import FrameWork.busInterface.*;

public class Container implements Engine_Out, DriverInput_Out, Gearbox_Out, Wheels_Out, Public_In{
	
	public enum GearMode {Parking, Reverse, Neutral, Drive}
	
	public static Container getInstance(){
		if(instance == null)
			instance = new Container();
		return instance;
	}
	
	public boolean getEngineToggleButtonState() {
		return EngineToggleButtonState;
	}

	public void setEngineToggleButtonState(boolean engineToggleButtonState) {
		EngineToggleButtonState = engineToggleButtonState;
	}
	
	public double getSteeringWheelRotationPercent() {
		return SteeringWheelRotationPercent;
	}
	public void setSteeringWheelRotationPercent(double steeringWheelRotationPercent) {
		SteeringWheelRotationPercent = steeringWheelRotationPercent;
	}

	public double getBrakePedalPushPercent() {
		return BrakePedalPushPercent;
	}

	public void setBrakePedalPushPercent(double brakePedalPushPercent) {
		BrakePedalPushPercent = brakePedalPushPercent;
	}

	public double getGasPedalPushPercent() {
		return GasPedalPushPercent;
	}

	public void setGasPedalPushPercent(double gasPedalPushPercent) {
		GasPedalPushPercent = gasPedalPushPercent;
	}

	public int getShiftLeverPosition() {
		return ShiftLeverPosition;
	}

	public void setShiftLeverPosition(int position) {
		ShiftLeverPosition = position;
	}

	public double getGearTorque() {
		return GearTorque;
	}

	public void setGearTorque(int gearTorque) {
		GearTorque = gearTorque;
	}

	public int getGearRevolution() {
		return GearRevolution;
	}

	public void setGearRevolution(int gearRevolution) {
		GearRevolution = gearRevolution;
	}

	public int getGearMode() {
		return GearMode;
	}

	public void setGearMode(int gearMode) {
		GearMode = gearMode;
	}

	public double getEngineTorque() {
		return EngineTorque;
	}

	public void setEngineTorque(int engineTorque) {
		EngineTorque = engineTorque;
	}

	public int getEngineRevolution() {
		return EngineRevolution;
	}

	public void setEngineRevolution(int engineRevolution) {
		EngineRevolution = engineRevolution;
	}

	public double getWaterTemperature() {
		return WaterTemperature;
	}

	public void setWaterTemperature(int waterTemperature) {
		WaterTemperature = waterTemperature;
	}

	public double getOilTemperature() {
		return OilTemperature;
	}

	public void setOilTemperature(int oilTemperature) {
		OilTemperature = oilTemperature;
	}

	public double getOilPressure() {
		return OilPressure;
	}

	public void setOilPressure(int oilPressure) {
		OilPressure = oilPressure;
	}

	public int getServiceCode() {
		return ServiceCode;
	}

	public void setServiceCode(int serviceCode) {
		ServiceCode = serviceCode;
	}

	public double getCenterOfXAxis() {
		return CenterOfXAxis;
	}

	public void setCenterOfXAxis(double centerOfXAxis) {
		CenterOfXAxis = centerOfXAxis;
	}

	public double getCenterOfYAxis() {
		return CenterOfYAxis;
	}

	public void setCenterOfYAxis(double centerOfYAxis) {
		CenterOfYAxis = centerOfYAxis;
	}

	public double getMotionVectorXWithLengthAsSpeedInKm() {
		return MotionVectorXWithLengthAsSpeedInKm;
	}

	public void setMotionVectorXWithLengthAsSpeedInKm(double motionVectorXWithLengthAsSpeedInKm) {
		MotionVectorXWithLengthAsSpeedInKm = motionVectorXWithLengthAsSpeedInKm;
	}

	public double getMotionVectorYWithLengthAsSpeedInKm() {
		return MotionVectorYWithLengthAsSpeedInKm;
	}

	public void setMotionVectorYWithLengthAsSpeedInKm(double motionVectorYWithLengthAsSpeedInKm) {
		MotionVectorYWithLengthAsSpeedInKm = motionVectorYWithLengthAsSpeedInKm;
	}
	
	public void setWheelTorqueInNewton(double newTorque) {
		WheelTorqueInNewton = newTorque;
	}
	
	public void setMaximumWheelTorqueInNewton(double newTorque) {
		MaximumTorqueInNewton = newTorque;
	}
	
	public void setMaximumBrakeTorqueInNewton(double newTorque) {
		MaximumBrakeTorqueInNewton = newTorque;
	}
	
	public void setFrictionalCoefficientOfBrakes(double coefficient) {
		FrictionalCoefficientOfBrakes = coefficient;
	}
	
	public void setDiameterOfDriveAxesInMeters(double diameter) {
		DiameterOfDriveAxesInMeters = diameter;
	}
	
	public void setLengthOfAxesInMeters(double length) {
		LengthOfAxesInMeters = length;
	}
	
	public void setDistanceBetweenAxesInMeters(double distance) {
		DistanceBetweenAxesInMeters = distance;
	}
	
	public void setDiameterOfWheelsInMeters(double diameter) {
		DiameterOfWheelsInMeters = diameter;
	}
	
	public void setWidthOfWheelsInMeters(double width) {
		WidthOfWheelsInMeters = width;
	}
	
	public void setDriveWheelStateZeroBasedDegree(double degree) {
		DriveWheelStateZeroBasedDegree = degree;
	}
	
	public void setMaximumDriveWheelStateZeroBasedDegree(double degree) {
		MaximumDriveWheelStateZeroBasedDegree = degree;		
	}

	public void setMaximumWheelsTurnDegree(double degree) {
		MaximumWheelsTurnDegree = degree;		
	}

	public void setTotalMassInKg(double newMass) {
		TotalMassInKg = newMass;
	}

	public void setInnerFrictionalCoefficientInNewton(double coefficient) {
		InnerFrictionalCoefficientInNewton = coefficient;
	}
	
	public double getWheelTorqueInNewton() {
		return WheelTorqueInNewton;
	}
	
	public double getMaximumTorqueInNewton() {
		return MaximumTorqueInNewton;
	}
	
	public double getMaximumBrakeTorqueInNewton() {
		return MaximumBrakeTorqueInNewton;
	}
	
	public double getFrictionalCoefficientOfBrakes() {
		return FrictionalCoefficientOfBrakes;
	}

	public double getDiameterOfDriveAxesInMeters() {
		return DiameterOfDriveAxesInMeters;
	}

	public double getLengthOfAxesInMeters() {
		return LengthOfAxesInMeters;
	}

	public double getDistanceBetweenAxesInMeters() {
		return DistanceBetweenAxesInMeters;
	}
	public double getDiameterOfWheelsInMeters() {
		return DiameterOfWheelsInMeters;
	}

	public double getWidthOfWheelsInMeters() {
		return WidthOfWheelsInMeters;
	}

	public double getDriveWheelStateZeroBasedDegree() {
		return DriveWheelStateZeroBasedDegree;
	}
	
	public double getMaximumDriveWheelStateZeroBasedDegree() {
		return MaximumDriveWheelStateZeroBasedDegree;
	}
	
	public double getMaximumWheelsTurnDegree() {
		return MaximumWheelsTurnDegree;
	}
	
	public double getTotalMassInKg() {
		return TotalMassInKg;
	}

	public double getInnerFrictionalCoefficientInNewton() {
		return InnerFrictionalCoefficientInNewton;
	}
	
	
	/*DriverInput*/
	private boolean EngineToggleButtonState;
	private double SteeringWheelRotationPercent;
	private double BrakePedalPushPercent;
	private double GasPedalPushPercent;
	private int ShiftLeverPosition;
	
	/*Gearbox*/
	private double GearTorque;
	private int GearRevolution;
	private int GearMode;
	
	/*Engine*/
	private double EngineTorque;
	private int EngineRevolution;
	private double WaterTemperature;
	private double OilTemperature;
	private double OilPressure;
	private int ServiceCode;
	
	/*Wheels*/
	private double CenterOfXAxis;
	private double CenterOfYAxis;
	
	private double MotionVectorXWithLengthAsSpeedInKm;
	private double MotionVectorYWithLengthAsSpeedInKm;

	private double WheelTorqueInNewton;                
	private double MaximumTorqueInNewton;                
	private double MaximumBrakeTorqueInNewton;           
	private double FrictionalCoefficientOfBrakes;        
	private double DiameterOfDriveAxesInMeters;          
	private double LengthOfAxesInMeters;                 
	private double DistanceBetweenAxesInMeters;          
	private double DiameterOfWheelsInMeters;             
	private double WidthOfWheelsInMeters;                
	private double DriveWheelStateZeroBasedDegree;       
	private double MaximumDriveWheelStateZeroBasedDegree;
	private double MaximumWheelsTurnDegree;              
	private double TotalMassInKg;                        
	private double InnerFrictionalCoefficientInNewton;   
	
	private static Container instance = null;
	
	private Container(){
		//do not instantiate; do not subclass;
	}

}
