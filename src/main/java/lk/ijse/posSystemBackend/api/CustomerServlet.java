package lk.ijse.posSystemBackend.api;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.posSystemBackend.bo.BoFactory;
import lk.ijse.posSystemBackend.bo.custom.CustomerBO;
import lk.ijse.posSystemBackend.dto.CustomerDTO;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;


@WebServlet(urlPatterns = "/Customers")
public class CustomerServlet extends HttpServlet {
    CustomerBO customerBO = BoFactory.getBoFactory().getBO(BoFactory.BOTypes.CUSTOMER_BO);
    private DataSource source;

public void init() {
    try {
        source=(DataSource) new InitialContext().lookup("java:/comp/env/jdbc/pos");
    } catch (NamingException e) {
        throw new RuntimeException(e);
    }
}

}
