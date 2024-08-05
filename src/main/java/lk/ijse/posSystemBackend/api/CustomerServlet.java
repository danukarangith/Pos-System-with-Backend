package lk.ijse.posSystemBackend.api;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


@WebServlet(urlPatterns = "/Customers")
public class CustomerServlet extends HttpServlet {
    private DataSource source;

public void init() {
    try {
        source=(DataSource) new InitialContext().lookup("java:/comp/env/jdbc/pos");
    } catch (NamingException e) {
        throw new RuntimeException(e);
    }
}
}
