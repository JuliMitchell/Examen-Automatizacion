package stepDefinitions;

import com.cucumber.listener.Reporter;

import static org.junit.Assert.*;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AutomationBackendSteps {
	
	private JsonPath busquedaRespuestaJson = null;
	
	private String busquedaTotalProductos = null;
	private String busquedaLimitePaginado = null;
	private String busquedaCantProductosDevueltos = null;
	
	private String busquedaProductoSeleccionadoID = null;
	private String busquedaProductoSeleccionadoTitulo = null;
	private String busquedaProductoSeleccionadoPrecio = null;
	private String busquedaProductoSeleccionadoMercadoPago = null;
	private String busquedaProductoSeleccionadoMoneda = null;
	private String busquedaProductoSeleccionadoEnvioGratis = null;
	
	private JsonPath productoRespuestaJson = null;
	
	
	
	@Given("^envio una petición de búsqueda con el valor huawei p9 lite$")
	public void envio_una_petición_de_búsqueda_con_el_valor_huawei_p9_lite(){
		RestAssured.baseURI = "https://api.mercadolibre.com";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET, "/sites/MLA/search?q=huawei+p9+lite");
		busquedaRespuestaJson = response.jsonPath();	 
	}

	@Then("^imprimo informacion obtenida de la búsqueda$")
	public void imprimo_informacion_obtenida_de_la_búsqueda(){
		busquedaTotalProductos = busquedaRespuestaJson.getString("paging.total");
		busquedaLimitePaginado = busquedaRespuestaJson.getString("paging.limit");
		busquedaCantProductosDevueltos = busquedaRespuestaJson.getString("results.size");
		
		Reporter.addStepLog("Total de productos encontrados: " + busquedaTotalProductos);
		Reporter.addStepLog("Límite del páginado: " + busquedaLimitePaginado);
		Reporter.addStepLog("Cantidad de productos devueltos: " + busquedaCantProductosDevueltos);
	}
	
	@Then("^verifico que la cantidad de productos devueltos no exceda el límite de paginado$")
	public void verifico_que_la_cantidad_de_productos_devueltos_no_exceda_el_límite_de_paginado(){
		assertEquals(busquedaLimitePaginado, busquedaCantProductosDevueltos);
	}
	
	@Then("^imprimo producto seleccionado$")
	public void imprimo_producto_seleccionado(){
		busquedaProductoSeleccionadoID = busquedaRespuestaJson.getString("results[9].id");
		busquedaProductoSeleccionadoTitulo = busquedaRespuestaJson.getString("results[9].title");
		busquedaProductoSeleccionadoPrecio = busquedaRespuestaJson.getString("results[9].price");
		busquedaProductoSeleccionadoMercadoPago = busquedaRespuestaJson.getString("results[9].accepts_mercadopago");
		busquedaProductoSeleccionadoMoneda = busquedaRespuestaJson.getString("results[9].currency_id");
		busquedaProductoSeleccionadoEnvioGratis = busquedaRespuestaJson.getString("results[9].shipping.free_shipping");
		
		Reporter.addStepLog("Datos del producto seleccionado");
		Reporter.addStepLog("ID: " + busquedaProductoSeleccionadoID);
		Reporter.addStepLog("Título: " + busquedaProductoSeleccionadoTitulo);
		Reporter.addStepLog("Precio: " + busquedaProductoSeleccionadoPrecio);
		Reporter.addStepLog("Mercado Pago: " + busquedaProductoSeleccionadoMercadoPago);
		Reporter.addStepLog("Moneda: " + busquedaProductoSeleccionadoMoneda);
		Reporter.addStepLog("Envio gratis: " + busquedaProductoSeleccionadoEnvioGratis);
	}
	
	@Then("^envio una petición sobre el producto seleccionado$")
	public void envio_una_petición_sobre_el_producto_seleccionado() {
			RestAssured.baseURI = "https://api.mercadolibre.com";
			RequestSpecification httpRequest = RestAssured.given();
			Response response = httpRequest.request(Method.GET, "items/" + busquedaProductoSeleccionadoID);
			productoRespuestaJson = response.jsonPath();
	}
	
	@Then("^verifico que el ID coincida$")
	public void verifico_que_el_ID_coincida(){
		assertEquals(busquedaProductoSeleccionadoID, productoRespuestaJson.getString("id"));
	}

	@Then("^verifico que el Titulo coincida$")
	public void verifico_que_el_Titulo_coincida() {
		assertEquals(busquedaProductoSeleccionadoTitulo, productoRespuestaJson.getString("title"));
	}

	@Then("^verifico que el Precio coincida$")
	public void verifico_que_el_Precio_coincida() {
		assertEquals(busquedaProductoSeleccionadoPrecio, productoRespuestaJson.getString("price"));
	}

	@Then("^verifico que Acepta Mercado Pago coincida$")
	public void verifico_que_Acepta_Mercado_Pago_coincida() {
		assertEquals(busquedaProductoSeleccionadoMercadoPago, productoRespuestaJson.getString("accepts_mercadopago"));
	}

	@Then("^verifico que la Moneda coincida$")
	public void verifico_que_la_Moneda_coincida() {
		assertEquals(busquedaProductoSeleccionadoMoneda, productoRespuestaJson.getString("currency_id"));
	}

	@Then("^verifico que Envio Gratis coincida$")
	public void verifico_que_Envio_Gratis_coincida() {
		assertEquals(busquedaProductoSeleccionadoEnvioGratis, productoRespuestaJson.getString("shipping.free_shipping"));
	}

}
