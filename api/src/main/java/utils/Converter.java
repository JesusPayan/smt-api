package utils;

import com.smtcoders.api.dto.ResourceDTO;
import com.smtcoders.api.dto.UserDTO;
import com.smtcoders.api.entity.Payment;
import com.smtcoders.api.entity.Resource;
import com.smtcoders.api.entity.User;

import java.util.Optional;

public class Converter {

    public static Resource converToEntity(ResourceDTO resourceDTO){
        Resource entity = new Resource(
                resourceDTO.getName(),
                resourceDTO.getCarrierPath(),
                resourceDTO.getTecnologyStack(),
                resourceDTO.getType(),
                resourceDTO.getScore(),
                resourceDTO.getDescription(),
                resourceDTO.getAvailable());

        return entity;
    }
    public static Payment converToEntity(Payment payment){
        Payment entity = new Payment();
                entity.setId(payment.getId());
                entity.setPaymentDate(payment.getPaymentDate());
                entity.setPaymentAmount(payment.getPaymentAmount());
        return entity;
    }
//    public static UserDTO converteToDTO(Optional<User> user){
//        UserDTO currentUser = new UserDTO();
//        currentUser.setId(user);
//        currentUser.setName(user.getName());
//        currentUser.setEmail(user.getEmail());
//        currentUser.setPhone(user.getPhone());
//        currentUser.setCreateTimeStamp(user.getCreateTimeStamp());
//        currentUser.setPassword(user.getPassword());
//        currentUser.setUserPictureUrl(user.getUserPictureUrl());
//        currentUser.setScore(user.getScore());
//        currentUser.setNextPayment(user.getNextPayment());
//        currentUser.setPaymentStatus(user.getPaymentStatus());
//        currentUser.setLastAssistance(user.getLastAssistance());
//        currentUser.setAssistanceCounter(user.getAssistanceCounter());
//        return currentUser;
//    }
}
