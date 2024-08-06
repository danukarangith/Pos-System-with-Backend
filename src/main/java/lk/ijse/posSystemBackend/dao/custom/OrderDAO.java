package lk.ijse.posSystemBackend.dao.custom;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import lk.ijse.posSystemBackend.dao.CrudDAO;
import lk.ijse.posSystemBackend.entity.Orders;

public interface OrderDAO extends CrudDAO<Orders, String> {
}
