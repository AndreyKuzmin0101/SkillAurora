<#macro title>Сообщения</#macro>
<#macro headtags>
    <link rel="stylesheet" href="/css/styles.css">
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/webjars/sockjs-client/sockjs.min.js"></script>
    <script type="text/javascript" src="/webjars/stomp-websocket/stomp.min.js"></script>
    <script type="module" src="/js/chats.js"></script>
</#macro>

<#macro content>
    <div class="main-content">
        <div id="chats" class="chats">

        </div>
        <div id="chat" class="messages">

        </div>
    </div>

</#macro>

<#include "base.ftl">