package lk.ijse.posSystemBackend.dao.custom;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import lk.ijse.posSystemBackend.dao.CrudDAO;

public interface OrderDAO extends CrudDAO<MysqlxCrud.Order, String> {
}
