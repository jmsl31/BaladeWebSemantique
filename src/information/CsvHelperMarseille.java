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
import java.util.List;

/**
 *
 * @author johan
 */
public class CsvHelperMarseille extends CsvFileHelper{

    @Override
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
            
            f.setTitre(result[0]);
            f.setRealisateur(result[3]);
            f.setNbjoursTournage(result[1]);
            ListFilm.add(f);
        }

        br.close();
        fr.close();

        return ListFilm;

    }
    
    
    
}
