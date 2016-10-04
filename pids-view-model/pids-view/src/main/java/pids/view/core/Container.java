package pids.view.core;

import javafx.collections.ObservableMap;
import pids.core.Model;

interface Container<T extends Model<?, ?>, P extends PModel> {
	ObservableMap<T, P> list();
	P add(T itm) throws Exception;
	P get(T p);
}
