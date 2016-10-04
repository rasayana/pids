package pids.data;

import lombok.Getter;
import lombok.Setter;

abstract class ToggleDevice extends DataBase {
	@Getter @Setter private double x;
	@Getter @Setter private double y;
	@Getter @Setter private boolean on;
}
