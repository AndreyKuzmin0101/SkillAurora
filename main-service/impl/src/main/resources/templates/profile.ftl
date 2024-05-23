<#macro title>Мой профиль</#macro>
<#macro headtags>
    <script src="/js/validate.js"></script>
    <link rel="stylesheet" href="/css/styles.css">
    <script src="/js/tags.js"></script>
    <script type="module" src="/js/profile.js"></script>
</#macro>

<#macro content>
    <div class="profile">
        <h2>Профиль пользователя</h2>
        <div class="info-item">
            <span>Имя пользователя:</span>
            <span id="username"></span>
            <button class="edit-btn" data-target="Username">✏️</button>
            <div class="edit-field" id="editUsername">
                <input id ="username-input" type="text" placeholder="Новое имя пользователя">
                <button id="username-button" data-target="Username" class="confirm-btn">Подтвердить</button>
            </div>
        </div>
        <div class="info-item">
            <span>Настоящее имя</span>
            <span id="realName"></span>
            <button class="edit-btn" data-target="RealName">✏️</button>
            <div class="edit-field" id="editRealName">
                <input id="realName-input" type="text" placeholder="Настроящее имя">
                <button id="realName-button" data-target="RealName" class="confirm-btn">Подтвердить</button>
            </div>
        </div>
        <div class="info-item">
            <span>Возраст</span>
            <span id="age"></span>
            <button class="edit-btn" data-target="Age">✏️</button>
            <div class="edit-field" id="editAge">
                <input id="age-input" type="number" placeholder="Возраст">
                <button id="age-button" data-target="Age" class="confirm-btn">Подтвердить</button>
            </div>
        </div>
        <div class="info-item">
            <span>Email:</span>
            <span id="email"></span>
            <button class="edit-btn" data-target="Email">✏️</button>
            <div class="edit-field" id="editEmail">
                <input id="email-input" type="email" placeholder="Новый Email">
                <button id="email-button" data-target="Email" class="confirm-btn">Подтвердить</button>
            </div>
        </div>
        <div class="info-item">
            <span>Страна:</span>
            <span id="country"></span>
            <button class="edit-btn" data-target="Country">✏️</button>
            <div class="edit-field" id="editCountry">
                <input class="form-control me-2" type="search" placeholder="Поиск стран" id="country-search" style="width: 200px; margin-bottom: 5px; display: inline-block">
                <button id="search-countries" class="edit-btn">🔍</button>
                <div class="mb-3">
                    <select class="form-select" id="country-list">
                    </select>
                </div>
                <button id="country-button" data-target="Country" class="confirm-btn">Подтвердить</button>
            </div>
        </div>
        <div class="info-item">
            <span>Город:</span>
            <span id="city"></span>
            <button class="edit-btn" data-target="City">✏️</button>
            <div class="edit-field" id="editCity">
                <input class="form-control me-2" type="search" placeholder="Поиск стран" id="city-search" style="width: 200px; margin-bottom: 5px; display: inline-block">
                <button id="search-cities" class="edit-btn">🔍</button>
                <div class="mb-3">
                    <select class="form-select" id="city-list">
                    </select>
                </div>
                <button id="city-button" data-target="City" class="confirm-btn">Подтвердить</button>
            </div>
        </div>
        <div class="info-item">
            <span>Дата регистрации:</span>
            <span id="registerDate"></span>
        </div>
        <div class="info-item">
            <span>Рейтинг:</span>
            <span id="rating"></span>
        </div>
        <div class="info-item">
            <span>Навыки:</span>
            <span id="skills"></span>
            <br>
            <button class="edit-btn confirm-btn" data-target="Skills"️ style="margin-top: 5px">Изменить навыки</button>
            <div class="edit-field" id="editSkills">
                <input class="form-control me-2" type="search" placeholder="Поиск тегов" id="tag-search" style="width: 200px; margin-bottom: 5px;">
                <div id="selected-tags" style="margin-top: 10px; margin-bottom: 10px;">
                </div>
                <div class="mb-3">
                    <select multiple class="form-select" id="tag-list">
                    </select>
                </div>
                <button id="skills-button" data-target="Skills" class="confirm-btn">Подтвердить</button>
            </div>
        </div>

        <div>
            <button class="edit-btn confirm-btn" style="margin-top: 20px" data-target="Password">Изменить пароль</button>
            <div class="edit-field" id="editPassword">
                <input id="password-input" type="password" placeholder="Новый пароль">
                <input id="confirmedPassword-input" type="password" placeholder="Подтвердите пароль">
                <button id="password-button" data-target="Password" class="confirm-btn">Подтвердить</button>
            </div>
        </div>
        </div>
    </div>
    <div class="profile-img">
        <div id="profile-image"><img id="profile-img" src="" style="width: 230px"></div>
        <label for="image-input">Аватарка</label>
        <input type="file" name="image" id="image-input" accept=".png, .jpg, .jpeg" title=" ">
        <br>
        <button style="margin-top: 10px" id="upload-image">Загрузить</button>
    </div>
    <script type="module" src="/js/settings.js"></script>
</#macro>

<#include "base.ftl">