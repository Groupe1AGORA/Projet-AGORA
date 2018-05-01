<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agora</title>
    </head>
    <body>
        <h1>Modifier un client</h1>
        
        <p>Note : Un champ vide ne sera pas modifié.<p>
        
        <form action="ModifierClient" method="post">
            <table>
                <c:forEach var="client" begin="0" items="${requestScope.clientListe}">
                    <tr><td>ID</td><td><input type="text" id = "idClient" name="idClient" value="${client.idClient}" readonly/></td></tr>
                    <tr><td>Pseudo</td><td><input type="text" id = "pseudo" name="pseudo" value="${client.pseudo}" /></td></tr>
                    <tr><td>Mot de passe</td><td><input type="password" id = "motdepasse" name="motdepasse" /></td></tr>
                    <tr><td>Nom</td><td><input type="text" id = "nom" name="nom" value="${client.nom}" /></td></tr>
                    <tr><td>Prénom</td><td><input type="text" id = "prenom" name="prenom" value="${client.prenom}" /></td></tr>
                    <tr><td>Téléphone</td><td><input type="text" id = "telephone" name="telephone" value="${client.telephone}" /></td></tr>
                    <tr><td>Mail</td><td><input type="text" id = "mail" name="mail" value="${client.mail}" /></td></tr>
                    <tr><td>Adresse</td><td><input type="text" id = "adresse" name="adresse" value="${client.adresse}" /></td></tr>
                        </c:forEach>
            </table>
            <input type="submit" id="ModifyRecord" value="Valider" />
        </form>

        <br>
        
        <a href="ListeClient"><strong>Voir la liste des clients</strong></a><br>
        <a href="InterfaceAA.jsp"><strong>Retourner à mon interface</strong></a><br>
        <a href="Deconnexion"><strong>Se déconnecter</strong></a>
    </body>
</html>