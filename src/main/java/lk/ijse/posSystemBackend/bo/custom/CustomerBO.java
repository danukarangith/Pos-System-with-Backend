package lk.ijse.posSystemBackend.bo.custom;

import lk.ijse.posSystemBackend.bo.SuperBO;
import lk.ijse.posSystemBackend.dto.CustomerDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {
    ArrayList<CustomerDTO> getAllCustomers(Connection connection) throws SQLException, ClassNotFoundException;

    boolean saveCustomer(CustomerDTO dto, Connection connection) throws SQLException, ClassNotFoundException;

    boolean updateCustomer(CustomerDTO dto,Connection connection) throws SQLException, ClassNotFoundException;

    boolean deleteCustomer(String id,Connection connection) throws SQLException, ClassNotFoundException;
}
