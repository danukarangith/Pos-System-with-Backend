package lk.ijse.posSystemBackend.bo.custom;

import lk.ijse.posSystemBackend.bo.SuperBO;
import lk.ijse.posSystemBackend.dto.ItemDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO extends SuperBO {
    ArrayList<ItemDTO> getAllItems(Connection connection) throws SQLException, ClassNotFoundException ;

    boolean deleteItem(String code ,Connection connection) throws SQLException, ClassNotFoundException ;

    boolean saveItem(ItemDTO dto,Connection connection) throws SQLException, ClassNotFoundException ;

    boolean updateItem(ItemDTO dto,Connection connection) throws SQLException, ClassNotFoundException;
}
