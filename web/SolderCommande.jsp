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
        <h1>Solder une commande</h1>
        
        <table id="commandeListeTable" border="3">
            <tr >
                <th>ID</th>
                <th>Etat</th>
                <th>Coût</th>
            </tr>
            <c:forEach var="commande" begin="0" items="${requestScope.commandeListe}">
                <tr>
                    <td>${commande.idCommande}&nbsp;&nbsp;</td> 
                    <td>${commande.etat}&nbsp;&nbsp;</td> 
                    <td>${commande.cout}&nbsp;&nbsp;</td>
                </tr> 
            </c:forEach>
        </table>
        
        <br>
        
        Solder cette commande
        <form id="solderCommandeForm" action="SolderCommande" method="post">
            <select name="idCommande">
                <c:forEach var="commande" begin="0" items="${requestScope.commandeListe}">
                    <option>${commande.idCommande}</option>
                </c:forEach>
            </select>       
            <input type="submit" id="UpdateRecord" value="Valider" />
        </form>
        
        <br>
        <a href="ListeFacture"><strong>Créer une facture</strong></a>
        <a href="RelancerClient"><strong>Relancer un client</strong></a>
        <a href="Deconnexion"><strong>Se déconnecter</strong></a>
    </body>
</html>
