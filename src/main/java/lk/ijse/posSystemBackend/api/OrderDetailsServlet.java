package lk.ijse.posSystemBackend.api;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import lk.ijse.posSystemBackend.bo.BoFactory;
import lk.ijse.posSystemBackend.bo.custom.OrderDetailBO;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class OrderDetailsServlet extends HttpServlet {
    OrderDetailBO detailBO = BoFactory.getBoFactory().getBO(BoFactory.BOTypes.Detail_BO);

    private DataSource source;

    @Override
    public void init() throws ServletException {
        try {
            source = (DataSource) new InitialContext().lookup("java:/comp/env/jdbc/pos");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
