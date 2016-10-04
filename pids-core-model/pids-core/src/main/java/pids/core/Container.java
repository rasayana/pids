package pids.core;

@SuppressWarnings("rawtypes")
public interface Container<T extends Container, P extends Container, H extends Model> extends Model<T, P> {
    T createhead(String id) throws Exception;
    T createhead() throws Exception;
    T head(H value);
    H head();
}