package lk.ijse.posSystemBackend.dao.custom;

import lk.ijse.posSystemBackend.dao.SuperDAO;
import lk.ijse.posSystemBackend.dto.OrderJoinEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface QuearyDAO extends SuperDAO {
    ArrayList<OrderJoinEntity> searchOrderByOID(String oid, Connection connection) throws SQLException, ClassNotFoundException;
}
