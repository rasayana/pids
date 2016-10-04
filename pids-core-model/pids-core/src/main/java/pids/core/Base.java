package pids.core;

@SuppressWarnings("rawtypes")
public interface Base<P extends Model> {
    P parent();
}
