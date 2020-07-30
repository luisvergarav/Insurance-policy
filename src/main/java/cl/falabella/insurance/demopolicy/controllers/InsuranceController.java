package cl.falabella.insurance.demopolicy.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.falabella.insurance.demopolicy.domain.model.Policy;
import cl.falabella.insurance.demopolicy.domain.service.InsuranceService;
import cl.falabella.insurance.demopolicy.vo.LifeInsuranceVO;
import cl.falabella.insurance.demopolicy.vo.PolicyRequest;
import cl.falabella.insurance.demopolicy.vo.PolicyResponse;
import cl.falabella.insurance.demopolicy.vo.PolicyVO;


@CrossOrigin(origins = "*")
@RestController
public class InsuranceController {

	@Autowired
	private InsuranceService insuranceService;
	
    @Autowired
    private ModelMapper modelMapper;

	@RequestMapping(path = "/insurance/v1/policy", method = POST)
	public ResponseEntity<PolicyResponse> addStore(@RequestBody PolicyRequest policyRequest) {
		try {
			PolicyResponse response = new PolicyResponse();
			
			response.setPolicies(insuranceService.getPolicies().stream()
			.map(this::convertToDto).collect(Collectors.toList()));
			
			return new ResponseEntity<PolicyResponse>(response,HttpStatus.OK);	
		}catch(Exception e) {
			return new ResponseEntity<PolicyResponse>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	private PolicyVO convertToDto(Policy policie) {

		return modelMapper.map(policie, LifeInsuranceVO.class);
	   
	}
	
	
	private Policy convertToEntity(PolicyVO policyVo) throws ParseException {
	    return modelMapper.map(policyVo, Policy.class);
	  }

}