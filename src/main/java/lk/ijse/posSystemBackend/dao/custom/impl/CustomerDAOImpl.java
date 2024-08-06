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
}
