package lk.ijse.posSystemBackend.dao.custom.impl;

import lk.ijse.posSystemBackend.dao.custom.ItemDAO;
import lk.ijse.posSystemBackend.dao.custom.impl.util.SQLUtil;
import lk.ijse.posSystemBackend.entity.Item;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAOImpl  implements ItemDAO {
    @Override
    public ArrayList<Item> getAll(Connection connection) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute(connection,"SELECT * FROM item");
        ArrayList<Item> allItems = new ArrayList<>();
        while (rst.next()) {
            allItems.add(new Item(rst.getString(1), rst.getString(2), rst.getDouble(3),rst.getInt(4)));
        }
        return allItems;
    }
    @Override
    public boolean save(Item entity,Connection connection) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(connection,"INSERT INTO item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)", entity.getCode(), entity.getDescription(), entity.getUnitPrice(), entity.getQtyOnHand());

    }

    @Override
    public boolean update(Item entity,Connection connection) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(connection,"UPDATE item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?", entity.getDescription(), entity.getUnitPrice(), entity.getQtyOnHand(), entity.getCode());
    }

    @Override
    public boolean delete(String code,Connection connection) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(connection,"DELETE FROM item WHERE code=?", code);
    }

    @Override
    public boolean exist(String s, Connection connection) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Item search(String code, Connection connection) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute(connection,"SELECT * FROM item WHERE code=?", code);
        if (rst.next()) {
            return new Item(rst.getString(1), rst.getString(2),  rst.getDouble(3),rst.getInt(4));
        }
        return null;
    }

}
