/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package information;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONObject;

/**
 *
 * @author johan
 */

public class Film {
    
        String titre;
        String type;
        String annee;
        String note;
        String duree;
        List<String> genre;
        String realisateur;
        String affiche;
        List<String> origine;
        String resume;
        String imdbId;
        String anneeTournage;
        String nbjoursTournage;
        String adresse;
        String codePostale;
        String coordonneeLat;
        String coordonneeLong;
        List<String> acteurs;
        private String baseUrl = "http://www.omdbapi.com/?y=&plot=short&r=json&apikey=b17302a6&t=";//base de l'url correspondant à la requête get qui devra être compléter avec le nom du film à considérer

    public Film() {
        this.titre = "";
        this.type = "";
        this.annee = "";
        this.note = "";
        this.duree = "";
        this.genre = new ArrayList<>();
        this.realisateur = "";
        this.affiche = "";
        this.origine = new ArrayList<>();
        this.resume = "";
        this.imdbId = "";
        this.anneeTournage = "";
        this.nbjoursTournage = "";
        this.adresse = "";
        this.codePostale = "";
        this.coordonneeLat = "";
        this.coordonneeLong = "";
        this.acteurs = new ArrayList<>();
    }

    public Film(String titre, String type, String annee, String note, String duree, List<String> genre, String realisateur, String affiche, List<String> origine, String resume, String imdbId, String anneeTournage, String nbjoursTournage, String adresse, String codePostale, String coordonneeLat, String coordonneeLong, List<String> acteurs) {
        this.titre = titre;
        this.type = type;
        this.annee = annee;
        this.note = note;
        this.duree = duree;
        this.genre = genre;
        this.realisateur = realisateur;
        this.affiche = affiche;
        this.origine = origine;
        this.resume = resume;
        this.imdbId = imdbId;
        this.anneeTournage = anneeTournage;
        this.nbjoursTournage = nbjoursTournage;
        this.adresse = adresse;
        this.codePostale = codePostale;
        this.coordonneeLat = coordonneeLat;
        this.coordonneeLong = coordonneeLong;
        this.acteurs = acteurs;
    }

    public String getCoordonneeLat() {
        return coordonneeLat;
    }

    public void setCoordonneeLat(String coordonneeLat) {
        this.coordonneeLat = coordonneeLat;
    }

    public String getCoordonneeLong() {
        return coordonneeLong;
    }

    public void setCoordonneeLong(String coordonneeLong) {
        this.coordonneeLong = coordonneeLong;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public String getTitre() {
        return titre;
    }

    public String getAnnee() {
        return annee;
    }

    public String getNote() {
        return note;
    }

    public String getDuree() {
        return duree;
    }

    public List<String> getGenre() {
        return genre;
    }

    public String getRealisateur() {
        return realisateur;
    }

    public String getAffiche() {
        return affiche;
    }

    public List<String> getOrigine() {
        return origine;
    }

    public String getResume() {
        return resume;
    }

    public String getImdbId() {
        return imdbId;
    }

    public String getAnneeTournage() {
        return anneeTournage;
    }

    public String getNbjoursTournage() {
        return nbjoursTournage;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getCodePostale() {
        return codePostale;
    }

    public List<String> getActeurs() {
        return acteurs;
    }

    public String getBaseUrl() {
        return baseUrl;
    }
	
    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public void setGenre(String genre) {
        String[] temp;
        temp = genre.split(",");
        for (String string : temp) {
           this.genre.add(string);
        }
        
    }

    public void setRealisateur(String realisateur) {
        this.realisateur = realisateur;
    }

    public void setAffiche(String affiche) {
        this.affiche = affiche;
    }

    public void setOrigine(String origine) {
        String[] temp;
        temp = origine.split(",");
        for (String string : temp) {
           this.origine.add(string);
        }
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public void setAnneeTournage(String anneeTournage) {
        this.anneeTournage = anneeTournage;
    }

    public void setNbjoursTournage(String nbjoursTournage) {
        this.nbjoursTournage = nbjoursTournage;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setCodePostale(String codePostale) {
        this.codePostale = codePostale;
    }


    public void setActeurs(String acteurs) {
        String[] temp;
        temp = acteurs.split(",");
        for (String string : temp) {
           this.acteurs.add(string);
        }
    }

    public void GetFilmInfo()
    { 

    //permet pour un titre de film de récupérer un hachage contenant les couples (propriété du film / valeur) retournés par OMDB
		
		 URL url;
	      HttpURLConnection conn;
	      BufferedReader rd;
	      String line;
	      String result = "";
	try {
	         url = new URL(this.baseUrl+URLEncoder.encode(getTitre(), "UTF-8"));
	         conn = (HttpURLConnection) url.openConnection();
	         conn.setRequestMethod("GET");
	         rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	         while ((line = rd.readLine()) != null) {
	            result += line;
	         }
	         rd.close();
	         
         JSONObject obj = new JSONObject(result);
         
         this.setAnnee(obj.getString("Year"));
         this.setActeurs(obj.getString("Actors"));
         this.setAffiche(obj.getString("Poster"));
         this.setDuree(obj.getString("Runtime"));
         this.setGenre(obj.getString("Genre"));
         this.setImdbId(obj.getString("imdbID"));
         this.setNote(obj.getString("imdbRating"));
         this.setOrigine(obj.getString("Country"));
         this.setRealisateur(obj.getString("Director"));
         this.setResume(obj.getString("Plot"));
               
	      } catch (IOException e) {
	         e.printStackTrace();
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
		
	}
    
        
   
}
