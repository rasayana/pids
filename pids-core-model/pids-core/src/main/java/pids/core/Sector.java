package pids.core;

public interface Sector extends Container<Sector, Perimeter, Anchor> {
    Camera removeCamera(String id) throws Exception;
    Camera createCamera(String id) throws Exception;
    Camera camera() throws Exception;
    Hooter removeHooter(String id) throws Exception;
    Hooter createHooter(String id) throws Exception;
    Hooter hooter() throws Exception;
}