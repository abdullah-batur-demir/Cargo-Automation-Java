package model;

// Müşteri kişisi
public class Musteri extends Kisi {

    
    public Musteri(String name, long phone) {
        super(name,phone);

    }
    // Get Metotları
    public String getName() {
        return name;
    }

    public long getPhone() {
        return phone;
    }

} 