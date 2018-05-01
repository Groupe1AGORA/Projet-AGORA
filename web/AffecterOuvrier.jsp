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
        <h1>Affecter un ouvrier à un devis</h1>
        
        <table id="devisListeTable" border="3">
            <tr >
                <th>ID</th>
                <th>Prestation</th>
                <th>Commentaire</th>
                <th>Avis</th>
                <th>Nombre d'heures</th>
                <th>Etat</th>
            </tr>
            <tr>
                <td>${d.idDevis}&nbsp;&nbsp;</td> 
                <td>${d.prestation}&nbsp;&nbsp;</td> 
                <td>${d.commentaire}&nbsp;&nbsp;</td>
                <td>${d.avis}&nbsp;&nbsp;</td>
                <td>${d.nbheures}&nbsp;&nbsp;</td>
                <td>${d.etat}&nbsp;&nbsp;</td>
            </tr> 
        </table>
            
            <br>
            
        <table id="ouvrierListeTable" border="3">
            <tr >
                <th>ID</th>
                <th>Pseudo</th>
                <th>Mot de passe</th>
                <th>Nom</th>
                <th>Prénom</th>
                <th>Téléphone</th>
                <th>Mail</th>
                <th>Adresse</th>
                <th>Prestation</th>
                <th>Moyenne</th>
            </tr>
            <c:forEach var="ouvrier" begin="0" items="${requestScope.ouvrierListe}">
                <tr>
                    <td>${ouvrier.idOuvrier}&nbsp;&nbsp;</td>
                    <td>${ouvrier.pseudo}&nbsp;&nbsp;</td>
                    <td>${ouvrier.motdepasse}&nbsp;&nbsp;</td>
                    <td>${ouvrier.nom}&nbsp;&nbsp;</td> 
                    <td>${ouvrier.prenom}&nbsp;&nbsp;</td>
                    <td>${ouvrier.telephone}&nbsp;&nbsp;</td> 
                    <td>${ouvrier.mail}&nbsp;&nbsp;</td> 
                    <td>${ouvrier.adresse}&nbsp;</td>
                    <td>${ouvrier.prestation}&nbsp;&nbsp;</td> 
                    <td>${ouvrier.moyennefinale}&nbsp;&nbsp;</td>
                </tr> 
            </c:forEach>
        </table>
        
            <br>
            
        <form id="affecterOuvrierForm" action="AffecterOuvrier" method="post">
            Affecter cet ouvrier
            <select name="idOuvrier">
                <c:forEach var="ouvrier" begin="0" items="${requestScope.ouvrierListe}">
                    <option>${ouvrier.idOuvrier}</option>
                </c:forEach>
            </select>
            à ce devis
            <select name="idDevis">
                    <option>${d.idDevis}</option>
            </select>
            <input type="submit" id="DeleteRecord" value="Valider" />
        </form> 
            <br>
            
            <a href="ListeDevis"><strong>Voir la liste des devis</strong></a><br>
            <a href="Deconnexion"><strong>Se déconnecter</strong></a>
    </body>
</html>
