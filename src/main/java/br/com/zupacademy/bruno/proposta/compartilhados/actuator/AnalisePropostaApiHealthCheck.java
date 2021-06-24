package br.com.zupacademy.bruno.proposta.compartilhados.actuator;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import br.com.zupacademy.bruno.proposta.compartilhados.apiExternas.solicitacaoAnaliseProposta.AnalisePropostaViaApi;

@Component
public class AnalisePropostaApiHealthCheck implements HealthIndicator {

	private final Logger logger = LoggerFactory.getLogger(AnalisePropostaViaApi.class);
	
	private static final String host = "localhost";
	private static final int port = 9999;
	
    @Override
    public Health health() {
    	Map<String, Object> details = new HashMap<>();
	        details.put("description", "External API to make analisys of proposal");
	        details.put("url", "http://"+ host +":"+ port);

        try {
//			boolean reachable = InetAddress.getByName(URL).isReachable(5000);
        	Socket socket = new Socket(host, port);
        	logger.info("Succeded to reach: {}", "http://"+ host +":"+ port );
        	socket.close();
			return Health.up().withDetails(details).build();
		} catch (Exception e) {
			logger.warn("Failed to connect to: {}", "http://"+ host +":"+ port );
//			e.printStackTrace();
			return Health.down()
					.withDetails(details)
			        .withDetail("error", e.getMessage())
			        .build();
		} 
    }

}
