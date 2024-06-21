package com.mongotest.repository;

import com.mongotest.model.EmployeeDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends MongoRepository<EmployeeDTO, String> {
}
