package com.example.myproject.obj;

public class OrdDevices {
    public String key_user;
    public String key_devices;
    public String time;
    public String add;
    public String sl;
    public String color;
    public String status;

    public OrdDevices() {
    }

    public OrdDevices(String key_user, String key_devices, String time, String add, String sl, String color, String status) {
        this.key_user = key_user;
        this.key_devices = key_devices;
        this.time = time;
        this.add = add;
        this.sl = sl;
        this.color = color;
        this.status = status;
    }
}
