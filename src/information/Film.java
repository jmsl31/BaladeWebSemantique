/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package information;

import com.fasterxml.jackson.databind.JsonNode;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
        List<String> adresse;
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
        this.adresse =new ArrayList<>();
        this.codePostale = "";
        this.coordonneeLat = "";
        this.coordonneeLong = "";
        this.acteurs = new ArrayList<>();
        sparqlClient = new SparqlClient("localhost:3030/Balade");
        
    }
    
    public Film(String ville,String titre, String type, String annee, String note, String duree, List<String> genre, String realisateur, String affiche, List<String> origine, String resume, String imdbId, String anneeTournage, String nbjoursTournage, List<String> adresse, String codePostale, String coordonneeLat, String coordonneeLong, List<String> acteurs) {
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
        sparqlClient = new SparqlClient("localhost:3030/Balade");
    }
    
    public void setFilm(String ville,String titre, String type, String annee, String note, String duree, String genre,
                        String realisateur, String affiche, String origine, String resume, String imdbId,
                        String anneeTournage, String nbjoursTournage, String adresse, String codePostale,
                        String coordonneeLat, String coordonneeLong, String acteurs)
    {
            this.setVille(ville);
            this.setTitre(titre);
            this.setType(type);
            this.setAnnee(annee);
            this.setNote(note);
            this.setDuree(duree);
            this.setGenre(genre);
            this.setRealisateur(realisateur);
            this.setAffiche(affiche);
            this.setOrigine(origine);
            this.setResume(resume);
            this.setImdbId(imdbId);
            this.setAnneeTournage(anneeTournage);
            this.setNbjoursTournage(nbjoursTournage);
            this.setAdresse(adresse);
            this.setCodePostale(codePostale);
            this.setCoordonneeLat(coordonneeLat);
            this.setCoordonneeLong(coordonneeLong);
            this.setActeurs(acteurs);
       
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
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

    public List<String> getAdresse() {
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
        String d = duree.replaceAll("min", "");
        this.duree = d;
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
        
           this.adresse.add(adresse);
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
           String t = titre .replaceAll(" ", "");
           String l = lieu.replaceAll(" ","");
           String query =  "PREFIX : </Balade#>\n"+
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"+
            "PREFIX dbfilm: <http://www.semanticweb.org/johan/ontologies/2018/4/untitled-ontology-3#>\n"+
            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"+
        
           " INSERT DATA\n" +
                "{\n"+
                "dbfilm:"+t+" dbfilm:sederoule dbfilm:"+l+".\n"+
                "dbfilm:"+t+" dbfilm:titre \""+titre+"\".\n"+
                "dbfilm:"+l+" rdfs:label \""+lieu+"\"@fr.\n"+
                "}";
           
           sparqlClient.update(query);
                        
        }                
        public void SetOntologieType(String titre,String type)
        {
           String t = titre .replaceAll(" ", "");
           String l = type.replaceAll(" ","");
           String query =  "PREFIX : </Balade#>\n"+
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"+
            "PREFIX dbfilm: <http://www.semanticweb.org/johan/ontologies/2018/4/untitled-ontology-3#>\n"+
            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"+
        
           " INSERT DATA\n" +
                "{\n"+
                "dbfilm:"+t+" dbfilm:type \""+type+"\"@fr.\n"+
                "}";
           
           sparqlClient.update(query);
        }
        
        public void SetOntologieDateTournage(String titre,String datetournage)
        {
            String t = titre .replaceAll(" ", "");
            String l = datetournage.replaceAll(" ","");
            String query =  "PREFIX : </Balade#>\n"+
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"+
            "PREFIX dbfilm: <http://www.semanticweb.org/johan/ontologies/2018/4/untitled-ontology-3#>\n"+
            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"+
        
           " INSERT DATA\n" +
                "{\n"+
                "dbfilm:"+t+" dbfilm:datetournage \""+datetournage+"\".\n"+
                "}\n";
           
           sparqlClient.update(query);
        }
        
        public void SetOntologieAnneeSortie(String titre,String datesortie)
        {
            String t = titre .replaceAll(" ", "");
            String l = datesortie.replaceAll(" ","");
            String query =  "PREFIX : </Balade#>\n"+
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"+
            "PREFIX dbfilm: <http://www.semanticweb.org/johan/ontologies/2018/4/untitled-ontology-3#>\n"+
            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"+
        
           " INSERT DATA\n" +
                "{\n"+
                "dbfilm:"+t+" dbfilm:annee \""+datesortie+"\"@fr.\n"+
                
                "}";
           
           sparqlClient.update(query);   
        
        }
        public void SetOntologieNote(String titre,String note)
        {
           String t = titre .replaceAll(" ", "");
           String l = note.replaceAll(" ","");
           String query =  "PREFIX : </Balade#>\n"+
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"+
            "PREFIX dbfilm: <http://www.semanticweb.org/johan/ontologies/2018/4/untitled-ontology-3#>\n"+
            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"+
        
           " INSERT DATA\n" +
                "{\n"+
                "dbfilm:"+t+" dbfilm:note \""+note+"\"@fr.\n"+
                
                "}";
           
           sparqlClient.update(query);    
        }
         public void SetOntologieDuree(String titre,String duree)
         {  
            String t = titre .replaceAll(" ", "");
            String d = duree.replaceAll(" ","");
            String query =  "PREFIX : </Balade#>\n"+
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"+
            "PREFIX dbfilm: <http://www.semanticweb.org/johan/ontologies/2018/4/untitled-ontology-3#>\n"+
            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"+
        
           " INSERT DATA\n" +
                "{\n"+
                "dbfilm:"+t+" dbfilm:duree \""+duree+"\".\n"+
                
                "}";
           
           sparqlClient.update(query);  
         }
         public void SetOntologieRealisateur(String titre,String realisateur)
         {
            String t = titre .replaceAll(" ", "");
            String d = realisateur.replaceAll(" ","");
            String query =  "PREFIX : </Balade#>\n"+
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"+
            "PREFIX dbfilm: <http://www.semanticweb.org/johan/ontologies/2018/4/untitled-ontology-3#>\n"+
            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"+
        
           " INSERT DATA\n" +
                "{\n"+
                "dbfilm:"+t+" dbfilm:apourrealisateur dbfilm:"+realisateur+".\n"+
                "dbfilm:"+realisateur+" rdfs:label \""+ realisateur+"\".\n"+    
                "}";
           
           sparqlClient.update(query);  
         }
        public void SetOntologieAffiche(String titre,String affiche)
         {
            String t = titre .replaceAll(" ", "");
            String d = affiche.replaceAll(" ","");
            String query =  "PREFIX : </Balade#>\n"+
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"+
            "PREFIX dbfilm: <http://www.semanticweb.org/johan/ontologies/2018/4/untitled-ontology-3#>\n"+
            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"+
        
           " INSERT DATA\n" +
                "{\n"+
                "dbfilm:"+t+" dbfilm:affiche \""+affiche+"\"@fr.\n"+
                
                "}";
           
           sparqlClient.update(query);  
         }
        public void SetOntologieResume(String titre,String resume)
         {
            String t = titre .replaceAll(" ", "");
            String d = resume.replaceAll(" ","");
            String query =  "PREFIX : </Balade#>\n"+
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"+
            "PREFIX dbfilm: <http://www.semanticweb.org/johan/ontologies/2018/4/untitled-ontology-3#>\n"+
            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"+
        
           " INSERT DATA\n" +
                "{\n"+
                "dbfilm:"+t+" dbfilm:resume \""+resume+"\"@fr.\n"+
                
                "}";
           
           sparqlClient.update(query);  
         }
         public void SetOntologieImdbId(String titre,String Id)
         {
            String t = titre .replaceAll(" ", "");
            String d = Id.replaceAll(" ","");
            String query =  "PREFIX : </Balade#>\n"+
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"+
            "PREFIX dbfilm: <http://www.semanticweb.org/johan/ontologies/2018/4/untitled-ontology-3#>\n"+
            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"+
        
           " INSERT DATA\n" +
                "{\n"+
                "dbfilm:"+t+" dbfilm:imdbid \""+Id+"\"@fr.\n"+
                
                "}";
           
           sparqlClient.update(query);  
         }
          public void SetOntologieNbJour(String titre,String Nb)
         {
            String t = titre .replaceAll(" ", "");
            String d = Nb.replaceAll(" ","");
            String query =  "PREFIX : </Balade#>\n"+
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"+
            "PREFIX dbfilm: <http://www.semanticweb.org/johan/ontologies/2018/4/untitled-ontology-3#>\n"+
            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"+
        
           " INSERT DATA\n" +
                "{\n"+
                "dbfilm:"+t+" dbfilm:nbjourstournage \""+Nb+"\"@fr.\n"+
                
                "}";
           
           sparqlClient.update(query);  
         }
         public void SetOntologieAdresse(String titre,String adresse,String ville)
         {
            String t = titre .replaceAll(" ", "");
            String d = adresse.replaceAll(" ","");
            String v = ville.replaceAll(" ", "");
            String query =  "PREFIX : </Balade#>\n"+
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"+
            "PREFIX dbfilm: <http://www.semanticweb.org/johan/ontologies/2018/4/untitled-ontology-3#>\n"+
            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"+
        
           " INSERT DATA\n" +
                "{\n"+
                "dbfilm:"+t+" dbfilm:adressetournage \""+ adresse+"\".\n"+  
                "}";
           
           sparqlClient.update(query);  
         }
         
         public void SetOntologieZip(String titre,String zip)
         {
            String t = titre .replaceAll(" ", "");
            String d = zip.replaceAll(" ","");
            String query =  "PREFIX : </Balade#>\n"+
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"+
            "PREFIX dbfilm: <http://www.semanticweb.org/johan/ontologies/2018/4/untitled-ontology-3#>\n"+
            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"+
        
           " INSERT DATA\n" +
                "{\n"+
                "dbfilm:"+t+" dbfilm:codepostal \""+zip+"\"@fr.\n"+
                
                "}";
           
           sparqlClient.update(query);  
         }
          public void SetOntologieCoordLat(String titre,String coordlat)
         {
            String t = titre .replaceAll(" ", "");
            String d = coordlat.replaceAll(" ","");
            String query =  "PREFIX : </Balade#>\n"+
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"+
            "PREFIX dbfilm: <http://www.semanticweb.org/johan/ontologies/2018/4/untitled-ontology-3#>\n"+
            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"+
        
           " INSERT DATA\n" +
                "{\n"+
                "dbfilm:"+t+" dbfilm:coordlat \""+coordlat+"\"@fr.\n"+
                
                "}";
           
           sparqlClient.update(query);  
         }
           public void SetOntologieCoordLong(String titre,String coordlong)
         {
            String t = titre .replaceAll(" ", "");
            String d = coordlong.replaceAll(" ","");
               String query =  "PREFIX : </Balade#>\n"+
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"+
            "PREFIX dbfilm: <http://www.semanticweb.org/johan/ontologies/2018/4/untitled-ontology-3#>\n"+
            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"+
        
           " INSERT DATA\n" +
                "{\n"+
                "dbfilm:"+t+" dbfilm:coordlong \""+coordlong+"\"@fr.\n"+
                
                "}";
           
           sparqlClient.update(query);  
         }
           public void SetOntologieAteur(String titre,String acteur)
         {
            String t = titre .replaceAll(" ", "");
            String act = acteur.replaceAll(" ","");
            String query =  "PREFIX : </Balade#>\n"+
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"+
            "PREFIX dbfilm: <http://www.semanticweb.org/johan/ontologies/2018/4/untitled-ontology-3#>\n"+
            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"+
        
           " INSERT DATA\n" +
                "{\n"+
                "dbfilm:"+act+" dbfilm:jouedans dbfilm:"+t+".\n"+
                "dbfilm:"+act+" rdfs:label \""+acteur+"\"@fr.\n"+
                "}";
           
           sparqlClient.update(query);  
         }  
         
         public void SetOntologieGenre(String titre,String genre)
         {
            String t = titre .replaceAll(" ", "");
            String g = genre.replaceAll(" ","");
            String query =  "PREFIX : </Balade#>\n"+
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"+
            "PREFIX dbfilm: <http://www.semanticweb.org/johan/ontologies/2018/4/untitled-ontology-3#>\n"+
            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"+
        
           " INSERT DATA\n" +
                "{\n"+
                "dbfilm:"+t+" dbfilm:genre \""+genre+"\"@fr.\n"+
                
                "}";
           
           sparqlClient.update(query);  
         }  
         
          public void SetOntologieOrigine(String titre,String origine)
         {
            String t = titre .replaceAll(" ", "");
            String o = origine.replaceAll(" ","");
            String query =  "PREFIX : </Balade#>\n"+
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"+
            "PREFIX dbfilm: <http://www.semanticweb.org/johan/ontologies/2018/4/untitled-ontology-3#>\n"+
            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"+
        
           " INSERT DATA\n" +
                "{\n"+
                "dbfilm:"+t+" dbfilm:apourorigine dbfilm:"+o+".\n"+
                "}\n";
           
           sparqlClient.update(query);  
         }  
          
         public JSONObject getFilmLieu(String Lieu) 
                 
         {
             JSONObject  film = new JSONObject();
             
             String query =  "PREFIX : </Balade#>\n"+
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"+
            "PREFIX dbfilm: <http://www.semanticweb.org/johan/ontologies/2018/4/untitled-ontology-3#>\n"+
            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"+
        
            " SELECT ?titre	?value\n" +
            "WHERE {\n" +
            "  ?subject dbfilm:a_pour	dbfilm:"+Lieu+".\n" +
            "  ?subject rdfs:label ?titre.\n" +
            "}";
             
             sparqlClient.select(query);
             
             return film;
             
         }
          public Iterable<Map<String, String>> getLieu(String Lieu)
                 
         {
             Iterable<Map<String, String>>  film ;
             
             String query =            
             "PREFIX : </Balade#>\n"+
             "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"+
             "PREFIX dbfilm: <http://www.semanticweb.org/johan/ontologies/2018/4/untitled-ontology-3#>\n"+
            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"+         
            "prefix dbr: <http://dbpedia.org/resource/>\n"+
            "prefix dbo: <http://dbpedia.org/ontology/>\n"+
            "select distinct ?population ?resume \n+"+
            "where {\n"+
            "dbr:Montpellier dbo:populationTotal ?population. \n"+
            "dbr:Montpellier dbo:abstract ?resume.\n"+
            "FILTER(lang(?resume)= \"fr\")\n"+
            "}";
            film =  sparqlClient.select(query);
             
             return film;
             
         }
         
         public void setUpdateFilmOntologie(Film f)
         {
             this.SetOntologieTitreLieu(f.titre,f.ville);
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
             
             for (String adresse : f.adresse) {
                 this.SetOntologieAdresse(f.titre, adresse,f.ville);
             }
             for (String acteur :f.acteurs ) 
             {
                 this.SetOntologieAteur(f.titre, acteur);
             }
             for (String genre :f.genre ) 
             {
                 this.SetOntologieAteur(f.titre, genre);
             }
             for (String origine :f.origine ) 
             {
                 this.SetOntologieOrigine(f.titre, origine);
             }
         }
   
}
