********************************
* Incluir categorias de gastos *
********************************
Metodo: POST
Endpoint: http://localhost:8081/categoria/adicionar
Usuurio: admin
Senha: 1234

{"descricao":"Alimentacao"
}
{"descricao":"Moradia"
}
{"descricao":"Vestuario"
}
{"descricao":"Educacao"
}
{"descricao":"Servicos"
}
{"descricao":"Comunicacao"
}


********************************************************
* 1.) Incluir gastos - Integração de gastos por cartão *
********************************************************
Método: POST
Endpoint: http://localhost:8081/gasto/adicionar
Usuario: admin
Senha: 1234

{"descricao":"Almoço","valor":125.55,"codigoUsuario":1,"data":"2019-06-10","categoriaGasto": {"id":1, "descricao":"Alimentacao"}}
{"descricao":"Condominio","valor":300.78,"codigoUsuario":1,"data":"2019-06-11","categoriaGasto": {"id":2, "descricao":"Moradia"}}
{"descricao":"Bolsa","valor":456.96,"codigoUsuario":2,"data":"2019-06-09"}
{"descricao":"Jantar","valor":150.0,"codigoUsuario":1,"data":"2019-06-10","categoriaGasto": {"id":1, "descricao":"Alimentacao"}}
{"descricao":"Aluguel","valor":1200.00,"codigoUsuario":2,"data":"2019-06-15","categoriaGasto": {"id":1, "descricao":"Alimentacao"}}


**************************
* 2.) Listagem de gastos *
************************** 
Metodo: GET
Endpoint: http://localhost:8081/gasto/listar/{codigoUsuario}
Usuario: test
Senha: 1234

http://localhost:8081/gasto/listar/1
 

************************
* 3.) Filtro de gastos *
************************ 
Metodo: GET
Endpoint: http://localhost:8081/gasto/filtrar/{codigoUsuario}/{data}
Usuario: test
Senha: 1234

http://localhost:8081/gasto/filtrar/1/2019-06-09


*******************************
* 4.) Categorização de gastos *
*******************************
Metodo: PUT
Endpoint: http://localhost:8081/gasto/alterar
Usuario: test
Senha: 1234

{
"id": 3,
 "descricao": "Bolsa",
 "valor": 456.96,
 "codigoUsuario": 2,
 "data": "2019-06-09",
 "categoriaGasto": {
"id":3,
 "descricao":"Vestuario"
}
}


*****************************
* 5.) Sugestão de categoria *
*****************************
Método: GET
Endpoint: http://localhost:8081/categoria/listar/{descricaoCategoria}
Usuario: test
Senha: 1234

http://localhost:8081/categoria/listar/cao


******************************************
* 6.) Categorização automática de gastos *
******************************************
Metodo: POST
Endpoint: http://localhost:8081/gasto/adicionarCategoriaAuto
Usuario: admin
Senha: 1234

{"descricao":"Lanche","valor":25.3,"codigoUsuario":1,"data":"2019-06-15","categoriaGasto":{
"id":0,
 "descricao":"Alimentacao"
}}
