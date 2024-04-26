<#macro title>Поток статей</#macro>

<#macro headtags>
    <link rel="stylesheet" href="/css/styles.css">
    <script src="/js/tags.js"></script>
</#macro>

<#macro content>
    <div class="main-content">
        <div class="filter">
            <h2>Фильтр</h2>
            <div class="search">
                <input type="text" placeholder="Поиск...">
                <button class="btn btn-outline-success">Найти</button>
            </div>
            <div class="article-filter">
                <h3>Сначала показывать</h3>
                <ul>
                    <li>
                        <input type="radio" name="articleFilter" value="new" checked> Новые
                    </li>
                    <li>
                        <input type="radio" name="articleFilter" value="popular"> Популярные
                    </li>
                </ul>
                <div id="rating-threshold">
                    <h4>Порог рейтинга</h4>
                    <ul>
                        <li>
                            <input type="radio" name="rating-input" value="all" checked> Все
                        </li>
                        <li>
                            <input type="radio" name="rating-input" value="10"> ≥ 0
                        </li>
                        <li>
                            <input type="radio" name="rating-input" value="10"> ≥ 10
                        </li>
                        <li>
                            <input type="radio" name="rating-input" value="25"> ≥ 25
                        </li>
                        <li>
                            <input type="radio" name="rating-input" value="50"> ≥ 50
                        </li>
                        <li>
                            <input type="radio" name="rating-input" value="100"> ≥ 100
                        </li>
                    </ul>
                </div>
                <div id="period-popular">
                    <h4>Период</h4>
                    <ul>
                        <li>
                            <input type="radio" name="period-input" value="day"> Сутки
                        </li>
                        <li>
                            <input type="radio" name="period-input" value="week"> Неделя
                        </li>
                        <li>
                            <input type="radio" name="period-input" value="month"> Месяц
                        </li>
                        <li>
                            <input type="radio" name="period-input" value="all"> Всё время
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
        <div class="articles">
            <!-- Список статей -->
        </div>
    </div>
    <script src="js/article-filter.js"></script>
</#macro>

<#include "base.ftl">