package pids.view.core;

import pids.core.Model;

public interface PModel extends PBase {
    Model<?, ?> model();
}