package co.com.bancolombia.service.libranza.api.v1.service;

import co.com.bancolombia.service.libranza.model.InformationConnection;

/***
 * Interfase para el servicio de Informacion conexion
 * @author lamiranda
 *
 */
public interface InformationConnectionServiceI {

	/**
	 * Función que retorna un objeto InformacionConnection a partir de loa datos recibidos
	 * @param idSesion Id de la sesion del usuario
	 * @param ipCliente Ip de conexion del cliente
	 * @param actualPage pagina actual
	 * @return InformationConnection
	 */
	InformationConnection getInformationConnection(String idSesion, 
			String ipCliente, String actualPage);


}