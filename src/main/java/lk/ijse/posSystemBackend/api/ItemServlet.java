package lk.ijse.posSystemBackend.api;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.posSystemBackend.bo.BoFactory;
import lk.ijse.posSystemBackend.bo.custom.ItemBO;
import lk.ijse.posSystemBackend.dto.ItemDTO;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/items")
public class ItemServlet extends HttpServlet {

    ItemBO itemBO =  BoFactory.getBoFactory().getBO(BoFactory.BOTypes.ITEM_BO);

    private DataSource source;

    @Override
    public void init() throws ServletException {
        try {
            source = (DataSource) new InitialContext().lookup("java:/comp/env/jdbc/pos");
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try(Connection connection = source.getConnection();) {
            ArrayList<ItemDTO> allitems = itemBO.getAllItems(connection);
            resp.setContentType("application/json");
            Jsonb jsonb = JsonbBuilder.create();
            jsonb.toJson(allitems,resp.getWriter());
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Jsonb jsonb = JsonbBuilder.create();
        ItemDTO itemDTO = jsonb.fromJson(req.getReader(), ItemDTO.class);
        String code = itemDTO.getCode();
        String description = itemDTO.getDescription();
        double unitPrice = itemDTO.getUnitPrice();
        int qty = itemDTO.getQtyOnHand();


        if(code==null || !code.matches("^(P0)[0-9]{3}$")){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Item Code is empty or invalid");
            return;
        } else if (description == null || !description.matches("^[A-Za-z ]{5,}$")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Description is empty or invalid");
            return;
        } else if (unitPrice < 0.0) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unit Price is empty or invalid");
            return;
        }else if (qty < 0 ){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Quantity is empty or invalid");
            return;
        }

        try(Connection connection = source.getConnection();) {

            boolean saveItem = itemBO.saveItem(new ItemDTO(code,description,unitPrice,qty), connection);
            if (saveItem) {
                resp.setStatus(HttpServletResponse.SC_CREATED);
                resp.getWriter().write("Added item successfully");
            }else {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to save the item");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
