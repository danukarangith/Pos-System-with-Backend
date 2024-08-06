package lk.ijse.posSystemBackend.dao.custom.impl;

import lk.ijse.posSystemBackend.dao.custom.OrderDetailsDAO;
import lk.ijse.posSystemBackend.dao.custom.impl.util.SQLUtil;
import lk.ijse.posSystemBackend.entity.OrderDetails;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {
    @Override
    public ArrayList<OrderDetails> getAll(Connection connection) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute(connection,"SELECT * FROM order_detail");
        ArrayList<OrderDetails> allItems = new ArrayList<>();
        while (rst.next()) {
            allItems.add(new OrderDetails(rst.getString(1), rst.getString(2), rst.getInt(3),rst.getDouble(4)));
        }
        return allItems;
    }

    @Override
    public boolean save(OrderDetails entity, Connection connection) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(connection,"INSERT INTO order_detail (orderId, itemCode, qty ,unitPrice) VALUES (?,?,?,?)", entity.getOrderId(), entity.getItemCode(), entity.getQty(), entity.getUnitPrice());
    }

    @Override
    public boolean update(OrderDetails dto, Connection connection) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s, Connection connection) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exist(String s, Connection connection) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public OrderDetails search(String code, Connection connection) throws SQLException, ClassNotFoundException {
        return null;
    }
}
