package com.UPL.UPL_UsrReg;

import com.UPL.UPL_UsrReg.Controller.UPLController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.UPL.UPL_UsrReg")
public class UplUsrRegApplication  extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder builder) {
		return builder.sources(UplUsrRegApplication.class);
	}

	public static void main(String[] args) {

		SpringApplication.run(UplUsrRegApplication.class, args);
	}


}
