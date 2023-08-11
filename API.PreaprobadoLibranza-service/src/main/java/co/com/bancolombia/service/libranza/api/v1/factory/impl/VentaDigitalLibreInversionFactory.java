package co.com.bancolombia.service.libranza.api.v1.factory.impl;

import javax.inject.Inject;

import com.bcol.vtd.lib.comunes.dto.DatosPersonales;
import com.bcol.vtd.lib.comunes.dto.InformacionCredito;
import com.bcol.vtd.lib.comunes.dto.InformacionDispositivo;
import com.bcol.vtd.lib.comunes.dto.InformacionTransaccion;
import com.bcol.vtd.lib.comunes.dto.Producto;
import com.bcol.vtd.lib.comunes.dto.VentaDigitalLibreInversion;
import com.bcol.vtd.proxy.gestionsolcreditoconsumo.util.ConstantesGestionSolicitudCreditoConsumo;

import co.com.bancolombia.service.libranza.api.v1.factory.IFactory;
import co.com.bancolombia.service.libranza.api.v1.factory.dto.ParameterFactoryDTO;
import co.com.bancolombia.service.libranza.api.v1.service.comun.impl.ParameterService;

/**
 * 
 * <b>IBM. Colombia.</b>
 *
 * <p>
 * Description:
 * </p>
 *
 * @author <A HREF="mailto:duvacano@co.ibm.com">Duban Cano</A>
 *
 * @version Aug 28, 2018
 */
public class VentaDigitalLibreInversionFactory implements IFactory {

	private ParameterService parameterService;

}