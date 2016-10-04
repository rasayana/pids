package pids.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pids.view.ZoomMapView;

@Component
public class ZoomViewController extends ZoomMapPaneController<ZoomMapView>{
    @Autowired
	public ZoomViewController(ZoomMapView content) {
    	super(content);
	}
}
