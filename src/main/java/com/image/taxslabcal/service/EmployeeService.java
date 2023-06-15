package com.image.taxslabcal.service;

import java.util.List;

import com.image.taxslabcal.domain.Employee;
import com.image.taxslabcal.domain.EmployeeVo;

public interface EmployeeService {
	
	 public int employeeAdd(Employee emp);
	 
	 public List<EmployeeVo> employeeDetails(String financialYear);

}
