package exemple;

import java.io.File;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;

public class main {
	
	public static void main(String args[])
   {
     OMDBProxy omdbProxy = new OMDBProxy();
     System.out.println(omdbProxy.getMovieInfos("LOVE LOCKS").get("imdbRating"));
   }

}
