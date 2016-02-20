<div class="starter-template">
    <form class="form-horizontal" role="form" id='world-create-form' method='POST' action="/world/create">
        <div class="form-group">
            <label class="col-sm-3 control-label" for="title">Title: </label>
            <div class="col-sm-5">
                <input class="form-control" type='text' id="title" name='world-title' placeholder="Enter world name" value="" />
                <input class="form-control" type='text' id="id" name='world-id' placeholder="Enter world id" value="" />
            </div>
        </div>
        
    </form>

    
    <input type='submit' <#if article??>value='Update'<#else>value='Publish'</#if> class="btn btn-primary" form='article-create-form' />
</div>