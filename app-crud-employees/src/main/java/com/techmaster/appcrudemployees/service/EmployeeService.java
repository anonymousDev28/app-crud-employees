package com.techmaster.appcrudemployees.service;

import com.techmaster.appcrudemployees.model.Employee;
import com.techmaster.appcrudemployees.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    void saveEmployee(Employee employee);
    Employee getEmployeeById(int id);
    void deleteEmployeeById(int id);
    Page<Employee> findPaginated(int pageNo,int pageSize,String sortField,String sortDirection);
}
