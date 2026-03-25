package model;

// Kişilerin kalıtım alacağı soyut sınıf
public abstract class Kisi {
    
    public String name;
    public long phone;

    public Kisi(String name, long phone) {
        this.name = name;
        this.phone = phone;
    }

}
