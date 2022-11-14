package com.example.backend.service;

import com.example.backend.configuration.JwtRequestFilter;
import com.example.backend.dao.OrderDetailDao;
import com.example.backend.dao.ProductDao;
import com.example.backend.dao.UserDao;
import com.example.backend.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService {

    private static  String ORDER_PLACED = "Placed";
    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;
    public void placeOrder(OrderInput orderInput){
        List<OrderProductQuantity> productQuantityList =  orderInput.getOrderProductQuantityList();

        for(OrderProductQuantity ord: productQuantityList){
            Product product = productDao.findById(ord.getProductId()).get();

           // System.out.println("--------- 1"+product);

            String currentUser = JwtRequestFilter.CURRENT_USER;
            User user = userDao.findById(currentUser).get();

           // System.out.println("--------- 2"+user);

            OrderDetail orderDetail = new OrderDetail(

                    orderInput.getFullName(),
                    orderInput.getFullAddress(),
                    orderInput.getContactNumber(),
                    orderInput.getAlternativeContactNumber(),
                    ORDER_PLACED,
                    product.getProductDiscountedPrice() * ord.getQuantity(),
                    product,
                    user

            );
            //System.out.println("--------- 3"+orderDetail);

            orderDetailDao.save(orderDetail);


        }
    }
}
