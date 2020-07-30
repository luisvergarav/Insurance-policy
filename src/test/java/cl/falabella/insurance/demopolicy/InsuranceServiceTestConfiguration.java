package cl.falabella.insurance.demopolicy;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import cl.falabella.insurance.demopolicy.domain.service.InsuranceService;
import cl.falabella.insurance.demopolicy.domain.service.InsuranceServiceImpl;

@Profile("test")
@Configuration
public class InsuranceServiceTestConfiguration {
    @Bean
    @Primary
    public InsuranceService nameService() {
        return Mockito.mock(InsuranceServiceImpl.class);
    }
}