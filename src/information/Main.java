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
public class Main {
    
    
    public static void main(String[] args) throws IOException {
        List<Film> ListFilmComplet = new ArrayList<>();
        
        CsvHelperMarseille FilmMarseille = new CsvHelperMarseille();
        CsvHelperParis FilmParis = new CsvHelperParis();
        CsvHelperSanFrancisco FilmSanFrancisco = new CsvHelperSanFrancisco();
        int i=0;
       
     //   FilmMarseille.readFile("marseille_tournages_series_2015.csv");
        FilmMarseille.readFile("marseille_tournages_films_2015.csv");        
        
        for (Film film : FilmMarseille.ListFilm) {
           
           ListFilmComplet.add(film);
            System.out.println("Nombre de film:"+ i++);
        }
        
        for (Film film : FilmParis.readFile("film_paris.csv")) {
           
           ListFilmComplet.add(film);
           System.out.println("Nombre de film:"+ i++);
        }
       
        
        for (Film film : FilmSanFrancisco.readFile("film_San_Francisco.csv")) {
      
           ListFilmComplet.add(film);
           System.out.println("Nombre de film:"+ i++);
        }
        i=0;
        for (Film film : ListFilmComplet) {
            film.GetFilmInfo();
           // film.SetAlignementActeur(film.acteurs);
            film.setUpdateFilmOntologie(film);
            System.out.println(ListFilmComplet.get(i).titre +" a été ajouté.");
            i++;
        }
        System.out.println("Nombre de film : " + ListFilmComplet.size());
      
        
    }
    
    
}
