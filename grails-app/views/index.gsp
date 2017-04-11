<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Welcome to Grails</title>
    <asset:stylesheet href="bootstrap.css"/>
    <asset:javascript src="bootstrap.js"/>
    <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>
    <style>
    .row {
        margin: 5px;
    }
    </style>
</head>

<body>
<div class="row">
    <div class="col-md-12">
        <legend>Payment Gateways</legend>

        <div class="row">
            <div class="col-md-12">
                <g:link controller="NI" action="nonSeamless" class="btn btn-danger">Network International</g:link>
                <g:link controller="payu" action="seamless" class="btn btn-danger">Payu</g:link>
                <g:link controller="demo" action="nonSeamless" class="btn btn-danger">CCAvenue</g:link>
            </div>
        </div>

    </div>
</div>
</body>
</html>
