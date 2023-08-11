package co.com.bancolombia.service.libranza.util;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Clase centralizada para manejo de logs <b>IBM. Global Bussiness Services GBS
 * Colombia.</b>
 * 
 * @author juancard - Juan Carlos Cardona - IBM
 * @since 16/6/2017
 */
public class LibranzaLog implements Serializable {

	/** serialVersionUID **/
	private static final long serialVersionUID = 8508423458440566372L;

	/**
	 * Method que permite la escritura de errores en el log.
	 *
	 * @param thisClass    Clase en la que se genera el error.
	 * @param errorMessage Mensaje de error.
	 * @param exception    Excepción.
	 */
	private static final Logger logger = LogManager.getLogger(LibranzaLog.class);

	private static final String INICIANDO = "Iniciando metodo: ";
	private static final String FINALIZANDO = "Finalizando metodo: ";

	public static void logErrorMessage(final Class<?> thisClass, final String srcErrorMessage) {
		logErrorMessage(thisClass, srcErrorMessage, null);
	}

	/**
	 * Log Error
	 * 
	 * @param thisClass
	 * @param srcErrorMessage
	 * @param srcException
	 */
	public static void logErrorMessage(final Class<?> thisClass, final String srcErrorMessage,
			final Throwable srcException) {

		String errorMessage = "Error en [" + thisClass.getCanonicalName() + "]";

	}

	/**
	 * Method que permite ingresar un mensaje informativo al log.
	 *
	 * @param thisClass   Clase en la que se genera el error.
	 * @param infoMessage Mensaje.
	 */
	public static void logInfoMessage(final Class<?> thisClass, final String infoMessage) {
		String message = "INFO en [" + thisClass.getCanonicalName() + "]";

	}

	/**
	 * Method que permite ingresar un mensaje informativo al log.
	 *
	 * @param thisClass   Clase en la que se genera el error.
	 * @param infoMessage Mensaje.
	 */
	public static void debugInfoMessage(final Class<?> thisClass, final String infoMessage) {
		String message = "INFO en [" + thisClass.getCanonicalName() + "]";

	}

	/**
	 * Method que permite ingresar un mensaje informativo al log.
	 *
	 * @param thisClass   Clase en la que se genera el error.
	 * @param infoMessage Mensaje.
	 */
	public static void logInfoMessageCharacters(final Class<?> thisClass, final String infoMessage) {
		String message = "INFO en [" + thisClass.getCanonicalName() + "]";

	}

	/**
	 * Metodo que permite ingresar un mensaje informativo al log.
	 * 
	 * @param thisClass   Clase en la que se genera el error.
	 * @param infoMessage Mensaje.
	 * @param items       Objeto a imprimir en texto.
	 */
	public static void logInfoMessageCharactersWithObject(final Class<?> thisClass, final String infoMessage,
			final Object items) {

	}

	/**
	 * Method que permite ingresar un mensaje informativo al log.
	 *
	 * @param thisClass   Clase en la que se genera el error.
	 * @param iniciando   Bandera para verificar si se esta iniciando o finalizando
	 *                    el metodo.
	 * @param infoMessage Objeto a imprimir en texto.
	 */
	public static void logInfoMessageCharactersInit(final Class<?> thisClass, String infoMessage,

	}

	/**
	 * Method que permite ingresar un mensaje informativo al log.
	 *
	 * @param thisClass   Clase en la que se genera el error.
	 * @param infoMessage Mensaje en texto para denotar el request.
	 * @param request     Objeto a imprimir en texto.
	 */
	public static void logInfoMessageCharactersRequest(final Class<?> thisClass, String infoMessage,


}
