var app = angular.module('app', [])
	.constant('API_URL', '/api/convert/');

app.controller("mainController", function ($scope, myService) {
	
	getAvailableConversions();
	
	function getAvailableConversions() {
		var getData = myService.getAvailableConversions();

		getData.then(function (conversion) {
			$scope.conversions = conversion.data; 
		}, function (conversion) {
			alert("Records gathering failed!");
		});
	}
	
	$scope.convert = function() {
		//selectedValue + selectedConversion
		//http://localhost:8080/api/convert/distance/12?from=kilometer&to=yard
		
		var getData = myService.convert($scope.selectedValue, $scope.selectedConversion);
		getData.then(function (result) {
			$scope.result = result.data + " " + $scope.selectedConversion.to;
		}, function (result) {
			alert("Error converting!");
		});
	}
	
});

app.service("myService", function ($http, API_URL) {
	this.getAvailableConversions = function () {
		return $http.get(API_URL + "conversions");
	};
	
	this.convert = function (value, conversion) {
		return $http.get(API_URL + conversion.category + "/" + value + "?from=" + conversion.from + "&to=" + conversion.to);
	};
});
