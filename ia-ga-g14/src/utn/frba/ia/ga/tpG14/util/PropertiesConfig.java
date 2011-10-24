package utn.frba.ia.ga.tpG14.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesConfig {

	private final String pathName;
	private Properties properties;
	private static final String ALGORITMO_SELECCION = "Algoritmo_seleccion";
	private static final String METODO_POBLACION_INICIAL = "Metodo_poblacion_inicial";
	private static final String INYECCION_PATRONES = "Inyeccion_patrones";
	private static final String TAMANIO_POBLACION = "Tamanio_poblacion";
	private static final String CANTIDAD_GENERACIONES = "Cantidad_generaciones";

	private static final String ALGORITMO_SELECCION_DEFAULT = "ruleta";
	private static final String METODO_POBLACION_INICIAL_DEFAULT = "ad-hoc";
	private static final String INYECCION_PATRONES_DEFAULT = "si";
	private static final String TAMANIO_POBLACION_DEFAULT = "500";
	private static final String CANTIDAD_GENERACIONES_DEFAULT = "100";

	// String algoritmoSeleccion = ""; // "Ruleta" ó "Torneo"
	// String metodoPoblacionInicial = ""; // "ad-hoc" ó "azar"
	// String inyeccionPatrones = ""; // "si" ó "no"
	// int tamanioPoblacion = 250;
	// int cantidadGeneraciones = 500;

	public PropertiesConfig(String pathName) {
		this.pathName = pathName;
		this.properties = new Properties();
	}

	public void cargarConfiguracion() throws FileNotFoundException, IOException {
		this.properties.load(new FileInputStream(new File(pathName)));
	}

	public String getAlgoritmoSeleccion() {
		return this.properties.getProperty(ALGORITMO_SELECCION,
				ALGORITMO_SELECCION_DEFAULT);
	}

	public String getMetodoPoblacionInicial() {
		return this.properties.getProperty(METODO_POBLACION_INICIAL,
				METODO_POBLACION_INICIAL_DEFAULT);
	}

	public String getInyeccionPatrones() {
		return this.properties.getProperty(INYECCION_PATRONES,
				INYECCION_PATRONES_DEFAULT);
	}

	public int getTamanioPoblacion() {
		return Integer.valueOf(this.properties.getProperty(TAMANIO_POBLACION,
				TAMANIO_POBLACION_DEFAULT));
	}

	public int getCantidadGeneraciones() {
		return Integer.valueOf(this.properties.getProperty(
				CANTIDAD_GENERACIONES, CANTIDAD_GENERACIONES_DEFAULT));
	}

	public void crearConfiguracionDefault() {
		try {
			this.properties.setProperty(ALGORITMO_SELECCION,
					ALGORITMO_SELECCION_DEFAULT);
			this.properties.setProperty(METODO_POBLACION_INICIAL,
					METODO_POBLACION_INICIAL_DEFAULT);
			this.properties.setProperty(INYECCION_PATRONES,
					INYECCION_PATRONES_DEFAULT);
			this.properties.setProperty(TAMANIO_POBLACION,
					TAMANIO_POBLACION_DEFAULT);
			this.properties.setProperty(CANTIDAD_GENERACIONES,
					CANTIDAD_GENERACIONES_DEFAULT);
			properties
					.store(new FileOutputStream(new File(this.pathName)),
							"Archivo de configuración del algoritmo genético. Para conocer su uso, leer el archivo leame.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
