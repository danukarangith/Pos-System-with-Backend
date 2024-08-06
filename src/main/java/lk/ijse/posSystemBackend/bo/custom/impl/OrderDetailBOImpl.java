package lk.ijse.posSystemBackend.bo.custom.impl;

import lk.ijse.posSystemBackend.bo.custom.OrderDetailBO;
import lk.ijse.posSystemBackend.dao.DAOFactory;
import lk.ijse.posSystemBackend.dao.custom.OrderDetailsDAO;
import lk.ijse.posSystemBackend.dto.OrderDetailDTO;
import lk.ijse.posSystemBackend.entity.OrderDetails;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailBOImpl implements OrderDetailBO {
    OrderDetailsDAO detailsDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDER_DETAILS);

    @Override
    public ArrayList<OrderDetailDTO> getAllDetails(Connection connection) throws SQLException, ClassNotFoundException {
        ArrayList<OrderDetailDTO> detailDTOS = new ArrayList<>();
        ArrayList<OrderDetails> details = detailsDAO.getAll(connection);
        for (OrderDetails i : details) {
            detailDTOS.add(new OrderDetailDTO(i.getOrderId(), i.getItemCode(), i.getQty(), i.getUnitPrice()));
        }
        return detailDTOS;
    }
}
