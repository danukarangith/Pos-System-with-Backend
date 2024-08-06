package lk.ijse.posSystemBackend.dao.custom.impl;

import lk.ijse.posSystemBackend.dao.custom.OrderDAO;
import lk.ijse.posSystemBackend.dao.custom.impl.util.SQLUtil;
import lk.ijse.posSystemBackend.entity.Orders;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public ArrayList<Orders> getAll(Connection connection) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Orders entity, Connection connection) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(connection,"INSERT INTO `orders` (id, date, customerID) VALUES (?,?,?)", entity.getId(), Date.valueOf(entity.getDate()), entity.getCustomerID());

    }

    @Override
    public boolean update(Orders dto, Connection connection) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s, Connection connection) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exist(String orderID, Connection connection) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute(connection,"SELECT id FROM `orders` WHERE id=?", orderID);
        return rst.next();
    }

    @Override
    public Orders search(String code, Connection connection) throws SQLException, ClassNotFoundException {
        return null;
    }
}
