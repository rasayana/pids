package pids.core;

public interface Data extends Container<Data, Data, Perimeter> {
	Data mapProperties(MapProperties value);
	MapProperties mapProperties();
	public void setMonitoring(boolean flag);
}