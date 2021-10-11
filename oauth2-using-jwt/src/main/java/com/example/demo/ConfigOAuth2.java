package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@SuppressWarnings("deprecation")
@Configuration 
public class ConfigOAuth2 extends AuthorizationServerConfigurerAdapter{
	
	   private String clientId = "skpdemo";
	   private String clientSecret = "skpdemo-secret-key";
	   private String privateKey = "-----BEGIN RSA PRIVATE KEY-----\n" + 
	   		"MIIEowIBAAKCAQEAtQqDs+3FtXYQXs1Jlybqt88r6/YVrI8hBcSjf9WkVNgoNmiG\n" + 
	   		"rsAa1q1i9JOdf1TJC9JN16VGZA6jOIkwpan3QF/RxbCYJvzWVZ0fY2gJI6AAAkmj\n" + 
	   		"UcSSy+mFZW7UN9RG4eyn2AcSNZ3KeeE5qmo7vLIfZ1ocbrcYsuihXzpDPV27Bw6c\n" + 
	   		"U7XipO+xAlc9Biy6Um4MfF6wOsBNAKtljtwIExwb9uS1ARkHrmB1UWHpZH8Cndu+\n" + 
	   		"n8zMYy6zE9NehMw/F1ylmLW9xoxectoduH4aNepLEuVJzQfQVl5rRrKRq2Lq/C8b\n" + 
	   		"bImXlj5/di+pDr1e4AvdWxvhpQJLLX7bdA2r0wIDAQABAoIBAQCtYYB4VQK/om5T\n" + 
	   		"HG+Z3p9zoWkC9g4T0I9josqipsxWpRz93mT9/uS1LCBU8vom8+AG/kWmfJZJROt0\n" + 
	   		"PY7lWT193UZc+Z791669F1j0LrJx982KY1OR/5xGxUIHvnhnb1jjDW5BKaR6zL8Y\n" + 
	   		"smYzmsHTGROSO253acqxrLC/LmEu5nPdUlwPlShR3XSkU3N8zruv6sd/HzTWSLW+\n" + 
	   		"IWJzSxX9dX5oIi6dumqi1lWQ7JDOQ9qqSaU6g/wl2RW2TsLxfezuKpcZkqQrefrL\n" + 
	   		"drAkD3ek7UB+mX0seTEUteEJCf/MTzRfEO20mphP6v4WJmvkcN1kaOl07QzN9XvL\n" + 
	   		"ivjDel5hAoGBAOasJ+1fufXPQfChfHyIEgxf4H+NxRSuXsw7f4yNL8Jg1lKVGNzD\n" + 
	   		"WvJze31Qlk3P73UU+iFPyQXrOMG3KsQduXWAhX0+je7OEObn7YYHlnRKgrxdMTRd\n" + 
	   		"fgTJfHCRnIDPuRWsFVEhq1ndJzCdhmaIsYCN7HWpVQjapR7NWAt1MHf9AoGBAMjr\n" + 
	   		"S/6UPgBqsYa/Bli6Q+OttaOaLLy6J5nFBytDsZ5NJ2A5gw1IKeQ7EeYbxZSV/mj6\n" + 
	   		"7Cfw8RmWI3riYAdOBrRUjeGkVUWB5T6lZcQryqx6AtKEEJuFeO9vjW1sUymVEnWI\n" + 
	   		"uaA+ArfIQCcdS/IP8DkkRJRbjr+ez0Yd2iWKuHQPAoGATcICw5XdTetLwx3spN4J\n" + 
	   		"l3pKZzUXFl2hPx9fY7XEP7X9CWGRO/nukoQzQI8F270Yb1Ne7hPDI+ei/koMdIfA\n" + 
	   		"mMd2OD36AmIceHn+K4wrP5BOecjAaXKKpHp3JEsxgoqDTbbwiKTc6jIOn+i6AZ5a\n" + 
	   		"l4dACnnGXHG376Yw48vhRtECgYBzT7/cy13ORW2/xPCyiayfBjpiPAEDC9/ge9kC\n" + 
	   		"dqaBEQzRSBCz1b/dMh2ysCu0fGV4ANL6lWj6Y1XXmAa/CoQEiSao4vThoIxeaPTA\n" + 
	   		"ItX+KURnZ85UQ8VFvgMwUuRj0B+4/xByLETJD4/qIwFMWuNnGC6X6vhBd7bEKivi\n" + 
	   		"Mfxs9wKBgGIiHwDOfJFuAi/80GGg4Wv874aQAnzVtmNRrJvXKczlPYaM2BC/Tkqv\n" + 
	   		"TOFJ42xzXoHEI7Lbcj5Baev0ly72XeoFpExbX87FhdfAo/N3c7vxgTxyM/5NBmrY\n" + 
	   		"H9GQ3qCDo5XxFGd17nvtlXXAJc4uRfraecgHC2MQ9ACm0DSskvQA\n" + 
	   		"-----END RSA PRIVATE KEY-----";
	   private String publicKey = "-----BEGIN PUBLIC KEY-----\n" + 
	   		"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtQqDs+3FtXYQXs1Jlybq\n" + 
	   		"t88r6/YVrI8hBcSjf9WkVNgoNmiGrsAa1q1i9JOdf1TJC9JN16VGZA6jOIkwpan3\n" + 
	   		"QF/RxbCYJvzWVZ0fY2gJI6AAAkmjUcSSy+mFZW7UN9RG4eyn2AcSNZ3KeeE5qmo7\n" + 
	   		"vLIfZ1ocbrcYsuihXzpDPV27Bw6cU7XipO+xAlc9Biy6Um4MfF6wOsBNAKtljtwI\n" + 
	   		"Exwb9uS1ARkHrmB1UWHpZH8Cndu+n8zMYy6zE9NehMw/F1ylmLW9xoxectoduH4a\n" + 
	   		"NepLEuVJzQfQVl5rRrKRq2Lq/C8bbImXlj5/di+pDr1e4AvdWxvhpQJLLX7bdA2r\n" + 
	   		"0wIDAQAB\n" + 
	   		"-----END PUBLIC KEY-----";
	   
	   @Autowired
	   @Qualifier("authenticationManagerBean")
	   private AuthenticationManager authenticationManager;
	   
	   @Autowired
	   PasswordEncoder passwordEncoder;
	   
	   @Bean
	   public JwtAccessTokenConverter tokenEnhancer() {
	      JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
	      converter.setSigningKey(privateKey);
	      converter.setVerifierKey(publicKey);
	      return converter;
	   }
	   
	   @Bean
	   public JwtTokenStore tokenStore() {
	      return new JwtTokenStore(tokenEnhancer());
	   }
	   
	   @Override
	   public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
	      endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
	      .accessTokenConverter(tokenEnhancer());
	   }
	   @Override
	   public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
	      security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	   }
	   @Override
	   public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
	      clients.inMemory().withClient(clientId).secret(passwordEncoder.encode(clientSecret)).scopes("read", "write")
	         .authorizedGrantTypes("password", "refresh_token").accessTokenValiditySeconds(20000)
	         .refreshTokenValiditySeconds(20000);

	   }

}
