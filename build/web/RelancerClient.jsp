<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agora</title>
    </head>
    <body>
        Bonjour ${sessionScope.sessionUtilisateur} ! Vous êtes connecté en tant que ${sessionScope.sessionPoste}.
        <h1>Relancer un client</h1>
             
        <table id="commandeListeTable" border="3">
            <tr >
                <th>ID</th>
                <th>Etat</th>
                <th>Coût</th>
                <th>Prestation</th>
                <th>Moyenne</th>
            </tr>
            <c:forEach var="commande" begin="0" items="${requestScope.commandeListe}">
                <tr>
                    <td>${commande.idCommande}&nbsp;&nbsp;</td> 
                    <td>${commande.etat}&nbsp;&nbsp;</td> 
                    <td>${commande.cout}&nbsp;&nbsp;</td>
                    <td>${commande.devisidDevis.prestation}&nbsp;&nbsp;</td>
                    <td>${commande.devisidDevis.clientidClient.nom}&nbsp;&nbsp;</td>
                    
                </tr> 
            </c:forEach>
        </table>
         
        <br>
        
        Relancer le client pour la commande
        <form id="relancerClientForm" action="RelancerCommande" method="post">
            <select name="idCommande">
                <c:forEach var="commande" begin="0" items="${requestScope.commandeListe}">
                    <option>${commande.idCommande}</option>
                </c:forEach>
            </select>       
            <input type="submit" value="Valider" />
        </form>
        
        <br>
        <a href="ListeCommande"><strong>Solder une commande</strong></a>
        <a href="ListeFacture"><strong>Créer une facture</strong></a>
        <a href="Deconnexion"><strong>Se déconnecter</strong></a>
    </body>
</html>
