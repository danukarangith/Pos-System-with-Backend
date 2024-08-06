package lk.ijse.posSystemBackend.bo.custom.impl;

import lk.ijse.posSystemBackend.bo.custom.PurchaseOrderBO;
import lk.ijse.posSystemBackend.dao.DAOFactory;
import lk.ijse.posSystemBackend.dao.custom.ItemDAO;
import lk.ijse.posSystemBackend.dao.custom.OrderDAO;
import lk.ijse.posSystemBackend.dao.custom.OrderDetailsDAO;
import lk.ijse.posSystemBackend.dto.ItemDTO;
import lk.ijse.posSystemBackend.dto.OrderDTO;
import lk.ijse.posSystemBackend.dto.OrderDetailDTO;
import lk.ijse.posSystemBackend.entity.Item;
import lk.ijse.posSystemBackend.entity.OrderDetails;
import lk.ijse.posSystemBackend.entity.Orders;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class PurchaseOrderBOImpl implements PurchaseOrderBO {
    ItemDAO itemDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    OrderDAO orderDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    OrderDetailsDAO orderDetailsDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDER_DETAILS);

    @Override
    public boolean saveOrder(OrderDTO dto, DataSource source) throws SQLException, ClassNotFoundException {

        try (Connection connection = source.getConnection();){

            try(Connection connection1 = source.getConnection()){
                if (orderDAO.exist(dto.getId(),connection1)){
                    return false;
                }
            }

            connection.setAutoCommit(false);

            Orders orderEntity = new Orders(dto.getId(), dto.getDate(), dto.getCustomerId());

            try (Connection connection2 = source.getConnection();){
                boolean orderAdded = orderDAO.save(orderEntity,connection2);
                if (!orderAdded) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }
            }

            for (OrderDetailDTO odDTO : dto.getOrderDetaisList()) {
                OrderDetails orderDetailsEntity = new OrderDetails(odDTO.getOrderId(), odDTO.getItemCode(), odDTO.getQty(), odDTO.getUnitPrice());
                try (Connection connection3 = source.getConnection();){
                    boolean odAdded = orderDetailsDAO.save(orderDetailsEntity, connection3);
                    if (!odAdded) {
                        connection.rollback();
                        connection.setAutoCommit(true);
                        return false;
                    }
                }
                ItemDTO item;
                try (Connection connection4 = source.getConnection();){
                    item = findItemByID(orderDetailsEntity.getItemCode(), connection4);
                    item.setQtyOnHand(item.getQtyOnHand() - orderDetailsEntity.getQty());
                }

                boolean itemUpdate;

                try (Connection connection5 = source.getConnection();) {
                    itemUpdate = itemDAO.update(new Item(item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand()), connection5);
                }
                if (!itemUpdate) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }
            }
            connection.commit();
            connection.setAutoCommit(true);
            return true;
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public ItemDTO findItemByID(String code,Connection connection) {
        try {
            Item search = itemDAO.search(code,connection);
            return new ItemDTO(search.getCode(),search.getDescription(),search.getUnitPrice(),search.getQtyOnHand());
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find the Item " + code, e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
