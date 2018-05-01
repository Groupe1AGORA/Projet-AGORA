<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agora</title>
    </head>
    <body>
        <h1>Liste des clients actuellement dans la base de données</h1>

        <table id="clientListeTable" border="3">
            <tr >
                <th>ID</th>
                <th>Pseudo</th>
                <th>Mot de passe</th>
                <th>Nom</th>
                <th>Prénom</th>
                <th>Téléphone</th>
                <th>Mail</th>
                <th>Adresse</th>
            </tr>
            <c:forEach var="client" begin="0" items="${requestScope.clientListe}">
                <tr>
                    <td>${client.idClient}&nbsp;&nbsp;</td> 
                    <td>${client.pseudo}&nbsp;&nbsp;</td>
                    <td>${client.motdePasse}&nbsp;&nbsp;</td>
                    <td>${client.nom}&nbsp;&nbsp;</td> 
                    <td>${client.prenom}&nbsp;&nbsp;</td>
                    <td>${client.telephone}&nbsp;&nbsp;</td> 
                    <td>${client.mail}&nbsp;&nbsp;</td> 
                    <td>${client.adresse}&nbsp;</td>
                </tr> 
            </c:forEach>
        </table>
        
        <br>
        
        Modifier ce client
        <form id="modifierClientForm" action="ModifierClient" method="post">
            <select name="idClient">
                <c:forEach var="client" begin="0" items="${requestScope.clientListe}">
                    <option>${client.idClient}</option>
                </c:forEach>
            </select>       
            <input type="submit" value="Valider" />
        </form>

        <br>
        
        Supprimer ce client
        <form id="supprimerClientForm" action="SupprimerClient" method="post">
            <select name="idClient">
                <c:forEach var="client" begin="0" items="${requestScope.clientListe}">
                    <option>${client.idClient}</option>
                </c:forEach>
            </select>       
            <input type="submit" value="Valider" />
        </form>
    
        <br>
    
        <a href="CreerClient.jsp"><strong>Créer un client</strong></a><br>
    <a href="InterfaceAA.jsp"><strong>Retourner à mon interface</strong></a><br>
    <a href="Deconnexion"><strong>Se déconnecter</strong></a>
</body>
</html>

