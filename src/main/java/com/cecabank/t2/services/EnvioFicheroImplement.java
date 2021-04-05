package com.cecabank.t2.services;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ExchangeFilterFunctions;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import com.cecabank.t2.models.entity.*;
import reactor.core.publisher.Mono;

@Service
public class EnvioFicheroImplement implements IEnvioFichero {
		private static String   BASE_URL= "http://localhost:8090";
		//private static String   BASE_URL= "https://api.np.intranet.cajastur.es:843";

		private final WebClient webClient;
	    @Autowired	
	    public EnvioFicheroImplement() { webClient = WebClient.builder()
		        .baseUrl(BASE_URL)
		        .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json")
		        .defaultHeader(HttpHeaders.USER_AGENT, "Spring 5 WebClient")
		        .build();
	    }

	  
		@Override
		public Respuesta enviarFichero(Fichero fichero){
			
			TokenServiceImpl tokenService = new TokenServiceImpl();
			Token token = tokenService.obtenerToken();
			
			Mono<Respuesta> respuesta = null;
			 
			respuesta =  webClient.post()
					.uri("/perpetualChange/fichero")
	                .header("Authorization", "Bearer " + token.getAccess_token())
	                .bodyValue(fichero)
					.retrieve()
					.bodyToMono(Respuesta.class);
			Respuesta respuestaRET = respuesta.block();
			
			return respuestaRET; 
		}
	}





