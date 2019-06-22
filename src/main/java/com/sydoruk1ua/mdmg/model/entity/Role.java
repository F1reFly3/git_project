package com.sydoruk1ua.mdmg.model.entity;

import java.util.Objects;

public class Role {
    private final String type;
    private Integer id;

    public Role(Integer id, String type) {
        this.id = id;
        this.type = type;
    }

    public Role(String type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Role role = (Role) o;
        return Objects.equals(id, role.id) &&
                Objects.equals(type, role.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type);
    }
}
