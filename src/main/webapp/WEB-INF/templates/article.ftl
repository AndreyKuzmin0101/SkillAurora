<#macro title></#macro>
<#macro headtags>
    <script type="module" src="/js/article.js"></script>
    <link rel="stylesheet" href="/css/styles.css">
</#macro>

<#macro content>
    <div class="article-content">
        <div>
            <img id="article-author-mini-img" src="" class="article-profile-mini-img">
            <strong id="article-author-username"></strong>
            <span id="article-publication-date" style="color: gray"></span>
        </div>
        <h3><strong id="article-title"></strong></h3>
        <div>
            <img class="article-icon-views" src="/images/views.png">
            <strong id="article-views" style="color: gray"></strong>
        </div>
        <img id="article-cover" src="">
        <div id="article-tags">
        </div>
        <div id="article-content">

        </div>
        <div>
            <img class="article-icon-rating" src="/images/rating.png"><strong id="rating" style="color: gray;"></strong>
            <button id="plus-btn" class="btn"><img class="article-icon-rating" src="/images/plus.png"></button>
            <button id="minus-btn" class="btn"><img class="article-icon-rating" src="/images/minus.png"></button>
        </div>
    </div>
</#macro>

<#include "base.ftl">