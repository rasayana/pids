package pids.model;

import java.lang.reflect.Constructor;
import java.util.logging.Logger;
import pids.core.Model;
import pids.core.ModelFactory;
@SuppressWarnings("rawtypes")
abstract class Factory<T extends Model, P extends Model> extends OBase<T, P> implements ModelFactory {
	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(Factory.class.getName());
    Factory(P parent) {
        super(parent);
    }
    protected final Constructor<T> getConstructor(String[] params) throws Exception {
        return parent() == null ? tClass().getConstructor(String.class) : tClass().getConstructor(pClass(), String.class);
    }
    protected final T get(Constructor<T> c, String[] params) throws Exception {
        return parent() == null ? (T) c.newInstance(params[0]) : (T) c.newInstance(parent(), params[0]);
    }
    @Override
    public final T instance(String... params) throws Exception {
        T result = (T) get(getConstructor(params), params);
        if(result != null)
            result.factory(this);
        return result;
    }
    final static class HooterFactory extends Factory<OHooter, OSector> {
        HooterFactory(OSector parent) {
            super(parent);
        }
    }
    final static class CameraFactory extends Factory<OCamera, OSector> {
        CameraFactory(OSector parent) {
            super(parent);
        }
    }
    final static class DataFactory extends Factory<OData, OData> {
        DataFactory(OData parent) {
            super(parent);
        }
    }
    final static class PerimeterFactory extends Factory<OPerimeter, OData> {
        PerimeterFactory(OData parent) {
            super(parent);
        }
    }
    final static class SectorFactory extends Factory<OSector, OPerimeter> {
        SectorFactory(OPerimeter parent) {
            super(parent);
        }
    }
    final static class AnchorFactory extends Factory<OAnchor, OSector> {
        AnchorFactory(OSector parent) {
            super(parent);
        }
    }
}