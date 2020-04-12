package edu.kg.readinglist.dao;

import edu.kg.readinglist.io.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class CustomerDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CustomerDao(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Customer> getCustomers()
    {
        return jdbcTemplate.query("select * from reading_list", new RowMapper<Customer>() {
            public Customer mapRow(ResultSet resultSet, int i) throws SQLException {
                Customer customer = new Customer();

                customer.setId(resultSet.getInt("id"));
                customer.setFirstName(resultSet.getString("firstName"));
                customer.setLastName(resultSet.getString("lastName"));
                customer.setEmailAddress(resultSet.getString("emailAddress"));
                customer.setJobTitle(resultSet.getString("jobTitle"));
                customer.setEmailAddress(resultSet.getString("emailAddress"));
                customer.setMobilePhone(resultSet.getInt("mobilePhone"));
                customer.setCity(resultSet.getString("city"));
                customer.setWebPage(resultSet.getString("webPage"));




                return customer;
            }
        });
    }

    public Customer getCustomer(int id)
    {
        String sql = "SELECT * FROM reading_list WHERE id = ?";
        RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class);
        Customer customer = jdbcTemplate.queryForObject(sql, rowMapper, id);

        return customer;
    }

    public boolean delete(int id)
    {
        return jdbcTemplate.update("delete from reading_list where id = ?", id) == 1;
    }

    public boolean create(Customer customer)
    {
        return jdbcTemplate.update("insert into reading_list (id, firstName, lastName, emailAddress, jobTitle, mobilePhone, city, webPage) values (?, ?, ?)", customer.getFirstName(), customer.getLastName(), customer.getEmailAddress(), customer.getJobTitle(), customer.getMobilePhone(), customer.getCity(), customer.getWebPage()) == 1;
    }

    public boolean update(Customer customer)
    {
        return jdbcTemplate.update("update reading_list set firstName =?, lastName=?, emailAddress=?, jobTitle=?,mobilePhone=?,city=?, webPage=?  where id=?", customer.getFirstName(), customer.getLastName(), customer.getEmailAddress(), customer.getJobTitle(), customer.getMobilePhone(), customer.getCity(), customer.getWebPage()) == 1;
    }
}
