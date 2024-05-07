<#macro title>${article.title()}</#macro>
<#macro headtags>
    <script src="/js/article.js"></script>
    <link rel="stylesheet" href="/css/styles.css">
</#macro>

<#macro content>
    <div class="article-content">
        <div>
            <img src="${article.author().profileImage()}" class="article-profile-mini-img">
            <strong>${article.author().username()}</strong>
            <span style="color: gray">Дата публикации:${article.publicationDate()}</span>
        </div>
        <h3><strong>${article.title()}</strong></h3>
        <div>
            <img class="article-icon-views" src="/images/views.png">
            <strong style="color: gray">${article.views()}</strong>
        </div>
        <img src="${article.cover()}">
        <div>
            <#list article.tags() as tag>
                <span class="tag-style">${tag.name()}</span>
            </#list>
        </div>
        <div>
            ${article.content()}
        </div>
        <div>
            <img class="article-icon-rating" src="/images/rating.png"><strong id="rating" style="color: gray;">${article.rating()}</strong>
            <button id="plus-btn" class="btn"><img class="article-icon-rating" src="/images/plus.png"></button>
            <button id="minus-btn" class="btn"><img class="article-icon-rating" src="/images/minus.png"></button>
        </div>
    </div>


</#macro>

<#include "base.ftl">