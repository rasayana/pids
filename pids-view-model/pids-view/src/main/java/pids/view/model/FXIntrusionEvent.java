package pids.view.model;

import javafx.event.Event;
import javafx.event.EventType;
import javafx.event.EventTarget;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import java.time.LocalDateTime;
import pids.view.core.Zone;
import pids.view.core.IntrusionEvent;
import pids.core.Toggle;

public final class FXIntrusionEvent extends Event implements IntrusionEvent<Toggle<?>> {
	private static final long serialVersionUID = 8407204116266223739L;
	public static final EventType<FXIntrusionEvent> INTRUSION_EVENT = new EventType<FXIntrusionEvent>(ANY, "INTRUSION_EVENT");
    private BooleanProperty handled = new SimpleBooleanProperty(this, "hendled", false);
    private final ObjectProperty<LocalDateTime> time;
    private final ObjectProperty<Severity> severity;
    private final StringProperty zone;
    private final BoundLine<?> line;
    public FXIntrusionEvent(BoundLine<?> line, Severity severity) {
        this(INTRUSION_EVENT, line, severity);
    }
    public FXIntrusionEvent(EventType<? extends Event> arg0, BoundLine<?> line, Severity severity) {
        this(null, null, arg0, line, severity);
    }
    public FXIntrusionEvent(Object arg0, EventTarget arg1, EventType<? extends Event> arg2, BoundLine<?> line, Severity severity) {
        super(arg0, arg1, arg2);
        this.line = line;
        zone = new SimpleStringProperty(line.model().id() + '-' + line.other().id());
        this.severity = new SimpleObjectProperty<Severity>(severity);
        time = new SimpleObjectProperty<LocalDateTime>(LocalDateTime.now());
    }
    public final ObjectProperty<Severity> severityProperty() {
    	return severity;
    }
    @Override
    public final Severity getSeverity() {
        return severityProperty().get();
    }
    public final ObjectProperty<LocalDateTime> timeProperty() {
    	return time;
    }
    @Override
    public final LocalDateTime getTime() {
        return time.get();
    }
    public final StringProperty zoneProperty() {
    	return zone;
    }
    @Override
    public final Zone getZone() {
        return line;
    }
    public final BooleanProperty handledProperty() {
    	return handled;
    }
    @Override
    public final boolean isHandled() {
        return handled.get();
    }
    @Override
    public final void setHandled(boolean value) {
        handled.set(value);
    }
}