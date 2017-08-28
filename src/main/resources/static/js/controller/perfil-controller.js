appPlataforma.controller("perfilController", function($scope, $http,$rootScope,$location,$localStorage,$routeParams){
	
	$scope.servicos = [];
	
	$scope.avaliacoes = [];
	
	$scope.agenda = {};
	
	$scope.dadosPessoais = {};
	
	$scope.aux = [];
	
	$scope.mostraOpcoes = false;
	
	$scope.mostraImagem = false;
	
	$scope.dias = [];
	
	$scope.frmPerfil;
	
	
		$http({method : 'GET',url : 'http://localhost:8080/restrito/dadosPessoais/'+ $routeParams.id}).then(function(response) {
			$scope.dadosPessoais = response.data;
			if($scope.dadosPessoais.url != null){
				$scope.mostraImagem = true;
			}else{
				$scope.mostraImagem = false;
			}
			
		}, function(response) {
			if(response.status != '401'){
				window.alert(response.data.message);
			}
			
			console.log(response.data);
			console.log(response.status);
	
		});

		$http({method : 'GET',url : 'http://localhost:8080/restrito/usuarioServico/'+ $routeParams.id}).then(function(response) {
			$scope.servicos = response.data;
		}, function(response) {
			if(response.status != '401'){
				window.alert(response.data.message);
			}
			
			console.log(response.data);
			console.log(response.status);

		});
		

		$http({method : 'GET',url : 'http://localhost:8080/restrito/usuarioServico/diasDoMes/'}).then(function(response) {
			$scope.dias = response.data;
			console.log($scope.dias);
		}, function(response) {
			if(response.status != '401'){
				window.alert(response.data.message);
			}
			
			console.log(response.data);
			console.log(response.status);

		});
		
		$http({method : 'GET',url : 'http://localhost:8080/restrito/usuarioServico/valor/'+$routeParams.id+'/'+1}).then(function(response) {
			$scope.valorDiaria = response.data;
			console.log($scope.dias);
		}, function(response) {
			if(response.status != '401'){
				window.alert(response.data.message);
			}
			
			console.log(response.data);
			console.log(response.status);

		});
		
		$http({method : 'GET',url : 'http://localhost:8080/restrito/usuarioServico/valor/'+$routeParams.id+'/'+2}).then(function(response) {
			$scope.valorMeia = response.data;
			console.log($scope.dias);
		}, function(response) {
			if(response.status != '401'){
				window.alert(response.data.message);
			}
			
			console.log(response.data);
			console.log(response.status);

		});
		
		$scope.abreOpcoes = function(param) {
			if($scope.mostraOpcoes == false){
				$scope.mostraOpcoes = true;
				
			}else{
				$scope.mostraOpcoes = false;
			}
			$scope.aux.diaEscolha = param;
			
		}
		
		$scope.cadastrarSolicitacao = function(dia) {
			$http({method:'POST',url:'http://localhost:8080/restrito/solicitacaoServico/'+ $routeParams.id +'/' + dia + '/' + $scope.agenda.manhaOuTardeDiaria + '/' + $routeParams.distancia})
			   .then(function(response){
				   window.alert("Dados cadastrados com sucesso!");
				   
			   }, function(response){
				   if(response.status != '401'){
						window.alert(response.data.message);
					}
				   console.log(response.data);
				   console.log(response.status);
				  
				   
			   });
			
		}
		

		
	
	
	
});
