package pids.model;

import java.util.logging.Logger;
import pids.core.Base;
import pids.core.Model;

@SuppressWarnings("rawtypes")
abstract class OBase<T, P extends Model> implements Base<P> {
	private P parent;
    @Override
    public P parent() {
        return parent;
    }
    @SuppressWarnings("unchecked")
	T parent(P value) {
        parent = value;
        return (T) this;
    }
    protected final Class<? extends Model> gClass(int pos) throws Exception {
        return Util.gAnnotatedClass(this, pos);
    }
    protected int tPos() {
        return 0;
    }
    protected Logger getLogger() {
        return null;
    }
    @SuppressWarnings("unchecked")
	protected final Class<T> tClass() throws Exception {
        return (Class<T>) gClass(tPos());
    }
    protected int pPos() {
        return 1;
    }
    @SuppressWarnings("unchecked")
	protected final Class<P> pClass() throws Exception {
        return (Class<P>) gClass(pPos());
    }
    OBase(P parent) {
        this.parent = parent;
    }
}
