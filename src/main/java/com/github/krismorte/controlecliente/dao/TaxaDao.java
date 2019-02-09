/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.krismorte.controlecliente.dao;

import com.github.krismorte.controlecliente.model.Taxa;
import java.util.List;

/**
 * @date   08/02/2019
 * @author krisnamourtscf <krisnamourt_ti@hotmail.com>
 */
public class TaxaDao extends GenericDao<Taxa> {
    
    public TaxaDao() {
        super(Taxa.class);
    }
    
    
    public List<Taxa> listAll() {
        beginTransaction();
        List<Taxa> doses = findAll();
        commitAndCloseTransaction();
        return doses;
    }
    
    public void add(Taxa taxa) {
        beginTransaction();
        update(taxa);
        commitAndCloseTransaction();
    }
    
}
