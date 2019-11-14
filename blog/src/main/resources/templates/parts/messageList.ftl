<#include "security.ftl">

<div class="card-columns">
    <#list messages as message>
        <div class="card my-3">
            <#if message.filename??>
                <img src="/img/${message.filename}" class="card-img-top">
            </#if>
            <div class="m-2">
                <span>${message.text}</span><br/>
                <i>#${message.tag}</i>
            </div>
            <div class="card-footer text-muted">
                 Author name: ${message.authorName} </br>
               <a href="/message?id=${message.id}"/>link to comments</a>
                
                <#if message.author.id == currentUserId>
                    <a class="btn btn-primary" href="/user-messages/${message.author.id}?message=${message.id}">
                        Edit
                    </a>
                   <a type="button" class="btn btn-danger" href="/delete?id=${message.id}">Delete</a>
                    
                </#if>
            </div>
        </div>
    <#else>
        No messages
    </#list>
</div>