appPlataforma.controller("dadosLocalServicoController", function($scope, $http,
		$rootScope, $localStorage, $location ) {

	$scope.localServico = [];

	$scope.frmLocalServico;

	carregarLocalServico = function() {
		$http({method : 'GET',url : 'http://localhost:8080/restrito/dadosLocalServico/'}).then(function(response) {
			$scope.localServico = response.data;

		}, function(response) {
			if(response.status != '401'){
				$location.path("/Menu");
				window.alert(response.data.message);
				
			}
			console.log(response.data);
			console.log(response.status);

		});
	}
	
	$scope.salvarLocalServico = function(){
		if($scope.frmLocalServico.$valid){
		$http({method:'POST',url:'http://localhost:8080/restrito/dadosLocalServico/',data:$scope.localServico})
		   .then(function(response){
			   window.alert("Dados cadastrados com sucesso!");
			   $scope.limpaCampos();
			   $scope.frmLocalServico.$setPristine(true);
			   
			   
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
	$scope.cancelarAlteracao = function() {
		$scope.localServico = [];
		carregarLocalServico();
		$scope.frmLocalServico.$setPristine(true);
	}
	
	//Limpa os campos após um PST
	$scope.limpaCampos = function() {
		$scope.localServico = [];
	}

	carregarLocalServico();

});