package cl.falabella.insurance.demopolicy.domain.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.Data;

/**
 * @author lvergara Abstract policy
 */
@Data
@Document(collection = "Policies")
public abstract class Policy {
	@Id
	protected String plan;
	protected String company;
	protected Double amountPesos;
	protected Double amountUF;
	protected Integer durationMonths;
	protected List<Promotion> promotions = new ArrayList<Promotion>();
	protected Date limitDate;

	
	

}
