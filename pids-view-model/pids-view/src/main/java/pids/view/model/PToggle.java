package pids.view.model;

import pids.core.Toggle;
import pids.core.Device;
import pids.core.Camera;
import pids.core.Hooter;
import pids.core.Anchor;
import pids.view.core.PSector;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.adapter.JavaBeanBooleanPropertyBuilder;
import javafx.scene.paint.Color;

abstract class PToggle<T extends Toggle<T>> extends PNode<T> {
	final private BooleanProperty toggleProperty;
    PToggle(NodeControl<?, ?> shape, T node, PSector sector) throws NoSuchMethodException {
        super(shape, node, sector);
        toggleProperty = JavaBeanBooleanPropertyBuilder.create().bean(getNode()).beanClass(Toggle.class).name("on").build();
    }
    BooleanProperty toggleProperty() {
        return toggleProperty;
    }
    static class PDevice<Y extends Device<Y>> extends PToggle<Y> {
        public PDevice(NodeControl<?, ?> shape, Y device, PSector sector) throws NoSuchMethodException {
            super(shape, device, sector);
        }
    }
    static class PCamera extends PDevice<Camera> {
        public PCamera(NodeCamera shape, Camera device, PSector sector) throws NoSuchMethodException {
            super(shape, device, sector);
            color().set(sector.getAttribute().getCameraColor());
        }
        @Override
        final ObjectProperty<Color> color() {
            return thisParent().cameraColor();
        }
    }
    static class PHooter extends PDevice<Hooter> {
        public PHooter(NodeHooter shape, Hooter device, PSector sector) throws NoSuchMethodException {
            super(shape,  device, sector);
        }
        @Override
        final ObjectProperty<Color> color() {
            return thisParent().hooterColor();
        }
    }
	static class PAnchor extends PToggle<Anchor> {
        public PAnchor(NodeControl<?, ?> shape, Anchor anchor, PSector sector) throws NoSuchMethodException {
            super(shape, anchor, sector);
        }
    }
}