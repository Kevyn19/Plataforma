appPlataforma.controller("clienteController", function($scope, $http){
	
	
	$scope.clientes = [];
	$scope.cliente={};
	
	carregarClientes = function(){
		
		$http({method:'GET',url:'http://localhost:8080/restrito/clientes'})
		   .then(function(response){
			   $scope.clientes = response.data;
			  
		   }, function(response){
			   
			   console.log(response.data);
			   console.log(response.status);
			   
		   });
	}
	
	
	$scope.salvarClientes = function(){
		if($scope.frmCliente.$valid){
		$http({method:'POST',url:'http://localhost:8080/restrito/clientes',data:$scope.cliente})
		   .then(function(response){
			   
			   carregarClientes();
			   $scope.cancelarAlteracaoCliente();
			   $scope.frmCliente.$setPristine(true);
			   
		   }, function(response){
			   
			   console.log(response.data);
			   console.log(response.status);
			   
		   });
		}else{
			window.alert("Dados inválidos!")
		}
	}
	
	$scope.excluirClientes = function(cliente){
		$http({method:'DELETE',url:'http://localhost:8080/restrito/clientes/'+cliente.id})
		   .then(function(response){
			  
			pos = $scope.clientes.indexOf(cliente);
			$scope.clientes.splice(pos,1);
			   
		   }, function(response){
		
			   console.log(response.data);
			   console.log(response.status);
			   
		   });
	}
	
	$scope.alterarClientes = function(cliente){
		$scope.cliente = angular.copy(cliente);
	}
	 
	$scope.cancelarAlteracaoCliente = function(){
		$scope.cliente={};
	}
	
	
	
	carregarClientes();
	
	
});
