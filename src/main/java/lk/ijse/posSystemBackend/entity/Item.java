package lk.ijse.posSystemBackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Item {
    private String code;
    private String description;
    private double unitPrice;
    private int qtyOnHand;
}