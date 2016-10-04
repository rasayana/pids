package pids.model;
import pids.core.Node;
import pids.core.Sector;
@SuppressWarnings("rawtypes")
abstract class ONode<T extends Node> extends OModel<T, Sector> implements Node<T> {
    private class Delta { double x = 100, y = 100; }
    private final Delta delta = new Delta();
    ONode(Sector parent, String id) {
        super(parent, id);
    }
    @Override
    public final void setX(double value) {
        if (value != delta.x)
            delta.x = value;
    }
    @Override
    public final double getX() {
        return delta.x;
    }
    @Override
    public final void setY(double value) {
        if (value != delta.y)
            delta.y = value;
    }
    @Override
    public final double getY() {
        return delta.y;
    }
    private static String D2S(double value) {
        return String.valueOf(value);
    }
    @Override
    protected String modelInfo() {
        StringBuilder b = new StringBuilder();
        b.append(super.modelInfo()).append(", x = ").append(D2S(getX())).append(", y = ").append(D2S(getY()));
        return b.toString();
    }
}