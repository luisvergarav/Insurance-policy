package cl.falabella.insurance.demopolicy.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.falabella.insurance.demopolicy.domain.model.Policy;
import cl.falabella.insurance.demopolicy.domain.service.InsuranceService;
import cl.falabella.insurance.demopolicy.vo.LifeInsuranceVO;
import cl.falabella.insurance.demopolicy.vo.PolicyVO;
import lombok.extern.slf4j.Slf4j;


@CrossOrigin(origins = "*")
@RestController
@Slf4j
public class InsuranceController {

	@Autowired
	private InsuranceService insuranceService;
	
    @Autowired
    private ModelMapper modelMapper;

	@RequestMapping(path = "/insurance/v1/policy", method = GET)
	public ResponseEntity<List<PolicyVO>> getPolicies(
			@RequestParam  String rut,
			@RequestParam String birthDate,
			@RequestParam String email,
			@RequestParam String phone) {
		try {
			log.info("Peticion  [Rut {}] [BirthDate {}] [Email {}] [Phone {}] ", rut,birthDate,email,phone);
			
			List<PolicyVO> listaPolizas = insuranceService.getPolicies().stream()
			.map(this::convertToDto).collect(Collectors.toList());
			
			return new ResponseEntity<List<PolicyVO>>(listaPolizas,HttpStatus.OK);	
		}catch(Exception e) {
			log.error("Error consultando las polizas [{}] [{}]",rut, e.getLocalizedMessage());
			return new ResponseEntity<List<PolicyVO>>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	private PolicyVO convertToDto(Policy policie) {

		return modelMapper.map(policie, LifeInsuranceVO.class);
	   
	}
	
	

}