package lk.ijse.posSystemBackend.bo.custom.impl;

import lk.ijse.posSystemBackend.bo.custom.ItemBO;
import lk.ijse.posSystemBackend.dao.DAOFactory;
import lk.ijse.posSystemBackend.dao.custom.ItemDAO;
import lk.ijse.posSystemBackend.dto.ItemDTO;
import lk.ijse.posSystemBackend.entity.Item;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {
    ItemDAO itemDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ITEM);


    @Override
    public ArrayList<ItemDTO> getAllItems(Connection connection) throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO> list = new ArrayList<>();
        ArrayList<Item> all = itemDAO.getAll(connection);
        for (Item i : all) {
            list.add(new ItemDTO(i.getCode(), i.getDescription(), i.getUnitPrice(), i.getQtyOnHand()));
        }
        return list;
    }

    @Override
    public boolean deleteItem(String code,Connection connection) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(code,connection);    }

    @Override
    public boolean saveItem(ItemDTO dto,Connection connection) throws SQLException, ClassNotFoundException {
        return itemDAO.save(new Item(dto.getCode(), dto.getDescription(), dto.getUnitPrice(), dto.getQtyOnHand()),connection);
    }

    @Override
    public boolean updateItem(ItemDTO dto,Connection connection) throws SQLException, ClassNotFoundException {
        return itemDAO.update(new Item(dto.getCode(), dto.getDescription(), dto.getUnitPrice(), dto.getQtyOnHand()),connection);
    }
}
