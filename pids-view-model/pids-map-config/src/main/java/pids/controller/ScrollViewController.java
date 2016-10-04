package pids.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pids.view.ScrollMapView;

@Component
public class ScrollViewController extends ScrollMapPaneController<ScrollMapView> {
    @Autowired
	public ScrollViewController(ScrollMapView content) {
		super(content);
	}
}
