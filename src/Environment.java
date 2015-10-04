
public interface Environment {
	public List<Object> getRadarSensorRelevantObjects(List<Coordinate> area);
	public List<Object> getCameraSensorRelevantObjects(List<Coordinate> area);
	public List<Object> getUltrasonicSensorRelevantObjects(List<Coordinate> area);
}
