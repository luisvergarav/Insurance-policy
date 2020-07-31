package cl.falabella.insurance.demopolicy.domain.service;

import cl.falabella.insurance.demopolicy.repository.InsuranceRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cl.falabella.insurance.demopolicy.domain.model.LifeInsurance;
import cl.falabella.insurance.demopolicy.domain.model.LifeInsuranceList;
import cl.falabella.insurance.demopolicy.domain.model.Policy;

/**
 * @author lvergara Insurance Service Concrete implementation Service
 *         implementing cache manager Pattern
 */
@Service
public class InsuranceServiceImpl implements InsuranceService {

	private final InsuranceRepository insuranceRepository;
	private final RestTemplate restTemplate;
	private final String insuranceApiUrl;
	private final MongoTemplate mongoTemplate;

	@Autowired
	public InsuranceServiceImpl(final InsuranceRepository insuranceRepository,

			final RestTemplate restTemplate, MongoTemplate mongoTemplate,
			@Value("${api.insurance.endpoint}") final String insuranceApiUrl) {
		this.insuranceApiUrl = insuranceApiUrl;
		this.insuranceRepository = insuranceRepository;
		this.restTemplate = restTemplate;
		this.mongoTemplate = mongoTemplate;
	}

	public List<Policy> getPoliciesByDate(Date birthDate) {

		Query query = new Query();
		query.addCriteria(Criteria.where("limitDate").gt(birthDate));
		List<Policy> policyQuery = mongoTemplate.find(query, Policy.class);

		LifeInsurance nuevo = new LifeInsurance();
		nuevo.setPlan("Plan 1");
		nuevo.setLimitDate(new Date());

		this.insuranceRepository.save(nuevo);

		if (policyQuery.isEmpty())
			policyQuery = getPoliciesFromAPI(birthDate);

		return policyQuery;
	}

	protected List getPoliciesFromAPI(final Date birthDate) {


		DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
		String strDate = dateFormat.format(birthDate);

		final String url = insuranceApiUrl + "?birthDate=" + strDate;
		
		
		ResponseEntity<List<LifeInsurance>> rateResponse =
		        restTemplate.exchange(url,
		                    HttpMethod.GET, null, new ParameterizedTypeReference<List<LifeInsurance>>() {
		            });
		List<LifeInsurance> policies = rateResponse.getBody();
		
		if (!policies.isEmpty()) {

			insuranceRepository.saveAll(policies);
		}

		return  policies;
	}
}
