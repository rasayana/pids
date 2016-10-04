package pids.data;

import java.util.List;
import pids.core.*;
import pids.model.Builder;

public class DataConverter {
	@SuppressWarnings({ "rawtypes" })
	private static Toggle getHead(Sector s, DataBase c) throws Exception {
		return c instanceof DataCamera ? s.camera() : s.hooter();
	}
	@SuppressWarnings("rawtypes")
	private static <T extends ToggleDevice> void copyToggles(List<T> list, Sector sector) throws Exception {
		for (T c : list) {
			Toggle head = getHead(sector, c);
			if (head == null) {
				if (c instanceof DataCamera)
					sector.createCamera(c.getId());
				else
					sector.createHooter(c.getId());
				head = getHead(sector, c);
			} else head = (Toggle) getHead(sector, c).add(c.getId());
			copyFrom(c, head);
		}
	}
	@SuppressWarnings("rawtypes")
	private static <T extends DataBase, Y extends Model> void preProcess(T from, Y to) throws Exception {
		if (from instanceof DataSector) {
			DataSector dataSector = (DataSector) from;
			Sector sector = (Sector) to;
			copyToggles((List<DataCamera>) dataSector.getCameras(), sector);
			copyToggles((List<DataHooter>) dataSector.getHooters(), sector);
		} else if (from instanceof ToggleDevice) {
			Toggle t = (Toggle) to;
			ToggleDevice td = (ToggleDevice) from;
			t.setOn(td.isOn());
			t.setX(td.getX());
			t.setY(td.getY());
			if (td instanceof DataDevice) {
				Device d = (Device) to;
				DataDevice dd = (DataDevice) td;
				d.prepareDevice(dd.getIp(), dd.getUser(), dd.getPassword());
			}
		}
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static <T extends DataBase, Z extends Model> Z copyFrom(T from, Z to) throws Exception {
		preProcess(from, to);
		if (from instanceof DataContainer) {
			DataContainer dataFrom = (DataContainer) from;
			Container dataTo = (Container) to;
			Model head;
			for (T c : (List<T>) dataFrom.getItems()) {
				if (dataTo.head() == null) {
					dataTo.createhead(c.getId());
					head = (Model) dataTo.head();
				} else head = (Model) dataTo.head().add(c.getId());
				copyFrom(c, head);
			}
		}
		return to;
	}
	public static Data convertFrom(PerimeterData data, MapProperties prop) throws Exception {
		Data result = copyFrom(data, Builder.createData(data.getId()));
		result.mapProperties(prop);
		return result;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static <T extends Model, Z extends DataBase> Z copyFrom(T from, Z to) throws Exception {
		if (from instanceof Container) {
			Container dataFrom = (Container) from;
			DataContainer dataTo = (DataContainer) to;
			Model head = dataFrom.head();
			Model last = head.last();
			do {
				dataTo.addItem(getItem(head));
				head = head.next();
			} while (!last.equals(head));
			if (!last.equals(dataFrom.head()))
				dataTo.addItem(getItem(last));
		}
		return to;
	}
	@SuppressWarnings("rawtypes")
	public static DataBase getItem(Model m) throws Exception {
		DataBase result = null;
		if (m instanceof Container) {
			if (m instanceof Data)
				result = new PerimeterData();
			if (m instanceof Perimeter)
				result = new DataPerimeter();
			if (m instanceof Sector) {
				Sector s = (Sector) m;
				DataSector sd = new DataSector();
				Camera headcamera = s.camera();
				if (headcamera != null) {
					Camera lastCamera = headcamera.last();
					do {
						sd.addCamera((DataCamera) getItem(headcamera));
						headcamera = headcamera.next();
					}  while (!lastCamera.equals(headcamera));
					if(!lastCamera.equals(s.camera()))
						sd.addCamera((DataCamera) getItem(lastCamera));
				}
				Hooter headHooter = s.hooter();
				if (headHooter != null) {
					Hooter lastHooter = headHooter.last();
					do {
						sd.addHooter((DataHooter) getItem(headHooter));
						headHooter = headHooter.next();
					}  while (!lastHooter.equals(headHooter));
					if(!lastHooter.equals(s.hooter()))
						sd.addHooter((DataHooter) getItem(lastHooter));
				}
				result = sd;
			}
			if (result != null)
				result = copyFrom(m, result);
		} else if (m instanceof Toggle) {
			ToggleDevice toggle = null;
			Toggle t  = (Toggle) m;
			if (t instanceof Anchor)
				toggle = new DataAnchor();
			if (t instanceof Camera)
				toggle = new DataCamera();
			if (t instanceof Hooter)
				toggle = new DataHooter();
			if (t instanceof Device){
				Device d = (Device) t;
				DataDevice dd = (DataDevice) toggle;
				dd.setIp(d.ip());
				dd.setUser(d.user());
				dd.setPassword(d.password());
			} else if (t instanceof Anchor) {
				Anchor a = (Anchor) t;
				DataAnchor da = (DataAnchor) toggle;
			}
			toggle.setX(t.getX());
			toggle.setY(t.getY());
			toggle.setOn(t.isOn());
			result = toggle;
		}
		if (result != null)
			result.setId(m.id());
		return result;
	}
	public static PerimeterData convertFrom(Data data) throws Exception {
		return (PerimeterData) getItem(data);
	}
}