/**
 * AIzaSyDyILtXD4EEL57wzfR1DzI2mzjfMU1Velo
 */
function init_map(){
	map = L.map('mapid');
	update_ville();
//	L.marker([51.5, -0.09]).addTo(map)
//	    .bindPopup('A pretty CSS3 popup.<br> Easily customizable.')
//	    .openPopup();

	L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
	    attribution: 'Projet web semantique - Balade - Meissl & Marty'
	}).addTo(map);
}



function update_ville(){
	// On change l'emplacement de la carte sur la bonne ville
	ville = document.getElementById("ville").value;
	
	if(ville == "paris"){
		map.setView([48.856614, 2.352222], 13);
	} else if (ville == "marseille"){
		map.setView([43.296482, 5.36978], 13);
	} else if (ville == "sanFrancisco"){
		map.setView([37.77493, -122.419616], 13);
	}
	
//	var resultat = "";
//	var testQuery = 
//		"PREFIX dbfilm: <http://www.semanticweb.org/johan/ontologies/2018/4/untitled-ontology-3#>" +
//		"		select distinct *" +
//		"		where		{" +
//		"		?film dbfilm:titre \"" + titre + "\" ." +
//				"?film ?propertie ?value." +
//		"		}";
//
//	var url = "http://localhost:3030/Balade/query";
//	var params = "testQuery";
//	var http = new XMLHttpRequest();
//
////	console.log(testQuery);
//	http.open("POST", url + '?query=' + encodeURIComponent(testQuery), true);
//	http.onreadystatechange = function()
//	{
//	    if(http.readyState == 4 && http.status == 200) {

	    	update_map();
//	    }
//	}
//	http.send();
	
	
}

function update_type(){
	type = document.getElementById("type").value;
}


//On met � jour la carte en fonction des filtres
function update_map(){
	console.log("update_map()");
	
	// On r�cupere les films [correspondant aux filtres];
	var resultat = "";
	var testQuery = 
		"		PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
		"		PREFIX dbfilm: <http://www.semanticweb.org/johan/ontologies/2018/4/untitled-ontology-3#>" +
		"		PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
		"		" +
		"		select distinct *" +
		"		where" +
		"		{" +
		"		  ?x dbfilm:sederoule dbfilm:Paris." +
		"		  ?x dbfilm:titre ?titre." +
		"		  ?x dbfilm:coordlat ?latittude." +
		"		  ?x dbfilm:coordlong ?longittude." +
		"		}";

	var url = "http://localhost:3030/Balade/query";
	var params = "testQuery";
	var http = new XMLHttpRequest();

	http.open("POST", url + '?query=' + encodeURIComponent(testQuery), true);
	http.onreadystatechange = function()
	{
	    if(http.readyState == 4 && http.status == 200) {
	    	films = JSON.parse(http.responseText);
	    	var i = 0;
	    	for (film in films.results.bindings){
	    		var titre = films.results.bindings[film].titre.value;
	    		marker = L.marker([films.results.bindings[film].latittude.value, films.results.bindings[film].longittude.value]).addTo(map);
	    		marker.bindPopup("<b>"+titre+"</b>");
	    		marker.titre = titre;
				marker.on("click", function(e) {
					select_film(e.target.titre);
				});
				i++;
	    	}
	    }
	}
	http.send();
	
}

//On s�lectionne un pointeur sur la carte
function select_film(titre){
	console.log("Mise � jour des informations");
	console.log("titre : " + titre);

	// On r�cupere le titre du film, on fait une requete sur l'ontologie
	var resultat = "";
	var testQuery = 
		"PREFIX dbfilm: <http://www.semanticweb.org/johan/ontologies/2018/4/untitled-ontology-3#>" +
		"		select distinct *" +
		"		where		{" +
		"		?film dbfilm:titre \"" + titre + "\" ." +
				"?film ?propertie ?value." +
		"		}";

	var url = "http://localhost:3030/Balade/query";
	var params = "testQuery";
	var http = new XMLHttpRequest();

//	console.log(testQuery);
	http.open("POST", url + '?query=' + encodeURIComponent(testQuery), true);
	http.onreadystatechange = function()
	{
	    if(http.readyState == 4 && http.status == 200) {
	    	films = JSON.parse(http.responseText);
	    		
	    	for (i = 0; i<films.results.bindings.length; i++){
	    		annee_sortie = infoCorrespondante(i, "annee");
	    		note =  infoCorrespondante(i, "note");
	    		duree = infoCorrespondante(i, "duree");
	    		nb_jour_tournage = infoCorrespondante(i, "nbjourstournage");
//	    		var genre = "";
//	    		var realisateur = "";
//	    		var debut_tournage = "";
//	    		var fin_tournage = "";
//	    		var acteurs = "";
	    		resume = infoCorrespondante(i, "resume");
	    		affiche = infoCorrespondante(i, "affiche");
	    	}

    		// On met � jour les informations
    		document.getElementById("info_titre").innerHTML = "Titre : " + titre;
    		document.getElementById("info_annee_sortie").innerHTML = "Ann�e de sortie : " + annee_sortie;
    		document.getElementById("info_note").innerHTML = "Note (imdb) : " + note;
    		document.getElementById("info_duree").innerHTML = "Dur�e : " + duree + " minutes";
    		document.getElementById("info_nb_jour_tournage").innerHTML = "Nombre de jour de tournage : " + nb_jour_tournage + " jours";
//    		document.getElementById("info_genre").innerHTML = "Genre : " + genre;
//    		document.getElementById("info_realisateur").innerHTML = "R�alisateur : " + realisateur;
//    		document.getElementById("info_debut_tournage").innerHTML = "Debut du tournage : " + debut_tournage;
//    		document.getElementById("info_fin_tournage").innerHTML = "Fin du tournage : " + fin_tournage;
//    		document.getElementById("info_acteurs").innerHTML = "Acteurs : " + acteurs;
    		document.getElementById("info_resume").innerHTML = "R�sum� : " + resume;
    		
    		document.getElementById("affiche").src = affiche;
	    }
	}
	http.send();
}

function infoCorrespondante(i, val){
	if (films.results.bindings[i].propertie.value.includes(val) ){
		return films.results.bindings[i].value.value
	}
}
	