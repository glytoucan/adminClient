package org.glytoucan.admin.client;

import static org.junit.Assert.assertNotNull;

import java.math.BigInteger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.glytoucan.admin.client.Application;
import org.glytoucan.admin.client.UserClient;
import org.glytoucan.admin.model.Authentication;
import org.glytoucan.admin.model.UserKeyRequest;
import org.glytoucan.admin.model.UserKeyResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringApplicationConfiguration(classes = { Application.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserClientTest {

  private static final Log logger = LogFactory.getLog(UserClientTest.class);
  
  @Autowired
  UserClient userClient;

  String token="ya29.CjBXA4l-rJxxG7g2PpaTzo3061sa6KIlLzF6y-SX39VRQjKVRGaWcqoZkvxVb48FX6U";

  
  
  @Test
  public void testGetKey() {
    UserKeyRequest request = new UserKeyRequest();
    Authentication auth = new Authentication();
    auth.setId("1");
    auth.setApiKey(token);
    request.setAuthentication(auth);
    request.setEmail("glytoucan@gmail.com");
    
    UserKeyResponse result = userClient.getKey(request);
    assertNotNull(result);
    logger.debug(result);
    logger.debug(result.getKey());
    Assert.assertEquals("0",result.getResponseMessage().getErrorCode());
    Assert.assertNotNull(result.getKey());
  }
  
//  @Test
//  public void testDetailRequestG97036DW() throws Exception {
//    
//    GlycoSequenceDetailResponse response = glycoSequenceClient.detailRequest("G97036DW");
//    Assert.assertNotNull(response);
//    
//    logger.debug(response);
//    logger.debug(response.getDescription());
//    Assert.assertEquals(new BigInteger("0"),response.getResponseMessage().getErrorCode());
//    Assert.assertEquals("G97036DW", response.getAccessionNumber());
//    Assert.assertTrue(response.getDescription().contains("org.eurocarbdb.MolecularFramework.util.visitor.GlycoVisitorException"));
//  }
//  
//  @Test
//  public void testTextSearchWurcs() throws Exception {
//    
//    GlycoSequenceSearchResponse response = glycoSequenceClient.textSearchRequest("WURCS=2.0/4,7,6/[u2122h_2*NCC/3=O][a2122h-1b_1-5_2*NCC/3=O][a1122h-1b_1-5][a1122h-1a_1-5]/1-2-3-4-2-4-2/a4-b1_b4-c1_c3-d1_c6-f1_e1-d2|d4_g1-f2|f4");
//    Assert.assertNotNull(response);
//    
//    logger.debug(response);
//    logger.debug(response.getAccessionNumber());
//    Assert.assertEquals(new BigInteger("0"),response.getResponseMessage().getErrorCode());
//    Assert.assertEquals("G00030MO", response.getAccessionNumber());
//  }
//
//  
//  @Test
//  public void testTextSearchIupac() throws Exception {
//    
//    GlycoSequenceSearchResponse response = glycoSequenceClient.textSearchRequest("Glc");
//    Assert.assertNotNull(response);
//    
//    logger.debug(response);
//    logger.debug(response.getAccessionNumber());
//    Assert.assertEquals(new BigInteger("0"),response.getResponseMessage().getErrorCode());
//    Assert.assertEquals("G15021LG", response.getAccessionNumber());
//  }
//  
//  @Test
//  public void testGInvalid() throws Exception {
//    
//    GlycoSequenceDetailResponse response = glycoSequenceClient.detailRequest("GTESTING");
//    Assert.assertNotNull(response);
//    
//    logger.debug(response);
//    logger.debug(response.getDescription());
//    Assert.assertEquals(new BigInteger("-100"),response.getResponseMessage().getErrorCode());
//    Assert.assertEquals("GTESTING", response.getAccessionNumber());
//  }
//  
//  @Test
//  public void testCount() throws Exception {
//    
//    GlycoSequenceCountResponse response = glycoSequenceClient.countRequest();
//    Assert.assertNotNull(response);
//    
//    logger.debug(response);
//    Assert.assertEquals(new BigInteger("0"),response.getResponseMessage().getErrorCode());
//    Assert.assertEquals("59632", response.getCount());
//  }
}
