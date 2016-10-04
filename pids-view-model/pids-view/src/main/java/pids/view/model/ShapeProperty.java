package pids.view.model;

import pids.view.core.PShape;
import pids.view.core.PBase;
import pids.view.core.PSector;

abstract class ShapeProperty<T extends PShape> implements PBase {
    private T obj;
    final T getShape() {
        return obj;
    }
    final void setShape(T obj) {
        this.obj = obj;
    }
    private final PSector sector;
    @Override
	public final PSector thisParent() {
		return sector;
	}
    ShapeProperty(T obj, PSector sector) {
    	this.sector = sector;
        this.obj = obj;
    }
}