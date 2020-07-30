package cl.falabella.insurance.demopolicy.vo;

import java.util.ArrayList;

import java.util.List;

import lombok.Data;

/**
 * @author lvergara
 * API Response
 */
@Data
public class PolicyResponse {
	private List<PolicyVO> policies = new ArrayList<PolicyVO>();

	
	
}
