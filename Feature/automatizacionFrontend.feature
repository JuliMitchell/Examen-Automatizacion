Feature: Automatización de Frontend
	
	@TestReady
  Scenario Outline: Navegación entre categorías
    Given el usuario entra a MercadoLibre
    When accede a Categorias
    And accede a la seccion "<seccion>" 
    And accede a la categoria "<categoria>"
    Then valido que el titulo sea igual a "<categoria>"
    And imprimo cantidad de resultados

    Examples: 
      | seccion											| categoria 							|
      | Hogar y Electrodomésticos 	| Climatización 					|
			| Tecnología 									| Celulares y Smartphones	|
			| Belleza y Cuidado Personal 	| Perfumes Importados 		|
			| Herramientas e Industrias		| Industria Textil 				|
			| Juguetes y Bebés						| Cuarto del Bebé					|
	
	@TestReady
	Scenario: Validación título y precio
		Given el usuario entra a MercadoLibre
  	When accede a Categorias
  	And accede a la seccion Tecnología
    And accede a la categoria Celulares y Smartphones
    And filtra por ubicación Capital Federal
    Then accede al primer artículo
    And Verifico que el titulo y el precio sean correctos
     