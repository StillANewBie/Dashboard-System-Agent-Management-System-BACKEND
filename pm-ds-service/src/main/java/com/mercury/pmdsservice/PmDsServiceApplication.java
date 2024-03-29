package com.mercury.pmdsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import com.netflix.appinfo.AmazonInfo;

@SpringBootApplication
@EnableEurekaClient
public class PmDsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PmDsServiceApplication.class, args);
	}

	
	@Bean
	@Profile("!default")
	public EurekaInstanceConfigBean eurekaInstanceConfig(InetUtils inetUtils) {
	  EurekaInstanceConfigBean b = new EurekaInstanceConfigBean(inetUtils);
	  AmazonInfo info = AmazonInfo.Builder.newBuilder().autoBuild("eureka");
	  b.setDataCenterInfo(info);
	  return b;
	}
}
