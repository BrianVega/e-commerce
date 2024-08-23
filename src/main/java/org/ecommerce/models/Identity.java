package org.ecommerce.models;

import lombok.Getter;

@Getter
public abstract class Identity {
    private final Long id;

    Identity(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "id=" + id;
    }
}
