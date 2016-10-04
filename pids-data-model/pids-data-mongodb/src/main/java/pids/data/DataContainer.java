package pids.data;

import java.util.List;
import java.util.ArrayList;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Field;

abstract class DataContainer<T> extends DataBase {
	@Field @Getter private final List<T> items = new ArrayList<T>();
	public DataContainer<T> addItem(T item) {
		items.add(item);
		return this;
	}
}