package lk.ijse.posSystemBackend.dao.custom.impl.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLUtil {
    public static <T>T execute(Connection connection , String sql, Object... args) throws SQLException, ClassNotFoundException {

        PreparedStatement pstm = connection.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            pstm.setObject((i+1),args[i]);
        }
        if (sql.startsWith("SELECT") || sql.startsWith("select")){
            return (T) pstm.executeQuery();
        }else{
            return (T)  new Boolean(pstm.executeUpdate()>0);
        }

    }
}
