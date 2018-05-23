var Fuseki = {

	getFilms: function () {
		const fuseqiBdUrl = 'http://localhost:3030/Balade/query?output=json&query=';

		const headers = new Headers({
			"Content-Type": "application/x-www-form-urlencoded; charset=UTF-8",
			"X-Requested-With": "XMLHttpRequest"
		});
		const query = encodeURIComponent(`
			PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
			PREFIX dbfilm: <http://www.semanticweb.org/johan/ontologies/2018/4/untitled-ontology-3#>
			PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
			
			select distinct *
			where
			{
			  ?x dbfilm:sederoule dbfilm:Paris.
			  ?x dbfilm:titre ?titre.
			  ?x dbfilm:coordlat ?latittude.
			  ?x dbfilm:coordlong ?longittude.
			}
		`);	
debugger
                    console.log(fuseqiBdUrl+query);
		return window.fetch(fuseqiBdUrl+query, { method: 'GET', headers: headers });
	},


	getDetailsFilm: function () {
		const fuseqiBdUrl = 'http://localhost:3030/Balade/query';
		const headers = new Headers({
			"Content-Type": "application/x-www-form-urlencoded; charset=UTF-8",
			"X-Requested-With": "XMLHttpRequest"
		});
		const query = `
	    
		`;
		let formData = new FormData();
		formData.append('query=', query);
	
		return window.fetch(fuseqiBdUrl, { method: 'POST', headers: headers, body: formData });
	}
};