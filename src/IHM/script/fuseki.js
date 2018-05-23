var Fuseki = {

	getFilms: function () {
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