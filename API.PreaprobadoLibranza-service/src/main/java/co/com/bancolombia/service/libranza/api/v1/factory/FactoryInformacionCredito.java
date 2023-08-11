package co.com.bancolombia.service.libranza.api.v1.factory;

import com.bcol.vtd.lib.comunes.dto.InformacionCredito;

public class FactoryInformacionCredito {
    
	/**
	 * 
	 * @param CupoTotalPreaprobado
	 * @param montoSolicitado
	 * @param plazo
	 * @param cuotaMensual
	 * @param tasaNAMV
	 * @param tasaEA
	 * @param tasaMesVencida
	 * @param tasaMora
	 * @param valorSeguro
	 * @param tipoCuenta
	 * @param cuentaDesembolso
	 * @param idPlanSeleccionado
	 * @return InformacionCredito
	 */
	public InformacionCredito create() {

        return informacionCredito;
    }
}