package selenium;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import selenium.pageobjects.PO_AddIncidencia;
import selenium.pageobjects.PO_LoginView;
import selenium.pageobjects.PO_View;
import selenium.util.SeleniumUtils;

import org.junit.runners.MethodSorters;

//Ordenamos las pruebas por el nombre del método
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InciManagerTests {

	// En Windows (Debe ser la versión 46.0 y desactivar las actualizacioens
	// automáticas)):
	static String PathFirefox = "D:\\Chus\\Firefox46.win\\FirefoxPortable.exe";
	// Común a Windows y a MACOSX
	static WebDriver driver = getDriver(PathFirefox);
	static String URL = "http://localhost:8070";

	public static WebDriver getDriver(String PathFirefox) {
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

	@Before
	public void setUp() {
		// Antes de cada prueba se navega al URL home de la aplicaciónn
		driver.navigate().to(URL);
	}

	// Después de cada prueba se borran las cookies del navegador
	@After
	public void tearDown() {
		driver.manage().deleteAllCookies();
		driver.navigate().to(URL + "/logout");
	}

	@BeforeClass
	static public void begin() {
	}

	@AfterClass
	static public void end() {
		driver.quit();
	}

	@Test
	public void test1IntentoDeRellenarFormularioSinIniciarSesion() {
		driver.navigate().to(URL + "/formSendIncidence");
		// Comprobamos que nos vamos a la pantalla de login
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Sistema de participacion ciudadana", PO_View.getTimeout());
	}

	@Test
	public void test2IntentoDeListarIncidencias() {
		driver.navigate().to(URL + "/list");
		// Comprobamos que nos vamos a la pantalla de login
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Sistema de participacion ciudadana", PO_View.getTimeout());
	}

	@Test
	public void test3InicioSesionInvalido() {
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "juan", "123", "Person");
		// Comprobamos que nos vamos a la pantalla de error
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Ha ocurrido un error", PO_View.getTimeout());
	}

	@Test
	public void test4InicioSesionValido() {
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "juan", "1234", "Person");
		// Comprobamos que entramos en sesión
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Bienvenidos al sistema de incidencias", PO_View.getTimeout());
	}

	@Test
	public void test5FormularioIncorrectoCamposVacios() {
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "juan", "1234", "Person");
		// Comprobamos que entramos en sesión
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Bienvenidos al sistema de incidencias", PO_View.getTimeout());

		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free",
				"//li[contains(@id,'formSendIncidence')]/a", PO_View.getTimeout());
		elementos.get(0).click();

		PO_AddIncidencia.fillForm(driver, "", "", "", "", "", "");
		// Al dejar campos vacíos seguimos en el envío de la incidencia mirando como
		// aparecen los inputs
		SeleniumUtils.EsperaCargaPagina(driver, "class", "col-sm-10", PO_View.getTimeout());
	}

	@Test
	public void test6TituloYDescripcionPequeña() {
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "juan", "1234", "Person");
		// Comprobamos que entramos en sesión
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Bienvenidos al sistema de incidencias", PO_View.getTimeout());

		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free",
				"//li[contains(@id,'formSendIncidence')]/a", PO_View.getTimeout());
		elementos.get(0).click();

		PO_AddIncidencia.fillForm(driver, "hola", "hola", "fuego,incendio", "fuego:extremo", "45.356", "-32.145");
		// Al dejar campos vacíos seguimos en el envío de la incidencia mirando como
		// aparecen los inputs
		SeleniumUtils.EsperaCargaPagina(driver, "class", "text-danger", PO_View.getTimeout());
	}

	@Test
	public void test7ValorProhibido() {
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "juan", "1234", "Person");
		// Comprobamos que entramos en sesión
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Bienvenidos al sistema de incidencias", PO_View.getTimeout());

		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free",
				"//li[contains(@id,'formSendIncidence')]/a", PO_View.getTimeout());
		elementos.get(0).click();

		PO_AddIncidencia.fillForm(driver, "@@@@@", "@@@@@", "@@@@@", "@@@@@:$$$$$", "45.356", "-32.145");
		// Al dejar campos vacíos seguimos en el envío de la incidencia mirando como
		// aparecen los inputs
		SeleniumUtils.EsperaCargaPagina(driver, "class", "text-danger", PO_View.getTimeout());
	}

	@Test
	public void test8CampoIncorrecto() {
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "juan", "1234", "Person");
		// Comprobamos que entramos en sesión
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Bienvenidos al sistema de incidencias", PO_View.getTimeout());

		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free",
				"//li[contains(@id,'formSendIncidence')]/a", PO_View.getTimeout());
		elementos.get(0).click();

		PO_AddIncidencia.fillForm(driver, "Titulo incidencia", "Descripcion incorrecta", "Fuego", "NoHayDosPuntos",
				"45.356", "-32.145");
		// Al dejar campos vacíos seguimos en el envío de la incidencia mirando como
		// aparecen los inputs
		SeleniumUtils.EsperaCargaPagina(driver, "class", "text-danger", PO_View.getTimeout());
	}

	@Test
	public void test9IncidenciaCorrecta() {
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "juan", "1234", "Person");
		// Comprobamos que entramos en sesión
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Bienvenidos al sistema de incidencias", PO_View.getTimeout());

		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free",
				"//li[contains(@id,'formSendIncidence')]/a", PO_View.getTimeout());
		elementos.get(0).click();

		PO_AddIncidencia.fillForm(driver, "Titulo-incidencia", "Descripcion-incidencia", "Fuego", "Clave:Valor",
				"45.356", "-32.145");
		// Al dejar campos vacíos seguimos en el envío de la incidencia mirando como
		// aparecen los inputs
		SeleniumUtils.EsperaCargaPagina(driver, "text", "La Incidencia ha sido enviada correctamente",
				PO_View.getTimeout());
	}
	
	@Test
	public void test10ListaIncidencia() {
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "juan", "1234", "Person");
		// Comprobamos que entramos en sesión
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Bienvenidos al sistema de incidencias", PO_View.getTimeout());

		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free",
				"//li[contains(@id,'formSendIncidence')]/a", PO_View.getTimeout());
		elementos.get(0).click();

		PO_AddIncidencia.fillForm(driver, "Titulo-incidencia2", "Descripcion-incidencia2", "Fuego", "Clave:Valor",
				"45.356", "-32.145");
		// Al dejar campos vacíos seguimos en el envío de la incidencia mirando como
		// aparecen los inputs
		SeleniumUtils.EsperaCargaPagina(driver, "text", "La Incidencia ha sido enviada correctamente",
				PO_View.getTimeout());
		
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free",
				"//li[contains(@id,'list')]/a", PO_View.getTimeout());
		elementos.get(0).click();
		 List<WebElement> incidencias = SeleniumUtils.EsperaCargaPagina(driver, "free",
		 "//tbody/tr", PO_View.getTimeout());
		 // Comprobamos que solo hay un usuario con ese email
		assertEquals("Titulo-incidencia2", incidencias.get(0).getText().split(" ")[0]);
		assertEquals("Descripcion-incidencia2", incidencias.get(0).getText().split(" ")[1]);
		assertEquals("ABIERTA", incidencias.get(0).getText().split(" ")[2]);
		assertEquals("Titulo-incidencia", incidencias.get(1).getText().split(" ")[0]);
		assertEquals("Descripcion-incidencia", incidencias.get(1).getText().split(" ")[1]);
		assertEquals("ABIERTA", incidencias.get(1).getText().split(" ")[2]);
	}

	// * 3.1 [LisUsrVal] Acceso al listado de usuarios desde un usuario en sesión.

	// /**
	// * 4.1 [BusUsrVal] Realizar una búsqueda valida en el listado de usuarios
	// desde
	// * un usuario en sesión.
	// */
	// @Test
	// public void PR041_BusUsrVal() {
	// // Vamos al formulario de logueo.
	// PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
	// // Rellenamos el formulario
	// PO_LoginView.fillForm(driver, "pedro@mail.es", "123456");
	// // Comprobamos que entramos en la lista de usuarios de la aplicación
	// SeleniumUtils.EsperaCargaPagina(driver, "text", "Usuario",
	// PO_View.getTimeout());
	// // Escribimos el email del usuario utilizado para identificarnos
	// SeleniumUtils.esperarSegundos(driver, 1);
	// PO_ListUserView.fillForm(driver, "pedro@mail.es");
	
	// // Clickamos el botón de desconectar
	// PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
	// }
	//
	// /**
	// * 4.2 [BusUsrInVal] Intento de acceso con URL a la búsqueda de usuarios desde
	// * un usuario no identificado. Debe producirse un acceso no permitido a vistas
	// * privadas.
	// */
	// @Test
	// public void PR042_BusUsrInVal() {
	// // La busqueda se hace en la lista de usuarios por lo que es el mismo test
	// que
	// // el PR32.
	// PR032_LisUsrInVal();
	// }
	//
	// /**
	// * 5.1 [InvVal] Enviar una invitación de amistad a un usuario de forma valida.
	// */
	// @Test
	// public void PR051_InvVal() {
	// // Vamos al formulario de logueo.
	// PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
	// // Rellenamos el formulario
	// PO_LoginView.fillForm(driver, "marta@mail.es", "123456");
	// // Comprobamos que entramos en la lista de usuarios
	// SeleniumUtils.EsperaCargaPagina(driver, "text", "Usuario",
	// PO_View.getTimeout());
	// // Realizamos una búsqueda para asegurarnos que está en primera página
	// SeleniumUtils.esperarSegundos(driver, 1);
	// PO_ListUserView.fillForm(driver, "pedro@mail.es");
	// // Buscamos a Pedro y le enviamos solicitud de amistad
	// List<WebElement> usuarios = SeleniumUtils.EsperaCargaPagina(driver, "id",
	// "pedro@mail.es",
	// PO_View.getTimeout());
	// usuarios.get(0).click();
	// // Buscamos a Pedro que ahora tiene solicitud de amistad
	// usuarios = SeleniumUtils.EsperaCargaPagina(driver, "id", "pedro@mail.es",
	// PO_View.getTimeout());
	// // Comprobamos como la petición de amistad a Pedro ya existe
	// assertTrue(usuarios.get(0).getText().equals("Petición pendiente"));
	// // Clickamos el botón de desconectar
	// PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
	// }
	//
	// /**
	// * 5.2 [InvInVal] Enviar una invitación de amistad a un usuario al que ya le
	// * habíamos invitado la invitación previamente. No debería dejarnos enviar la
	// * invitación, se podría ocultar el botón de enviar invitación o notificar que
	// * ya había sido enviada previamente.
	// */
	// @Test
	// public void PR052_InvInVal() {
	// // Vamos al formulario de logueo.
	// PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
	// // Rellenamos el formulario
	// PO_LoginView.fillForm(driver, "marta@mail.es", "123456");
	// // Comprobamos que entramos en la lista de usuarios
	// SeleniumUtils.EsperaCargaPagina(driver, "text", "Usuario",
	// PO_View.getTimeout());
	// // Realizamos una búsqueda para asegurarnos que está en primera página
	// SeleniumUtils.esperarSegundos(driver, 1);
	// PO_ListUserView.fillForm(driver, "pelayo@mail.es");
	// // Buscamos a Pelayo que ya tiene solicitud de amistad
	// List<WebElement> usuarios = SeleniumUtils.EsperaCargaPagina(driver, "id",
	// "pelayo@mail.es",
	// PO_View.getTimeout());
	// // Comprobamos como la petición de amistad a Pelayo ya existe
	// assertTrue(usuarios.get(0).getText().equals("Petición pendiente"));
	// // Clickamos el botón de desconectar
	// PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
	// }
	//
	// /**
	// * 6.1 [LisInvVal] Listar las invitaciones recibidas por un usuario, realizar
	// la
	// * comprobación con una lista que al menos tenga una invitación recibida.
	// */
	// @Test
	// public void PR061_LisInvVal() {
	// // Vamos al formulario de logueo.
	// PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
	// // Rellenamos el formulario
	// PO_LoginView.fillForm(driver, "pedro@mail.es", "123456");
	// // Vamos a la opción de peticiones recibidas
	// List<WebElement> elementos = PO_View.checkElement(driver, "free",
	// "//li[contains(@id,'requests-menu')]/a");
	// elementos.get(0).click();
	// // Esperamos a aparezca la opción de ver peticiones: //a[contains(@href,
	// // 'request/list')]
	// elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,
	// 'request/list')]");
	// // Pinchamos en ver peticiones.
	// elementos.get(0).click();
	// // Listamos todas las peticiones por defecto
	// List<WebElement> usuarios = SeleniumUtils.EsperaCargaPagina(driver, "free",
	// "//tbody/tr", PO_View.getTimeout());
	// // Comprobamos que solo hay 1 petición
	// assertTrue(usuarios.size() == 1);
	// // Comprobamos que la petición es de Lucas
	// assertTrue(usuarios.get(0).getText().split(" ")[2].equals("lucas@mail.es"));
	// // Clickamos el botón de desconectar
	// PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
	// }
	//
	// /**
	// * 7.1 [AcepInvVal] Aceptar una invitación recibida.
	// */
	// @Test
	// public void PR071_AcepInvVal() {
	// // Vamos al formulario de logueo.
	// PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
	// // Rellenamos el formulario
	// PO_LoginView.fillForm(driver, "pedro@mail.es", "123456");
	// // Vamos a la opción de peticiones recibidas
	// List<WebElement> elementos = PO_View.checkElement(driver, "free",
	// "//li[contains(@id,'requests-menu')]/a");
	// elementos.get(0).click();
	// // Esperamos a aparezca la opción de ver peticiones: //a[contains(@href,
	// // 'request/list')]
	// elementos = SeleniumUtils.EsperaCargaPagina(driver, "free",
	// "//a[contains(@href, 'request/list')]",
	// PO_View.getTimeout());
	// // Pinchamos en ver peticiones.
	// elementos.get(0).click();
	// // Listamos las peticiones
	// List<WebElement> usuarios = SeleniumUtils.EsperaCargaPagina(driver, "free",
	// "//tbody/tr", PO_View.getTimeout());
	// // Comprobamos que solo hay 1 petición
	// assertTrue(usuarios.size() == 1);
	// // Buscamos la petición de Lucas
	// usuarios = SeleniumUtils.EsperaCargaPagina(driver, "id", "lucas@mail.es",
	// PO_View.getTimeout());
	// // Clickamos en la petición
	// usuarios.get(0).click();
	// // Ya no se encuentra la petición
	// SeleniumUtils.textoNoPresentePagina(driver, "lucas@mail.es");
	// // Clickamos el botón de desconectar
	// PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
	// }
	//
	// /**
	// * 8.1 [ListAmiVal] Listar los amigos de un usuario, realizar la comprobación
	// * con una lista que al menos tenga un amigo.
	// */
	// @Test
	// public void PR081_ListAmiVal() {
	// // Vamos al formulario de logueo.
	// PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
	// // Rellenamos el formulario
	// PO_LoginView.fillForm(driver, "pedro@mail.es", "123456");
	// // Vamos a la opción de peticiones recibidas
	// List<WebElement> elementos = PO_View.checkElement(driver, "free",
	// "//li[contains(@id,'requests-menu')]/a");
	// elementos.get(0).click();
	// // Esperamos a aparezca la opción de ver peticiones: //a[contains(@href,
	// // 'user/friends')]
	// elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,
	// 'user/friends')]");
	// // Pinchamos en ver peticiones.
	// elementos.get(0).click();
	// // Listamos los amigos de Pedro
	// List<WebElement> usuarios = SeleniumUtils.EsperaCargaPagina(driver, "free",
	// "//tbody/tr", PO_View.getTimeout());
	// // Comprobamos que Pedro tiene 2 amigos
	// assertTrue(usuarios.size() == 2);
	// List<String> amigos = new ArrayList<String>();
	// // Añadimos los email de todos los amigos de Pedro
	// for (int i = 0; i < usuarios.size(); i++)
	// amigos.add(usuarios.get(i).getText().split(" ")[0]);
	// // Comprobamos que contiene los email de Pelayo y María (Este test se realiza
	// de
	// // esta manera ya que como estos usuarios se introducen con el DataSample, no
	// se
	// // introducirán siempre en el mismo orden por lo que no se puede saber cual
	// irá
	// // primero
	// assertTrue(amigos.contains("pelayo@mail.es"));
	// assertTrue(amigos.contains("mary@mail.es"));
	// // Clickamos el botón de desconectar
	// PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
	// }
	//
	// /**
	// * 9.1 [PubVal] Crear una publicación con datos válidos.
	// */
	// @Test
	// public void PR091_PubVal() {
	// // Vamos al formulario de logueo.
	// PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
	// // Rellenamos el formulario
	// PO_LoginView.fillForm(driver, "pedro@mail.es", "123456");
	// // Vamos a la opción de publicaciones
	// List<WebElement> elementos = PO_View.checkElement(driver, "free",
	// "//li[contains(@id,'post-menu')]/a");
	// elementos.get(0).click();
	// // Esperamos a aparezca la opción de ver peticiones: //a[contains(@href,
	// // '/post/add')]
	// elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,
	// '/post/add')]");
	// // Pinchamos en ver añadir publicación
	// elementos.get(0).click();
	// // Rellenamos la nueva publicación
	// SeleniumUtils.esperarSegundos(driver, 2);
	// PO_AddPost.fillForm(driver, "Publicación de Prueba", "Esto es una publicación
	// hecha con Selenium");
	// // Accedemos a la página de publicaciones
	// SeleniumUtils.EsperaCargaPagina(driver, "text", "Tus publicaciones",
	// PO_View.getTimeout());
	// // Clickamos el botón de desconectar
	// PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
	// }
	//
	// /**
	// * 10.1 [LisPubVal] Acceso al listado de publicaciones desde un usuario en
	// * sesión.
	// */
	// @Test
	// public void PR101_LisPubVal() {
	// // Vamos al formulario de logueo.
	// PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
	// // Rellenamos el formulario
	// PO_LoginView.fillForm(driver, "pedro@mail.es", "123456");
	// // Vamos a la opción de publicaciones
	// List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free",
	// "//li[contains(@id,'post-menu')]/a", PO_View.getTimeout());
	// elementos.get(0).click();
	// // Esperamos a aparezca la opción de ver peticiones: //a[contains(@href,
	// // '/post/list')]
	// elementos = SeleniumUtils.EsperaCargaPagina(driver, "free",
	// "//a[contains(@href, '/post/list')]",
	// PO_View.getTimeout());
	// // Pinchamos en ver publicaciones
	// elementos.get(0).click();
	// // Listamos las publicaciones de Pedro
	// List<WebElement> posts = SeleniumUtils.EsperaCargaPagina(driver, "class",
	// "list-group-item",
	// PO_View.getTimeout());
	// // Comprobamos que hay 2 publicaciones
	// assertTrue(posts.size() == 2);
	// // Clickamos el botón de desconectar
	// PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
	// }
	//
	// /**
	// * 11.1 [LisPubAmiVal] Listar las publicaciones de un usuario amigo
	// */
	// @Test
	// public void PR111_LisPubAmiVal() {
	// // Vamos al formulario de logueo.
	// PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
	// // Rellenamos el formulario
	// PO_LoginView.fillForm(driver, "pedro@mail.es", "123456");
	// // Vamos a la opción de peticiones recibidas
	// List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free",
	// "//li[contains(@id,'requests-menu')]/a", PO_View.getTimeout());
	// elementos.get(0).click();
	// // Esperamos a aparezca la opción de ver peticiones: //a[contains(@href,
	// // 'user/friends')]
	// elementos = SeleniumUtils.EsperaCargaPagina(driver, "free",
	// "//a[contains(@href, 'user/friends')]",
	// PO_View.getTimeout());
	// // Pinchamos en ver peticiones.
	// elementos.get(0).click();
	// // Buscamos a Pelayo en los amigos de Pedro
	// List<WebElement> usuarios = SeleniumUtils.EsperaCargaPagina(driver, "id",
	// "pelayo@mail.es",
	// PO_View.getTimeout());
	// // Clickamos en Pelayo y vemos sus publicaciones
	// usuarios.get(0).click();
	// // Vemos las publicaciones de Pelayo
	// SeleniumUtils.EsperaCargaPagina(driver, "text", "Publicaciones de Pelayo",
	// PO_View.getTimeout());
	// // Clickamos el botón de desconectar
	// PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
	// }
	//
	// /**
	// * 11.2 [LisPubAmiInVal] Utilizando un acceso vía URL tratar de listar las
	// * publicaciones de un usuario que no sea amigo del usuario identificado en
	// * sesión.
	// */
	// @Test
	// public void PR112_LisPubAmiInVal() {
	// // Vamos al formulario de logueo.
	// PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
	// // Rellenamos el formulario
	// PO_LoginView.fillForm(driver, "pedro@mail.es", "123456");
	// // Intentamos acceder directamente desde la URL (Utilizamos el id 0 porque
	// como
	// // el id varía al introducir usuarios con el InsertDataSample nos aseguramos
	// que
	// // nunca habrá un id 0)
	// driver.navigate().to("http://localhost:8090/post/friend/0");
	// /// Comprobamos que nos sale un mensaje de error
	// SeleniumUtils.EsperaCargaPagina(driver, "class", "alert alert-danger",
	// PO_View.getTimeout());
	// }
	//
	// /**
	// * 12.1 [PubFot1Val] Crear una publicación con datos válidos y una foto
	// adjunta.
	// *
	// * Información: Es posible que las pruebas fallen a partir de esta prueba al
	// no
	// * cerrarse bien el navegador por tener el cuadro de diálogo de abrir archivo
	// * tapando la aplicación. Reiniciar a partir de esta en este caso.
	// */
	// @Test
	// public void PR121_PubFot1Val() {
	// // Vamos al formulario de logueo.
	// PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
	// // Rellenamos el formulario
	// PO_LoginView.fillForm(driver, "pedro@mail.es", "123456");
	// // Vamos a la opción de publicaciones
	// List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free",
	// "//li[contains(@id,'post-menu')]/a", PO_View.getTimeout());
	// elementos.get(0).click();
	// // Esperamos a aparezca la opción de ver peticiones: //a[contains(@href,
	// // '/post/add')]
	// elementos = SeleniumUtils.EsperaCargaPagina(driver, "free",
	// "//a[contains(@href, '/post/add')]",
	// PO_View.getTimeout());
	// // Pinchamos en ver añadir publicación
	// elementos.get(0).click();
	// // Rellenamos la nueva publicación
	// SeleniumUtils.esperarSegundos(driver, 2);
	// PO_AddPost.fillForm(driver, "Publicación de Prueba", "Esto es una publicación
	// hecha con Selenium",
	// "img/prueba.jpg");
	// SeleniumUtils.EsperaCargaPagina(driver, "text", "Tus publicaciones",
	// PO_View.getTimeout());
	// }
	//
	// /**
	// * 12.2 [PubFot2Val] Crear una publicación con datos válidos y sin una foto
	// * adjunta.
	// */
	// @Test
	// public void PR122_PubFot2Val() {
	// // Es la misma prueba que la hecha en el caso de uso 9 ya que no incluye
	// imagen
	// PR091_PubVal();
	// }
	//
	// /**
	// * 13.1 [AdInVal] Inicio de sesión como administrador con datos válidos.
	// */
	// @Test
	// public void PR131_AdInVal() {
	// // Accedemos por URL al login de admin
	// driver.navigate().to(URL + "/admin/login");
	// // Rellenamos el formulario
	// PO_LoginView.fillForm(driver, "admin@admin.es", "123456");
	// // Comprobamos que entramos en la página con las listas de usuarios
	// SeleniumUtils.EsperaCargaPagina(driver, "text", "Usuario",
	// PO_View.getTimeout());
	// // Clickamos el botón de desconectar
	// PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
	// }
	//
	// /**
	// * 13.2 [AdInInVal] Inicio de sesión como administrador con datos inválidos
	// * (usar los datos de un usuario que no tenga perfil administrador).
	// */
	// @Test
	// public void PR132_AdInInVal() {
	// // Accedemos por URL al login de admin
	// driver.navigate().to(URL + "/admin/login");
	// // Rellenamos el formulario con los datos de Pedro
	// PO_LoginView.fillForm(driver, "pedro@mail.es", "123456");
	// // Comprobamos que nos sale un mensaje de error
	// SeleniumUtils.EsperaCargaPagina(driver, "class", "alert alert-danger",
	// PO_View.getTimeout());
	// }
	//
	// /**
	// * 14.1 [AdLisUsrVal] Desde un usuario identificado en sesión como
	// administrador
	// * listar a todos los usuarios de la aplicación.
	// */
	// @Test
	// public void PR141_AdLisUsrVal() {
	// // Accedemos por URL al login de admin
	// driver.navigate().to(URL + "/admin/login");
	// // Rellenamos el formulario con los datos de Pedro
	// PO_LoginView.fillForm(driver, "admin@admin.es", "123456");
	// // Comprobamos que entramos en la página con las listas de usuarios
	// SeleniumUtils.EsperaCargaPagina(driver, "text", "Usuario",
	// PO_View.getTimeout());
	// // Pinchamos en la opción de menu de administración: //li[contains(@id,
	// // 'admin-menu')]/a
	// List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free",
	// "//li[contains(@id,'admin-menu')]/a", PO_View.getTimeout());
	// elementos.get(0).click();
	// // Esperamos a aparezca la opción de ver usuarios: //a[contains(@href,
	// // 'admin/list')]
	// elementos = SeleniumUtils.EsperaCargaPagina(driver, "free",
	// "//a[contains(@href, 'admin/list')]",
	// PO_View.getTimeout());
	// // Pinchamos en ver usuarios.
	// elementos.get(0).click();
	// // Comprobamos que estamos en la lista de Usuarios de administrador
	// SeleniumUtils.EsperaCargaPagina(driver, "text", "Usuarios (vista de
	// administrador)", PO_View.getTimeout());
	// // Clickamos el botón de desconectar
	// PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
	// }
	//
	// /**
	// * 15.1 [AdBorUsrVal] Desde un usuario identificado en sesión como
	// administrador
	// * eliminar un usuario existente en la aplicación.
	// */
	// @Test
	// public void PR151_AdBorUsrVal() {
	// // Accedemos por URL al login de admin
	// driver.navigate().to(URL + "/admin/login");
	// // Rellenamos el formulario con los datos de Pedro
	// PO_LoginView.fillForm(driver, "admin@admin.es", "123456");
	// // Comprobamos que entramos en la página con las listas de usuarios
	// PO_View.checkElement(driver, "text", "Usuario");
	// // Pinchamos en la opción de menu de administración: //li[contains(@id,
	// // 'admin-menu')]/a
	// List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free",
	// "//li[contains(@id,'admin-menu')]/a", PO_View.getTimeout());
	// elementos.get(0).click();
	// // Esperamos a aparezca la opción de ver usuarios: //a[contains(@href,
	// // 'admin/list')]
	// elementos = SeleniumUtils.EsperaCargaPagina(driver, "free",
	// "//a[contains(@href, 'admin/list')]",
	// PO_View.getTimeout());
	// // Pinchamos en ver usuarios.
	// elementos.get(0).click();
	// // Buscamos al usuario Pedro en la aplicación
	// List<WebElement> usuarios = SeleniumUtils.EsperaCargaPagina(driver, "id",
	// "pedro@mail.es",
	// PO_View.getTimeout());
	// // Eliminamos a Pedro
	// usuarios.get(0).click();
	// // Comprobamos que el usuario Pedro ya no sale en la lista
	// SeleniumUtils.textoNoPresentePagina(driver, "pedro@mail.es");
	// // Clickamos el botón de desconectar
	// PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
	// }
	//
	// /**
	// * 15.2 [AdBorUsrInVal] Intento de acceso vía URL al borrado de un usuario
	// * existente en la aplicación. Debe utilizarse un usuario identificado en
	// sesión
	// * pero que no tenga perfil de administrador.
	// */
	// @Test
	// public void PR152_AdBorUsrInVal() {
	// // Vamos al formulario de logueo.
	// PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
	// // Rellenamos el formulario
	// PO_LoginView.fillForm(driver, "pedro@mail.es", "123456");
	// // Comprobamos que entramos en la página con las listas de usuarios
	// SeleniumUtils.EsperaCargaPagina(driver, "text", "Usuario",
	// PO_View.getTimeout());
	// // Intentamos eliminar por URL a un usuario de la aplicación
	// driver.navigate().to(URL + "/admin/delete/800");
	// // Accdemos a la página de error de la aplicación
	// SeleniumUtils.EsperaCargaPagina(driver, "text", "Whitelabel Error Page",
	// PO_View.getTimeout());
	// }
	//
	// /**
	// * Source: https://stackoverflow.com/questions/45841500/generate-random-emails
	// */
	// private String getSaltString() {
	// String saltchars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	// saltchars = saltchars.toLowerCase();
	// StringBuilder salt = new StringBuilder();
	// Random rnd = new Random();
	// while (salt.length() < 15) {
	// int index = (int) (rnd.nextFloat() * saltchars.length());
	// salt.append(saltchars.charAt(index));
	// }
	// String saltStr = salt.toString();
	// return saltStr;
	// }
}