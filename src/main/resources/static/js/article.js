$(document).ready(function () {
   $.get('/auth-check', function (response) {
      if (response === true) {
         let article_id = window.location.pathname.split('/')[2];
         $.get("/rating/" + article_id +'/me', function (response) {
            if (response > 0) {
               $('#plus-btn').prop('disabled', true);
               $('#minus-btn').prop('disabled', false);
               $('#plus-btn').addClass('btn-success');
            } else if (response < 0) {
               $('#minus-btn').prop('disabled', true);
               $('#plus-btn').prop('disabled', false);
               $('#minus-btn').addClass('btn-success');
            }
         });

         $('#plus-btn').on('click', function () {
            $.post('/rating/' + article_id + '/plus').done(function (response) {
               $('#plus-btn').prop('disabled', true);
               $('#minus-btn').prop('disabled', false);
               $('#plus-btn').addClass('btn-success');
               $('#minus-btn').removeClass('btn-success');
               $('#rating').html(response);
            });
         })
         $('#minus-btn').on('click', function () {
            $.post('/rating/' + article_id + '/minus').done(function (response) {
               $('#minus-btn').prop('disabled', true);
               $('#plus-btn').prop('disabled', false);
               $('#minus-btn').addClass('btn-success');
               $('#plus-btn').removeClass('btn-success');
               $('#rating').html(response);
            });
         })
      } else {

      }
   });


});