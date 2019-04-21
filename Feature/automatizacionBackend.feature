Feature: Automatización de Backend
	
	@TestReady
  Scenario: Validación de búsqueda y consulta de producto en API
  	Given envio una petición de búsqueda con el valor huawei p9 lite
  	And imprimo informacion obtenida de la búsqueda
  	Then verifico que la cantidad de productos devueltos no exceda el límite de paginado 
   	And imprimo producto seleccionado
   	And envio una petición sobre el producto seleccionado
   	Then verifico que el ID coincida
   	And verifico que el Titulo coincida
   	And verifico que el Precio coincida
   	And verifico que Acepta Mercado Pago coincida
   	And verifico que la Moneda coincida
   	And verifico que Envio Gratis coincida