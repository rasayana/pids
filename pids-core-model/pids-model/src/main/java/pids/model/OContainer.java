package pids.model;

import pids.core.Container;
import pids.core.Model;
import pids.core.ModelFactory;

@SuppressWarnings("rawtypes")
abstract class OContainer<T extends Container, P extends Container, H extends Model> extends OModel<T, P> 
    implements Container<T, P, H> {
    private ModelFactory headerfactory;
    private H head = null;
    @SuppressWarnings("unchecked")
	@Override
    public final T createhead(String id) throws Exception {
        if (head == null) {
            head = (H) headerfactory.instance(id);
            head.prepare();
            
        } else head.add(id);
        return (T) this;
    }
    @Override
    public final T createhead() throws Exception {
        return createhead(Util.getUUId());
    }
    @Override
    public final H head() {
        return head;
    }
    abstract ModelFactory createHeaderFactory();
    private void prepareHead() {
        headerfactory = createHeaderFactory();
    }
    @SuppressWarnings("unchecked")
	@Override
    public final T remove(T value) throws Exception {
        H prevHead = (H) ((T) value.prev()).head().last();
        H nextHead = (H) ((T) value.next()).head().first();
//        while (value.head() != null)
//            value.head().remove(value.head());
        value = (T) super.remove(value);
        prevHead.next(nextHead);
        nextHead.prev(prevHead);
        return value;
    }
    @SuppressWarnings("unchecked")
	@Override
    public T head(H value) {
        if(!head.equals(value))
            head = value;
        return (T) this;
    }
    OContainer(P parent, String id) {
        super(parent, id);
        prepareHead();
    }
}