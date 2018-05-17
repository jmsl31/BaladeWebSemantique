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
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author johan
 */
public class CsvHelperMarseille extends CsvFileHelper{

    	    private static final String [] FILE_HEADER_MAPPING = {"Titre","Nombre de Jours  de Tournages","Produit Par","Réalisé par","Principaux Interprètes","Date de sortie"};
	    
	    //Student attributes

	    private static final String Movie_Title = "Titre";
	    private static final String Movie_nb_tournage = "Nombre de Jours  de Tournages";
	    private static final String Movie_realisateur = "Réalisé par";
            
    @Override
    public List<Film> readFile(String fileName) throws FileNotFoundException, IOException {
    
         FileReader fileReader = null;      

	        CSVParser csvFileParser = null;      

	        //Creation du format du CVS
                
	        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(FILE_HEADER_MAPPING);
        
        try {
	            //Creation de la liste des films 
	            ListFilm = new ArrayList<Film>();
	            //initialize FileReader object
	            fileReader = new FileReader(fileName);
	            //initialize CSVParser object
	            csvFileParser = new CSVParser(fileReader, csvFileFormat);
                    
                    //Recuperation des enregistrements dans le CSV
	            List csvRecords = csvFileParser.getRecords(); 	             
                    
	            //Lecture des enregistrements du fichier CVS 
	            for (int i = 1; i < csvRecords.size(); i++) {
	                CSVRecord record = (CSVRecord) csvRecords.get(i);
	                //Creation de l'objet Film
                        //Film (String adresse, String codePostale, String coordonneeLat, String coordonneeLong, List<String> acteurs) {
    
                        Film film = new Film("Marseille",record.get(Movie_Title), 
                                             "","","","",new ArrayList<String>(),
                                             record.get(Movie_realisateur),"",
                                             new ArrayList<String>(),"","","",
                                             record.get(Movie_nb_tournage),"","","","",
                                             new ArrayList<String>());
                        ListFilm.add(film);
                    }
        } 
	catch (Exception e) {
	            System.out.println("Error in CsvFileReader !!!");
	            e.printStackTrace();
	        } finally {
	            try {
                        fileReader.close();
	                csvFileParser.close();
	            } catch (IOException e) {
	                System.out.println("Error while closing fileReader/csvFileParser !!!");
	                e.printStackTrace();
	            }
	        }
        return ListFilm;

    }
    
    
    
}
