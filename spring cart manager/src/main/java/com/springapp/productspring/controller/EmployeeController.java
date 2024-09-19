package com.springapp.productspring.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springapp.productspring.dto.Employee;



public class EmployeeController {
	
	@Autowired
	private ServiceContract<Employee, Integer> _service;

	@GetMapping(path = "/employees/sort/{sortchoice}")
	@CrossOrigin(origins = "*")
	public ResponseEntity<List<Employee>> getEmplpoyees(@PathVariable("sortchoice") int sortChoice)
			throws ClassNotFoundException, SQLException {
		try {
			List<Employee> employeeRecords = _service.fetchRecords(sortChoice);
			System.out.println("request came");
			return ResponseEntity.ok(employeeRecords);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} catch (SQLException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}
	}

	@PostMapping(path = "/employees/add")
	public ResponseEntity<Integer> addEmployee(@RequestBody Employee employee)
			throws ClassNotFoundException, SQLException, Exception {
		try {
			int res = _service.insertRecord(employee);
			if (res > 0) {
				return ResponseEntity.of(Optional.of(res)).status(HttpStatus.CREATED).build();
			} else {
				return ResponseEntity.of(Optional.empty()).status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		} catch (

		ClassNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		} catch (SQLException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}

	}

	@PutMapping(path = "/employees/update/{eid}")
	public ResponseEntity<Integer> updateEmployee(@RequestBody Employee employee, @PathVariable("eid") int id)
			throws ClassNotFoundException, SQLException, Exception {
		try {
			int res = _service.modifyRecord(id, employee);
			if (res > 0) {
				return ResponseEntity.of(Optional.of(res)).status(HttpStatus.CREATED).build();
			} else {
				return ResponseEntity.of(Optional.empty()).status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		} catch (SQLException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}

	}

	@GetMapping(path = "/employees/view/{eid}")
	@CrossOrigin(origins = "*")
	public ResponseEntity<Employee> getEmplpoyeeById(@PathVariable("eid") int eid)
			throws ClassNotFoundException, SQLException {
		try {
			Employee employee = _service.fetchRecord(eid);
			return ResponseEntity.ok(employee);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} catch (SQLException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@DeleteMapping(path = "/employees/delete/{eid}")
	@CrossOrigin(origins = "*")
	public ResponseEntity<Integer> deleteEmplpoyeeById(@PathVariable("eid") int eid)
			throws ClassNotFoundException, SQLException {
		try {
			int res = _service.removeRecord(eid);
			return ResponseEntity.ok(res);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} catch (SQLException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}
	}

}
