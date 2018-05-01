<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agora</title>
    </head>
    <body>
        
         <p>Bonjour ${sessionScope.sessionUtilisateur} ! Vous êtes connecté en tant que ${sessionScope.sessionPoste}.<p>
        <h1>Liste des devis vous concernant.</h1>

       

        <table id="devisListeTable" border="3">
            <tr >
                <th>ID</th>
                <th>Prestation</th>
                <th>Commentaire</th>
                <th>Avis</th>
                <th>Nombre d'heures</th>
                <th>Prix</th>
                <th>Etat</th>
                <th>Ouvrier assigné</th>
                <th>Commande correspondante</th>
                <th>Etat de la commande</th>
            </tr>
            <c:forEach var="devis" begin="0" items="${requestScope.devisListe}">
                <tr>
                    <td>${devis.idDevis}&nbsp;&nbsp;</td> 
                    <td>${devis.prestation}&nbsp;&nbsp;</td> 
                    <td>${devis.commentaire}&nbsp;&nbsp;</td>
                    <td>${devis.avis}&nbsp;&nbsp;</td>
                    <td>${devis.nbheures}&nbsp;&nbsp;</td>
                    <td>${devis.prix}&nbsp;&nbsp;</td>
                    <td>${devis.etat}&nbsp;&nbsp;</td>
                    <td>${devis.ouvrieridOuvrier.nom} ${devis.ouvrieridOuvrier.prenom}&nbsp;&nbsp;</td>
                    <td>${devis.commandeidCommande.idCommande}&nbsp;&nbsp;</td>
                    <td>${devis.commandeidCommande.etat}&nbsp;&nbsp;</td>
                </tr> 
            </c:forEach>
        </table> 

        <br>

        <a href="CreerDevis.jsp"><strong>Créer un devis</strong></a>

        <h1>Liste des commandes vous concernant.</h1>  

        <table id="commandeListeTable" border="3">
            <tr >
                <th>ID</th>
                <th>Etat</th>
                <th>Coût</th>
                <th>Moyenne</th>
            </tr>
            <c:forEach var="commande" begin="0" items="${requestScope.commandeListe}">
                <tr>
                    <td>${commande.idCommande}&nbsp;&nbsp;</td> 
                    <td>${commande.etat}&nbsp;&nbsp;</td> 
                    <td>${commande.cout}&nbsp;&nbsp;</td>
                    <td>${commande.noteidNote.moyenne}&nbsp;&nbsp;</td>
                </tr> 
            </c:forEach>
        </table>

        <br>

        <a href="Deconnexion"><strong>Se déconnecter</strong></a>
    </body>
</html>

