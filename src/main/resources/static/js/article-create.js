
let editor = ClassicEditor
	.create( document.querySelector( '#content' ), {
		simpleUpload: {
            uploadUrl: 'http://localhost:8080/upload/image',
			types: ['png', 'jpg', 'jpeg']
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

	$('#submit-button').on('click', function () {
		let title = $('#title').val();
		let content = window.editor.getData();
		let tags = getTagsFromSelectedTags();
		let json= {
			title: title,
			content: content,
			tags: tags
		}
		let jsonString = JSON.stringify(json);
		$.ajax({
			type: "POST",
			url: "/create/article",
			data: jsonString,
			contentType: 'application/json'
		}).done(function (response, status, xhr){
			window.location.replace(xhr.getResponseHeader('Location'))
		});
	});
});