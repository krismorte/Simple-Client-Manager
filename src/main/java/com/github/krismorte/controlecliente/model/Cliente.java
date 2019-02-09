/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.krismorte.controlecliente.model;

import com.github.krismorte.controlecliente.util.NoPhoneNumberException;
import com.towel.el.annotation.Resolvable;
import ezvcard.VCard;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @date 08/02/2019
 * @author krisnamourtscf <krisnamourt_ti@hotmail.com>
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Cliente extends MyEntity {

    @Resolvable
    private String nome;
    @Resolvable
    private String email;
    @Resolvable
    private String telefone;
    @Resolvable
    private String telefone2;
    @Resolvable
    private String endereco;
    @Resolvable
    private String observacao;

    public Cliente(VCard vcard) {
        
        nome =  StringEscapeUtils.unescapeJava((vcard.getFormattedName().getValue()));
        if (!vcard.getEmails().isEmpty()) {
            email = StringEscapeUtils.unescapeJava(vcard.getEmails().get(0).getValue());
        }
        if (!vcard.getTelephoneNumbers().isEmpty()) {
            if(StringUtils.isBlank(vcard.getTelephoneNumbers().get(0).getText())){
                System.out.println("com.github.krismorte.controlecliente.model.Cliente.<init>()");
                throw new NullPointerException();
            }
            telefone = StringEscapeUtils.unescapeJava(vcard.getTelephoneNumbers().get(0).getText());
            
            if (vcard.getTelephoneNumbers().size()>1) {
                telefone2 = StringEscapeUtils.unescapeJava(vcard.getTelephoneNumbers().get(1).getText());
            }
        }else{
            throw new NoPhoneNumberException();
        }
        if (!vcard.getAddresses().isEmpty()) {
            endereco = StringEscapeUtils.unescapeJava(vcard.getAddresses().get(0).getExtendedAddressFull());
        }
    }

}
