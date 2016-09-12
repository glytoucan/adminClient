package org.glytoucan.admin.client.config;

import org.glytoucan.admin.client.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
@Import(AdminServerConfiguration.class)
public class UserClientConfig {

  @Autowired
  String adminUri;

  @Autowired
  @Qualifier("adminMarshaller")
  Jaxb2Marshaller adminMarshaller;

  @Bean
  public UserClient userClient() {
    UserClient client = new UserClient();
    client.setDefaultUri(adminUri);
    client.setMarshaller(adminMarshaller);
    client.setUnmarshaller(adminMarshaller);
    return client;
  }
}
