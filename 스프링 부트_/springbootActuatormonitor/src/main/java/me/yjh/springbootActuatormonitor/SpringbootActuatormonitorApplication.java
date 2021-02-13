package me.yjh.springbootActuatormonitor;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAdminServer
public class SpringbootActuatormonitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootActuatormonitorApplication.class, args);
	}

}
