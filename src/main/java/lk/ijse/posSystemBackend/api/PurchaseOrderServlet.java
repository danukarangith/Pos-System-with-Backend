package lk.ijse.posSystemBackend.api;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.posSystemBackend.bo.BoFactory;
import lk.ijse.posSystemBackend.bo.custom.PurchaseOrderBO;
import lk.ijse.posSystemBackend.dto.OrderDTO;
import lk.ijse.posSystemBackend.dto.OrderDetailDTO;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(urlPatterns = "/orders")
public class PurchaseOrderServlet extends HttpServlet {

    PurchaseOrderBO poBO = BoFactory.getBoFactory().getBO(BoFactory.BOTypes.PURCHASE_ORDER_BO);

    private DataSource source;

    @Override
    public void init() throws ServletException {
        try {
            source = (DataSource) new InitialContext().lookup("java:/comp/env/jdbc/pos");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Jsonb jsonb = JsonbBuilder.create();
        OrderDTO orderDTO = jsonb.fromJson(req.getReader(), OrderDTO.class);
        String id = orderDTO.getId();
        LocalDate date = orderDTO.getDate();
        String customerId = orderDTO.getCustomerId();
        List<OrderDetailDTO> detaisList = orderDTO.getOrderDetaisList();


        if(id==null || !id.matches("^(OR)[0-9]{3}$")){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID is empty or invalid");
            return;
        } else if (date == null ) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Date is empty or invalid");
            return;
        } else if (customerId == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "CustomerId is empty or invalid");
            return;
        }else if ( detaisList==null){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Detail list is empty or invalid");
            return;
        }

        try {

            boolean saveOrder = poBO.saveOrder(new OrderDTO(id, date, customerId, detaisList),source);
            if (saveOrder) {
                resp.setStatus(HttpServletResponse.SC_CREATED);
                resp.getWriter().write("Added order successfully");
            }else {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to save the order");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
