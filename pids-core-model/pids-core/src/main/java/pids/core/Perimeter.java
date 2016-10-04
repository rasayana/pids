package pids.core;

public interface Perimeter extends Container<Perimeter, Data, Sector> {
   Anchor[] anchors() throws Exception;
   Camera[] cameras() throws Exception;
   Hooter[] hooters() throws Exception;
}