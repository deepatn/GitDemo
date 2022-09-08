Feature: Validating Place API's

@addPlace @Regression
Scenario Outline: Verify if place is successfully added using AddPlace API

Given Add place payload with "<name>" "<language>" "<address>"
When user call "AddPlaceAPI" with "Post" http request
Then the API call is success with status code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"
And verify place_Id created maps to "<name>" using "GetPlaceAPI"

Examples: 
	|name| 		|language| |address|
	|AAhouse|   |English|  |World cross center|
#	|BBhouse|   |kannada|  |Bengaluru|

@DeletePlace @Regression
Scenario: Verify if place is successfully deleted using DeletePlace API

Given Delete place payload
When user call "DeletePlaceAPI" with "Post" http request
Then the API call is success with status code 200
And "status" in response body is "OK"