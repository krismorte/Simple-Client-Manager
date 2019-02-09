/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.krismorte.controlecliente.dao;

import com.github.krismorte.controlecliente.model.Cliente;
import java.util.List;

/**
 *
 * @author krisnamourtscf
 */
public class ClienteDao extends GenericDao<Cliente> {
    
    public ClienteDao() {
        super(Cliente.class);
    }
    
    
    public List<Cliente> listAll() {
        beginTransaction();
        List<Cliente> clientes = findAll();
        commitAndCloseTransaction();
        return clientes;
    }
    
    public void add(Cliente cliente) {
        beginTransaction();
        update(cliente);
        commitAndCloseTransaction();
    }
    
}
