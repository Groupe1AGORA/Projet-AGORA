<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agora</title>
    </head>
    <body>
        <h1>Interface Agent Administratif</h1>
        
        Bonjour ${sessionScope.sessionUtilisateur} ! Vous êtes connecté en tant que ${sessionScope.sessionPoste}.
         
        <p>Que souhaitez-vous faire ?</p>

        <a href="ListeOuvrier">Gérer les ouvriers</a><br>
        <a href="ListeEmploye">Gérer les employés</a><br>
        <a href="ListeClient" >Gérer les clients </a><br>
        <a href="Deconnexion" >Se déconnecter </a><br> 
    </body>
</html>
