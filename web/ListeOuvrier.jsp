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
        <h1>Liste des ouvriers</h1>

        <table id="ouvrierListeTable" border="3">
            <tr>
                <th>ID</th>
                <th>Pseudo</th>
                <th>Mot de passe</th>
                <th>Nom</th>
                <th>Prénom</th>
                <th>Téléphone</th>
                <th>Mail</th>
                <th>Adresse</th>
                <th>Prestation</th>
                <th>Tarif</th>
                <th>Moyenne</th>
                <th>Notes reçues</th>
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
                    <td>${ouvrier.tarif}&nbsp;&nbsp;</td>
                    <td>${ouvrier.moyennefinale}&nbsp;&nbsp;</td>
                    <td>
                    <c:forEach var="devis" begin="0" items="${ouvrier.devisCollection}">
                        ${devis.commandeidCommande.noteidNote.moyenne}&nbsp;&nbsp;</c:forEach></td>
                </tr> 
            </c:forEach>
        </table>
        
        <br>
        
        Modifier cet ouvrier
        <form id="modifierOuvrierForm" action="ModifierOuvrier" method="post">
            <select name="idOuvrier">
                <c:forEach var="ouvrier" begin="0" items="${requestScope.ouvrierListe}">
                    <option>${ouvrier.idOuvrier}</option>
                </c:forEach>
            </select>       
            <input type="submit" value="Valider" />
        </form>
        
        <br>
        
        Supprimer cet ouvrier
        <form id="supprimerOuvrierForm" action="SupprimerOuvrier" method="post">
            <select name="idOuvrier">
                <c:forEach var="ouvrier" begin="0" items="${requestScope.ouvrierListe}">
                    <option>${ouvrier.idOuvrier}</option>
                </c:forEach>
            </select>       
            <input type="submit" value="Valider" />
        </form>
        
        <br>
        
        <a href="CreerOuvrier.jsp"><strong>Créer un ouvrier</strong></a><br>
        <a href="InterfaceAA.jsp"><strong>Retourner à l'interface</strong></a><br>
        <a href="Deconnexion"><strong>Se déconnecter</strong></a>  
    </body>
</html>

