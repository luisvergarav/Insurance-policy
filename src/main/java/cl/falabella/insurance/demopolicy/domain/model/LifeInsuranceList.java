package cl.falabella.insurance.demopolicy.domain.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;


/**
 * @author lvergara
 * Wrapper util class
 */
@Data
public class LifeInsuranceList {

	  private List<Policy> policies;
	  
	    public LifeInsuranceList() {
	        policies = new ArrayList<>();
	    }
}
