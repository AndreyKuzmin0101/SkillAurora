ClassicEditor
	.create( document.querySelector( '#articleContent' ), {
		simpleUpload: {
            // The URL that the images are uploaded to.
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
