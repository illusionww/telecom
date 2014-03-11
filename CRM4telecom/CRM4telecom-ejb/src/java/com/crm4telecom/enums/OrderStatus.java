package com.crm4telecom.enums;

import java.util.ArrayList;
import java.util.List;

public enum OrderStatus {

    NEW {
                @Override
                public OrderStatus nextState(OrderEvent event) {
                    if (event == OrderEvent.SENT_TO_TECH_SUPPORT || event == OrderEvent.ENGINEER_APPOINTED) {
                        return OPENED;
                    }
                    return this;
                }

                @Override
                public List<OrderEvent> possibleEvents() {
                    List<OrderEvent> events = new ArrayList<>();
                    events.add(OrderEvent.SENT_TO_TECH_SUPPORT);
                    events.add(OrderEvent.ENGINEER_APPOINTED);

                    return events;
                }
            },
    OPENED {
                @Override
                public OrderStatus nextState(OrderEvent event) {
                    if (event == OrderEvent.DELAY) {
                        return WAITING;
                    } else if (event == OrderEvent.DONE) {
                        return CLOSED;
                    }
                    return this;
                }

                @Override
                public List<OrderEvent> possibleEvents() {
                    List<OrderEvent> events = new ArrayList<>();
                    events.add(OrderEvent.DELAY);
                    events.add(OrderEvent.DONE);

                    return events;
                }
            },
    WAITING {
                @Override
                public OrderStatus nextState(OrderEvent event) {
                    if (event == OrderEvent.READY) {
                        return OPENED;
                    } else if (event == OrderEvent.CANCELLED) {
                        return LOCKED;
                    }
                    return this;
                }

                @Override
                public List<OrderEvent> possibleEvents() {
                    List<OrderEvent> events = new ArrayList<>();
                    events.add(OrderEvent.READY);
                    events.add(OrderEvent.CANCELLED);

                    return events;
                }
            },
    LOCKED {
                @Override
                public OrderStatus nextState(OrderEvent event) {
                    if (event == OrderEvent.READY) {
                        return OPENED;
                    }
                    return this;
                }

                @Override
                public List<OrderEvent> possibleEvents() {
                    List<OrderEvent> events = new ArrayList<>();
                    events.add(OrderEvent.READY);

                    return events;
                }
            },
    CLOSED {
                @Override
                public OrderStatus nextState(OrderEvent event) {
                    return this;
                }

                @Override
                public List<OrderEvent> possibleEvents() {
                    List<OrderEvent> events = new ArrayList<>();

                    return events;
                }
            };

    public abstract OrderStatus nextState(OrderEvent event);

    public abstract List<OrderEvent> possibleEvents();
}