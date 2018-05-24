/**
 * AIzaSyDyILtXD4EEL57wzfR1DzI2mzjfMU1Velo
 */
function init_map(){
	map = L.map('mapid');
	update_ville();

	L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
	    attribution: 'Projet web semantique - Balade - Meissl & Marty'
	}).addTo(map);
}



function update_ville(){
	// On change l'emplacement de la carte sur la bonne ville
	ville = document.getElementById("ville").value;
	
	if(ville == "Paris"){
		map.setView([48.856614, 2.352222], 13);
		villebis = "Paris";
	} else if (ville == "Marseille"){
		map.setView([43.296482, 5.36978], 13);
		villebis = "Marseille";
	} else if (ville == "SanFrancisco"){
		map.setView([37.77493, -122.419616], 13);
		villebis = "San_Francisco";
	}
	
	var resultat = "";
	var testQuery = 
		"prefix dbr: <http://dbpedia.org/resource/>" +
		"prefix dbo: <http://dbpedia.org/ontology/>" +
		"		select distinct ?population ?resume" +
		"		where {" +
		"			dbr:"+ villebis +" dbo:populationTotal ?population. " +
		"			dbr:"+ villebis +" dbo:abstract ?resume." +
		"			FILTER(lang(?resume)= \"fr\")"+
		"		}";

	var url = "http://dbpedia.org/sparql/format=json&query";
	var params = "testQuery";
	var http = new XMLHttpRequest();

//	console.log(testQuery);
	http.open("POST", url + '?query=' + encodeURIComponent(testQuery), true);
	http.onreadystatechange = function()
	{
	    if(http.readyState == 4 && http.status == 200) {
			parser = new DOMParser();
			ville_infos = parser.parseFromString(http.responseText,"text/xml")
//	    	ville_infos = JSON.parse(http.responseText);
	    	document.getElementById("info_ville_nb_habitant").innerHTML = "<b>Population : </b>" + ville_infos.getElementsByName("population")["1"].getElementsByTagName("literal")[0].textContent +" habitants.";
	    	document.getElementById("info_ville_resume").innerHTML = "<b>Résumé :  </b>" + ville_infos.getElementsByName("resume")["1"].getElementsByTagName("literal")[0].textContent

	    	update_map(ville);
	    }
	}
	http.send();
}

function update_type(){
	type = document.getElementById("type").value;
}


//On met é jour la carte en fonction des filtres
function update_map(ville){
//	console.log("update_map()");
	
	// On récupere les films [correspondant aux filtres];
	var resultat = "";
	var testQuery = 
		"		PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
		"		PREFIX dbfilm: <http://www.semanticweb.org/johan/ontologies/2018/4/untitled-ontology-3#>" +
		"		PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
		"		" +
		"		select distinct *" +
		"		where" +
		"		{" +
		"		  ?x dbfilm:sederoule dbfilm:" + ville + "." +
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

//On sélectionne un pointeur sur la carte
function select_film(titre){
//	console.log("Mise é jour des informations");
//	console.log("titre : " + titre);

	// On récupere le titre du film, on fait une requete sur l'ontologie
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
    		type = "";
    		annee_sortie = "";
    		note = "";
    		duree = "";
    		nb_jour_tournage = "";
    		genre = "";
    		realisateur = "";
    		debut_tournage = "";
    		fin_tournage = "";
    		acteurs = "";
    		resume = "";
    		affiche = "";	
	    	for (i = 0; i<films.results.bindings.length; i++){
	    		annee_sortie += infoCorrespondante(i, "annee");
	    		type += type == "" ? infoCorrespondante(i, "type") : "";
	    		note +=  infoCorrespondante(i, "note");
	    		duree += infoCorrespondante(i, "duree");
	    		nb_jour_tournage += infoCorrespondante(i, "nbjourstournage");
//	    		var genre = "";
//	    		var realisateur = "";
	    		debut_tournage += debut_tournage == "" ? infoCorrespondante(i, "datetournage") : "";
//	    		var fin_tournage = "";
//	    		var acteurs = "";
	    		resume += infoCorrespondante(i, "resume");
	    		affiche += infoCorrespondante(i, "affiche");
	    	}

    		// On met é jour les informations
    		document.getElementById("info_titre").innerHTML = titre == "" ? "" : "Titre : " + titre;
    		document.getElementById("info_type").innerHTML = type == "" ? "" :  "Type : " + type;
    		document.getElementById("info_annee_sortie").innerHTML = annee_sortie == "" ? "" :  "Année de sortie : " + annee_sortie;
    		document.getElementById("info_note").innerHTML = note == "" ? "" :  "Note (imdb) : " + note;
    		document.getElementById("info_duree").innerHTML = duree == "" ? "" :  "Durée : " + duree + " minutes";
    		document.getElementById("info_nb_jour_tournage").innerHTML = nb_jour_tournage == "" ? "" :  "Nombre de jour de tournage : " + nb_jour_tournage + " jours";
//    		document.getElementById("info_genre").innerHTML = "Genre : " + genre;
//    		document.getElementById("info_realisateur").innerHTML = "Réalisateur : " + realisateur;
    		document.getElementById("info_debut_tournage").innerHTML = debut_tournage == "" ? "" :  "Debut du tournage : " + debut_tournage;
//    		document.getElementById("info_fin_tournage").innerHTML = "Fin du tournage : " + fin_tournage;
//    		document.getElementById("info_acteurs").innerHTML = "Acteurs : " + acteurs;
    		document.getElementById("info_resume").innerHTML = resume == "" ? "" :  "Résumé : " + resume;
    		
    		document.getElementById("affiche").src = affiche == "" ? "http://rodericke.com/sites/default/files/58442.png" : affiche;
	    }
	}
	http.send();
}

function infoCorrespondante(i, val){
	if (films.results.bindings[i].propertie.value.includes(val) ){
		return films.results.bindings[i].value.value
	} else {
		return "";
	}
}
	