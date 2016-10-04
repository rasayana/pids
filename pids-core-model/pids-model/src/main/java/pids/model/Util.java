package pids.model;

import java.util.UUID;
import java.util.Arrays;
import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import pids.core.*;

abstract class Util {
    static String getUUId() {
        return UUID.randomUUID().toString();
    }
    @SuppressWarnings({ "rawtypes", "unchecked" })
	static Class<? extends Model> gAnnotatedClass(Object obj, int pos) throws Exception {
        ParameterizedType superclass = (ParameterizedType) obj.getClass().getGenericSuperclass();
        return (Class<? extends OModel>) superclass.getActualTypeArguments()[pos];
    }
    static <T> T[] concatenate(T[] a, T[] b) {
        int aLen = a.length;
        int bLen = b.length;
        @SuppressWarnings("unchecked")
        T[] c = (T[]) Array.newInstance(a.getClass().getComponentType(), aLen + bLen);
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);
        return c;
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
	static <T extends Device> T[] getDevicesFor(Device[] devices, Class<?> cls) {
        T[] result = (T[]) Array.newInstance(cls, 0);
        for (Device device : devices ) {
            if (device.getClass().isAssignableFrom(cls)) {
                result = Arrays.copyOf(result, result.length + 1);
                result[result.length - 1] = (T) device;
            }
        }
        return result;
    }
    @SuppressWarnings("rawtypes")
	static <T extends Base> T[] addToArray(T[] arr, T o)  {
        if(o != null && arr != null ) {
            arr = Arrays.copyOf(arr, arr.length + 1);
            arr[arr.length - 1] = o;
        }
        return arr;
    }
    static String getString(String[] values) {
        StringBuilder b = new StringBuilder();
        for (String s: values)
            b.append(s).append(" ");
        return b.toString();
    }
}