/**
 * 
 */
package cl.falabella.insurance.demopolicy.domain.service;

import java.util.List;

import cl.falabella.insurance.demopolicy.domain.model.Policy;

/**
 * @author lvergara
 * Insurance Service Interface
 */
public interface InsuranceService {
	List<Policy> getPolicies();
}
