appPlataforma.controller("loginController", function($scope, $http, $location,$localStorage){
	
	
	$scope.usuario = {};
	$scope.token = "";

	
	$scope.autenticar= function(){
		
		$http.post("http://localhost:8080/autenticar",$scope.usuario)
		.then(function(response){
			
			localStorage.setItem("userToken", response.data.token);
			
			$location.path("/Menu");
	
			
		},function(response){
			if(response.status != '401'){
				window.alert(response.data.message);
			}
			console.log("Falha "+response);
		});
	}
	
	
});