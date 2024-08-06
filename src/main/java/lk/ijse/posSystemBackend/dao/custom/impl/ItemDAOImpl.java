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
}
