package org.glytoucan.admin.client;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.math.BigInteger;

import javax.xml.transform.stream.StreamResult;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.glytoucan.admin.client.Application;
import org.glytoucan.admin.client.UserClient;
import org.glytoucan.admin.model.Authentication;
import org.glytoucan.admin.model.User;
import org.glytoucan.admin.model.UserDetailsRequest;
import org.glytoucan.admin.model.UserDetailsResponse;
import org.glytoucan.admin.model.UserGenerateKeyRequest;
import org.glytoucan.admin.model.UserGenerateKeyResponse;
import org.glytoucan.admin.model.UserKeyCheckRequest;
import org.glytoucan.admin.model.UserKeyCheckResponse;
import org.glytoucan.admin.model.UserKeyRequest;
import org.glytoucan.admin.model.UserKeyResponse;
import org.glytoucan.admin.model.UserRegisterRequest;
import org.glytoucan.admin.model.UserRegisterResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.ClassUtils;
import org.springframework.ws.client.core.WebServiceTemplate;

@SpringApplicationConfiguration(classes = { Application.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class UserClientTest {

  private static final Log logger = LogFactory.getLog(UserClientTest.class);

  @Autowired
  UserClient userClient;

  String adminEmail = "815e7cbca52763e5c3fbb5a4dccc176479a50e2367f920843c4c35dca112e33d";
  String token = "b83f8b8040a584579ab9bf784ef6275fe47b5694b1adeb82e076289bf17c2632";

  @Test
  public void testGetKey() {
    UserKeyRequest request = new UserKeyRequest();
    Authentication auth = new Authentication();
    auth.setId(adminEmail);
    auth.setApiKey(token);
    request.setAuthentication(auth);
    request.setPrimaryId("glytoucan@gmail.com");

    UserKeyResponse result = userClient.getKey(request);
    assertNotNull(result);
    logger.debug(result);
    logger.debug(result.getKey());
    Assert.assertEquals("0", result.getResponseMessage().getErrorCode());
    Assert.assertNotNull(result.getKey());
  }

  @Test
  public void testUserDetailsRequest() {
    UserDetailsRequest request = new UserDetailsRequest();
    Authentication auth = new Authentication();
    auth.setId(adminEmail);
    auth.setApiKey(token);
    request.setAuthentication(auth);
    request.setPrimaryId("glytoucan@gmail.com");

    UserDetailsResponse result = userClient.userDetailsRequest(request);
    assertNotNull(result);
    logger.debug(result);
    logger.debug(result.getUser());
    Assert.assertEquals("0", result.getResponseMessage().getErrorCode());
    Assert.assertNotNull(result.getUser());
    Assert.assertEquals("glytoucan@gmail.com", result.getUser().getEmail());
    Assert.assertEquals("815e7cbca52763e5c3fbb5a4dccc176479a50e2367f920843c4c35dca112e33d", result.getUser().getExternalId());
    Assert.assertEquals("", result.getUser().getFamilyName());
  }

  @Test
  public void testUserKeyCheckRequest() {
    UserKeyCheckRequest request = new UserKeyCheckRequest();
    Authentication auth = new Authentication();
    auth.setId(adminEmail);
    auth.setApiKey(token);
    request.setAuthentication(auth);
    request.setContributorId("815e7cbca52763e5c3fbb5a4dccc176479a50e2367f920843c4c35dca112e33d");
    request.setApiKey(token);
    UserKeyCheckResponse result = userClient.userKeyCheckRequest(request);

    assertNotNull(result);
    logger.debug(result);
    logger.debug(result.getResponseMessage());
    logger.debug(result.getResponseMessage().getTime());
    Assert.assertEquals("0", result.getResponseMessage().getErrorCode());
    Assert.assertTrue(result.isResult());
  }

  // @Test
  public void testGenerateHash() {
    UserGenerateKeyRequest request = new UserGenerateKeyRequest();
    Authentication auth = new Authentication();
    auth.setId(adminEmail);
    auth.setApiKey(token);
    request.setAuthentication(auth);
    request.setPrimaryId("glytoucan@gmail.com");

    UserGenerateKeyResponse result = userClient.generateKey(request);

    assertNotNull(result);
    logger.debug(result);
    logger.debug(result.getResponseMessage());
    logger.debug(result.getResponseMessage().getTime());
    Assert.assertEquals("0", result.getResponseMessage().getErrorCode());
    Assert.assertNotNull(result.getKey());
  }
  
  public void testGenerateHash2() {
	    UserGenerateKeyRequest request = new UserGenerateKeyRequest();
	    Authentication auth = new Authentication();
	    auth.setId(adminEmail);
	    auth.setApiKey(token);
	    request.setAuthentication(auth);
	    request.setPrimaryId("glycodm@gmail.com");

	    UserGenerateKeyResponse result = userClient.generateKey(request);

	    assertNotNull(result);
	    logger.debug(result);
	    logger.debug(result.getResponseMessage());
	    logger.debug(result.getResponseMessage().getTime());
	    Assert.assertEquals("0", result.getResponseMessage().getErrorCode());
	    Assert.assertNotNull(result.getKey());
	  }

  @Test
  public void testRegisterUser() {
    UserRegisterRequest request = new UserRegisterRequest();
    Authentication auth = new Authentication();
    auth.setId(adminEmail);
    auth.setApiKey(token);
    request.setAuthentication(auth);
    User user = new User();
    user.setEmail("glytoucan@gmail.com");
    user.setEmailVerified("true");
    user.setExternalId("815e7cbca52763e5c3fbb5a4dccc176479a50e2367f920843c4c35dca112e33d");
    user.setGivenName("");
    user.setFamilyName("");
    request.setUser(user);

    UserRegisterResponse result = userClient.register(request);

    assertNotNull(result);
    logger.debug(result);
    logger.debug(result.getResponseMessage());
    logger.debug(result.getResponseMessage().getTime());
    Assert.assertEquals("0", result.getResponseMessage().getErrorCode());
    Assert.assertNotNull(result.getUser());
    Assert.assertEquals(user.getEmail(), result.getUser().getEmail());
    Assert.assertEquals(user.getGivenName(), result.getUser().getGivenName());
    Assert.assertEquals(user.getExternalId(), result.getUser().getExternalId());
    Assert.assertEquals(user.getEmailVerified(), result.getUser().getEmailVerified());
  }
}