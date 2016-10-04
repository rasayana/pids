package pids.resources;

import org.springframework.core.io.ResourceLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;

@Configuration
public class ResourceConfiguration {
	@Bean
	ResourceLoader defaultResourceLoader() {
		return new DefaultResourceLoader();
	}
}
