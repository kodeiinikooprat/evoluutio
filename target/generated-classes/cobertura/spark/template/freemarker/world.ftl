<div class="starter-template">
        <#if noWorlds??>
    <h3>${noWorlds}</h3>
<#else>
    <h3>${world.getTitle()}</h3>     
    <h3>${world.getTurn()}</h3>      
    </#if>       
    </div>