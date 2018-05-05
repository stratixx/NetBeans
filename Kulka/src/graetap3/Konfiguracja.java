package graetap3;
import java.io.FileNotFoundException;
import java.io.IOException;
 
/**
 * Klasa czytaj¹ca i prasuj¹ca dane z pliku konfiguracyjnego
 *
 */
 
public class Konfiguracja {
    
    public int width=0, height=0;
 
    public Konfiguracja(){
        ObslugaPlikow config2 = null;
 
        try {
            config2 = new ObslugaPlikow("konfiguracja.txt");
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        
        try {
            this.width = Integer.parseInt(config2.getSetting("PREFERRED WINDOW WIDTH"));
            this.height = Integer.parseInt(config2.getSetting("PREFERRED WINDOW HEIGHT"));
            
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }
    }
 
}