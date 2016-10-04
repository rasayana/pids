package pids.data.service;

import pids.data.PerimeterData;

public interface PService {
	PerimeterData getData() throws Exception;
	void saveData(PerimeterData data);
	void deleteAll();
}