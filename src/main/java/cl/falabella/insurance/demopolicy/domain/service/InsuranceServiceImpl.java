package cl.falabella.insurance.demopolicy.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cl.falabella.insurance.demopolicy.domain.model.LifeInsurance;
import cl.falabella.insurance.demopolicy.domain.model.Policy;

/**
 * @author lvergara
 * Insurance Service Concrete implementation
 */
@Service
public class InsuranceServiceImpl implements InsuranceService {

	public List<Policy> getPolicies() {
		LifeInsurance policy = new LifeInsurance();
		List<Policy> listPolicy = new ArrayList<Policy>();
		policy.setAmountPesos(100.0);
		policy.setAmountUF(1.0);
		policy.setCapital(1000.0);
		policy.setCompany("SURA");
		policy.setDurationMonths(1);
		policy.setPlan("Plan 1");
		listPolicy.add(policy);
		
		return listPolicy;
	}

}
