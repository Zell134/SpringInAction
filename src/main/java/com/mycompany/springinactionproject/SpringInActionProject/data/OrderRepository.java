package com.mycompany.springinactionproject.SpringInActionProject.data;

import com.mycompany.springinactionproject.SpringInActionProject.models.Order;

public interface OrderRepository {

    Order save (Order order);
}
