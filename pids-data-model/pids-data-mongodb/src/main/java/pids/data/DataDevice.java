package pids.data;

import lombok.Getter;
import lombok.Setter;

abstract class DataDevice extends ToggleDevice {
	@Getter @Setter private String ip;
	@Getter @Setter private String user;
	@Getter @Setter private String password;
}
