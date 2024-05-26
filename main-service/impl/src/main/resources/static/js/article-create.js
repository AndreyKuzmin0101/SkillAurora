import {sendAuthenticatedRequest, sendWithTokensIfExist} from './auth.js';

sendWithTokensIfExist('/api/v1/auth/check-role-access?url=/create/article', {
	method: 'GET',
	headers: {
		'Content-Type': 'application/json'
	}
}).then(res => {
	if (res.status === 200) {
		return res.text();
	}
	return Promise.reject(res);
}).then(res => {
	if (res === false) {
		return Promise.reject();
	}
}).catch((reason) => {
	if (reason.status === 401) {
		alert("Вы не вошли в аккаунт");
		window.location.replace('/login');
	} else if (reason.status === 403) {
		alert('Вы ещё не стали автором!');
		window.location.replace('/info/rating');
	} else {
		alert("Something went wrong.")
		window.location.replace('/');
	}
})

let editor = ClassicEditor
	.create( document.querySelector( '#content' ), {
		simpleUpload: {
            uploadUrl: 'http://localhost:8080/api/v1/files/upload/image',
			types: ['png', 'jpg', 'jpeg'],
			withCredentials: true,
			headers: {
				Authorization: 'Bearer ' + localStorage.getItem('access_token')
			}
        }
	} )
	.then( editor => {
		window.editor = editor;
	} )
	.catch( handleSampleError );

function handleSampleError( error ) {
	const issueUrl = 'https://github.com/ckeditor/ckeditor5/issues';

	const message = [
		'Oops, something went wrong!',
		`Please, report the following error on ${ issueUrl } with the build id "b2jez7pwo5fg-80e0rp4748r8" and the error stack trace:`
	].join( '\n' );

	console.error( message );
	console.error( error );
}

window.editor = editor;


$(document).ready(function() {
	let cover;
	$('#upload-cover').click(function() {
		let formData = new FormData();
		let fileInput = $('#cover-input')[0].files[0];

		formData.append('upload', fileInput);
		sendAuthenticatedRequest('/api/v1/files/upload/image', {
			method: 'POST',
			body: formData,
		}).then(response => {
			if (response.status === 201) {
				return response.json()
			}
			return response.json().then(error => Promise.reject(error));
		}).then(data => {
			cover = data.url;
			$('#cover-preview').empty();
			$('#cover-preview').append('<img src="' + cover + '">');
		}).catch(reason => alert(JSON.stringify(reason)));
	});

	$('#submit-button').on('click', function () {
		let title = $('#title').val();
		let content = window.editor.getData();
		let description = $('#description').val();
		let tags = getTagsFromSelectedTags();
		let json= {
			title: title,
			content: content,
			description: description,
			cover: cover,
			tags: tags
		}
		let jsonString = JSON.stringify(json);

		sendAuthenticatedRequest('/create/article', {
			method: 'POST',
			body: jsonString,
			headers: {
				'Content-Type': 'application/json'
			}
		}).then(res => {
			if (res.status === 201) {
				return res.json();
			}
			return res.json().then(error => Promise.reject(error));
		}).then(res => {
			window.location.replace(res.url);
		}).catch((reason) => {
			console.log(JSON.stringify(reason))
			alert(JSON.stringify(reason));
		})
	});
});