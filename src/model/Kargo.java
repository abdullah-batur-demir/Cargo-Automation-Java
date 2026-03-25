package model;

// Kargonun ve özelliklerinin tanımlandığı sınıf 
public class Kargo {
    private int deliveryId;
    private KargoDurum status;
    private Musteri sender;
    private Musteri receiver;
    private Kurye courier;
    private String deliveryAddress;

    // Kargo sınıfının constructoru 
    public Kargo(int deliveryId, Musteri sender, Musteri receiver, String deliveryAddress) {
        this.deliveryId = deliveryId;
        this.sender = sender;
        this.receiver = receiver;
        this.deliveryAddress = deliveryAddress;
        this.status = KargoDurum.KABUL_EDILDI;
    }
    // Get ve Set Metotları
    public String getDeliveryAddress() { return deliveryAddress; }
    public void setDeliveryAddress(String deliveryAddress) { this.deliveryAddress = deliveryAddress; }
    public int getDeliveryId() { return deliveryId; }
    public KargoDurum getStatus() { return status; }
    public void setStatus(KargoDurum status) { this.status = status; }
    public Musteri getSender() { return sender; }
    public Musteri getReceiver() { return receiver; }
    public Kurye getCourier() { return courier; }
    public void setCourier(Kurye courier) { this.courier = courier; }
}