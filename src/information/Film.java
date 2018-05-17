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
    
        String ville;
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
        SparqlClient sparqlClient;
        
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
        sparqlClient = new SparqlClient("localhost:3030/Balade");
    }

    public Film(String ville,String titre, String type, String annee, String note, String duree, List<String> genre, String realisateur, String affiche, List<String> origine, String resume, String imdbId, String anneeTournage, String nbjoursTournage, String adresse, String codePostale, String coordonneeLat, String coordonneeLong, List<String> acteurs) {
        this.ville = ville;
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
    
        public void SetOntologieTitreLieu(String titre,String lieu)
        {
           String query =  "PREFIX : </Balade#>\n"+
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"+
            "PREFIX dbfilm: <http://www.semanticweb.org/johann.meissl/ontologies/2018/0/untitled-ontology-2#>\n"+
            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"+
        
           " INSERT\n" +
                "{\n"+
                "dbfilm:"+titre+" ?real dbfilm:"+lieu+".\n"+
                "dbfilm:"+titre+" rdfs:label "+titre+".\n"+
                "dbfilm:"+lieu+" rdfs:label "+lieu+"@fr.\n"+
                "}\n"+
                "where {\n"+
                "?real rdfs:label \"a pour lieu\"@fr.\n"+
                "}\n";
           
           sparqlClient.update(query);
                        
        }                
        public void SetOntologieType(String titre,String type)
        {
            
           String query =  "PREFIX : </Balade#>\n"+
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"+
            "PREFIX dbfilm: <http://www.semanticweb.org/johann.meissl/ontologies/2018/0/untitled-ontology-2#>\n"+
            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"+
        
           " INSERT\n" +
                "{\n"+
                "dbfilm:"+titre+" ?type dbfilm:Film"+titre+"@fr.\n"+
                "dbfilm:Film"+titre+" rdfs:label "+type+"@fr.\n"+
                
                "}\n"+
                "where {\n"+
                "?type rdfs:label \"apourtype\"@fr."+
                "}\n";
           
           sparqlClient.update(query);
        }
        
        public void SetOntologieDateTournage(String titre,String datetournage)
        {
              String query =  "PREFIX : </Balade#>\n"+
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"+
            "PREFIX dbfilm: <http://www.semanticweb.org/johann.meissl/ontologies/2018/0/untitled-ontology-2#>\n"+
            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"+
        
           " INSERT\n" +
                "{\n"+
                "dbfilm:"+titre+" dbfilm:realiseen dbfilm:"+titre+"date_tournage@fr.\n"+
                "dbfilm:"+titre+"date_tournage@fr rdfs:label "+datetournage+".\n"+
                
                "}\n";
           
           sparqlClient.update(query);
        }
        
        public void SetOntologieAnneeSortie(String titre,String datesortie)
        {
         String query =  "PREFIX : </Balade#>\n"+
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"+
            "PREFIX dbfilm: <http://www.semanticweb.org/johann.meissl/ontologies/2018/0/untitled-ontology-2#>\n"+
            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"+
        
           " INSERT\n" +
                "{\n"+
                "dbfilm:"+titre+" ?sortie dbfilm:"+titre+"annee@fr.\n"+
                "dbfilm:"+titre+"annee rdfs:label "+datesortie+"@fr.\n"+
                
                "}\n"+
                "where {\n"+
                "?sortie rdfs:label \"sortieen\"@fr."+
                "}\n";
           
           sparqlClient.update(query);   
        
        }
        public void SetOntologieNote(String titre,String note)
        {
           String query =  "PREFIX : </Balade#>\n"+
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"+
            "PREFIX dbfilm: <http://www.semanticweb.org/johann.meissl/ontologies/2018/0/untitled-ontology-2#>\n"+
            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"+
        
           " INSERT\n" +
                "{\n"+
                "dbfilm:"+titre+" ?note dbfilm:"+titre+"note@fr.\n"+
                "dbfilm:"+titre+"note rdfs:label "+note+"@fr.\n"+
                
                "}\n"+
                "where {\n"+
                "?note rdfs:label \"apournote\"@fr."+
                "}\n";
           
           sparqlClient.update(query);    
        }
         public void SetOntologieDuree(String titre,String note)
         {
              String query =  "PREFIX : </Balade#>\n"+
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"+
            "PREFIX dbfilm: <http://www.semanticweb.org/johann.meissl/ontologies/2018/0/untitled-ontology-2#>\n"+
            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"+
        
           " INSERT\n" +
                "{\n"+
                "dbfilm:"+titre+" ?duree dbfilm:"+titre+"duree@fr.\n"+
                "dbfilm:"+titre+"note rdfs:label "+note+"@fr.\n"+
                
                "}\n"+
                "where {\n"+
                "?duree rdfs:label \"apourduree\"@fr."+
                "}\n";
           
           sparqlClient.update(query);  
         }
         public void SetOntologieRealisateur(String titre,String realisateur)
         {
               String query =  "PREFIX : </Balade#>\n"+
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"+
            "PREFIX dbfilm: <http://www.semanticweb.org/johann.meissl/ontologies/2018/0/untitled-ontology-2#>\n"+
            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"+
        
           " INSERT\n" +
                "{\n"+
                "dbfilm:"+titre+" ?realis dbfilm:"+titre+"realisateur@fr.\n"+
                "dbfilm:"+titre+"realisateur rdfs:label "+realisateur+"@fr.\n"+
                
                "}\n"+
                "where {\n"+
                "?realis rdfs:label \"estrealise\"@fr."+
                "}\n";
           
           sparqlClient.update(query);  
         }
        public void SetOntologieAffiche(String titre,String affiche)
         {
               String query =  "PREFIX : </Balade#>\n"+
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"+
            "PREFIX dbfilm: <http://www.semanticweb.org/johann.meissl/ontologies/2018/0/untitled-ontology-2#>\n"+
            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"+
        
           " INSERT\n" +
                "{\n"+
                "dbfilm:"+titre+" ?affiche dbfilm:"+titre+"affiche@fr.\n"+
                "dbfilm:"+titre+"affiche rdfs:label "+affiche+"@fr.\n"+
                
                "}\n"+
                "where {\n"+
                "?affiche rdfs:label \"apouraffiche\"@fr."+
                "}\n";
           
           sparqlClient.update(query);  
         }
        public void SetOntologieResume(String titre,String resume)
         {
               String query =  "PREFIX : </Balade#>\n"+
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"+
            "PREFIX dbfilm: <http://www.semanticweb.org/johann.meissl/ontologies/2018/0/untitled-ontology-2#>\n"+
            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"+
        
           " INSERT\n" +
                "{\n"+
                "dbfilm:"+titre+" ?resume dbfilm:"+titre+"resume@fr.\n"+
                "dbfilm:"+titre+"resume rdfs:label "+resume+"@fr.\n"+
                
                "}\n"+
                "where {\n"+
                "?resume rdfs:label \"apouresume\"@fr."+
                "}\n";
           
           sparqlClient.update(query);  
         }
         public void SetOntologieImdbId(String titre,String Id)
         {
               String query =  "PREFIX : </Balade#>\n"+
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"+
            "PREFIX dbfilm: <http://www.semanticweb.org/johann.meissl/ontologies/2018/0/untitled-ontology-2#>\n"+
            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"+
        
           " INSERT\n" +
                "{\n"+
                "dbfilm:"+titre+" ?imdbId dbfilm:"+titre+"imdbId@fr.\n"+
                "dbfilm:"+titre+"imdbId rdfs:label "+Id+"@fr.\n"+
                
                "}\n"+
                "where {\n"+
                "?imdbId rdfs:label \"apourimdbId\"@fr."+
                "}\n";
           
           sparqlClient.update(query);  
         }
          public void SetOntologieNbJour(String titre,String Nb)
         {
               String query =  "PREFIX : </Balade#>\n"+
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"+
            "PREFIX dbfilm: <http://www.semanticweb.org/johann.meissl/ontologies/2018/0/untitled-ontology-2#>\n"+
            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"+
        
           " INSERT\n" +
                "{\n"+
                "dbfilm:"+titre+" ?nbjour dbfilm:"+titre+"Nbjour@fr.\n"+
                "dbfilm:"+titre+"Nbjour rdfs:label "+Nb+"@fr.\n"+
                
                "}\n"+
                "where {\n"+
                "?nbjour rdfs:label \"atournerjour\"@fr."+
                "}\n";
           
           sparqlClient.update(query);  
         }
         public void SetOntologieAdresse(String titre,String adresse)
         {
               String query =  "PREFIX : </Balade#>\n"+
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"+
            "PREFIX dbfilm: <http://www.semanticweb.org/johann.meissl/ontologies/2018/0/untitled-ontology-2#>\n"+
            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"+
        
           " INSERT\n" +
                "{\n"+
                "dbfilm:"+titre+" ?adresse dbfilm:"+titre+"adresse@fr.\n"+
                "dbfilm:"+titre+"adresse rdfs:label "+adresse+"@fr.\n"+
                
                "}\n"+
                "where {\n"+
                "?adresse rdfs:label \"apouradresse\"@fr."+
                "}\n";
           
           sparqlClient.update(query);  
         }
         
         public void SetOntologieZip(String titre,String zip)
         {
               String query =  "PREFIX : </Balade#>\n"+
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"+
            "PREFIX dbfilm: <http://www.semanticweb.org/johann.meissl/ontologies/2018/0/untitled-ontology-2#>\n"+
            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"+
        
           " INSERT\n" +
                "{\n"+
                "dbfilm:"+titre+" ?zip dbfilm:"+titre+"zip@fr.\n"+
                "dbfilm:"+titre+"zip rdfs:label "+zip+"@fr.\n"+
                
                "}\n"+
                "where {\n"+
                "?zip rdfs:label \"apourzip\"@fr."+
                "}\n";
           
           sparqlClient.update(query);  
         }
          public void SetOntologieCoordLat(String titre,String coordlat)
         {
               String query =  "PREFIX : </Balade#>\n"+
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"+
            "PREFIX dbfilm: <http://www.semanticweb.org/johann.meissl/ontologies/2018/0/untitled-ontology-2#>\n"+
            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"+
        
           " INSERT\n" +
                "{\n"+
                "dbfilm:"+titre+" ?coordlat dbfilm:"+titre+"coordlat@fr.\n"+
                "dbfilm:"+titre+"coordlat rdfs:label "+coordlat+"@fr.\n"+
                
                "}\n"+
                "where {\n"+
                "?cordlat rdfs:label \"apourlat\"@fr."+
                "}\n";
           
           sparqlClient.update(query);  
         }
           public void SetOntologieCoordLong(String titre,String coordlong)
         {
               String query =  "PREFIX : </Balade#>\n"+
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"+
            "PREFIX dbfilm: <http://www.semanticweb.org/johann.meissl/ontologies/2018/0/untitled-ontology-2#>\n"+
            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"+
        
           " INSERT\n" +
                "{\n"+
                "dbfilm:"+titre+" ?coordlong dbfilm:"+titre+"coordlong@fr.\n"+
                "dbfilm:"+titre+"coordlong rdfs:label "+coordlong+"@fr.\n"+
                
                "}\n"+
                "where {\n"+
                "?coordlong rdfs:label \"apourlong\"@fr."+
                "}\n";
           
           sparqlClient.update(query);  
         }
         public void setUpdateFilmOntologie(Film f)
         {
             this.SetOntologieTitreLieu(f.titre,f.ville);
             this.SetOntologieAdresse(f.titre,f.adresse);
             this.SetOntologieAffiche(f.titre,f.affiche);
             this.SetOntologieAnneeSortie(f.titre,f.annee);
             this.SetOntologieCoordLat(f.titre,f.coordonneeLat);
             this.SetOntologieCoordLong(f.titre,f.coordonneeLong);
             this.SetOntologieDateTournage(f.titre,f.anneeTournage);
             this.SetOntologieDuree(f.titre,f.duree);
             this.SetOntologieImdbId(f.titre,f.imdbId);
             this.SetOntologieNbJour(f.titre,f.nbjoursTournage);
             this.SetOntologieNote(f.titre,f.note);
             this.SetOntologieRealisateur(f.titre,f.realisateur);
             this.SetOntologieResume(f.titre,f.resume);
             this.SetOntologieType(f.titre,f.type);
             this.SetOntologieZip(f.titre,f.codePostale);
             
         }
   
}
