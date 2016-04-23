<div class="starter-template">
<#if noWorlds??>
    <h3>${noWorlds}</h3>
<#else>
    <h3>${world.getTitle()}</h3>     
    <h3>${world.getTurn()}</h3>      
    <#if noSpecies??>
        <h3>${noSpecies}</h3>
    <#else>
        <div class="starter-template">
            <#list speciesList as species>
            <h3>${species.getName()}</h3>
            <h4>${species.getNumberOfAnimals()}</h4>
            </#list>
            </div>
    </#if>
</#if>
</div>