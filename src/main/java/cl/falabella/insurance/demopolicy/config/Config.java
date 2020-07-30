package cl.falabella.insurance.demopolicy.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lvergara
 * Model mapper configuration Bean
 */
@Configuration
public class Config {
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
}

