package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.*;
import service.VeriDeposu;
import java.awt.*;

// JFrame arayüzünün oluşturulduğu sınıf

public class KargoArayuz extends JFrame {
    private DefaultTableModel kargoModel;
    private JComboBox<String> kuryeSecimi;
    private JTable table;
    
    // Arayüzün constructoru 
    public KargoArayuz() {
        setTitle("Kargo Otomasyonu"); // Genel başlık
        setSize(1200, 600); // Genel boyut
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Programın tamamen sonlanmasını sağlayan kod

        JTabbedPane tabbedPane = new JTabbedPane(); // Sekmeli yapı

        // 1. Kargo Kayıt sekmesi
        tabbedPane.addTab("Kargo Kayıt", createKargoKayitPanel());

        // 2. Kargo Takip & Güncelle sekmesi
        tabbedPane.addTab("Kargo Takip & Güncelle", createKargoTakipPanel());

        add(tabbedPane); // Sekmeler ana pencereye eklendi.
        tasarimiUygula(); // İstenilen tasarımı uygulayan metot
    }

    private JPanel createKargoKayitPanel() { // Kargo Kayıt sekmesinin oluşturulması
        JPanel panel = new JPanel(new GridLayout(9, 2, 15, 15)); // Panel düzeni
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50)); // Kenarlar arası boşluk
        panel.setBackground(Color.WHITE); // Beyaz arka plan
        // Kullanıcıdan veri alınacak metin giriş kutularını
        JTextField txtId = new JTextField();
        JTextField txtGonderici = new JTextField();
        JTextField txtGondericiTel = new JTextField();
        JTextField txtAlici = new JTextField();
        JTextField txtAliciTel = new JTextField();
        JTextField txtAdres = new JTextField();

        kuryeSecimi = new JComboBox<>(); // Kurye seçimi listesi
        for(Kurye k : VeriDeposu.kuryeListesi) { // Kayıtlı kuryeler listeye ekleniyor.
            kuryeSecimi.addItem(k.getName());
        }

        // "Sisteme Kaydet" butonu ve özellikleri
        JButton btnEkle = new JButton("Sisteme Kaydet");
        btnEkle.setBackground(new Color(46, 204, 113)); // Yeşil buton
        btnEkle.setForeground(Color.WHITE); // Beyaz yazı
        btnEkle.setFocusPainted(false); // Mavi çerçeveyi kaldırır

        // Hazırlanan bileşenleri (Etiketler ve Metin Kutuları) sırasıyla panele eklenmesi
        panel.add(new JLabel(" Kargo ID:")); panel.add(txtId);
        panel.add(new JLabel(" Gönderen Adı:")); panel.add(txtGonderici);
        panel.add(new JLabel(" Gönderen Tel:")); panel.add(txtGondericiTel);
        panel.add(new JLabel(" Alıcı Adı:")); panel.add(txtAlici);
        panel.add(new JLabel(" Alıcı Tel:")); panel.add(txtAliciTel);
        panel.add(new JLabel(" Teslimat Adresi:")); panel.add(txtAdres);
        panel.add(new JLabel(" Kurye Seç:")); panel.add(kuryeSecimi);
        panel.add(new JLabel(" İşlem:")); panel.add(btnEkle);

        // Butona basıldığında yaşanacak olan kaydetme işlemi
        btnEkle.addActionListener(e -> {
            try {
                // Boş Alan Kontrolü
                if (txtId.getText().trim().isEmpty() || 
                txtGonderici.getText().trim().isEmpty() || 
                txtGondericiTel.getText().trim().isEmpty() || 
                txtAlici.getText().trim().isEmpty() || 
                txtAliciTel.getText().trim().isEmpty() || 
                txtAdres.getText().trim().isEmpty()) {
                // Hata Önleme
                JOptionPane.showMessageDialog(this, "Lütfen tüm alanları doldurunuz!", "Eksik Bilgi", JOptionPane.WARNING_MESSAGE);
                return;
                }

                // Sayısal Verilerin Dönüştürülmesi
                int id = Integer.parseInt(txtId.getText());
                long gTel = Long.parseLong(txtGondericiTel.getText()); 
                long aTel = Long.parseLong(txtAliciTel.getText());
                String adresVerisi = txtAdres.getText();
                

                // Girilen bilgilerle göre müşteri ve alıcı nesneleri oluşturuluyor.
                Musteri g = new Musteri(txtGonderici.getText(), gTel);
                Musteri a = new Musteri(txtAlici.getText(), aTel);

                // Seçilen kuryenin ve tüm bilgilerin VeriDeposu.java'daki listeye kaydı
                Kurye seciliKurye = VeriDeposu.kuryeListesi.get(kuryeSecimi.getSelectedIndex());
                VeriDeposu.kargoEkle(id, g, a, adresVerisi, seciliKurye);

                tabloyuGuncelle(); // Takip sekmesinde tabloya ekleme yapan metot

                // Kayıttan sonra kutuların temizlenmesi
                txtId.setText("");
                txtGonderici.setText("");
                txtGondericiTel.setText("");
                txtAlici.setText("");
                txtAliciTel.setText("");
                txtAdres.setText("");

                JOptionPane.showMessageDialog(this, "Kargo başarıyla kaydedildi!");
                
            } catch (NumberFormatException ex) { // Hata Önleme
            JOptionPane.showMessageDialog(this, "ID ve Telefon alanlarına sadece sayı girilmelidir!", "Format Hatası", JOptionPane.ERROR_MESSAGE);
            }
        });

    return panel;
    }
    // Tasarımın oluşturulması
    private void tasarimiUygula() {

        // Sütunların genişlikleri manuel ayarlandı.
        table.getColumnModel().getColumn(0).setPreferredWidth(10);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);
        table.getColumnModel().getColumn(3).setPreferredWidth(325);
        table.getColumnModel().getColumn(4).setPreferredWidth(80);
        table.getColumnModel().getColumn(5).setPreferredWidth(40);
        table.getColumnModel().getColumn(6).setPreferredWidth(50);

        // Yazı tipi ve genel renkler
        Font anaFont = new Font("Segoe UI", Font.PLAIN, 14); // Seçilen yazı tipi
        UIManager.put("Label.font", anaFont); // Etikete uygulandı.
        UIManager.put("TextField.font", anaFont); // Metin kutularına uygulandı.
        UIManager.put("Button.font", new Font("Segoe UI", Font.BOLD, 12)); // Butona uygulandı.

        // Tablonun detayları
        table.setRowHeight(25); // Satır yüksekliği
        table.setIntercellSpacing(new Dimension(10, 5)); // Hücreler arası boşluk
        table.setSelectionBackground(new Color(72, 209, 204)); // Tablonun arka plan rengi
        
        // Tablo başlığı 
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13)); // Font ve boyut
        table.getTableHeader().setBackground(new Color(48, 213, 200)); // Arka plan rengi
        table.getTableHeader().setForeground(Color.WHITE); // Yazı rengi
    }

    private JPanel createKargoTakipPanel() { // Kargo takip sekmesinin oluşturulması
        JPanel panel = new JPanel(new BorderLayout());
        table = new JTable(kargoModel);
        

        // Tablo Kurulumu
        String[] cols = {"ID", "Gönderen", "Alıcı", "Adres", "Kurye", "Kurye Tel", "Durum"};
        
        kargoModel = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {return false;} // Metinlerin elle değişmesi engellendi
        };

        table = new JTable(kargoModel);
        table.getTableHeader().setReorderingAllowed(false); // Sütun başlıklarını sürükleyerek yerlerinin değiştirilmesi engellendi
        panel.add(new JScrollPane(table), BorderLayout.CENTER); // Tablonun panele ve merkesine eklenmesi
        
        // Durum güncelleme ve silme 
        JPanel pnlAlt = new JPanel(new FlowLayout()); // Alt panel
        
        // Kargo durumnlarının listeli oluşumu 
        JComboBox<KargoDurum> comboDurumlar = new JComboBox<>(KargoDurum.values());
        JButton btnGuncelle = new JButton("Durumu Güncelle");

        // Butona basıldığında yaşanacak olan güncelleme işlemi
        btnGuncelle.addActionListener(e -> {
            int seciliSatir = table.getSelectedRow(); // Seçilen satır
            if (seciliSatir != -1) { 
                // Veri deposundaki kargo nesnesinin bulunması
                Kargo kargo = VeriDeposu.kargoListesi.get(seciliSatir);
                // Yeni durum ataması
                KargoDurum yeniDurum = (KargoDurum) comboDurumlar.getSelectedItem();
                kargo.setStatus(yeniDurum);
                
                tabloyuGuncelle(); // Tablonun yenilenmesi
                JOptionPane.showMessageDialog(this, "Kargo durumu '" + yeniDurum + "' olarak güncellendi."); // Ekrana bilgi verilmesi
            } else { // Satır seçilmezse
                JOptionPane.showMessageDialog(this, "Lütfen önce tablodan bir kargo seçin!");
            }
        });

        JButton btnSil = new JButton("Kargoyu Sil"); // Sil butonu
        btnSil.setBackground(new Color(255, 100, 100)); // buton rengi

        // Butona basıldığında yaşanacak olan silme işlemini
        btnSil.addActionListener(e -> {
            int seciliSatir = table.getSelectedRow(); // Seçilen satır
            if (seciliSatir != -1) { // Silme işlemi için onay mesajı
                int cevap = JOptionPane.showConfirmDialog(this, "Bu kargo kaydını silmek istediğinize emin misiniz?", "Silme Onayı", JOptionPane.YES_NO_OPTION);
                if (cevap == JOptionPane.YES_OPTION) { // Onay alındı
                    VeriDeposu.kargoSil(seciliSatir); // Verinin silinmesi
                    tabloyuGuncelle(); // Tablonun yenilenmesi
                }
            } else { // Satır seçilmezse
                JOptionPane.showMessageDialog(this, "Lütfen silmek istediğiniz kargoyu tablodan seçin!");
            }
        });

        // Butonların panele eklenmesi 
        pnlAlt.add(new JLabel("Yeni Durum:"));
        pnlAlt.add(comboDurumlar);
        pnlAlt.add(btnGuncelle);
        pnlAlt.add(btnSil);
        
        panel.add(pnlAlt, BorderLayout.SOUTH); // Alt panelin yeri (en alt)
        return panel;
    }

    // Tablonun yenilenme metotu
    private void tabloyuGuncelle() {
        
        kargoModel.setRowCount(0); // Verilerin silinmesi
        // VeriDeposundaki kargo nesnelerinin döngüsü
        for(Kargo k : VeriDeposu.kargoListesi) {
            // Yeni kargo satırının oluşturulup eklenmesi
            kargoModel.addRow(new Object[]{
                k.getDeliveryId(), // Kargo ID
                k.getSender().getName() + " (" + k.getSender().getPhone() + ")", // Gönderen adı ve telefon numarası 
                k.getReceiver().getName() + " (" + k.getReceiver().getPhone() + ")", // Alıcı adı ve telefon numarası
                k.getDeliveryAddress(), // İletilecek adres
                k.getCourier().getName(), // Kurye adı
                k.getCourier().getPhone(), // Kurye telefon numarası
                k.getStatus().toString() // Kargo durumu ve enumın toString() metotuyla çağırılması
            });
        
        }
    }

}
