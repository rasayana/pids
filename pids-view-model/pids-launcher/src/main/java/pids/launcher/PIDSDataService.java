package pids.launcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pids.core.Data;
import pids.service.Service;

@Component
public final class PIDSDataService extends DataService {
    @Autowired
	private Service client;
	public PIDSDataService() { }
    @Override
	protected Data readData() throws Exception {
		return client.get();
	}
    @Override
    public final void save() {
    	try {
    		client.save(getData());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}