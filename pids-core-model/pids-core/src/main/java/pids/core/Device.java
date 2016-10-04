package pids.core;

@SuppressWarnings("rawtypes")
public interface Device<T extends Device> extends Toggle<T> {
    T prepareDevice(String ip, String user,String password);
    T activateDevice();
    T deActivateDevice();
    String ip();
    String user();
    String password();
}
