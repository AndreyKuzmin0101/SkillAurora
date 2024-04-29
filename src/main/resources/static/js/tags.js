let tags;

$(document).ready(function() {
    $.get('/api/v1/tags/not-custom', function (response) {
        tags = response
    }).done(function () {
            updateTagList("");
            bind_tag_options();

            $("#tag-search").on("input", function() {
                let query = $(this).val();
                updateTagList(query);
                bind_tag_options()
            });
        }
    );
});

function bind_tag_options() {
    $('.tag-option').on('click', function () {
        $('#selected-tags').append('<span class="tag-style new-tag">' + $(this).val() + '</span>');
        $('.new-tag').on('click', function () {
            $(this).remove();
        });
    });
}

function updateTagList(query) {
    let filteredTags = $.grep(tags,function(tag) {
        return tag.name.toLowerCase().includes(query.toLowerCase());
    });
    $("#tag-list").empty();
    filteredTags.forEach(function(tag) {
        let listItem = '<option class="tag-option" value="' + tag.name + '">' + tag.name + '</option>';
        $("#tag-list").append(listItem);
    });
}


function getTagsFromSelectedTags() {
    let skills = [];
    $('#selected-tags span').each(function(){
        let spanText = $(this).text();
        let jsonSkill = {}
        jsonSkill.name = spanText;
        skills.push(jsonSkill);
    });
    return skills;
}