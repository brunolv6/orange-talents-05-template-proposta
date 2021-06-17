package br.com.zupacademy.bruno.proposta.compartilhados.actuator;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

@Component
public class PropostaApi implements HealthIndicator {

	@Override
	public Health health() {
		Map<String, Object> details = new HashMap<>();
		details.put("version", "0.2.0");
		details.put("description", "Health Check of this application");
		details.put("url", "http://localhost:8080");

		return Health.status(Status.UP).withDetails(details).build();
	}

}
