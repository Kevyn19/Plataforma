appPlataforma.controller("clienteDetalheController", function($scope,$routeParams, $http){
	
	$scope.cliente={};
	
	$http.get("http://localhost:8080/restrito/clientes/"+$routeParams.clienteId).then(function(response){
		$scope.cliente = response.data;
		
	},function(response){
		console.log(response);
	});
	
});