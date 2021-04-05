package com.cecabank.t2.services;

import java.util.Base64;

import org.springframework.web.reactive.function.BodyInserters;

import org.springframework.web.reactive.function.client.WebClient;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;

import org.springframework.stereotype.Service;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.cecabank.t2.models.entity.*;


import reactor.core.publisher.Mono;
@Service
public class TokenServiceImpl implements ITokenService{

	
	private static String	CLIENTID= "b824001f035d3f45be47e9c97c8a1a2a";
	private static String   CLIENTSECRET= ":da8d598531939c72cdd1c5a8e7844c45";
	private static String   BASE_URL= "http://localhost:8090";
	
	

	
	private final WebClient webClient;
    @Autowired	
    public TokenServiceImpl() { webClient = WebClient.builder()
	        .baseUrl(BASE_URL)
	        .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded")
	        .defaultHeader(HttpHeaders.USER_AGENT, "Spring 5 WebClient")
	        .build();
    }

  
	@Override
	public Token obtenerToken(){
		
		System.out.println("@@@@@ obtenerToken @@@@@@");   	

		
		MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
		formData.add("grant_type","client_credencials");
		formData.add("scope","read");
		System.out.println("Entro");
		//TODO
		//Pedir TOKEN a LBK 
		String cadenaCodificada =
		          Base64.getEncoder().encodeToString((CLIENTID+CLIENTSECRET).getBytes());
		System.out.println("Obtengo esto " + cadenaCodificada);
            		    		
        Mono<Token> token = null;
		try {
			System.out.println("Llego aquí");
			token = webClient.post()
					.uri("/perpetualChange/cabecera")
                    .header("Authorization", "Basic " + cadenaCodificada)
                    .body(BodyInserters.fromFormData(formData))
					.retrieve()
					.bodyToMono(Token.class);
					
				

		} catch (Exception e) {
			System.out.println("Errorako");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		Token tokenresp = token.block();
        System.out.println(tokenresp.getToken_type());   	

		return tokenresp;

	}

}




