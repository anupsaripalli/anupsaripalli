package com.image.taxslabcal.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.image.taxslabcal.domain.Employee;
import com.image.taxslabcal.domain.EmployeeVo;
import com.image.taxslabcal.service.EmployeeService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

@RestController
@RequestMapping("api/slab")
public class SlabCalController {

	@Autowired
	EmployeeService empServ;

	@RequestMapping(method = RequestMethod.POST, path = "/cal")
	public ResponseEntity<String> employee(@Valid @RequestBody Employee emp) {
		int empId = empServ.employeeAdd(emp);
		return ResponseEntity.ok("Employee Created with ID :" + empId);
	}
	
	
	@RequestMapping(method = RequestMethod.GET, path = "/cal")
	public ResponseEntity<List<EmployeeVo>> getSlabDetails(@RequestParam("financialYear") @Pattern(regexp="\\d{4}") String financialYear) {
		List<EmployeeVo> employeeVoList = empServ.employeeDetails(financialYear);
		return ResponseEntity.ok(employeeVoList);
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
	    Map<String, Object> responseBody = new HashMap<>();
	    responseBody.put("error", "Validation failed");
	    responseBody.put("details", ex.getBindingResult().getAllErrors());
	    return ResponseEntity.badRequest().body(responseBody);
	}


}
