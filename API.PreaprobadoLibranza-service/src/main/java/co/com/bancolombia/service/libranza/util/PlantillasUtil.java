package co.com.bancolombia.service.libranza.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import com.bcol.vtd.lib.comunes.dto.RespuestaServicioEnvioCorreo;
import com.bcol.vtd.lib.comunes.util.CargadorPropiedades;
import com.bcol.vtd.lib.comunes.util.CodigosRespuestaServicios;
import com.bcol.vtd.lib.comunes.util.ConstantesGeneracionPDF;
import com.bcol.vtd.lib.comunes.util.Parametro;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

/***
 * Clase de utilidades para la api de envio de correo
 */
public class PlantillasUtil {

	CargadorPropiedades configuracion;
	/***
	 * Lista de Parametros para agregar a la plantilla
	 */
	private List<Parametro> listaParametros = new ArrayList<Parametro>();

	private String rutaplantilla = null;
	private String plantilla = null;

	private String asuntoCorreo = null;
	private String deCorreo = null;
	private String paraCorreo = null;

	private List<String> imagen_tarjeta = null;

	private String numeroIdentificacion = null;

	private VelocityEngine velocityEngine = null;
	private VelocityContext context = null;
	private Template template = null;
	private StringWriter writer = null;

	private String nombreArchivo1 = null;
	private String contenidoArchivo1 = null;
	private byte[] archivoDecodificado = null;
	private ByteArrayInputStream bis = null;
	private byte[] archivoAdjunto = null;
	private PDDocument pdfDoc;

	private ByteArrayOutputStream out = null;
	private InputStream is = null;

	private String usuarioSL = null;
	private String claveSL = null;

	private String imagenes[] = null;

	private File filePdf;

	private RespuestaServicioEnvioCorreo respuestaServicioEnvioCorreo;

	public PlantillasUtil(String plantilla, List<Parametro> listaParametros, CargadorPropiedades configuracion) {

		this.plantilla = plantilla;
		this.listaParametros = listaParametros;
		this.configuracion = configuracion;
		respuestaServicioEnvioCorreo = new RespuestaServicioEnvioCorreo();
		respuestaServicioEnvioCorreo.setEstadoRespuesta(true);

	}

	/***
	 * Procedimiento que mapea los parametros recibidos y crea el cuerpo html del
	 * correo
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean createBodyHtml() {

		writer = new StringWriter();

		try {

//			plantilla = configuracion.getValue(ConstantesGeneracionPDF.NOMBRE_PLANTILLA_CORREO) != null
//					? configuracion.getValue(ConstantesGeneracionPDF.NOMBRE_PLANTILLA_CORREO)
//					: "";
			usuarioSL = configuracion.getValue(ConstantesGeneracionPDF.USUARIO_CORREO) != null
					? configuracion.getValue(ConstantesGeneracionPDF.USUARIO_CORREO)
					: "";
			claveSL = configuracion.getValue(ConstantesGeneracionPDF.CLAVE_CORREO) != null
					? configuracion.getValue(ConstantesGeneracionPDF.CLAVE_CORREO)
					: "";
			deCorreo = configuracion.getValue(ConstantesGeneracionPDF.DE_CORREO) != null
					? configuracion.getValue(ConstantesGeneracionPDF.DE_CORREO)
					: "";

			context = new VelocityContext();

			for (Parametro param : listaParametros) {

				context.put(param.getClave(), param.getValor());

				if (param.getObject() != null) {
					context.put(param.getClave(), param.getObject());
				}
				if (param.getClave().equals(ConstantesGeneracionPDF.PARA)) {
					paraCorreo = param.getObject().toString();
				}
				if (param.getClave().equals(ConstantesGeneracionPDF.ARCHIVO1)) {
					contenidoArchivo1 = param.getObject().toString();
				}
				if (param.getClave().equals(ConstantesGeneracionPDF.DOCUMENTO)) {
					numeroIdentificacion = param.getObject().toString();
				}
				if (param.getClave().equals(ConstantesGeneracionPDF.IMAGENES)) {
					imagenes = param.getValor().split(",");
				}
				if (param.getClave().equals(ConstantesGeneracionPDF.CODIGO_IMAGEN)) {
					imagen_tarjeta = ((ArrayList<String>) param.getObject());
				}
				if (param.getClave().equals(ConstantesGeneracionPDF.ASUNTO_CORREO)) {
					asuntoCorreo = configuracion.getValue(param.getValor());
				}
				if (param.getClave().equals(ConstantesGeneracionPDF.RUTA_PLANTILLA_CORREO)) {
					rutaplantilla = configuracion.getValue(param.getValor());
					context.put("RUTA_IMG", rutaplantilla);
				}
				if (param.getClave().equals(ConstantesGeneracionPDF.NOMBRE_PLANTILLA_PDF)) {
					plantilla = "pdf_" + plantilla;
				}

			}

			velocityEngine = new VelocityEngine();
			velocityEngine.setProperty("input.encoding", StandardCharsets.UTF_8.name());
			velocityEngine.setProperty("file.resource.loader.description", "Velocity File Resource Loader");
			velocityEngine.setProperty("file.resource.loader.class",
					"org.apache.velocity.runtime.resource.loader.FileResourceLoader");
			velocityEngine.setProperty("file.resource.loader.path", rutaplantilla);
			velocityEngine.setProperty("file.resource.loader.cache", "false");
			velocityEngine.setProperty("file.resource.loader.modificationCheckInterval", "2");
			velocityEngine.init();

			try {
				template = velocityEngine.getTemplate(plantilla);
			} catch (Exception e) {
				respuestaServicioEnvioCorreo.setError(CodigosRespuestaServicios.DDOC_002.getCodigo(),
						CodigosRespuestaServicios.DDOC_002.getDescripcion());
			}

			template.merge(context, writer);

			return true;

		} catch (Exception e) {
			respuestaServicioEnvioCorreo.setError(CodigosRespuestaServicios.DDOC_005.getCodigo(),
					CodigosRespuestaServicios.DDOC_005.getDescripcion());
			return false;
		}

	}

	/***
	 * procedimiento que se encarga de convertir la plantilla html en un archivo PDF
	 */
	public void createFile() {

		filePdf = null;

		try {

			filePdf = File.createTempFile("tmp", ".".concat("pdf"), null);

			Document document = new Document();

			document.setPageSize(PageSize.LETTER);

			PdfWriter pdfWriter = null;

			pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(filePdf.getPath()));

			document.open();

			createBodyHtml();

			if (respuestaServicioEnvioCorreo.getEstadoRespuesta()) {

				String content = this.writer.toString();

				XMLWorkerHelper.getInstance().parseXHtml(pdfWriter, document, new StringReader(content));

				document.close();

				pdfWriter.close();

				System.out.println("PDF generated successfully");

				System.out.println(filePdf.getPath());
			}

		} catch (Exception e) {
			respuestaServicioEnvioCorreo.setError(CodigosRespuestaServicios.DDOC_003.getCodigo(),
					CodigosRespuestaServicios.DDOC_003.getDescripcion());
		}

	}

	public File getFilePdf() {
		return this.filePdf;
	}

	public RespuestaServicioEnvioCorreo getRespuestaServicioEnvioCorreo() {
		return this.respuestaServicioEnvioCorreo;
	}
}
