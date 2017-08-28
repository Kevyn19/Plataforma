appPlataforma.controller("usuarioController", function($scope, $http,$rootScope,$localStorage){
	
	$scope.usuario={};
	
	$scope.aux=[];
	$scope.aux.idPerfil = 1;
	
	$scope.salvarUsuario = function(){
		if($scope.frmUsuario.$valid){
		$http({method:'POST',url:'http://localhost:8080/usuarios/'+$scope.aux.idPerfil,data:$scope.usuario})
		   .then(function(response){
			   window.alert("Dados cadastrados com sucesso!");
			   $scope.cancelarAlteracao();
			   $scope.frmUsuario.$setPristine(true);
			  			   
		   }, function(response){
			   if(response.status != '401'){
					window.alert(response.data.message);
					$scope.cancelarAlteracao();
				}
			   console.log(response.data);
			   console.log(response.status);
			   
		   });
		}else{
			window.alert("Dados inválidos!");
			$scope.cancelarAlteracao();
		}
	}
	
	//Retorna o formulario para o estado original
	$scope.cancelarAlteracao = function(){
		$scope.usuario={};
		$scope.aux.idPerfil = 1;
		$scope.frmUsuario.$setPristine(true);
	}
	
	
});
