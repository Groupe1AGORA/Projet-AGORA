<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agora</title>
    </head>
    <body>
        <h1>Oups...</h1>
        Il semblerait qu'il y ait eu une erreur.
        
        <br><br>
        Détail :
        <br>
        ${requestScope.messageErreur}   
    </body>
</html>
