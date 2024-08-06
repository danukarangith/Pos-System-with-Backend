package lk.ijse.posSystemBackend.bo;

import lk.ijse.posSystemBackend.bo.custom.impl.CustomerBOImpl;
import lk.ijse.posSystemBackend.bo.custom.impl.ItemBOImpl;
import lk.ijse.posSystemBackend.bo.custom.impl.OrderDetailBOImpl;
import lk.ijse.posSystemBackend.bo.custom.impl.PurchaseOrderBOImpl;

public class BoFactory {
    private static BoFactory boFactory;

    private BoFactory() {

    }

    public static BoFactory getBoFactory() {
        return (boFactory == null) ? boFactory = new BoFactory() : boFactory;
    }

    public enum BOTypes {
        CUSTOMER_BO, ITEM_BO, PURCHASE_ORDER_BO,Detail_BO
    }

    public <T extends SuperBO> T getBO(BOTypes boTypes) {
        switch (boTypes) {
            case CUSTOMER_BO:
                return (T) new CustomerBOImpl();
            case ITEM_BO:
                return (T) new ItemBOImpl();
            case PURCHASE_ORDER_BO:
                return (T) new PurchaseOrderBOImpl();
            case Detail_BO:
                return (T) new OrderDetailBOImpl();
            default:
                return null;
        }
    }
}
