package Tests;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import FrameWork.busInterface.DriverInput_Out;
import FrameWork.busInterface.Public_In;

/*
 * Components in order:
 * 		brake pedal
 * 		gas pedal
 * 		steering wheel
 * 		shift lever
 * 		engine toggle button
 */
public class InputVisualizer extends JFrame implements DriverInput_Out, Public_In {
	private final static int FrameWidth = 400;
	private final static int FrameHeight = 200;
	private final static int ElementPadding = 20;
	private final static int WheelRadius = 50;
	private final static double InnerWheelRatio = 0.8;
	private final static double WheelMovementScaleRatio = 2.0;
	private final static int PedalBoxWidth = 20;
	private final static int PedalBoxHeight = 100;
	private final static int PedalBorderSize = 1;
	private final static int GearBoxOffset = 25;
	private final static int GearLevelPadding = 20;
	private final static int EngineButtonRadius = 10;
	private final static int EngineButtonMargin = 5;

	private final JPanel mainPanel;
	private final int maxPedalPushValue;
	private final int[] shiftLeverPRNDPositionValues;

	private boolean engineToggleButtonState;
	private double steeringWheelRotation;
	private double gasPedalPushValue;
	private double brakePedalPushValue;
	private int shiftLeverPosition;

	private void drawSteeringWheel(Graphics g, int panelWidth, int panelHeight) {
		// outer circle
		int x = panelWidth / 2 - WheelRadius;
		int y = panelHeight / 2 - WheelRadius;
		Color defaultColor = this.getBackground();
		g.setColor(Color.LIGHT_GRAY);
		g.fillOval(x, y, WheelRadius * 2, WheelRadius * 2);

		// inner circle
		int innerWheelRadius = (int) (WheelRadius * InnerWheelRatio);

		x = panelWidth / 2 - innerWheelRadius;
		y = panelHeight / 2 - innerWheelRadius;
		g.setColor(defaultColor);
		g.fillOval(x, y, innerWheelRadius * 2, innerWheelRadius * 2);

		// drawing the 'needle'
		{
			double needleRadius = (WheelRadius - innerWheelRadius) / 2;
			double needleRotation = (steeringWheelRotation * WheelMovementScaleRatio - 90);
			double needleLength = innerWheelRadius + needleRadius;

			x = (int) (panelWidth / 2 - needleRadius + Math.cos(Math.toRadians(needleRotation)) * needleLength);
			y = (int) (panelHeight / 2 - needleRadius + Math.sin(Math.toRadians(needleRotation)) * needleLength);
			g.setColor(Color.DARK_GRAY);
			g.fillOval(x, y, (int) needleRadius * 2, (int) needleRadius * 2);
		}

		// drawing the value in text
		x = panelWidth / 2 - g.getFontMetrics().stringWidth(steeringWheelRotation + "%") / 2;
		y = panelHeight / 2;
		g.setColor(Color.BLACK);
		g.drawString(steeringWheelRotation + "%", x, y);
	}

	private void drawPedals(Graphics g, int panelWidth, int panelHeight) {
		// drawing the box for gas
		int x = panelWidth / 2 - WheelRadius - ElementPadding - PedalBoxWidth - 2 * PedalBorderSize;
		int y = panelHeight / 2 - PedalBoxHeight / 2 - 2 * PedalBorderSize;
		g.setColor(Color.LIGHT_GRAY);
		g.drawRect(x, y, PedalBoxWidth + PedalBorderSize, PedalBoxHeight + PedalBorderSize);

		// drawing the fill in the gas box
		int valueScaleRatio = (int) (PedalBoxHeight / maxPedalPushValue);
		double valueDiffFromMax = maxPedalPushValue - gasPedalPushValue;
		int tempGasPedalBoxX = x;
		int tempGasPedalBoxY = y;
		x += PedalBorderSize;
		y += valueDiffFromMax * valueScaleRatio + PedalBorderSize;
		g.setColor(Color.GREEN);
		g.fillRect(x, y, PedalBoxWidth, (int) (PedalBoxHeight - (valueDiffFromMax * valueScaleRatio)));

		// drawing the box for brake
		x = tempGasPedalBoxX - ElementPadding - PedalBoxWidth - 2 * PedalBorderSize;
		y = tempGasPedalBoxY;
		g.setColor(Color.LIGHT_GRAY);
		g.drawRect(x, y, PedalBoxWidth + PedalBorderSize, PedalBoxHeight + PedalBorderSize);

		// drawing the fill in the brake box
		valueDiffFromMax = maxPedalPushValue - brakePedalPushValue;
		x += PedalBorderSize;
		y += valueDiffFromMax * valueScaleRatio + PedalBorderSize;
		g.setColor(Color.RED);
		g.fillRect(x, y, PedalBoxWidth, (int) (PedalBoxHeight - (valueDiffFromMax * valueScaleRatio)));
	}

	private void drawGear(Graphics g, int panelWidth, int panelHeight) {
		int x = panelWidth / 2 + WheelRadius + ElementPadding * 2;
		int y = panelHeight / 2 - WheelRadius + GearBoxOffset;

		// parking
		g.setColor(Color.LIGHT_GRAY);
		if (shiftLeverPosition == shiftLeverPRNDPositionValues[0])
			g.setColor(Color.BLUE);
		g.drawString("P", x, y);
		y += GearLevelPadding;

		// reverse
		g.setColor(Color.LIGHT_GRAY);
		if (shiftLeverPosition == shiftLeverPRNDPositionValues[1])
			g.setColor(Color.BLUE);
		g.drawString("R", x, y);
		y += GearLevelPadding;

		// neutral
		g.setColor(Color.LIGHT_GRAY);
		if (shiftLeverPosition == shiftLeverPRNDPositionValues[2])
			g.setColor(Color.BLUE);
		g.drawString("N", x, y);
		y += GearLevelPadding;

		// drive
		g.setColor(Color.LIGHT_GRAY);
		if (shiftLeverPosition == shiftLeverPRNDPositionValues[3])
			g.setColor(Color.BLUE);
		g.drawString("D", x, y);
	}

	private void drawEngineButton(Graphics g, int panelWidth, int panelHeight) {
		int x = panelWidth - EngineButtonRadius - EngineButtonMargin;
		int y = panelHeight - EngineButtonRadius - EngineButtonMargin;

		if (engineToggleButtonState)
			g.setColor(Color.GREEN);
		else
			g.setColor(Color.RED);
		g.fillOval(x, y, EngineButtonRadius, EngineButtonRadius);
		g.setColor(Color.DARK_GRAY);
		g.drawOval(x, y, EngineButtonRadius, EngineButtonRadius);
	}

	private void drawTestComponents(Graphics g) {
		int panelWidth = g.getClipBounds().width;
		int panelHeight = g.getClipBounds().height;

		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		drawSteeringWheel(g, panelWidth, panelHeight);
		drawPedals(g, panelWidth, panelHeight);
		drawGear(g, panelWidth, panelHeight);
		drawEngineButton(g, panelWidth, panelHeight);
	}

	@Override
	public void setEngineToggleButtonState(boolean buttonState) {
		engineToggleButtonState = buttonState;
		this.repaint();
	}

	@Override
	public boolean getEngineToggleButtonState() {
		return engineToggleButtonState;
	}

	@Override
	public void setSteeringWheelRotationPercent(double percent) {
		steeringWheelRotation = percent;
		this.repaint();
	}

	@Override
	public double getSteeringWheelRotationPercent() {
		return steeringWheelRotation;
	}

	@Override
	public void setGasPedalPushPercent(double percent) {
		gasPedalPushValue = percent;
		this.repaint();
	}

	@Override
	public double getGasPedalPushPercent() {
		return gasPedalPushValue;
	}

	@Override
	public void setBrakePedalPushPercent(double percent) {
		brakePedalPushValue = percent;
		this.repaint();
	}

	@Override
	public double getBrakePedalPushPercent() {
		return brakePedalPushValue;
	}

	@Override
	public void setShiftLeverPosition(int position) {
		shiftLeverPosition = position;
		this.repaint();
	}

	@Override
	public int getShiftLeverPosition() {
		return shiftLeverPosition;
	}

	public InputVisualizer(int maxWheelRotationPerDirection, int maxPedalPushValue, int[] shiftLeverPRNDPositionValues) {
		super("DriverInput Test");

		this.maxPedalPushValue = maxPedalPushValue;
		this.shiftLeverPRNDPositionValues = shiftLeverPRNDPositionValues;

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(FrameWidth, FrameHeight);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - FrameWidth / 2, dim.height / 2 - FrameHeight / 2);
		this.setLayout(new BorderLayout());
		this.mainPanel = new JPanel() {
			public void paint(Graphics g) {
				drawTestComponents(g);
			}
		};
		this.add(mainPanel, BorderLayout.CENTER);
		this.setVisible(true);
	}

	/*
	 * ---------- Non-relevant information for the test ----------
	 */
	@Override
	public double getGearTorque() {
		return 0;
	}

	@Override
	public int getGearRevolution() {
		return 0;
	}

	@Override
	public int getGearMode() {
		return 0;
	}

	@Override
	public double getEngineTorque() {
		return 0;
	}

	@Override
	public int getEngineRevolution() {
		return 0;
	}

	@Override
	public double getWaterTemperature() {
		return 0;
	}

	@Override
	public double getOilTemperature() {
		return 0;
	}

	@Override
	public double getOilPressure() {
		return 0;
	}

	@Override
	public int getServiceCode() {
		return 0;
	}

	@Override
	public double getCenterOfXAxis() {
		return 0;
	}

	@Override
	public double getCenterOfYAxis() {
		return 0;
	}

	@Override
	public double getMotionVectorXWithLengthAsSpeedInKm() {
		return 0;
	}

	@Override
	public double getMotionVectorYWithLengthAsSpeedInKm() {
		return 0;
	}

	@Override
	public double getWheelTorqueInNewton() {
		return 0;
	}

	@Override
	public double getMaximumTorqueInNewton() {
		return 0;
	}

	@Override
	public double getMaximumBrakeTorqueInNewton() {
		return 0;
	}

	@Override
	public double getFrictionalCoefficientOfBrakes() {
		return 0;
	}

	@Override
	public double getDiameterOfDriveAxesInMeters() {
		return 0;
	}

	@Override
	public double getLengthOfAxesInMeters() {
		return 0;
	}

	@Override
	public double getDistanceBetweenAxesInMeters() {
		return 0;
	}

	@Override
	public double getDiameterOfWheelsInMeters() {
		return 0;
	}

	@Override
	public double getWidthOfWheelsInMeters() {
		return 0;
	}

	@Override
	public double getDriveWheelStateZeroBasedDegree() {
		return 0;
	}

	@Override
	public double getMaximumDriveWheelStateZeroBasedDegree() {
		return 0;
	}

	@Override
	public double getMaximumWheelsTurnDegree() {
		return 0;
	}

	@Override
	public double getTotalMassInKg() {
		return 0;
	}

	@Override
	public double getInnerFrictionalCoefficientInNewton() {
		return 0;
	}
}
