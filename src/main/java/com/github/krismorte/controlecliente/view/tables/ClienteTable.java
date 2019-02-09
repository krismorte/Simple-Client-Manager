/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.krismorte.controlecliente.view.tables;

import com.github.krismorte.controlecliente.model.Cliente;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 * @date   08/02/2019
 * @author krisnamourtscf <krisnamourt_ti@hotmail.com>
 */
public class ClienteTable extends TablesHelper<Cliente> {

    public ClienteTable() {
        super(Cliente.class, "nome,email,telefone,telefone2,endereco,observacao");
    }

    @Override
    public List search(String t) {
        List resultado = new ArrayList<>();
        for (Cliente cliente : getData()) {
            if (cliente.getNome().toUpperCase().contains(t.toUpperCase())||
                    cliente.getTelefone().contains(t.toUpperCase())||
                    StringUtils.contains( StringUtils.defaultString(cliente.getTelefone2()) , t.toUpperCase())) {
                resultado.add(cliente);
            }
        }
        return resultado;
    }

}
