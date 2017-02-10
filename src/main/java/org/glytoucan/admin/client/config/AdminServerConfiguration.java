package org.glytoucan.admin.client.config;

//import org.glytoucan.client.GlycoSequenceClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class AdminServerConfiguration {

//	to run this test locally, set admin_URI to http://test.api.glytoucan.org/admin/ws
  @Value("${admin.uri:http://test.admin.glytoucan.org/ws}")
  String adminUri;
  
  @Bean
  String adminUri() {
    return adminUri;
  }
  
  @Bean(name="adminMarshaller")
	public Jaxb2Marshaller adminMarshaller() throws Exception {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("org.glytoucan.admin.model");
		return marshaller;
	}
}