$(document).ready(function () {

    $('#rating-threshold').show();
    $('#period-popular').hide();

    $('input[name="articleFilter"]').change(function () {
        if ($(this).val() === 'publicationDate') {
            $('#rating-threshold').show();
            $('#period-popular').hide();
        } else if ($(this).val() === 'rating') {
            $('#rating-threshold').hide();
            $('#period-popular').show();
        }
    });

    window.page = 0;
    $('#prev-btn').prop('disabled', true);

    $('#prev-btn').on('click', function () {
        if (window.page === 1) {
            $('#prev-btn').prop('disabled', true);
        }
        window.page -= 1;
        update_stream();
    });

    $('#next-btn').on('click', function () {
        if (window.page === 0) {
            $('#prev-btn').prop('disabled', false);
        }
        window.page += 1;
        update_stream();
    });

    update_stream();

    $('#find-button').on('click', function () {
        window.page = 0;
        $('#prev-btn').prop('disabled', true);
        update_stream();
    });
});

function update_stream() {
    let options = '';

    options += 'page=' + window.page + '&size=10&'

    let search = $('#search').val();
    options += 'search=' + search + '&';
    let show_first = $('#show-first input[type="radio"]:checked').val();
    options += 'showFirst=' + show_first + '&';
    let rating_threshold = $('#rating-threshold input[type="radio"]:checked').val();
    if (rating_threshold !== 'all') {
        options += 'ratingThreshold=' + rating_threshold + '&';
    }
    let period = $('#period input[type="radio"]:checked').val();
    if (period !== 'all') {
        options += 'period=' + period + '&'
    }

    $('#selected-tags span').each(function () {
        let spanText = $(this).text();
        options += 'tags=' + spanText + '&'
    });

    $.getJSON("/articles/filter?" + options, function (response) {
        $('#articles-stream').empty();


        response.forEach(function (article) {
            $('#articles-stream').append('<div class="article">' +
                '<div>' +
                '<img src="' + article.author.profileImage + '" class="article-profile-mini-img">' +
                '<strong>' + article.author.username + '</strong>' +
                '<span style="color: gray">Дата публикации:' + article.publicationDate + '</span>' +
                '</div>' +
                '<h3><strong>' + article.title + '</strong></h3>' +
                '<div>' +
                '<img class="article-icon-rating" src="/images/rating.png"><strong style="color: gray">' + article.rating + '</strong>' +
                '<img class="article-icon-views" src="/images/views.png" style="margin-left: 20px;">' +
                '<strong style="color: gray">' + article.views + '</strong>' +
                '</div>' +
                '<img src="' + article.cover + '">' +
                '<br><span>' + article.description + '</span>' +
                '<div id="tags-' + article.id + '"></div>' +
                '<a href="/articles/' + article.id + '" style="text-decoration: none;"><button class="btn btn-outline-success">Читать далее</button></a>' +
                '</div>'
            );

            article.tags.forEach(function (tag) {
                $('#tags-' + article.id).append('<span class="tag-style">' + tag.name + '</span>')
            });
        });
    });
}