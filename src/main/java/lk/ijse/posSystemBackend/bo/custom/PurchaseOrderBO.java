package lk.ijse.posSystemBackend.bo.custom;

import lk.ijse.posSystemBackend.bo.SuperBO;
import lk.ijse.posSystemBackend.dto.OrderDTO;

import javax.sql.DataSource;
import java.sql.SQLException;

public interface PurchaseOrderBO extends SuperBO {
    boolean saveOrder(OrderDTO dto, DataSource source) throws SQLException, ClassNotFoundException;
}
