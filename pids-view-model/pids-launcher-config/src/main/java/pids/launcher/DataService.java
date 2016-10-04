package pids.launcher;

import javax.annotation.PostConstruct;
import pids.core.Data;

public abstract class DataService {
	protected Data data;
	protected abstract Data readData() throws Exception;
	@PostConstruct
	void prepareService() throws Exception {
		data = readData();
	}
	public final Data getData() {
		return data;
	}
	public void save() {
	}
}