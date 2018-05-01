<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agora</title>
    </head>
    <body>
        <h1>Modifier un devis</h1>

        <p>Note : Un champ vide ne sera pas modifié.<p>

        <form action="ModifierDevis" method="post">
            <table>
                <c:forEach var="devis" begin="0" items="${requestScope.devisListe}">
                    <tr><td>ID</td><td><input type="text" id = "idDevis" name="idDevis" value="${devis.idDevis}" readonly/></td></tr>
                    <tr><td>Prestation</td><td><input type="text" id = "prestation" name="prestation" value="${devis.prestation}" disabled/></td></tr>
                    <tr><td>Commentaire</td><td><input type="text" id = "commentaire" name="commentaire" value="${devis.commentaire}" disabled/></td></tr>
                    <tr><td>Avis</td><td><input type="text" id = "avis" name="avis" /></td></tr>
                    <tr><td>Nombre d'heures</td><td><input type="text" id = "nbheures" name="nbheures" /></td></tr>
                        </c:forEach>
            </table>
            <input type="submit" value="Valider" />
        </form>

        <br>

        <a href="ListeDevis"><strong>Voir la liste des devis</strong></a><br>
        <a href="Deconnexion"><strong>Se déconnecter</strong></a>
    </body>
</html>
