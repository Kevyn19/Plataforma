appPlataforma.controller("usuarioBuscarGeoController", function($scope, $http,$rootScope,$location,$localStorage){
	
	$scope.usuarios = [];

	$scope.frmUsuarioGeo;
	
	$scope.distancia = 10;
	
	$scope.valor = 170;
	
	//Busca usuario de perfil prestador em um raio de 'x' km (representado pela variavel distancia)
	carregarUsuarioGeo = function() {
		$http({method : 'GET',url : 'http://localhost:8080/usuarios/geo/'+ $scope.distancia +'/'+$scope.valor}).then(function(response) {
			$scope.usuarios = response.data;
			console.log($scope.usuarios);
		}, function(response) {
			if(response.status != '401' && response.status != '404'){
				window.alert(response.data.message);
				$location.path("/Menu");
			}else if(response.status == '404'){
				window.alert("Endereço ainda não cadastrado!");
				$location.path("/Cadastro/dadosPessoais");
			}
			
			console.log(response.data);
			console.log(response.status);

		});
	}
	
	//Retorna o formulario para o estado original
	$scope.cancelarAlteracao = function(){
		$scope.frmUsuarioGeo=[];
		$scope.frmUsuarioGeo.$setPristine(true);
		carregarUsuarioGeo();
	}
	
	$scope.buscarUsuario = function() {
		carregarUsuarioGeo();
	}
	
	carregarUsuarioGeo();
	
	
});