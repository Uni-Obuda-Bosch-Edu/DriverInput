package Interfaces;

// Max értékek beállítási és lekérdezési lehetõsége fontos?
// Százalékos és fix enum értékekrõl beszélünk..
public interface DriverInput {
	
	// started/stopped (true/false)
	public void setEngineToggleButtonState(boolean buttonState);
	public boolean getEngineToggleButtonState();
	
	// steering wheel (-100% left - right +100%)
	public void setWheelRotationPercent();
	public double getWheelRotationPercent(double percent);
	
	// gas pedal (0% released - pushed 100%)
	public void setGasPedalPushPercent();
	public double getGasPedalPushPercent(double percent);
	
	// break pedal
	public void setGasPedalPushPercent(double percent);
	public double getBreakPedalPushPercent();
	
	// shifting lever
	public void setShiftLeverPosition(ShiftLeverPosition shiftLeverPos);
	public ShiftLeverPosition getShiftLevelPosition();
}
