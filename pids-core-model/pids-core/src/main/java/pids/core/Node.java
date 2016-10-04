package pids.core;

@SuppressWarnings("rawtypes")
public interface Node<T extends Node> extends Model<T, Sector> {
    public double getX();
    public void setX(double value);
    public double getY();
    public void setY(double value);
}
