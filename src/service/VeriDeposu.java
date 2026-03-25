package service;

import model.*;
import java.util.ArrayList;
import java.util.List;

// Kargo ve kurye listesinin bulunduğu sınıf
public class VeriDeposu {
    public static List<Kargo> kargoListesi = new ArrayList<>(); // Kargo listesi
    public static List<Kurye> kuryeListesi = new ArrayList<>(); // Kurye listesi

    // Hazır kuryeler eklendi
    static {
        kuryeListesi.add(new Kurye("Abdullah Batur DEMİR", 5051112222L, 501));
        kuryeListesi.add(new Kurye("Berat Osman KONCA", 5053334444L, 502));
        kuryeListesi.add(new Kurye("Saliha Nur ÜNSAL", 5055556666L, 503));
        kuryeListesi.add(new Kurye("Simge ZEREPCAN", 5057778888L, 504));
    }
    // Kargo nesnesi oluşrulup listeye eklenmesi
    public static void kargoEkle(int id, Musteri gonderen, Musteri alici, String adres, Kurye kurye) {
        Kargo yeniKargo = new Kargo(id, gonderen, alici, adres);
        yeniKargo.setCourier(kurye); // Kurye ataması
        kargoListesi.add(yeniKargo);
    }
    // Seçilen indexteki kargonun silinmesi
    public static void kargoSil(int index) {
    if (index >= 0 && index < kargoListesi.size()) {
        kargoListesi.remove(index);
    }
}
}