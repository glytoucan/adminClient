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
import org.glytoucan.admin.model.UserDetailsRequest;
import org.glytoucan.admin.model.UserDetailsResponse;
import org.glytoucan.admin.model.UserGenerateKeyRequest;
import org.glytoucan.admin.model.UserGenerateKeyResponse;
import org.glytoucan.admin.model.UserKeyCheckRequest;
import org.glytoucan.admin.model.UserKeyCheckResponse;
import org.glytoucan.admin.model.UserKeyRequest;
import org.glytoucan.admin.model.UserKeyResponse;
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

  String token = "JDUkMjAxNjA5MDUwOTM5MjMkVWZzaHNyRVFkMVl4Umx0MjJiczVyZFZVNDQ5bUJBVTBoQTdaeGpiUkRpMw==";

  @Test
  public void testGetKey() {
    UserKeyRequest request = new UserKeyRequest();
    Authentication auth = new Authentication();
    auth.setId("1");
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
    auth.setId("1");
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
    Assert.assertEquals("1", result.getUser().getExternalId());
    Assert.assertEquals("Toucan", result.getUser().getFamilyName());
  }

  @Test
  public void testUserKeyCheckRequest() {
    UserKeyCheckRequest request = new UserKeyCheckRequest();
    Authentication auth = new Authentication();
    auth.setId("1");
    auth.setApiKey(token);
    request.setAuthentication(auth);
    request.setContributorId("1");
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
    auth.setId("1");
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

}
