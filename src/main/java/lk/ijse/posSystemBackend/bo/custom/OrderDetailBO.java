package lk.ijse.posSystemBackend.bo.custom;

import lk.ijse.posSystemBackend.bo.SuperBO;
import lk.ijse.posSystemBackend.dto.OrderDetailDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDetailBO extends SuperBO {
    ArrayList<OrderDetailDTO> getAllDetails(Connection connection) throws SQLException, ClassNotFoundException;
}
