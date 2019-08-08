package com.skp.casntemplt.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import cucumber.api.java.en.Given;



@Component
public class EmployeeStepDef{
	//private static final Logger LOGGER = LogManager.getLogger(EmployeeStepDef.class);
	
	@Autowired
	private Map<String, OAuth2AccessToken> employeeSessionMap;
	
	/*
	 * public EmployeeStepDef() {
	 * 
	 * 
	 * Given("I want to generate the access token with user {String} and {String}",
	 * (String userName, String password) -> { ResourceOwnerPasswordResourceDetails
	 * resource = new ResourceOwnerPasswordResourceDetails(); List<String> scopes =
	 * new ArrayList<>(); scopes.add("write"); scopes.add("read");
	 * resource.setAccessTokenUri("http://localhost:8089/oauth/token");
	 * resource.setClientId("skpAuth-client");
	 * resource.setClientSecret("skpAuth-secret");
	 * resource.setGrantType("password"); resource.setScope(scopes);
	 * 
	 * resource.setUsername(userName); resource.setPassword(password);
	 * AccessTokenRequest atr = new DefaultAccessTokenRequest();
	 * 
	 * OAuth2RestTemplate oAuthRestTemp = new OAuth2RestTemplate(resource, new
	 * DefaultOAuth2ClientContext(atr)); employeeSessionMap.put("accessToken",
	 * oAuthRestTemp.getAccessToken());
	 * 
	 * });
	 * 
	 * Then("I will call the {String} with generated access token", (String
	 * msRestApiName) -> { RestTemplate restTemp = new RestTemplate(); HttpHeaders
	 * headers = new HttpHeaders(); headers.set("access_token",
	 * employeeSessionMap.get("access_token").getValue());
	 * headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	 * HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
	 * ResponseEntity<Object[]> result =
	 * restTemp.exchange("http://localhost:8089/employee/list", HttpMethod.POST,
	 * entity, Object[].class); HttpStatus statusCode = result.getStatusCode();
	 * System.out.println("Status Code:-"+statusCode+"::"+"Response Object:-"+Arrays
	 * .toString(result.getBody())); }); }
	 */

	@Given("^I want to generate the access token with user {String} and {String}$")
	public void iWantToGenerateTheAccessTokenWithUserAlexaAndPassword(String userName, String password) throws Throwable {
		ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();
		List<String> scopes = new ArrayList<>();
        scopes.add("write");
        scopes.add("read");
        resource.setAccessTokenUri("http://localhost:8089/oauth/token");
        resource.setClientId("skpAuth-client");
        resource.setClientSecret("skpAuth-secret");
        resource.setGrantType("password");
        resource.setScope(scopes);

        resource.setUsername(userName);
        resource.setPassword(password);
        AccessTokenRequest atr = new DefaultAccessTokenRequest();

        OAuth2RestTemplate oAuthRestTemp =  new OAuth2RestTemplate(resource, new DefaultOAuth2ClientContext(atr));
        employeeSessionMap.put("accessToken", oAuthRestTemp.getAccessToken());
	}

}
