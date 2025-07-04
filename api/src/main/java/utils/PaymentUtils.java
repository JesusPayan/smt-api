package utils;

import com.smtcoders.api.entity.PaymentStatus;

public class PaymentUtils {

    public boolean getValidateStatus(String status){
        return status.equals(PaymentStatus.COMPLETO.name()) || status.equals(PaymentStatus.CORRIENTE.name());
    }

}
