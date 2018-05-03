/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package information;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author johan
 */
public class CsvHelperParis extends CsvFileHelper{
       

    public CsvHelperParis() {
 
    }
 
     public List<Film> readFile(String fileName) throws FileNotFoundException, IOException {
        
        String[] result;
               
        final String completeFileName = getResourcePath(fileName);
        File file = new File(completeFileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        br.readLine();
        
        for (String line = br.readLine(); line != null; line = br.readLine()) {
            Film f = new Film();
            result = line.split(",");
            f.setAdresse(result[2]);
            f.setTitre(result[0]);
            f.setCodePostale(result[5]);
            f.setCoordonneeLat(result[8]);
            f.setCoordonneeLong(result[9]);
            f.setRealisateur(result[1]);
            f.setAnneeTournage(result[6]);
            f.setType(result[4]);
            ListFilm.add(f);
        }

        br.close();
        fr.close();

        return ListFilm;
     }
     

}
