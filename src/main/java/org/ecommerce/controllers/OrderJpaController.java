package org.ecommerce.controllers;

import org.ecommerce.dtos.requests.OrderRequestDTO;
import org.ecommerce.dtos.responses.OrderDTO;
import org.ecommerce.models.services.responses.CreateOrderResponse;
import org.ecommerce.models.requests.*;
import org.ecommerce.models.services.responses.GetAllOrdersResponse;
import org.ecommerce.models.services.responses.GetOrderResponse;
import org.ecommerce.models.services.responses.UpdateOrderResponse;
import org.ecommerce.services.jpa.OrderServiceImpl;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Profile("local")
@RestController
public class OrderJpaController { //implements ControllerOperations<OrderRequestDTO, Long>{ // TODO: Q: Should i refactor this interface to something like ControllerOperations<RequestDTO, ResponseDTO, ID> ?

    private final OrderServiceImpl orderService;

    public OrderJpaController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

//    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/orders")
    public ResponseEntity<CreateOrderResponse> create(@RequestBody CreateRequest<OrderRequestDTO> request) {
        CreateOrderResponse response = orderService.create(request);

        return new ResponseEntity<CreateOrderResponse>(response, HttpStatus.CREATED);
    }

//    @Override
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(path = "/orders/{orderId}")
    public ResponseEntity<OrderDTO> delete(@PathVariable Long orderId) {
        orderService.delete(orderId);
        return new ResponseEntity<OrderDTO>(HttpStatus.OK);
    }

//    @Override
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(path = "/orders")
    public ResponseEntity<UpdateOrderResponse> update(@RequestBody UpdateRequest<OrderRequestDTO, Long> request) {
        UpdateOrderResponse response = orderService.update(request);

        return new ResponseEntity<UpdateOrderResponse>(response, HttpStatus.OK);
    }

//    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/orders")
    public GetAllOrdersResponse get() {
        return orderService.findAll();
    }

//    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/orders/{orderId}")
    public ResponseEntity<GetOrderResponse> get(@PathVariable Long orderId) {
        GetOrderResponse getOrderResponse = orderService.findById(orderId);

        return new ResponseEntity<GetOrderResponse>(getOrderResponse, HttpStatus.OK);
    }

}
