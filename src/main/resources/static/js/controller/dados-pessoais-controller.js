appPlataforma.controller("dadosPessoaisController", function($scope, $http,$rootScope,$localStorage){
	
	$scope.dadosPessoais=[];
	
	$scope.aux=[];
	$scope.aux.prefix = 1;
	$scope.frmDadosPessoais;
	
	
	carregarDadosPessoais= function(){
		$http({method:'GET',url:'http://localhost:8080/restrito/dadosPessoais/'})
		   .then(function(response){
			   $scope.dadosPessoais = response.data;
			   $scope.aux.url = $scope.dadosPessoais.url;
			  
		   }, function(response){
			   if(response.status != '401'){
				   $location.path("/Menu");
					window.alert(response.data.message);
					
				}
			   console.log(response.data);
			   console.log(response.status);
			   
		   });
	}
	
	$scope.salvarDadosPessoais = function(){
		if($scope.frmDadosPessoais.$valid){
		$http({method:'POST',url:'http://localhost:8080/restrito/dadosPessoais/',data:$scope.dadosPessoais})
		   .then(function(response){
			   window.alert("Dados cadastrados com sucesso!");
			   $scope.limpaCampos();
			   $scope.frmDadosPessoais.$setPristine(true);
			   $scope.upload = true;
			   
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
		$scope.dadosPessoais=[];
		carregarDadosPessoais();
		$scope.frmDadosPessoais.$setPristine(true);
	}
	
	//Limpa os campos após um PST
	$scope.limpaCampos = function() {
		$scope.dadosPessoais=[];
	}

	
	carregarDadosPessoais();
	
	
	//------------------------------------------------- upload teste ----------------------------------
	
	$scope.temFoto = function() {
		
		if($scope.aux.url != null){
			return true;
		}else{
			return false;
		}
		
		
	}
	
	 $scope.doUploadFile = function(){
	       var file = $scope.uploadedFile;
	       var url = "http://localhost:8080/restrito/dadosPessoais/uploadFile";
	       
	       var data = new FormData();
	       data.append('uploadfile', file);
	    
	       var config = {
	    	   	transformRequest: angular.identity,
	    	   	transformResponse: angular.identity,
		   		headers : {
		   			'Content-Type': undefined
		   	    }
	       }
	       
	       $http.post(url, data, config).then(function (response) {
				$scope.uploadResult=response.data;
			}, function (response) {
				$scope.uploadResult=response.data;
			});
	    };
	
});
