package pids.data.service;

import pids.data.PerimeterData;
import pids.data.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DataService implements PService {
	@Autowired
	private DataRepository repository;
	@Override
	public PerimeterData getData() throws Exception {
		return repository.findAll().get(0);
	}
	@Override
	public void saveData(PerimeterData data) {
		deleteAll();
		repository.save(data);
	}
	@Override
	public void deleteAll() {
		repository.deleteAll();
	}
}