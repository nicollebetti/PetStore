package utils;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Data {

    // funcao para ler arquivo JSON
    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    public List<String[]> lerCsv(String caminhoCsv) throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get(caminhoCsv)); // le um texto
        CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build(); // Abre como um csv
        List<String[]> users = csvReader.readAll(); // le todos os dados CSV
        return users;
    }

}
