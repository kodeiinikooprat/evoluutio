<#ftl strip_whitespace = true>

<html>
    <head>
        <title>Freemarker</title>
        <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="http://getbootstrap.com/examples/starter-template/starter-template.css">
        </head>
    <body>

        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        </button>
                    <a class="navbar-brand" href="/">
                        <span class="glyphicon glyphicon-home"></span>
                        Evolution</a>
                    </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <li><a href="/nextturn"><span class="glyphicon glyphicon-play"></span> Next turn</a></li>
                        <li><a href="/admin"><span class="glyphicon glyphicon-wrench"></span> Admin</a></li>
                        </ul>
                      </div><!--/.nav-collapse -->
                </div>
            </nav>

        <div class="page-header">
            <h1>Evolution - THE GAME!</h1>
            </div>

        <div class="container" role="main">
            <div class="row">
                <div class="col-md-8">
            <#include "${primary}">
                    </div>

                <div class="col-md-4">
        <#include "${sidebar}">
                    </div>

                </div>
            </div>

        <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
        <script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.2/js/bootstrap.min.js"></script>
        </body>
    </html>