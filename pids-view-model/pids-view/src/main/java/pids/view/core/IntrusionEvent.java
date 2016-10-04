package pids.view.core;

import java.time.LocalDateTime;
import pids.core.Node;

public interface IntrusionEvent<T extends Node<?>> extends PIDSEvent {
    enum Severity {
	    P1,
	    P2,
	    P3,
	    P4
    }
    Severity getSeverity();
    LocalDateTime getTime();
    Zone getZone();
    void setHandled(boolean value);
    boolean isHandled();
}