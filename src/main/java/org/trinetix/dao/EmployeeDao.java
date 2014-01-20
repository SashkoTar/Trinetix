package org.trinetix.dao;

import org.trinetix.dto.Employee;

import java.sql.SQLException;
import java.util.List;


public interface EmployeeDao {

    public Employee save(Employee employee) throws SQLException;

    public Employee getById(String  id) throws SQLException;

    public List<Employee> getList(String orderBy) throws SQLException;

    public List<String> getTitles() throws SQLException;

    int getCount() throws SQLException;

    List<Employee> getListByTitle(String title) throws SQLException;
}
