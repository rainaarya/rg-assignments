package org.example;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EmployeeDAO {
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        createTableIfNotExists();
    }

    private void createTableIfNotExists() {
        String SQL = "CREATE TABLE IF NOT EXISTS employee (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(100) NOT NULL," +
                "department VARCHAR(100) NOT NULL)";
        jdbcTemplate.execute(SQL);
    }

    public void create(Employee employee) {
        String SQL = "INSERT INTO employee (name, department) VALUES (?, ?)";
        jdbcTemplate.update(SQL, employee.getName(), employee.getDepartment());
    }

    public Employee getEmployee(int id) {
        String SQL = "SELECT * FROM employee WHERE id = ?";
        return jdbcTemplate.queryForObject(SQL, new Object[]{id}, new EmployeeMapper());
    }

    public List<Employee> getAllEmployees() {
        String SQL = "SELECT * FROM employee";
        return jdbcTemplate.query(SQL, new EmployeeMapper());
    }

    public void update(Employee employee) {
        String SQL = "UPDATE employee SET name = ?, department = ? WHERE id = ?";
        jdbcTemplate.update(SQL, employee.getName(), employee.getDepartment(), employee.getId());
    }

    public void delete(int id) {
        String SQL = "DELETE FROM employee WHERE id = ?";
        jdbcTemplate.update(SQL, id);
    }

    private static final class EmployeeMapper implements RowMapper<Employee> {
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            Employee employee = new Employee();
            employee.setId(rs.getInt("id"));
            employee.setName(rs.getString("name"));
            employee.setDepartment(rs.getString("department"));
            return employee;
        }
    }
}