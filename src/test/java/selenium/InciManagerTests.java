//package selenium;
//
//import static org.junit.Assert.assertEquals;
//
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//
//import org.junit.*;
//import org.openqa.selenium.*;
//import org.openqa.selenium.firefox.FirefoxDriver;
//
//import selenium.pageobjects.PO_AddIncidencia;
//import selenium.pageobjects.PO_LoginView;
//import selenium.pageobjects.PO_View;
//import selenium.util.SeleniumUtils;
//
//import org.junit.runners.MethodSorters;
//import org.openqa.selenium.htmlunit.HtmlUnitDriver;
//
////Ordenamos las pruebas por el nombre del método
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//public class InciManagerTests {
//
//	// En Windows (Debe ser la versión 46.0 y desactivar las actualizacioens
//	// automáticas)):
//	//static String PathFirefox = "C:\\Users\\Pelayo\\Desktop\\Firefox46.win\\FirefoxPortable.exe";
//	// Común a Windows y a MACOSX
//	static WebDriver driver = new HtmlUnitDriver();
//	static String URL = "http://localhost:8070";
//
//	public static WebDriver getDriver(String PathFirefox) {
//		System.setProperty("webdriver.firefox.bin", PathFirefox);
//		WebDriver driver = new FirefoxDriver();
//		return driver;
//	}
//
//	@Before
//	public void setUp() {
//		// Antes de cada prueba se navega al URL home de la aplicaciónn
//		driver.get(URL);
//		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
//	}
//
//	// Después de cada prueba se borran las cookies del navegador
//	@After
//	public void tearDown() {
//		driver.manage().deleteAllCookies();
//		driver.get(URL + "/logout");
//	}
//
//	@BeforeClass
//	static public void begin() {
//	}
//
//	@AfterClass
//	static public void end() {
//		driver.quit();
//	}
//
//	@Test
//	public void test1IntentoDeRellenarFormularioSinIniciarSesion() {
//		driver.get(URL + "/formSendIncidence");
//		// Comprobamos que nos vamos a la pantalla de login
//		SeleniumUtils.EsperaCargaPagina(driver, "text", "Sistema de participacion ciudadana", PO_View.getTimeout());
//	}
//
//	@Test
//	public void test2IntentoDeListarIncidencias() {
//		driver.get(URL + "/list");
//		// Comprobamos que nos vamos a la pantalla de login
//		SeleniumUtils.EsperaCargaPagina(driver, "text", "Sistema de participacion ciudadana", PO_View.getTimeout());
//	}
//
//	@Test
//	public void test3InicioSesionInvalido() {
//		// Rellenamos el formulario
//		PO_LoginView.fillForm(driver, "juan", "123", "Person");
//		// Comprobamos que nos vamos a la pantalla de error
//		SeleniumUtils.EsperaCargaPagina(driver, "text", "Ha ocurrido un error", PO_View.getTimeout());
//	}
//
//	@Test
//	public void test4InicioSesionValido() {
//		// Rellenamos el formulario
//		PO_LoginView.fillForm(driver, "juan", "1234", "Person");
//		// Comprobamos que entramos en sesión
//		SeleniumUtils.EsperaCargaPagina(driver, "text", "Bienvenidos al sistema de incidencias", PO_View.getTimeout());
//	}
//
//	@Test
//	public void test5FormularioIncorrectoCamposVacios() {
//		// Rellenamos el formulario
//		PO_LoginView.fillForm(driver, "juan", "1234", "Person");
//		// Comprobamos que entramos en sesión
//		SeleniumUtils.EsperaCargaPagina(driver, "text", "Bienvenidos al sistema de incidencias", PO_View.getTimeout());
//
//		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free",
//				"//li[contains(@id,'formSendIncidence')]/a", PO_View.getTimeout());
//		elementos.get(0).click();
//
//		PO_AddIncidencia.fillForm(driver, "", "", "", "", "", "");
//		// Al dejar campos vacíos seguimos en el envío de la incidencia mirando como
//		// aparecen los inputs
//		SeleniumUtils.EsperaCargaPagina(driver, "class", "col-sm-10", PO_View.getTimeout());
//	}
//
//	@Test
//	public void test6TituloYDescripcionPequeña() {
//		// Rellenamos el formulario
//		PO_LoginView.fillForm(driver, "juan", "1234", "Person");
//		// Comprobamos que entramos en sesión
//		SeleniumUtils.EsperaCargaPagina(driver, "text", "Bienvenidos al sistema de incidencias", PO_View.getTimeout());
//
//		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free",
//				"//li[contains(@id,'formSendIncidence')]/a", PO_View.getTimeout());
//		elementos.get(0).click();
//
//		PO_AddIncidencia.fillForm(driver, "hola", "hola", "fuego,incendio", "fuego:extremo", "45.356", "-32.145");
//		// Al dejar campos vacíos seguimos en el envío de la incidencia mirando como
//		// aparecen los inputs
//		SeleniumUtils.EsperaCargaPagina(driver, "class", "text-danger", PO_View.getTimeout());
//	}
//
//	@Test
//	public void test7ValorProhibido() {
//		// Rellenamos el formulario
//		PO_LoginView.fillForm(driver, "juan", "1234", "Person");
//		// Comprobamos que entramos en sesión
//		SeleniumUtils.EsperaCargaPagina(driver, "text", "Bienvenidos al sistema de incidencias", PO_View.getTimeout());
//
//		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free",
//				"//li[contains(@id,'formSendIncidence')]/a", PO_View.getTimeout());
//		elementos.get(0).click();
//
//		PO_AddIncidencia.fillForm(driver, "@@@@@", "@@@@@", "@@@@@", "@@@@@:$$$$$", "45.356", "-32.145");
//		// Al dejar campos vacíos seguimos en el envío de la incidencia mirando como
//		// aparecen los inputs
//		SeleniumUtils.EsperaCargaPagina(driver, "class", "text-danger", PO_View.getTimeout());
//	}
//
//	@Test
//	public void test8CampoIncorrecto() {
//		// Rellenamos el formulario
//		PO_LoginView.fillForm(driver, "juan", "1234", "Person");
//		// Comprobamos que entramos en sesión
//		SeleniumUtils.EsperaCargaPagina(driver, "text", "Bienvenidos al sistema de incidencias", PO_View.getTimeout());
//
//		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free",
//				"//li[contains(@id,'formSendIncidence')]/a", PO_View.getTimeout());
//		elementos.get(0).click();
//
//		PO_AddIncidencia.fillForm(driver, "Titulo incidencia", "Descripcion incorrecta", "Fuego", "NoHayDosPuntos",
//				"45.356", "-32.145");
//		// Al dejar campos vacíos seguimos en el envío de la incidencia mirando como
//		// aparecen los inputs
//		SeleniumUtils.EsperaCargaPagina(driver, "class", "text-danger", PO_View.getTimeout());
//	}
//
//	@Test
//	public void test9IncidenciaCorrecta() {
//		// Rellenamos el formulario
//		PO_LoginView.fillForm(driver, "juan", "1234", "Person");
//		// Comprobamos que entramos en sesión
//		SeleniumUtils.EsperaCargaPagina(driver, "text", "Bienvenidos al sistema de incidencias", PO_View.getTimeout());
//
//		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free",
//				"//li[contains(@id,'formSendIncidence')]/a", PO_View.getTimeout());
//		elementos.get(0).click();
//
//		PO_AddIncidencia.fillForm(driver, "Titulo-incidencia", "Descripcion-incidencia", "Fuego", "Clave:Valor",
//				"45.356", "-32.145");
//		// Al dejar campos vacíos seguimos en el envío de la incidencia mirando como
//		// aparecen los inputs
//		SeleniumUtils.EsperaCargaPagina(driver, "text", "La Incidencia ha sido enviada correctamente",
//				PO_View.getTimeout());
//	}
//	
//	@Test
//	public void test10ListaIncidencia() {
//		// Rellenamos el formulario
//		PO_LoginView.fillForm(driver, "juan", "1234", "Person");
//		// Comprobamos que entramos en sesión
//		SeleniumUtils.EsperaCargaPagina(driver, "text", "Bienvenidos al sistema de incidencias", PO_View.getTimeout());
//
//		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free",
//				"//li[contains(@id,'formSendIncidence')]/a", PO_View.getTimeout());
//		elementos.get(0).click();
//
//		PO_AddIncidencia.fillForm(driver, "Titulo-incidencia2", "Descripcion-incidencia2", "Fuego", "Clave:Valor",
//				"45.356", "-32.145");
//		// Al dejar campos vacíos seguimos en el envío de la incidencia mirando como
//		// aparecen los inputs
//		SeleniumUtils.EsperaCargaPagina(driver, "text", "La Incidencia ha sido enviada correctamente",
//				PO_View.getTimeout());
//		
//		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free",
//				"//li[contains(@id,'list')]/a", PO_View.getTimeout());
//		elementos.get(0).click();
//		// List<WebElement> incidencias = SeleniumUtils.EsperaCargaPagina(driver, "free",
//		// "//tbody/tr", PO_View.getTimeout());
//		 // Comprobamos que solo hay un usuario con ese email
//		//assertEquals("Titulo-incidencia2", incidencias.get(0).getText().split(" ")[0]);
//		//assertEquals("ABIERTA", incidencias.get(0).getText().split(" ")[1]);
//		//assertEquals("Titulo-incidencia" + "\n" + "Descripcion-incidencia", incidencias.get(1).getText().split(" ")[0]);
//		//assertEquals("ABIERTA", incidencias.get(1).getText().split(" ")[1]);
//	}
//
//}