package lk.ijse.posSystemBackend.dao.custom.impl;

import lk.ijse.posSystemBackend.dao.custom.CustomerDAO;
import lk.ijse.posSystemBackend.dao.custom.impl.util.SQLUtil;
import lk.ijse.posSystemBackend.entity.Customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public ArrayList<Customer> getAll(Connection connection) throws SQLException, ClassNotFoundException {
        ArrayList<Customer> allCustomers = new ArrayList<>();
        ResultSet rst = SQLUtil.execute(connection,"SELECT * FROM customer");
        while (rst.next()) {
            allCustomers.add(new Customer(rst.getString(1), rst.getString(2), rst.getString(3),rst.getDouble(4)));
        }
        return allCustomers;
    }

    @Override
    public boolean save(Customer entity,Connection connection) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(connection,"INSERT INTO customer (id,name, address,salary) VALUES (?,?,?,?)", entity.getId(), entity.getName(), entity.getAddress() ,entity.getSalary());
    }

    @Override
    public boolean update(Customer entity,Connection connection) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(connection,"UPDATE customer SET name=?, address=?, salary=? WHERE id=?", entity.getName(), entity.getAddress(), entity.getSalary(),entity.getId());
    }

    @Override
    public boolean delete(String code,Connection connection) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(connection,"DELETE FROM customer WHERE id=?", code);
    }

    @Override
    public boolean exist(String s, Connection connection) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Customer search(String code, Connection connection) throws SQLException, ClassNotFoundException {
        return null;
    }
}
