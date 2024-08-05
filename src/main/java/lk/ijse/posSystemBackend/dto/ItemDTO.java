package lk.ijse.posSystemBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemDTO {
    private String code;
    private String description;
    private double unitPrice;
    private int qtyOnHand;
}