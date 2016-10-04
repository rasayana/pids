package pids.core;

@SuppressWarnings("rawtypes")
public interface Model<T extends Model, P extends Container> extends Base<P>{
    Data data();
    String id();
    boolean idEquals(String thatId);
    void factory(ModelFactory factory);
    T[] list() throws Exception;
    T next();
    void next(T value);
    T prev();
    void prev(T value);
    T parent(P value);
    T last() throws Exception;
    T first() throws Exception;
    T prepare() throws Exception;
    T get(String id);
    T add(String id) throws Exception;
    T add() throws Exception;
    T add(T value) throws Exception;
    T remove(String id) throws Exception;
    T remove(T value) throws Exception;
    public boolean isMonitoring();
}