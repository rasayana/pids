package pids.core;

public interface ModelFactory {
    @SuppressWarnings("rawtypes")
	Model instance(String... params) throws Exception;
}