/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.krismorte.controlecliente.model;

import com.towel.el.annotation.Resolvable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

/**
 * @date   08/02/2019
 * @author krisnamourtscf <krisnamourt_ti@hotmail.com>
 */
@Entity
@Getter
@Setter
public class Taxa extends MyEntity{
    @Resolvable
    private String nome;
    @Resolvable
    private BigDecimal valor;
    
}
