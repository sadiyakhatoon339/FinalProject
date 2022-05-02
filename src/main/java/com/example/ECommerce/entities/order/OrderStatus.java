package com.example.ECommerce.entities.order;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class OrderStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonIgnore
    @OneToOne
    @MapsId
    private OrderProduct orderProduct;

    @Enumerated(EnumType.STRING)
    private com.example.ECommerce.enums.fromStatus fromStatus;

    @Enumerated(EnumType.STRING)
    private com.example.ECommerce.enums.toStatus toStatus;

    private String transitionNotesComments;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public OrderProduct getOrderProduct() {
        return orderProduct;
    }

    public void setOrderProduct(OrderProduct orderProduct) {
        this.orderProduct = orderProduct;
    }

    public com.example.ECommerce.enums.fromStatus getFromStatus() {
        return fromStatus;
    }

    public void setFromStatus(com.example.ECommerce.enums.fromStatus fromStatus) {
        this.fromStatus = fromStatus;
    }

    public com.example.ECommerce.enums.toStatus getToStatus() {
        return toStatus;
    }

    public void setToStatus(com.example.ECommerce.enums.toStatus toStatus) {
        this.toStatus = toStatus;
    }

    public String getTransitionNotesComments() {
        return transitionNotesComments;
    }

    public void setTransitionNotesComments(String transitionNotesComments) {
        this.transitionNotesComments = transitionNotesComments;
    }
}


