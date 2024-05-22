<#macro title>Профиль ${user.username()}</#macro>
<#macro headtags>
    <link rel="stylesheet" href="/css/styles.css">
</#macro>

<#macro content>
    <div class="profile">
        <h2>Профиль пользователя <strong>${user.username()}</strong></h2>
        <#if user.realName()??>
            <div class="info-item">
                <span>Настоящее имя</span>
                <span id="realName">${user.realName()}</span>
            </div>
        </#if>
        <#if user.age()??>
            <div class="info-item">
                <span>Возраст</span>
                <span id="age">${user.age()}</span>
            </div>
        </#if>
        <#if user.country()??>
            <div class="info-item">
                <span>Страна</span>
                <span id="age">${user.country()}</span>
            </div>
        </#if>
        <#if user.city()??>
            <div class="info-item">
                <span>Город</span>
                <span id="age">${user.city()}</span>
            </div>
        </#if>
        <div class="info-item">
            <span>Дата регистрации:</span>
            <span id="registerDate">${user.registerDate()}</span>
        </div>
        <div class="info-item">
            <span>Рейтинг:</span>
            <span id="rating">${user.rating()}</span>
        </div>
        <#if user.skills()?size gt 0>
            <div class="info-item">
                <span>Навыки:</span>
                <span id="skills">
                    <#list user.skills() as skill>
                        <span class="tag-style">${skill.name()}</span>
                    </#list>
                </span>
            </div>
        </#if>
    </div>

    <div class="profile-img">
        <div id="profile-image"><img id="profile-img" src="" style="width: 230px"></div>
        <label for="image-input">Аватарка</label>
    </div>
</#macro>

<#include "base.ftl">