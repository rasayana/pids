package pids.data;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
abstract class DataBase {
	@Id private String id;
}