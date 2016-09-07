package org.glytoucan.admin.client;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.glytoucan.admin.model.UserKeyRequest;
import org.glytoucan.admin.model.UserKeyResponse;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class UserClient extends WebServiceGatewaySupport {
	private static final Log logger = LogFactory.getLog(UserClient.class);

	public UserKeyResponse getKey(UserKeyRequest req) {
		logger.debug("client querying :>" + req.getEmail() + "<");
		logger.debug("client accessiong default URI:>" + getDefaultUri() + "<");

		return (UserKeyResponse) getWebServiceTemplate().marshalSendAndReceive(req);
	}

//	public GlycoSequenceSearchResponse textSearchRequest(String sequence) {
//		logger.debug("client querying :>" + sequence + "<");
//		logger.debug("client accessiong default URI:>" + getDefaultUri() + "<");
//		GlycoSequenceTextSearchRequest request = new GlycoSequenceTextSearchRequest();
//		request.setSequence(sequence);
//
//		return (GlycoSequenceSearchResponse) getWebServiceTemplate().marshalSendAndReceive(request);
//	}
//
//	/**
//	 * 
//	 * Request the default Count (total of wurcs glycosequences)
//	 * 
//	 * @return GlycoSequenceCountResponse response
//	 */
//	public GlycoSequenceCountResponse countRequest() {
//		logger.debug("client querying total count");
//		logger.debug("client accessiong default URI:>" + getDefaultUri() + "<");
//		GlycoSequenceCountRequest request = new GlycoSequenceCountRequest();
//
//		return (GlycoSequenceCountResponse) getWebServiceTemplate().marshalSendAndReceive(request);
//	}
//
}
