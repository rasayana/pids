package pids.model;

import java.lang.reflect.Array;
import pids.core.Model;
import pids.core.Container;
import pids.core.Data;
import pids.core.ModelFactory;
import pids.core.Perimeter;
import pids.core.Device;
import pids.core.Camera;
import pids.core.Sector;

@SuppressWarnings("rawtypes")
abstract class OModel<T extends Model, P extends Container> extends OBase<T, P> implements Model<T, P> {
	private String idStr;
    protected static final boolean checkStr(String value) {
        return value == null && "".equals(value);
    }
    @SuppressWarnings("unchecked")
	final T id(String value) {
        if (get(value) == null) {
            idStr = value;
            return (T) this;
        }
        return null;
    }
    @Override
    public final String id() {
        return idStr;
    }
    protected void info(String... values) {
//        log.info(Util.getString(values));
        System.out.println(Util.getString(values));
    }
    @SuppressWarnings("unchecked")
	@Override
    public final T[] list() throws Exception {
        T[] result = (T[]) Array.newInstance(((T) this).getClass(), 0);
        T first = (T) first();
        T _next = first;
        do {
            //info(_next.toString());
            result = (T[]) Util.addToArray(result, _next);
            _next = (T) _next.next();
        } while (!first.equals(_next));
        return result;
    }
    private boolean check(T value){
        return value != null && !equals(value);
    }
    @SuppressWarnings("unchecked")
	private T prev = (T) this;
    @Override
    public final void prev(T value) {
        if (check(value))
            prev = value;
    }
    @Override
    public final T prev() {
        return prev;
    }
    @SuppressWarnings("unchecked")
	private T next = (T) this;
    @Override
    public final void next(T value) {
        if (check(value))
            next = value;
    }
    @Override
    public final T next() {
        return next;
    }
    private boolean isNull(Model o) {
        return o == null;
    }
    private boolean isParent() {
        return !isNull(parent());
    }
    @SuppressWarnings("unchecked")
	private T get(boolean last) throws Exception {
        T first = (T) first();
        T obj = first;
        do
            obj = last ? (T) obj.next() : (T) obj.prev();
        while (!first.equals(obj) && (isParent() && !(parent() instanceof Data)) ? parent().equals(obj.parent()) : false);
        return last ? (T) obj.prev() : (T) obj.next();
    }
    @Override
    public final T last() throws Exception {
        return get(true);
    }
    @SuppressWarnings("unchecked")
	private T getHeadAsDevice(P Obj) throws Exception {
        return this instanceof Camera ? (T) ((Sector) Obj).camera() : (T) ((Sector) Obj).hooter();
    }
    @SuppressWarnings("unchecked")
	private T pHead(P obj) throws Exception {
        return this instanceof Device ? getHeadAsDevice(obj) : (T) obj.head();
    }
    @SuppressWarnings("unchecked")
	private T pHead(T obj) throws Exception {
        return (T) pHead((P) obj.parent());
    }
    @SuppressWarnings("unchecked")
	@Override
    public final T first() throws Exception {
        return isParent() ? pHead((T) this) : (T) this;
    }
    @SuppressWarnings("unchecked")
	@Override
    public final T get(String id) {
        if (!checkStr(id)) {
            T first = (T) this;
            T _next = first;
            do {
                if (_next.idEquals(id))
                    return _next;
                _next = (T) _next.next();
            } while (!first.equals(_next));
        }
        return null;
    }
    private ModelFactory factory;
    @Override
    public final void factory(ModelFactory factory) {
        //if(factory instanceof Factory)
            this.factory = factory;
    }
    @SuppressWarnings("unchecked")
	@Override
    public final T add(String id) throws Exception {
        T result = null;
        if(!isMonitoring()) {
            result = get(id);
            if (result == null && factory != null)
                result = add((T) factory.instance(id));
        }
        return result;
    }
    @Override
    public final T add() throws Exception {
        return add(Util.getUUId());
    }
    @SuppressWarnings("unchecked")
	@Override
    public final T add(T value) throws Exception {
        return !isMonitoring() ? (T) value.prepare() : null;
    }
    @Override
    public final T remove(String id) throws Exception {
        return remove(get(id));
    }
    @SuppressWarnings("unchecked")
	private void removeHead(T value, T newItem) throws Exception {
        if (value.first().equals(value))
            parent().head(newItem);
    }
    @SuppressWarnings("unchecked")
	@Override
    public T remove(T value) throws Exception {
        if (!isMonitoring() && value != null) {
            T _next = (T) value.next();
            removeHead(value, _next);
            T _prev = (T) value.prev();
            _prev.next(_next);
            _next.prev(_prev);
        }
        return value;
    }
    @SuppressWarnings("unchecked")
	@Override
    public final T parent(P value) {
        if (factory != null)
            ((Factory) factory).parent(value);
        return super.parent(value);
    }
    @SuppressWarnings("unchecked")
	private T getNextNode(T lastNode) throws Exception {
        return lastNode.parent() instanceof Perimeter ? pHead(lastNode) : pHead((P) lastNode.parent().next());
    }
    @SuppressWarnings("unchecked")
	@Override
    public final T prepare() throws Exception {
        if (!isMonitoring() && next().equals(this)) {
            if(!first().equals(this)) {
                T lastNode = (T) first().last();
                T nextNode = isNull(lastNode.parent()) ? (T) lastNode.next() : getNextNode(lastNode);
                next(nextNode);
                T savedNode = (T) nextNode.prev();
                nextNode.prev(this);
                prev(savedNode);
                savedNode.next(this);
            } else if (isParent()) { // head
                if(!parent().prev().equals(parent())) {
                    T lastNode = parent() instanceof Perimeter ? pHead((T) this) : (T) pHead((P) parent().prev()).last();
                    prev(lastNode);
                    T savedNode = (T) lastNode.next();
                    lastNode.next(this);
                    next(savedNode);
                    savedNode.prev(this);
                }
            } else { // parent is null
                T lastNode = (T) last();
                T nextNode = (T) first();
                prev(lastNode);
                lastNode.next(this);
                nextNode.prev(this);
                next(nextNode);
            }
        }
        return (T) this;
    }
    OModel(P parent, String id) {
        super(parent);
        if (checkStr(id))
            id = Util.getUUId();
        idStr = id;
    }
    @Override
    public final Data data() {
        Model mapper = this;
        while (mapper.parent() != null)
            mapper = mapper.parent();
        return (Data) mapper;
    }
    @Override
    public boolean isMonitoring() {
        return data().isMonitoring();
    }
    @Override
    public int hashCode() {
        return id().hashCode();
    }
    @Override
    public boolean idEquals(String thatId) {
        return id().equals(thatId);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OModel that = (OModel) o;
        return idEquals(that.id());
    }
    private String infoOf(Model m) {
        StringBuilder b = new StringBuilder();
        b.append(getClass().getSimpleName()).append('{').append(((OModel)m).modelInfo()).append('}');
        return b.toString();
    }
    protected String modelInfo() {
        StringBuilder b = new StringBuilder();
        if(isParent())
            b.append(parent().toString()).append(", ");
        b.append("id = '").append(id()).append('\'');
        return b.toString();
    }
    @Override
    public String toString() {
        return infoOf(this);
    }
}