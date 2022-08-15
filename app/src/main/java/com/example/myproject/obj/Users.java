package com.example.myproject.obj;

public class Users {
    public String avatar;
    public String fullname;
    public String dateofbirth;
    public String sex;
    public String phonenumber;
    public String password;
    public String address;
    public String position;

    public Users() {
    }

    public Users(String avatar, String fullname, String dateofbirth, String sex,  String phonenumber,  String password, String address, String position) {
        this.avatar = avatar;
        this.fullname = fullname;
        this.dateofbirth = dateofbirth;
        this.sex = sex;
        this.phonenumber = phonenumber;
        this.password = password;
        this.address = address;
        this.position = position;
    }
}