appPlataforma.controller("solicitacaoServicoBuscarController", function($scope, $http,$rootScope,$location,$localStorage){
	
	$scope.solicitacoes = [];
	
	carregarSolicitacoes = function() {
		$http({method : 'GET',url : 'http://localhost:8080/restrito/solicitacaoServico/prestador'}).then(function(response) {
			$scope.solicitacoes = response.data;
			console.log($scope.usuarios);
		}, function(response){
		   if(response.status != '401'){
			   $location.path("/Menu");
				window.alert(response.data.message);
				
			}
		   console.log(response.data);
		   console.log(response.status);
		   
	   });
	
	}
	
	$scope.aceitarSolicitacaoServico = function(param) {
		$http({method : 'PUT',url : 'http://localhost:8080/restrito/solicitacaoServico/prestador/'+param}).then(function(response) {
			 window.alert("Dados cadastrados com sucesso!");
		}, function(response){
		   if(response.status != '401'){
			   $location.path("/Menu");
				window.alert(response.data.message);
				
			}
		   console.log(response.data);
		   console.log(response.status);
		   
	   });
		
		
	}
	
	carregarSolicitacoes();
	
});