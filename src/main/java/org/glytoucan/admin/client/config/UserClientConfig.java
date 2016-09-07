package org.glytoucan.admin.client.config;

import org.glytoucan.admin.client.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class UserClientConfig {

  @Autowired
  String defaultUri;

  @Autowired
  Jaxb2Marshaller marshaller;

  @Bean
  public UserClient glycoSequenceClient(Jaxb2Marshaller marshaller) {
    UserClient client = new UserClient();
    client.setDefaultUri(defaultUri);
    client.setMarshaller(marshaller);
    client.setUnmarshaller(marshaller);
    return client;
  }
}
