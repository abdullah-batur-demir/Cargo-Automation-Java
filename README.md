# Java Swing: Kargo Otomasyon Sistemi (Cargo Management System)

Bu proje, kargo gönderim ve takip süreçlerini dijitalleştirmek amacıyla geliştirilmiş, Java Swing kütüphanesi tabanlı bir masaüstü otomasyon sistemidir. Proje, nesne yönelimli tasarımın lojistik sektöründeki gerçek dünya problemlerine uygulanmasını hedefler.

## 🛠️ Sistem Mimarisi ve Tasarım Prensipleri
Uygulama, sürdürülebilir ve genişletilebilir bir yapı için ileri seviye OOP prensipleriyle inşa edilmiştir:

* **Kalıtım (Inheritance):** `Musteri` ve `Kurye` sınıflarındaki kod tekrarını önlemek amacıyla `Kisi` adında **soyut (abstract)** bir üst sınıf tasarlanmıştır.
* **Kapsülleme (Encapsulation):** Veri güvenliği için gönderici, alıcı ve kargo bilgileri private erişim belirteçleri ve getter/setter metotları ile yönetilmektedir.
* **Tip Güvenliği (Enum):** Kargo durumları (`Hazırlanıyor`, `Yola Çıktı`, `Teslim Edildi`) metin yerine **Enum** yapısı ile tutularak yazım hatalarının ve mantıksal açıkların önüne geçilmiştir.
* **Statik Veri Yönetimi:** Veri kalıcılığı (persistence) için merkezi bir `VeriDeposu` sınıfı üzerinden statik listeler kullanılmıştır.

## 🚀 Fonksiyonel Özellikler
* **Kargo Kayıt Paneli:** Gönderici ve alıcı bilgilerinin doğrulanarak sisteme işlenmesi.
* **Dinamik Kurye Ataması:** Mevcut kargo kayıtlarına sistemde tanımlı kuryelerin atanabilmesi.
* **Anlık Durum Takibi:** JTable bileşeni üzerinden tüm kargoların listelenmesi ve durumlarının (`Enum` bazlı) güncellenmesi.
* **Hata Yönetimi (Robustness):** Geçersiz veri girişlerine (ID/Telefon alanlarına metin girilmesi vb.) karşı `JOptionPane` destekli kullanıcı uyarıları.

## 🎨 Kullanıcı Arayüzü (GUI)
* **TabbedPane Yapısı:** Kayıt ve Takip işlemlerinin birbirinden ayrıldığı, kullanıcı dostu sekmeli arayüz.
* **Event-Driven Programming:** Buton etkileşimleri ve tablo güncellemeleri için olay tabanlı programlama modeli.

## 📄 Mühendislik Raporu ve Analiz
Sistemin gereksinim analizini, Use-Case ve Sequence diyagramlarını içeren detaylı teknik raporuna buradan ulaşabilirsiniz:
👉 **[Kargo Otomasyonu Teknik Raporu (PDF)](./docs/Abdullah_Batur_Demir_Cargo_Automation_Report.pdf)**

## 🛠️ Geliştirme Ortamı
- **Dil:** Java
- **Kütüphane:** Java Swing (GUI)
- **Veri Yapısı:** ArrayList (Bellek tabanlı depolama)
