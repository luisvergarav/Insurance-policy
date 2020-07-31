package cl.falabella.insurance.demopolicy.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import cl.falabella.insurance.demopolicy.domain.model.Policy;


@Repository
public interface InsuranceRepository extends MongoRepository<Policy, String>{


}
