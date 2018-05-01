<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agora</title>
    </head>
    <body>
        <h1>Créer une note</h1>
        <form id="creerOuvrierForm" action="CreerNote" method="post">
            <table>
                <tr><td>notequalite</td><td><input type="number" id = "notequalite" name="notequalite"  min="1" max="10" required/></td></tr>
                <tr><td>noteproprete</td><td><input type="number" id = "noteproprete" name="noteproprete" min="1" max="10" required/></td></tr>
                <tr><td>noterespect</td><td><input type="number" id = "noterespect" name="noterespect" min="1" max="10" required /></td></tr>
                <tr><td>noteglobale</td><td><input type="number" id = "noteglobale" name="noteglobale" min="1" max="10" required/></td></tr>
            </table>
            Affecter la note à cette commande
            <select name="idCommande">
                <c:forEach var="commande" begin="0" items="${requestScope.commandeListe}">
                    <option>${commande.idCommande}</option>
                </c:forEach>
            </select>
            <input type="submit" value="Valider" />
        </form>
        <br>
        <a href="Deconnexion"><strong>Se déconnecter</strong></a>
    </body>
</html>