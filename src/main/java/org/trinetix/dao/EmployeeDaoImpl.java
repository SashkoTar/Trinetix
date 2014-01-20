package org.trinetix.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.trinetix.dto.Employee;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class EmployeeDaoImpl implements EmployeeDao {

    private static final Log log = LogFactory.getLog(EmployeeDaoImpl.class);

    public static final String INSERT_SQL = "INSERT INTO EMPLOYEE " +
            "(FIRST_NAME, LAST_NAME, DATE_OF_BIRTH, START_DATE, TITLE, MANAGER_ID, COMMENTS, ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String UPDATE_SQL = "UPDATE EMPLOYEE SET FIRST_NAME=?, LAST_NAME=?, DATE_OF_BIRTH=?, START_DATE=?, TITLE=?," +
            " MANAGER_ID=?, COMMENTS=? WHERE ID =?";


    @Autowired
    DataSource dataSource;

    @Override
    public Employee save(Employee employee) throws SQLException {
        Connection conn = getConnection();
        String sql = getById(employee.getId()) != null ? UPDATE_SQL : INSERT_SQL;

        log.info("Saving Employee: " + sql );

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, employee.getFirstName());
        ps.setString(2, employee.getLastName());
        ps.setDate(3, employee.getDateOfBirth() != null ? Date.valueOf(employee.getDateOfBirth()) : null);
        ps.setDate(4, employee.getStartDate() != null ? Date.valueOf(employee.getStartDate()) : null);
        ps.setString(5, employee.getTitle());
        ps.setString(6, employee.getManagerId());
        ps.setString(7, employee.getComments());
        ps.setString(8, employee.getId());
        ps.executeUpdate();

        ps.close();
        closeConnection(conn);
        return employee;

    }

    @Override
    public Employee getById(String id) throws SQLException {
        String sql = "SELECT e.*,  CONCAT(m.first_name , ' ', m.last_name) as 'MANAGER' from employee e" +
                " LEFT OUTER JOIN employee m ON e.manager_id = m.id where e.id=" + id;
        Connection conn = getConnection();
        Employee result = null;

        log.info("Retrieving Employee: " + sql );

        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            result = extractEmployeeFromResultSet(rs);
        }
        rs.close();
        ps.close();
        closeConnection(conn);
        return result;

    }

    @Override
    public List<Employee> getList(String orderBy) throws SQLException {
        String order = orderBy != null ? " ORDER BY E." + orderBy : " ORDER BY E.ID";
        String sql = "SELECT e.*,  CONCAT(m.first_name , ' ', m.last_name) as 'MANAGER' from employee e" +
                " LEFT OUTER JOIN employee m ON e.manager_id = m.id" + order;

        Connection conn = getConnection();
        List<Employee> result = new LinkedList<Employee>();

        log.info("Listing Employees: " + sql );

        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            result.add(extractEmployeeFromResultSet(rs));
        }

        rs.close();
        ps.close();
        return result;

    }


    @Override
    public int getCount() throws SQLException {
        String sql = "SELECT count(*) FROM employee";
        Connection conn = getConnection();
        int result = 0;

        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            result = rs.getInt(1);
        }

        rs.close();
        ps.close();
        closeConnection(conn);
        return result;

    }


    @Override
    public List<Employee> getListByTitle(String title) throws SQLException {
        String sql = "SELECT e.*,  CONCAT(m.first_name , ' ', m.last_name) as 'MANAGER' from employee e" +
                " LEFT OUTER JOIN employee m ON e.manager_id = m.id  where e.title='" + title + "'";
        Connection conn = getConnection();
        List<Employee> result = new ArrayList<Employee>();

        log.info("Searching Employee By Title: " + sql);

        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            result.add(extractEmployeeFromResultSet(rs));
        }
        rs.close();
        ps.close();
        closeConnection(conn);
        return result;

    }

    private Employee extractEmployeeFromResultSet(ResultSet rs) throws SQLException {
        Employee result = new Employee();
        result.setId(rs.getString("ID"));
        result.setFirstName(rs.getString("FIRST_NAME"));
        result.setLastName(rs.getString("LAST_NAME"));
        result.setDateOfBirth(rs.getDate("DATE_OF_BIRTH") != null ? rs.getDate("DATE_OF_BIRTH").toString() : null);
        result.setStartDate(rs.getDate("START_DATE") != null ? rs.getDate("START_DATE").toString() : null);
        result.setTitle(rs.getString("TITLE"));
        result.setManagerId(rs.getString("MANAGER_ID"));
        result.setManager(rs.getString("MANAGER"));
        result.setComments(rs.getString("COMMENTS"));
        return result;
    }

    private Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
            }
        }
    }
}
