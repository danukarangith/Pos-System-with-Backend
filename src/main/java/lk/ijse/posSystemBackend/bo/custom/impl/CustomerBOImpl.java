package lk.ijse.posSystemBackend.bo.custom.impl;

import lk.ijse.posSystemBackend.bo.custom.CustomerBO;
import lk.ijse.posSystemBackend.dao.DAOFactory;
import lk.ijse.posSystemBackend.dao.custom.CustomerDAO;
import lk.ijse.posSystemBackend.dto.CustomerDTO;
import lk.ijse.posSystemBackend.entity.Customer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO =  DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public ArrayList<CustomerDTO> getAllCustomers(Connection connection) throws SQLException, ClassNotFoundException {
        ArrayList<Customer> all = customerDAO.getAll(connection);
        ArrayList<CustomerDTO> arrayList= new ArrayList<>();
        for (Customer c : all) {
            arrayList.add(new CustomerDTO(c.getId(),c.getName(),c.getAddress(),c.getSalary()));
        }
        return arrayList;
    }

    @Override
    public boolean saveCustomer(CustomerDTO dto,Connection connection) throws SQLException, ClassNotFoundException {
        return customerDAO.save( new Customer(dto.getId(), dto.getName(),dto.getAddress() , dto.getSalary()),connection);
    }

    @Override
    public boolean updateCustomer(CustomerDTO dto,Connection connection) throws SQLException, ClassNotFoundException {
        return customerDAO.update( new Customer(dto.getId(), dto.getName(),dto.getAddress() , dto.getSalary()),connection);
    }

    @Override
    public boolean deleteCustomer(String id,Connection connection) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id,connection);
    }
}
