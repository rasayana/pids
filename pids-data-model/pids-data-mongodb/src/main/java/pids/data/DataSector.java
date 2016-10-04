package pids.data;

import java.util.List;
import java.util.ArrayList;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Getter;

@Document public class DataSector extends DataContainer<DataAnchor> {
	@Field @Getter private final List<DataCamera> cameras = new ArrayList<DataCamera>();
	public DataSector addCamera(DataCamera item) {
		cameras.add(item);
		return this;
	}
	@Field @Getter private final List<DataHooter> hooters = new ArrayList<DataHooter>();
	public DataSector addHooter(DataHooter item) {
		hooters.add(item);
		return this;
	}
}
