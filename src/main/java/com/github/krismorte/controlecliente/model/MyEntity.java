/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.krismorte.controlecliente.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

/**
 * @date   08/02/2019
 * @author krisnamourtscf <krisnamourt_ti@hotmail.com>
 */
@MappedSuperclass
@Getter
@Setter
public class MyEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    public MyEntity() {
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MyEntity identity = (MyEntity) o;
        return Objects.equals(id, identity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
