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
	update_map();
}

function update_type(){
	type = document.getElementById("type").value;
}


//On met à jour la carte en fonction des filtres
function update_map(){
	console.log("update_map()");
	//On récupere la valeur des filtres
	var anneeTournage = document.getElementById("anneeTournage").value;
	var nbJourTournage = document.getElementById("nbJourTournage").value;
	var genre_action = document.getElementById("genre_action").value;
	var genre_comedie = document.getElementById("genre_comedie").value;
	var genre_drama = document.getElementById("genre_drama").value;
	var genre_romance = document.getElementById("genre_romance").value;
	var origine_fr = document.getElementById("origine_fr").value;
	var origine_etr = document.getElementById("origine_etr").value;
	
	// On récupere les films [correspondant aux filtres];
	var films = Fuseki.getFilms()
	.then((response) => {
    	 console.log(response);
	})
	.catch(console.log);

	// Pour tout les films récupéré, on ajoute un marker
	// Au clic sur le marker, on met a jour la rubrique informations
	for (film in films){
		console.log(film.titre);
		var marker = L.marker([51.5, -0.09]).addTo(map);
//		marker.bindPopup("<b>Titre film ?</b><br>qque info ?.");
		marker.on("click", function(e) {
			select_film(e, "");
		});
	}


}

//On sélectionne un pointeur sur la carte
function select_film(e, titre){
	console.log("Mise à jour des informations");
	
	// On récupere le titre du film, on fait une requete sur l'ontologie
	var film = Fuseki.getDetailsFilm()
	.then((response) => {
    	 console.log(response);
	})
	.catch(console.log);
	// Puis on remplit les champs informations.
	
	// On récupére les infos correspondant au film depuis l'onthologie
	var titre = "";
	var annee_sortie = "";
	var note = "";
	var duree = "";
	var nb_jour_tournage = "";
	var genre = "";
	var realisateur = "";
	var debut_tournage = "";
	var fin_tournage = "";
	var acteurs = "";
	var resume = "";
	var affiche = "";
	
	// On met à jour les informations
	document.getElementById("info_titre").innerHTML = "Titre : " + titre;
	document.getElementById("info_annee_sortie").innerHTML = "Année de sortie : " + annee_sortie;
	document.getElementById("info_note").innerHTML = "Note (imdb) : " + note;
	document.getElementById("info_duree").innerHTML = "Durée : " + duree + " minutes";
	document.getElementById("info_nb_jour_tournage").innerHTML = "Nombre de jour de tournage : " + nb_jour_tournage + " jours";
	document.getElementById("info_genre").innerHTML = "Genre : " + genre;
	document.getElementById("info_realisateur").innerHTML = "Réalisateur : " + realisateur;
	document.getElementById("info_debut_tournage").innerHTML = "Debut du tournage : " + debut_tournage;
	document.getElementById("info_fin_tournage").innerHTML = "Fin du tournage : " + fin_tournage;
	document.getElementById("info_acteurs").innerHTML = "Acteurs : " + acteurs;
	document.getElementById("info_resume").innerHTML = "Résumé : " + resume;
	
	document.getElementById("affiche").src = affiche;
}