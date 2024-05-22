<#macro title>Поток вопросов</#macro>

<#macro headtags>
    <link rel="stylesheet" href="/css/styles.css">
    <script src="/js/tags.js"></script>
</#macro>

<#macro content>
    <div class="main-content">
        <div class="filter">
            <h2>Фильтр</h2>
            <div class="search">
                <input id="search" type="text" placeholder="Поиск...">
                <button id="find-button" class="btn btn-outline-success">Найти</button>
            </div>
            <div class="article-filter">
                <label for="no-answers-checkbox">Нет ответов</label>
                <input type="checkbox" id="no-answers-checkbox" name="myCheckbox" value="true">
                <h3>Сначала показывать</h3>
                <ul id="show-first">
                    <li>
                        <input type="radio" value="createdDate" name="articleFilter" checked> Новые
                    </li>
                    <li>
                        <input type="radio" name="articleFilter" value="views"> Популярные
                    </li>
                </ul>
                <div id="question-status">
                    <h4>Статус</h4>
                    <ul id="status">
                        <li>
                            <input type="radio" name="status-input" value="all"> Любой
                        </li>
                        <li>
                            <input type="radio" name="status-input" value="OPEN" checked> Открытые
                        </li>
                        <li>
                            <input type="radio" name="status-input" value="RESOLVED"> Решённые
                        </li>
                        <li>
                            <input type="radio" name="status-input" value="CLOSED"> Закрытые
                        </li>
                    </ul>
                </div>
                <h5 class="form-label">Теги</h5>
                <input class="form-control me-2" type="search" placeholder="Поиск тегов" id="tag-search" style="width: 200px; margin-bottom: 5px;">
                <div id="selected-tags" style="margin-top: 10px; margin-bottom: 10px;"></div>
                <div class="mb-3">
                    <select multiple class="form-select" id="tag-list">
                    </select>
                </div>
            </div>
        </div>

        <div id="questions" class="articles">
            <div class="stream-header" style="display:flex; justify-content: space-between">
                <h2>Поток вопросов</h2>
                <a style="text-decoration: none;" href="/create/question"><button class="btn btn-outline-success">Задать вопрос</button></a>
            </div>
            <div id="questions-stream"></div>
        </div>
    </div>
    <script src="js/question-filter.js"></script>
</#macro>

<#include "base.ftl">