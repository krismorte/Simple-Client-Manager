/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.krismorte.controlecliente;

import com.alee.laf.WebLookAndFeel;
import com.github.krismorte.controlecliente.view.TelaPrincipal;
import ezvcard.Ezvcard;
import ezvcard.VCard;
import java.io.File;
import java.util.List;

/**
 * @date 08/02/2019
 * @author krisnamourtscf <krisnamourt_ti@hotmail.com>
 */
public class Main {

    public static void main(String[] args) {
        WebLookAndFeel.install();

        try {

            List<VCard> list = Ezvcard.parse(new File("C:\\Users\\krisnamourtscf\\Downloads\\00001.vcf")).all();
            System.out.println("S: " + list.size());
            VCard vcard = Ezvcard.parse(new File("C:\\Users\\krisnamourtscf\\Downloads\\00001.vcf")).first();
            System.out.println(vcard.toString());

            String fullName = vcard.getFormattedName().getValue();
            String lastName = vcard.getStructuredName().getFamily();
        } catch (Exception e) {
            e.printStackTrace();
        }
        TelaPrincipal tela = new TelaPrincipal();
        tela.setLocationRelativeTo(null);
        tela.setVisible(true);

    }

}
