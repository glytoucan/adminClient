package org.glytoucan.admin.client;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.glytoucan.admin.model.ResponseMessage;
import org.glytoucan.admin.model.User;
import org.glytoucan.admin.model.UserDetailsRequest;
import org.glytoucan.admin.model.UserDetailsResponse;
import org.glytoucan.admin.model.UserGenerateKeyRequest;
import org.glytoucan.admin.model.UserGenerateKeyResponse;
import org.glytoucan.admin.model.UserKeyCheckRequest;
import org.glytoucan.admin.model.UserKeyCheckResponse;
import org.glytoucan.admin.model.UserKeyRequest;
import org.glytoucan.admin.model.UserKeyResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

public class UserClient extends WebServiceGatewaySupport {
	private static final Log logger = LogFactory.getLog(UserClient.class);

	public UserKeyResponse getKey(UserKeyRequest req) {
		logger.debug("client querying :>" + req.getPrimaryId() + "<");
		logger.debug("client accessiong default URI:>" + getDefaultUri() + "<");

		return (UserKeyResponse) getWebServiceTemplate().marshalSendAndReceive(req);
	}

  public UserDetailsResponse userDetailsRequest(UserDetailsRequest request) {
    Assert.notNull(request);
    Assert.notNull(request.getAuthentication());
    Assert.notNull(request.getPrimaryId());
    return (UserDetailsResponse) getWebServiceTemplate().marshalSendAndReceive(request);
  }
 
  public UserKeyCheckResponse userKeyCheckRequest(UserKeyCheckRequest request) {
    Assert.notNull(request);
    Assert.notNull(request.getAuthentication());
    Assert.notNull(request.getAuthentication().getId());
    Assert.notNull(request.getAuthentication().getApiKey());
    Assert.notNull(request.getPrimaryId());
    Assert.notNull(request.getApiKey());

    return (UserKeyCheckResponse) getWebServiceTemplate().marshalSendAndReceive(request);
  }

  public UserGenerateKeyResponse generateKey(UserGenerateKeyRequest request) {
    Assert.notNull(request);
    Assert.notNull(request.getAuthentication());
    Assert.notNull(request.getPrimaryId());

    return (UserGenerateKeyResponse) getWebServiceTemplate().marshalSendAndReceive(request);
  }
}
