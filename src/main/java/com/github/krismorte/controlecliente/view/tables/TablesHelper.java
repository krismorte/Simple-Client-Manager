/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.krismorte.controlecliente.view.tables;

import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * @date   08/02/2019
 * @author krisnamourtscf <krisnamourt_ti@hotmail.com>
 */
public abstract class TablesHelper<T> {

    protected JScrollPane scroll = new JScrollPane();
    protected AnnotationResolver resolver;
    protected ObjectTableModel<T> tableModel;
    protected JTable jTable;
    protected JPanel jPanel;
    protected List list;

    public TablesHelper(Class<T> entityClass) {
        resolver = new AnnotationResolver(entityClass);
    }

    public TablesHelper(Class<T> entityClass, String columns) {
        resolver = new AnnotationResolver(entityClass);
        tableModel = new ObjectTableModel<T>(resolver, columns);
    }

    public void setColumns(String columns) {
        tableModel = new ObjectTableModel<T>(resolver, columns);
    }

    public T getSelected() {
        return tableModel.getValue(jTable.getSelectedRow());
    }

    public List<T> getSelecteds() {
        return tableModel.getList(jTable.getSelectedRows());
    }

    public List<T> getData() {
        return tableModel.getData();
    }

    public JTable show() {
        tableModel.setData(list);
        jTable = new JTable(tableModel);
        scroll.setViewportView(jTable);
        //jTable.setPreferredSize(new Dimension(400, 200));
        jPanel.removeAll();
        jPanel.setLayout(new GridLayout(1, 1));
        jPanel.add(scroll);
        return jTable;
    }

    public abstract List search(String t);

    public void setList(List list) {
        this.list = list;
    }

    public List getList() {
        return list;
    }

    public void setJPanel(JPanel jPanel) {
        this.jPanel = jPanel;
    }

    public void setColEditable(int col, Boolean enable) {
        tableModel.setColEditable(col, enable);
    }

}
