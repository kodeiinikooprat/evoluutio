<div class="starter-template">
<#if noWorlds??>
    <h3>${noWorlds}</h3>
<#else>
    <h3>${world.getTitle()}</h3>     
    <h3>turn: ${world.getTurn()}</h3>      
    <#if noSpecies??>
    <h3>${noSpecies}</h3>
    <#else>
    <div class="bs-example">
        <ul class="list-group">
            <#list speciesList as species>
            <li class="list-group-item">
                <a href="/world/${world.getId()}/species/${species.getId()}">${species.getName()}</a>
                <span class="badge">${species.getNumberOfAnimals()}</span>
                </li>
            </#list>
            </ul>
        </div>
    </#if>
</#if>
    </div>