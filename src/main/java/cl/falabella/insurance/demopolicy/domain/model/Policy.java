package cl.falabella.insurance.demopolicy.domain.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
/**
 * @author lvergara
 * Abstract policy
 */
@Data
public abstract class Policy {
	protected String plan;
	protected String company;
	protected Double amountPesos;
	protected Double amountUF;
	protected Integer durationMonths;
	protected List<Promotion> promotions = new ArrayList<Promotion>();
	
}
