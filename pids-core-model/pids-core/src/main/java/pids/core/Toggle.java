package pids.core;

@SuppressWarnings("rawtypes")
public interface Toggle<T extends Toggle> extends Node<T> {
    public void setOn(boolean flag);
    public boolean isOn();
}
