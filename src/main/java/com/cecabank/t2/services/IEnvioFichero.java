package com.cecabank.t2.services;

import com.cecabank.t2.models.entity.Fichero;
import com.cecabank.t2.models.entity.Respuesta;

public interface IEnvioFichero {
	
	public Respuesta enviarFichero(Fichero fichero);

}
