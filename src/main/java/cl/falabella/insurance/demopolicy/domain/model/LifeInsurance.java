package cl.falabella.insurance.demopolicy.domain.model;

import java.math.BigDecimal;

import lombok.Data;

/**
 * @author lvergara
 * LifeInsurance class
 */
@Data
public class LifeInsurance extends Policy {
	private Double capital;
}
