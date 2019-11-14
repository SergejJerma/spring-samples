<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">

<@c.page>
<div class="card-columns">
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
                    </div>
        </div>
  </div>
 
 <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="true" aria-controls="collapseExample">
    Add new Comment
</a>
<div class="collapse" id="collapseExample">
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data">
            <div class="form-group">
                <input type="text" class="form-control" name="body" placeholder="Write comment" />
            </div> 
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div class="form-group">
              <button type="submit" class="btn btn-secondary">Post</button>
            </div>
        </form>
    </div>
</div>
 <div class="card-columns">

    <#list comments?ifExists as comment>
        <div class="card my-3">
                <div class="m-2">
                <span>${comment.body}</span><br/>
                <i>${comment.createDate}</i>
            </div>
           <div class="card-footer text-muted">
                 User name: ${comment.authorName} </br>              
                    </div>
        </div>

    <#else>
        No comments
    </#list>
</div>


</@c.page>