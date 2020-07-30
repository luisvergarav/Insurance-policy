package cl.falabella.insurance.demopolicy;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import cl.falabella.insurance.demopolicy.domain.model.Policy;
import cl.falabella.insurance.demopolicy.domain.service.InsuranceService;
import cl.falabella.insurance.demopolicy.vo.PolicyRequest;
import cl.falabella.insurance.demopolicy.vo.PolicyResponse;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@Profile("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class InsuranceControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private InsuranceService insuranceService;
	private final ObjectMapper mapper = new ObjectMapper();

	@Test
	public void shouldReturnOk() throws Exception {
		PolicyRequest request = new PolicyRequest();
		String rut = "1-9";
		String email = "luis@gmail.com";
		String phone = "123456789";
		String birthDate = "01-01-1990";
				
		Mockito.when(insuranceService.getPolicies()).thenReturn(new ArrayList<Policy>());
		
		this.mockMvc.perform(get("/insurance/v1/policy")
				.param("rut",rut)
				.param("birthDate",birthDate)
				.param("email",email)
				.param("phone",phone))
				.andDo(print()).andExpect(status().isOk());
	}

}
