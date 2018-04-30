package selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import selenium.pageobjects.PO_AddIncidencia;
import selenium.pageobjects.PO_LoginView;
import selenium.pageobjects.PO_View;
import selenium.util.SeleniumUtils;

import org.junit.runners.MethodSorters;
import org.openqa.selenium.*;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;

//Ordenamos las pruebas por el nombre del método
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InciManagerTests {

	private static WebDriver driver;
	static String URL;
	private static StringBuffer verificationErrors = new StringBuffer();

	// public static WebDriver getDriver(String PathFirefox) {
	// System.setProperty("webdriver.firefox.bin", PathFirefox);
	// WebDriver driver = new FirefoxDriver();
	// return driver;
	// }

	@Before
	public void setUp() {
		// Antes de cada prueba se navega al URL home de la aplicaciónn
		driver = new HtmlUnitDriver();
		URL = "http://localhost:8085";
		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
	}

	// Después de cada prueba se borran las cookies del navegador
	@After
	public void tearDown() {
		// driver.quit();
		driver.manage().deleteAllCookies();
		driver.get(URL + "/logout");
	}

	@AfterClass
	static public void end() {
		driver.quit();
		// String verificationErrorString = verificationErrors.toString();

	}

	@Test
	public void test1IntentoDeRellenarFormularioSinIniciarSesion() {
		driver.get(URL + "/formSendIncidence");
		// Comprobamos que nos vamos a la pantalla de login
		// PO_View.checkElement(driver, "text", "Sistema de participacion ciudadana");

		try {
			PO_View.checkElement(driver, "text", "Sistema de participacion ciudadana");
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}

		// SeleniumUtils.EsperaCargaPagina(driver, "text", "Sistema de participacion
		// ciudadana", PO_View.getTimeout());
	}

	@Test
	public void test2IntentoDeListarIncidencias() {
		driver.get(URL + "/list");
		// Comprobamos que nos vamos a la pantalla de login
		try {
			PO_View.checkElement(driver, "text", "Sistema de participacion ciudadana");
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}

	}

	@Test
	public void test3InicioSesionInvalido() {
		try {
			// Rellenamos el formulario
			PO_LoginView.fillForm(driver, "juanin", "135675476723", "Person");
			// Comprobamos que nos vamos a la pantalla de error
			PO_View.checkElement(driver, "text", "Ha ocurrido un error");
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}

	}

	@Test
	public void test4InicioSesionValido() {
		try {
			// Rellenamos el formulario
			PO_LoginView.fillForm(driver, "pepe", "123456", "Entity");
			// Comprobamos que entramos en sesión
			PO_View.checkElement(driver, "text", "Bienvenidos al sistema de incidencias");
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

	@Test
	public void test5FormularioIncorrectoCamposVacios() {
		try {
			// Rellenamos el formulario
			PO_LoginView.fillForm(driver, "pepe", "123456", "Entity");
			// Comprobamos que entramos en sesión
			SeleniumUtils.EsperaCargaPagina(driver, "text", "Bienvenidos al sistema de incidencias",
					PO_View.getTimeout());

			List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free",
					"//li[contains(@id,'formSendIncidence')]/a", PO_View.getTimeout());
			elementos.get(0).click();

			PO_AddIncidencia.fillForm(driver, "", "", "", "", "", "");
			// Al dejar campos vacíos seguimos en el envío de la incidencia mirando como
			// aparecen los inputs
			SeleniumUtils.EsperaCargaPagina(driver, "class", "col-sm-10", PO_View.getTimeout());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

	@Test
	public void test6TituloYDescripcionPequeña() {
		try {
			// Rellenamos el formulario
			PO_LoginView.fillForm(driver, "pepe", "123456", "Entity");
			// Comprobamos que entramos en sesión
			SeleniumUtils.EsperaCargaPagina(driver, "text", "Bienvenidos al sistema de incidencias",
					PO_View.getTimeout());

			List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free",
					"//li[contains(@id,'formSendIncidence')]/a", PO_View.getTimeout());
			elementos.get(0).click();

			PO_AddIncidencia.fillForm(driver, "hola", "hola", "fuego,incendio", "fuego:extremo", "45.356", "-32.145");
			// Al dejar campos vacíos seguimos en el envío de la incidencia mirando como
			// aparecen los inputs
			SeleniumUtils.EsperaCargaPagina(driver, "class", "text-danger", PO_View.getTimeout());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

	@Test
	public void test7ValorProhibido() {
		try {
			// Rellenamos el formulario
			PO_LoginView.fillForm(driver, "pepe", "123456", "Entity");
			// Comprobamos que entramos en sesión
			SeleniumUtils.EsperaCargaPagina(driver, "text", "Bienvenidos al sistema de incidencias",
					PO_View.getTimeout());

			List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free",
					"//li[contains(@id,'formSendIncidence')]/a", PO_View.getTimeout());
			elementos.get(0).click();

			PO_AddIncidencia.fillForm(driver, "@@@@@", "@@@@@", "@@@@@", "@@@@@:$$$$$", "45.356", "-32.145");
			// Al dejar campos vacíos seguimos en el envío de la incidencia mirando como
			// aparecen los inputs
			SeleniumUtils.EsperaCargaPagina(driver, "class", "text-danger", PO_View.getTimeout());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

	@Test
	public void test8CampoIncorrecto() {
		try {
			// Rellenamos el formulario
			PO_LoginView.fillForm(driver, "pepe", "123456", "Entity");
			// Comprobamos que entramos en sesión
			SeleniumUtils.EsperaCargaPagina(driver, "text", "Bienvenidos al sistema de incidencias",
					PO_View.getTimeout());

			List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free",
					"//li[contains(@id,'formSendIncidence')]/a", PO_View.getTimeout());
			elementos.get(0).click();

			PO_AddIncidencia.fillForm(driver, "Titulo incidencia", "Descripcion incorrecta", "Fuego", "NoHayDosPuntos",
					"45.356", "-32.145");
			// Al dejar campos vacíos seguimos en el envío de la incidencia mirando como
			// aparecen los inputs
			SeleniumUtils.EsperaCargaPagina(driver, "class", "text-danger", PO_View.getTimeout());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

	@Test
	public void test9IncidenciaCorrecta() {
		try {
			// Rellenamos el formulario
			PO_LoginView.fillForm(driver, "pepe", "123456", "Entity");
			// Comprobamos que entramos en sesión
			SeleniumUtils.EsperaCargaPagina(driver, "text", "Bienvenidos al sistema de incidencias",
					PO_View.getTimeout());

			List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free",
					"//li[contains(@id,'formSendIncidence')]/a", PO_View.getTimeout());
			elementos.get(0).click();

			PO_AddIncidencia.fillForm(driver, "Titulo-incidencia", "Descripcion-incidencia", "Fuego", "Clave:Valor",
					"45.356", "-32.145");
			// Al dejar campos vacíos seguimos en el envío de la incidencia mirando como
			// aparecen los inputs
			SeleniumUtils.EsperaCargaPagina(driver, "text", "La Incidencia ha sido enviada correctamente",
					PO_View.getTimeout());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

	@Test
	public void test10ListaIncidencia() {
		try {
			// Rellenamos el formulario
			PO_LoginView.fillForm(driver, "pepe", "123456", "Entity");
			// Comprobamos que entramos en sesión
			SeleniumUtils.EsperaCargaPagina(driver, "text", "Bienvenidos al sistema de incidencias",
					PO_View.getTimeout());

			List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free",
					"//li[contains(@id,'formSendIncidence')]/a", PO_View.getTimeout());
			elementos.get(0).click();

			PO_AddIncidencia.fillForm(driver, "Titulo-incidencia2", "Descripcion-incidencia2", "Fuego", "Clave:Valor",
					"45.356", "-32.145");
			// Al dejar campos vacíos seguimos en el envío de la incidencia mirando como
			// aparecen los inputs
			SeleniumUtils.EsperaCargaPagina(driver, "text", "La Incidencia ha sido enviada correctamente",
					PO_View.getTimeout());

			elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//li[contains(@id,'list')]/a",
					PO_View.getTimeout());
			elementos.get(0).click();
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

}
