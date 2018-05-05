package graetap3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
 
/**
 * Klasa czytaj¹ca pliki tekstowe u¿ywane w grze
 * 
 */
public class ObslugaPlikow {
 
    private String data_file_name;
 
    /**
     * Podaj nazwê pliku tekstowego
     * 
     * @param file_name Nazwa pliku tekstowego
     * @throws FileNotFoundException
     */
    public ObslugaPlikow(String file_name) throws FileNotFoundException{
        data_file_name = file_name;
    }
 
    /**
     * Policz wszystkie linie w pliku tekstowym
     * 
     * @return Liczbê linii w tekœcie
     * @throws IOException
     */
    public int countLines() throws IOException{
        FileReader file_reader = new FileReader(data_file_name);
        BufferedReader buffered_reader = new BufferedReader(file_reader);
        @SuppressWarnings("unused")
        String line;
        int count=0;
 
        while((line = buffered_reader.readLine()) != null) {
            count++;
        } 
 
        buffered_reader.close();
        return count;
    }
 
    /**
     * Odczytanie ca³ego pliku tekstowego
     * 
     * @return All lines in a string array
     * @throws IOException
     */
    public String[] readWholeFile() throws IOException {
        FileReader file_reader = new FileReader(data_file_name);
        BufferedReader buffered_reader = new BufferedReader(file_reader);
 
        int number_of_lines = countLines();
        String[] text_data = new String[number_of_lines];
 
        for(int i=0; i<number_of_lines; i++){
            text_data[i] = buffered_reader.readLine();
        }
 
        buffered_reader.close();
 
        return text_data;
    }
 
    /**
     * Dodaje liniê tekstu podanego w parametrze do pliku
     * 
     * @param line
     * @throws IOException
     */
    public void addToFile(String line) throws IOException{
        FileWriter file_writer = new FileWriter(data_file_name);
        BufferedWriter buffered_writer = new BufferedWriter(file_writer);
 
        buffered_writer.write(line);
        buffered_writer.newLine();
 
        buffered_writer.close();
    }
 
    /**
     * Szuka w tekœcie podanego parametru
     * 
     * @param line
     * @return Liczba linii, w których zosta³ znaleziony parametr b¹dŸ -1 jeœli nie znalaz³
     * @throws IOException
     */
    public int findInFile(String line) throws IOException {
        FileReader file_reader = new FileReader(data_file_name);
        BufferedReader buffered_reader = new BufferedReader(file_reader);
        int count = 0;
        String loaded_line;
 
        while((loaded_line = buffered_reader.readLine()) != null) {
            count++;
            if(loaded_line.equals(line)){
                buffered_reader.close();
                return count;
            }
        } 
 
        buffered_reader.close();
 
        return -1;
    }
 
    /**
     * Odczytanie jednej linii
     * 
     * @param line Liczba linii do odczytania.
     * @return Odczytanie linii b¹dŸ zwrócenie 'null' jeœli parametr jest niezgodny 
     * lub wiêkszy ni¿ liczba linii
     * @throws IOException
     */
    public String readOneLine(int line) throws IOException {
        FileReader file_reader = new FileReader(data_file_name);
        BufferedReader buffered_reader = new BufferedReader(file_reader);
        int count = 0;
        String loaded_line;
 
        while((loaded_line = buffered_reader.readLine()) != null) {
            count++;
            if(count==line){
                buffered_reader.close();
                return loaded_line;
            }
        } 
 
        buffered_reader.close();
 
        return null;
    }
 
   
 
    /**
     * Pobieranie danych z pliku konfiguracyjnego.
     * 
     * @param name Nazwa danej.
     * @return Nazwa danej.
     * @throws IOException
     */
    public String getSetting(String name) throws IOException {
        return readOneLine(findInFile("#"+name)+1);
    }
}