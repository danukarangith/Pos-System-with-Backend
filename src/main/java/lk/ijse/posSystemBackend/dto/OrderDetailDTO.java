package lk.ijse.posSystemBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDetailDTO {
    private String oid;
    private String code;
    private int qty;
    private double payment;
}