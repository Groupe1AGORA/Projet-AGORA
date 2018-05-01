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
        
        <h1>Liste des devis</h1>

        <table id="devisListeTable" border="3">
            <tr>
                <th>ID</th>
                <th>Prestation</th>
                <th>Commentaire</th>
                <th>Avis</th>
                <th>Nombre d'heures</th>
                <th>Etat</th>
                <th>Client</th>
                <th>Ouvrier affecté</th>
                <th>Commande correspondante</th>
            </tr>
            <c:forEach var="devis" begin="0" items="${requestScope.devisListe}">
                <tr>
                    <td>${devis.idDevis}&nbsp;&nbsp;</td> 
                    <td>${devis.prestation}&nbsp;&nbsp;</td> 
                    <td>${devis.commentaire}&nbsp;&nbsp;</td>
                    <td>${devis.avis}&nbsp;&nbsp;</td>
                    <td>${devis.nbheures}&nbsp;&nbsp;</td>
                    <td>${devis.etat}&nbsp;&nbsp;</td>
                    <td>${devis.clientidClient.idClient} : ${devis.clientidClient.nom} ${devis.clientidClient.prenom}&nbsp;&nbsp;</td>
                    <td>${devis.ouvrieridOuvrier.idOuvrier} : ${devis.ouvrieridOuvrier.nom} ${devis.ouvrieridOuvrier.prenom}&nbsp;&nbsp;</td>
                    <td>${devis.commandeidCommande.idCommande}&nbsp;&nbsp;</td>
                </tr> 
            </c:forEach>
        </table>
        
        <br>
        
        Modifier ce devis
        <form id="modifierDevisForm" action="ModifierDevis" method="post">
            <select name="idDevis">
                <c:forEach var="devis" begin="0" items="${requestScope.devisListe1}">
                    <option>${devis.idDevis}</option>
                </c:forEach>
            </select>       
            <input type="submit" value="Valider" />
        </form>

        <br>
        
        Supprimer ce devis
        <form id="supprimerDevisForm" action="SupprimerDevis" method="post">
            <select name="idDevis">
                <c:forEach var="devis" begin="0" items="${requestScope.devisListe1}">
                    <option>${devis.idDevis}</option>
                </c:forEach>
            </select>       
            <input type="submit" value="Valider" />
        </form>

        <br>    
        
        Affecter un ouvrier à ce devis
        <form id="affecterOuvrierForm" action="AffecterOuvrier" method="post">
            <select name="idDevis">
                <c:forEach var="devis" begin="0" items="${requestScope.devisListe2}">
                    <option>${devis.idDevis}</option>
                </c:forEach>
            </select>       
            <input type="submit" value="Valider" />
        </form>
        
        <br>
        
        Indiquer que ce devis a été accepté par le client
        <form id="accepterDevisForm" action="ModifierEtat" method="post">
            <select name="idDevis">
                <c:forEach var="devis" begin="0" items="${requestScope.devisListe3}">
                    <option>${devis.idDevis}</option>
                </c:forEach>
            </select>       
            <input type="submit" value="Valider" />
        </form>
        
        <br>
        
        Indiquer que ce devis a été refusé par le client
        <form id="refuserDevisForm" action="ModifierEtatR" method="post">
            <select name="idDevis">
                <c:forEach var="devis" begin="0" items="${requestScope.devisListe3}">
                    <option>${devis.idDevis}</option>
                </c:forEach>
            </select>       
            <input type="submit" value="Valider" />
        </form>

        <h1>Liste des commandes actuellement dans la base de données</h1>

        <table id="commandeListeTable" border="3">
            <tr >
                <th>ID</th>
                <th>Etat</th>
                <th>Coût</th>
                <th>Moyenne</th>
                <th>Client</th>
                <th>Ouvrier affecté</th>
                <th>Devis correspondant</th>
            </tr>
            <c:forEach var="commande" begin="0" items="${requestScope.commandeListe}">
                <tr>
                    <td>${commande.idCommande}&nbsp;&nbsp;</td> 
                    <td>${commande.etat}&nbsp;&nbsp;</td> 
                    <td>${commande.cout}&nbsp;&nbsp;</td>
                    <td>${commande.noteidNote.moyenne}&nbsp;&nbsp;</td>
                    <td>${commande.devisidDevis.clientidClient.idClient} : ${commande.devisidDevis.clientidClient.nom} ${commande.devisidDevis.clientidClient.prenom}&nbsp;&nbsp;</td>
                    <td>${commande.devisidDevis.ouvrieridOuvrier.idOuvrier} : ${commande.devisidDevis.ouvrieridOuvrier.nom} ${commande.devisidDevis.ouvrieridOuvrier.prenom}&nbsp;&nbsp;</td>
                    <td>${commande.devisidDevis.idDevis}&nbsp;&nbsp;</td>
                </tr> 
            </c:forEach>
        </table>
        <br>
        <a href="Deconnexion"><strong>Se déconnecter</strong></a>
    </body>
</html>

