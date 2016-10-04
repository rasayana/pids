package pids.view.core;

import pids.core.Model;

public interface Zone extends PShape {
    Model<?, ?> other();
}
