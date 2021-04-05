package com.cecabank.t2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.cecabank.t2.models.entity.Fichero;
import com.cecabank.t2.models.entity.Respuesta;

import com.cecabank.t2.services.IEnvioFichero;

@RestController
@RequestMapping("/api")
public class ClienteRestController {
	@Autowired
	public IEnvioFichero envio;
	@PostMapping
	public Respuesta index(@RequestBody Fichero fichero) {
		return envio.enviarFichero(fichero);
	}

}
