package pids.view.model;

import pids.core.Hooter;
import pids.view.core.PSector;
import pids.view.model.PToggle.PHooter;

public class NodeHooter extends NodeControl<PHooter, Hooter> {
	NodeHooter(PHooter node, PSector sector) {
		super(node, sector);
	}
}