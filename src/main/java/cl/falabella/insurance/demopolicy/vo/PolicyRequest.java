package cl.falabella.insurance.demopolicy.vo;

import java.util.Date;

import lombok.Data;

/**
 * @author lvergara
 * API Request
 */
@Data
public class PolicyRequest {
	private String rut;
	private Date birthDate;
	private String email;
	private String phone;
}
