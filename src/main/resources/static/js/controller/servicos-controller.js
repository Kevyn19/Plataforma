appPlataforma.controller("servicoController", function($scope, $http,
		$rootScope,$location, $localStorage) {

	$scope.servico = [];
	$scope.aux = [];
	$scope.frmServicos;
	$scope.servico.diariaOuMeia = 1;

	carregarServico = function() {
		$http({method : 'GET', url : 'http://localhost:8080/restrito/usuarioServico/'}).then(function(response) {
			$scope.servico = response.data;
			//Valida a integridade dos campos servico e servicosCompac
			if($scope.servico != null && $scope.servico.servicosCompac != null){
				//Processo para ticar os checkboxs do formulario - opções cadastradas
				var array =  $scope.servico.servicosCompac.split(',');
				for (var int = 0; int < array.length; int++) {

					switch (array[int].trim()) {
					
					
					case $scope.frmServicos.varrerOuAspirar.$name:
						
						 $scope.aux.varrerOuAspirarCh = true;
						 break;
					case $scope.frmServicos.limparJanela.$name:
						
						 $scope.aux.limparJanelaCh = true;
						 break;
					case $scope.frmServicos.arrumarCama.$name:
						
						$scope.aux.arrumarCamaCh = true; 
						break;

					}

				}
			}
			

		}, function(response) {
			if(response.status != '401'){
				$location.path("/Menu");
				window.alert(response.data.message);
				
			}
			console.log(response.data);
			console.log(response.status);

		});
	}
	
	
	
	$scope.salvarServico = function(){
		if($scope.frmServicos.$valid){
			
		$scope.servico.servicos = insereServicos();
			
		$http({method:'POST',url:'http://localhost:8080/restrito/usuarioServico/',data:$scope.servico})
		   .then(function(response){
			   window.alert("Dados cadastrados com sucesso!");
			   $scope.limpaCampos();
			   $scope.frmServicos.$setPristine(true);
	
			   
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
	
	//Carrega a lista temp com os serviçoes pré cadastrados
	insereServicos = function(){
	   temp = [];
	   count = 0;
	   
	   if($scope.aux.varrerOuAspirarCh == true){
		   temp[count] = 'varrerOuAspirar';
		   count++;
	   }
	   if($scope.aux.limparJanelaCh == true){
		   temp[count] = 'limparJanela';
		   count++;
	   } 
	   if($scope.aux.arrumarCamaCh == true){
		   temp[count] = 'arrumarCama';
		   count++;
	   }
	   return temp;
	}
	
	//Retorna o formulario para o estado original
	$scope.cancelarAlteracao = function(){
		$scope.servico = [];

		$scope.aux = [];
		carregarServico();
		$scope.servico.diariaOuMeia = 1;
		$scope.frmServicos.$setPristine(true);
	}
	
	//Limpa os campos após um PST
	$scope.limpaCampos = function() {
		$scope.servico = [];

		$scope.aux = [];
	}
	
	$scope.ivertValor = function(nome) {
		
		if(nome == 'varrerOuAspirar'){
		  $scope.aux.varrerOuAspirarCh = !$scope.aux.varrerOuAspirarCh;
		}else if(nome == 'limparJanela'){
		  $scope.aux.limparJanelaCh = !$scope.aux.limparJanelaCh;
		}else if(nome == 'arrumarCama'){
		  $scope.aux.arrumarCamaCh = !$scope.aux.arrumarCamaCh;
		}
	}
	
	carregarServico();
	

});