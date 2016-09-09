package org.glytoucan.admin.client.config;

//import org.glytoucan.client.GlycoSequenceClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class SoapConfiguration {
//  http://172.20.0.1:8031/ws/model.wsdl
  @Value("${soap.api.glycosequence.uri:http://test.api.glytoucan.org/admin/ws}")
  String defaultUri;
  
  @Bean
  String defaultUri() {
    return defaultUri;
  }
  
	@Bean
	public Jaxb2Marshaller marshaller() throws Exception {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("org.glytoucan.admin.model");
//		marshaller.setPackagesToScan(ClassUtils.getPackageName(LogInsertRequest.class));
//		marshaller.afterPropertiesSet();
		return marshaller;
	}
}