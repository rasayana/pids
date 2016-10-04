package pids.data;

import java.util.List;
import java.util.ArrayList;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBList;
import com.mongodb.DBObject;

@Configuration
public class DataConfig {
	static <T extends DataBase> BasicDBList getDBList(List<T> items) {
		BasicDBList result = new BasicDBList();
		for (T o: items)
			result.add(getDBObject(o));
		return result;
	}
    @SuppressWarnings({ "rawtypes", "unchecked" })
	static <T extends DataBase> DBObject getDBObject(T item) {
		DBObject result = new BasicDBObject("_id", item.getId());
		result.put("_class", item.getClass().getName());
		if (item instanceof DataContainer) {
			DataContainer container = (DataContainer) item;
			if(container instanceof DataSector) {
				DataSector sector = (DataSector) container;
				result.put("cameras", getDBList(sector.getCameras()));
				result.put("hooters", getDBList(sector.getHooters()));
			}
			result.put("items", getDBList(container.getItems()));
		} else if (item instanceof ToggleDevice) {
			ToggleDevice tDev = (ToggleDevice) item;
			result.put("_x", tDev.getX());
			result.put("_y", tDev.getY());
			result.put("_on", tDev.isOn());
			if(tDev instanceof DataDevice) {
				DataDevice dd = (DataDevice) tDev;
				result.put("_ip", dd.getIp());
				result.put("_user", dd.getUser());
				result.put("_password", dd.getPassword());
			}
		}
		return result;
	}
	static class DataWriter implements Converter<PerimeterData, DBObject> {
		@Override public DBObject convert(PerimeterData source) {
			return getDBObject(source);
		}
	}
	@SuppressWarnings({ "unchecked" })
	static <T extends DataBase> void copyItems(BasicDBList dbList, List<T> list) {
		for (Object o: dbList) {
			DBObject obj = (DBObject) o;
			DataBase m = null;
			String className = (String) obj.get("_class");
			if (className.equals(DataPerimeter.class.getName()))
				m = new DataPerimeter();
			if (className.equals(DataSector.class.getName()))
				m = new DataSector();
			if (className.equals(DataAnchor.class.getName()))
				m = new DataAnchor();
			if (className.equals(DataCamera.class.getName()))
				m = new DataCamera();
			if (className.equals(DataHooter.class.getName()))
				m = new DataHooter();
			if (m != null)
				list.add((T) copyDBObject(obj, m));
		}
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	static DataBase copyDBObject(DBObject o, DataBase m) {
		m.setId((String) o.get("_id"));
		if (m instanceof DataContainer){
			DataContainer c = (DataContainer) m;
			if (c instanceof DataSector) {
				DataSector sector = (DataSector) m;
				copyItems((BasicDBList) o.get("cameras"), sector.getCameras());
				copyItems((BasicDBList) o.get("hooters"), sector.getHooters());
			}
			copyItems((BasicDBList) o.get("items"), c.getItems());
		} else if (m instanceof ToggleDevice) {
			ToggleDevice td = (ToggleDevice) m;
			td.setX((double) o.get("_x"));
			td.setY((double) o.get("_y"));
			td.setOn((boolean) o.get("_on"));
			if (td instanceof DataDevice) {
				DataDevice dd = (DataDevice) td;
				dd.setIp((String) o.get("_ip"));
				dd.setUser((String) o.get("_user"));
				dd.setPassword((String) o.get("_password"));
			}
		}
		return m;
	}
	static class DataReader implements Converter<DBObject, PerimeterData> {
		@Override public PerimeterData convert(DBObject source) {
			return (PerimeterData) copyDBObject(source, new PerimeterData());
		}
	}
	@Bean public CustomConversions customConversions() {
        List<Converter<?, ?>> converters = new ArrayList<Converter<?, ?>>();
        converters.add(new DataReader());
        converters.add(new DataWriter());
        return new CustomConversions(converters);
    }
}