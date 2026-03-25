package model;

// Kurye kişisi 
public class Kurye extends Kisi {

    public int empId;

    public Kurye(String name, long phone, int empId){
        super(name,phone);
        this.empId = empId;
    }
    // Get metotları
    public String getName() {
        return name;
    }

    public long getPhone() {
    return phone;
    }
} 