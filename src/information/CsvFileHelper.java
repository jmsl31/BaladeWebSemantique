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

public abstract class CsvFileHelper {
    
    List<Film> ListFilm;
     //CSV file header


            
    public CsvFileHelper() {
        ListFilm = new ArrayList<>();
    }
    
    public String getResourcePath(String fileName) {
       final File f = new File("");
       final String dossierPath = f.getAbsolutePath() + File.separator + fileName;
       return dossierPath;
   }
   
   public abstract List<Film> readFile(String fileName) throws FileNotFoundException, IOException;

}
