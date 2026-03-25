package model;

// Kargo durumlarını oluşrurulduğu sınıf
public enum KargoDurum { // Kolaylık ve güvenlik için enum
    KABUL_EDILDI("Kabul Edildi"),
    HAZIRLANIYOR("Hazırlanıyor"),
    YOLA_CIKTI("Yola Çıktı"),
    SUBEDE("Şubede Bekliyor"),
    DAGITIMDA("Dağıtıma Çıktı"),
    TESLIM_EDILDI("Teslim Edildi"),
    IADE_EDILDI("İade Edildi");

    private String durum;
    // Enum constructor metot
    KargoDurum(String durum) {
        this.durum = durum;
    }
    // Standart metotun overrideı
    @Override
    public String toString() {
        return durum;
    }
}