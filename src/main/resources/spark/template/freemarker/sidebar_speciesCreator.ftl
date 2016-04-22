<#ftl strip_whitespace = true>

<div class="panel panel-primary">
    <div class ="panel-heading">
        <h3 class ="panel-title">Manage species!</h3>
        </div>
    <div class="panel-body">
        <form role="form" id='species-create-form' method='POST' action="/species/create">
            <div class="form-group">
                <label for="speciesName">Name: </label>
                <input type="text" class="form-control" id="speciesName" name='species-name' placeholder="Name of species"  />
                </div>
            <input type='submit' value='Create' class="btn btn-primary" form='species-create-form' />
            </form>
        </div>