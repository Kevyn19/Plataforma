//Criação do modulo principal
var appPlataforma = angular.module("appPlataforma", ['ngRoute','ngStorage']);


appPlataforma.config(function ($routeProvider, $locationProvider){
	$routeProvider
	.when("/clientes",{templateUrl:'view/cliente.html',controller:'clienteController'})
	.when("/clientes/:clienteId",{templateUrl:'view/cliente-detalhe.html',controller:'clienteDetalheController'})
	.when("/login",{templateUrl:'view/login.html',controller:'loginController'})
	
	.when("/Cadastro/Usuario",{templateUrl:'view/salvar-usuario.html',controller:'usuarioController'})
	.when("/Cadastro/dadosPessoais",{templateUrl:'view/salvar-dados-pessoais.html',controller:'dadosPessoaisController'})
	.when("/Cadastro/servicos",{templateUrl:'view/salvar-servicos.html',controller:'servicoController'})
	.when("/Cadastro/localServico",{templateUrl:'view/salvar-dados-local.html',controller:'dadosLocalServicoController'})
	.when("/Buscar/Usuario/Prestador",{templateUrl:'view/buscar-usuario-geo.html',controller:'usuarioBuscarGeoController'})
	
	.when("/Menu",{templateUrl:'view/menu.html',controller:'mainController'})
	
	.when("/Buscar/Servico/Prestador",{templateUrl:'view/buscar-solicitacao-prestador.html',controller:'solicitacaoServicoBuscarController'})

	
	.when("/perfil/:id/:distancia",{templateUrl:'view/buscar-usuario-perfil.html',controller:'perfilController'})
	.otherwise({redirectTo:'/'});
	
	$locationProvider.html5Mode(true);
	
});

appPlataforma.directive('fileModel', ['$parse', function ($parse) {
    return {
       restrict: 'A',
       link: function(scope, element, attrs) {
          var model = $parse(attrs.fileModel);
          var modelSetter = model.assign;
          
          element.bind('change', function(){
             scope.$apply(function(){
                modelSetter(scope, element[0].files[0]);
             });
          });
       }
    };
}]);

appPlataforma.config (function($httpProvider){
	$httpProvider.interceptors.push("tokenInterceptor");
});
