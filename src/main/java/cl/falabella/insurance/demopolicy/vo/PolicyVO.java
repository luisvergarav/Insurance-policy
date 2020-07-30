package cl.falabella.insurance.demopolicy.vo;

import java.util.ArrayList;

import java.util.List;
import lombok.Data;


/**
 * @author lvergara
 * Policy VO
 */
@Data
public abstract class PolicyVO {
	protected String plan;
	protected String company;
	protected Double amountPesos;
	protected Double amountUF;
	protected Integer durationMonths;
	protected List<PromotionVO> promotions = new ArrayList<PromotionVO>();
	
}
