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
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = source.getConnection();){

            ArrayList<CustomerDTO> allCustomers = customerBO.getAllCustomers(connection);
            resp.setContentType("application/json");
            Jsonb jsonb = JsonbBuilder.create();
            jsonb.toJson(allCustomers,resp.getWriter());
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Jsonb jsonb = JsonbBuilder.create();
        CustomerDTO customerDTO = jsonb.fromJson(req.getReader(), CustomerDTO.class);
        String id = customerDTO.getId();
        String name = customerDTO.getName();
        String address = customerDTO.getAddress();
        double salary = customerDTO.getSalary();


//        if(id==null || !id.matches("^(C0)[0-9]{3}$")){
//            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID is empty or invalid");
//            return;
//        } else if (name == null || !name.matches("^[A-Za-z ]{5,}$")) {
//            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Name is empty or invalid");
//            return;
//        } else if (address == null) {
//            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Address is empty or invalid");
//            return;
//        }else if (salary < 0.0 ){
//            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Salary is empty or invalid");
//            return;
//        }
        try ( Connection connection = source.getConnection();){

            boolean saveCustomer = customerBO.saveCustomer(new CustomerDTO(id, name, address, salary),connection);
            if (saveCustomer) {
                resp.setStatus(HttpServletResponse.SC_CREATED);
                resp.getWriter().write("Added customer successfully");
            }else {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to save the customer");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Jsonb jsonb = JsonbBuilder.create();
        CustomerDTO customerDTO = jsonb.fromJson(req.getReader(), CustomerDTO.class);
        String id = customerDTO.getId();
        String name = customerDTO.getName();
        String address = customerDTO.getAddress();
        double salary = customerDTO.getSalary();

        try ( Connection connection = source.getConnection();){
            boolean updateCustomer = customerBO.updateCustomer(new CustomerDTO(id, name, address, salary),connection);
            if (updateCustomer) {
                resp.setStatus(HttpServletResponse.SC_CREATED);
                resp.getWriter().write("Updated customer successfully");
            }else {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to update the customer");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    }
