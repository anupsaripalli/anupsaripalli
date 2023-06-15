package com.image.taxslabcal.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.image.taxslabcal.domain.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

	@Query(value = "SELECT * FROM employee WHERE DATE_FORMAT(doj, '%Y-%m-%d') BETWEEN :fromYear and :toYear", nativeQuery = true)
	public Optional<List<Employee>> getEmployeeData(@Param("fromYear") String fromYear, @Param("toYear") String toYear);
}
