import gui.KargoArayuz;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

// Otomasyonun başlatılacağı ana sınıf
public class Main {
    public static void main(String[] args) {
        // Swing arayüzlerini başlatmak için en güvenli yol
        SwingUtilities.invokeLater(() -> {
            KargoArayuz ekran = new KargoArayuz(); // Ana pencere nesnesi
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); // Modern Java görünümü
                } catch (Exception e) { // Tema hatasının yakalanması
                e.printStackTrace(); // Konsola yazdırma
            }
            ekran.setVisible(true); // Pencerenin açılması
            ekran.setLocationRelativeTo(null); // Pencerenin ekranın ortasında konumlanması
        });
    }
}