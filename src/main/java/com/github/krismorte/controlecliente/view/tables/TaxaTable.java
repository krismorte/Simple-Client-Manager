/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.krismorte.controlecliente.view.tables;

import com.github.krismorte.controlecliente.model.Taxa;
import java.util.ArrayList;
import java.util.List;

/**
 * @date   08/02/2019
 * @author krisnamourtscf <krisnamourt_ti@hotmail.com>
 */
public class TaxaTable extends TablesHelper<Taxa> {

    public TaxaTable() {
        super(Taxa.class, "nome,valor");
    }

    @Override
    public List search(String t) {
        List resultado = new ArrayList<>();
        for (Taxa taxa : getData()) {
            if (taxa.getNome().toUpperCase().contains(t.toUpperCase())) {
                resultado.add(taxa);
            }
        }
        return resultado;
    }

}
