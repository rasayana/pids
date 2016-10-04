package pids.model;

import java.lang.reflect.Array;
import java.util.Arrays;
import pids.core.DeviceInfo;
import pids.core.Anchor;

@SuppressWarnings("rawtypes")
final class TList extends OBase<DeviceInfo, Anchor> {
    final DeviceInfo remove(String id) {
        return remove(get(id));
    }
    private DeviceInfo[] list;
    final DeviceInfo[] list() {
        return list;
    }
	TList(Anchor parent) throws Exception {
        super(parent);
    	list = (DeviceInfo[]) Array.newInstance(tClass(), 0);
    }
    final DeviceInfo get(String id) {
        if (OModel.checkStr(id))
            return null;
        for (DeviceInfo obj : list)
            if (obj.getDevice().idEquals(id))
                return obj;
        return null;
    }
    final DeviceInfo add(DeviceInfo n) {
        list = (DeviceInfo[]) Util.addToArray(list, n);
        return n;
    }
	final DeviceInfo remove(DeviceInfo n) {
        if (n != null)
            list = (DeviceInfo[]) Arrays.stream(list).filter(e -> !e.equals(n)).toArray();
        return n;
    }
}