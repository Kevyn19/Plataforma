appPlataforma.controller("mainController", function($http,$scope, $location, $routeParams,$localStorage,$routeParams){
	$scope.$location = $location;
	$scope.total = 0;
	
		$http({method : 'GET',url : 'http://localhost:8080/usuario/perfil'}).then(function(response) {
			$location.path("/Menu");
			
		}, function(response) {
			if(response.status != '401'){
				window.alert(response.data.message);
			}
			
			console.log(response.data);
			console.log(response.status);

		});
		
		
	
	
	
	setInterval(function(){
		$http({method : 'GET',url : 'http://localhost:8080/restrito/solicitacaoServico/prestador/count'}).then(function(response) {
			$scope.total = response.data;
		}, function(response) {
			if(response.status != '401'){
				window.alert(response.data.message);
			}
			
			console.log(response.data);
			console.log(response.status);
	
		});
	
	
	}, 10000);
	
	
	$scope.logout= function(){

		localStorage.removeItem("userToken");
		$location.path("/login");
	}
	
});