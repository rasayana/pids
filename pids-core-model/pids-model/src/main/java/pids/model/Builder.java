package pids.model;

import pids.core.Anchor;
import pids.core.Camera;
import pids.core.Hooter;
import pids.core.Data;
import pids.core.Perimeter;
import pids.core.Sector;
import java.awt.Point;

@SuppressWarnings("rawtypes")
public final class Builder {
    private Builder() {
    }
    private static Data head;
    public static final Data data() {
        return head;
    }
    public static final Data createData(String id) throws Exception {
        if (data() == null)
            head = (Data) new Factory.DataFactory(null).instance(id);
        return head;
    }
    public final Data createData() throws Exception {
        return createData(Util.getUUId());
    }
    private static String I2S(int value) {
        return String.valueOf(value);
    }
    private static Data getData() throws Exception {
        return Builder.createData(I2S(0));
    }
	private static Sector prepareSector(Sector s, int begin, int count, int cameraBegin, int cameras, int hooterBegin, int hooters) throws Exception {
        Anchor a = s.createhead(I2S(begin)).head();
        for (int i = (begin + 1); i < begin + count; i++)
            a = (Anchor) a.add(I2S(i));
        Camera c = s.createCamera(I2S(cameraBegin));
        for (int i = (cameraBegin + 1); i < cameraBegin + cameras; i++)
            c = c.add(I2S(i));
        Hooter h = s.createHooter(I2S(hooterBegin));
        for (int i = (hooterBegin + 1); i < hooterBegin + hooters; i++)
            h = h.add(I2S(i));
        return s;
    }
    private static Perimeter preparePerimeter(Perimeter p, int begin, int sectors, int zones, int cameras, int hooters) throws Exception {
        int _begin = 0;
        int _cameraBegin = 0;
        int _hooterBegin = 0;
        Sector s = prepareSector(p.createhead(I2S(begin)).head(), _begin, zones, _cameraBegin, cameras, _hooterBegin, hooters);
        _begin += zones;
        _cameraBegin += cameras;
        _hooterBegin += hooters;
        for (int i = (begin + 1); i < begin + sectors; i++) {
            s = prepareSector(s.add(I2S(i)), _begin, zones, _cameraBegin, cameras, _hooterBegin, hooters);
            _begin += zones;
            _cameraBegin += cameras;
            _hooterBegin += hooters;
        }
        drawCirclePoints(p, 450, new Point(500, 500));
        return p;
    }
    public static void drawCirclePoints(Perimeter p, double radius, Point center) throws Exception {
    	Anchor[] list = p.anchors();
        double slice = 2 * Math.PI / list.length;
        int i = 0;
        for (Anchor a: list) {
            double angle = slice * i;
            a.setX(center.getX() + radius * Math.cos(angle));
            a.setY(center.getY() + radius * Math.sin(angle));
            i++;
        }
    }
    public static Data getSampleData(int perimeters, int sectors, int zones, int cameras, int hooters)  throws Exception {
        Data d = getData().createhead(I2S(0));
        Perimeter p = preparePerimeter(d.head(), 0, sectors, zones, cameras, hooters);
        for (int i = 1; i < perimeters; i++)
            preparePerimeter(p.add(I2S(i)), 0, sectors, zones, cameras, hooters);
        return d;
    }
}