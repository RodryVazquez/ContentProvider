package com.example.rodrigo.contentprovider.Models;

import java.io.Serializable;

public class ContactModel implements Serializable {

    int type;
    String typeOfCall;
    String phone;
    String nameContact;

    public ContactModel(){}
    public ContactModel(int type,String typeOfCall,String phone,String nameContact){
        this.type = type;
        this.typeOfCall = typeOfCall;
        this.phone = phone;
        this.nameContact = nameContact;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTypeOfCall() {
        return typeOfCall;
    }

    public void setTypeOfCall(String typeOfCall) {
        this.typeOfCall = typeOfCall;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNameContact() {
        return nameContact;
    }

    public void setNameContact(String nameContact) {
        this.nameContact = nameContact;
    }
}
