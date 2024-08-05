package lk.ijse.posSystemBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderJoinEntity {

    private String orderID;
    private LocalDate orderDate;
    private String customerID;
    private String itemCode;
    private int itemQty;
    private double unitPrice;

}