<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agora</title>
    </head>
    <body>
        <h1>Modifier un ouvrier</h1>
        
        <p>Note : Un champ vide ne sera pas modifié.<p>
        
        <form action="ModifierOuvrier" method="post">
            <table>
                <c:forEach var="ouvrier" begin="0" items="${requestScope.ouvrierListe}">
                    <tr><td>ID</td><td><input type="text" id = "idOuvrier" name="idOuvrier" value="${ouvrier.idOuvrier}" readonly/></td></tr>
                    <tr><td>Pseudo</td><td><input type="text" id = "pseudo" name="pseudo" value="${ouvrier.pseudo}" /></td></tr>
                    <tr><td>Mot de passe</td><td><input type="password" id = "motdepasse" name="motdepasse" /></td></tr>
                    <tr><td>Nom</td><td><input type="text" id = "nom" name="nom" value="${ouvrier.nom}" /></td></tr>
                    <tr><td>Prénom</td><td><input type="text" id = "prenom" name="prenom" value="${ouvrier.prenom}" /></td></tr>
                    <tr><td>Téléphone</td><td><input type="text" id = "telephone" name="telephone" value="${ouvrier.telephone}" /></td></tr>
                    <tr><td>Mail</td><td><input type="text" id = "mail" name="mail" value="${ouvrier.mail}" /></td></tr>
                    <tr><td>Adresse</td><td><input type="text" id = "adresse" name="adresse" value="${ouvrier.adresse}" /></td></tr>
                    <tr><td>Prestation(s) proposée(s)</td><td><select id = "prestation" name = "prestation" >
                                <option label="Prestation inchangée"></option>
                                <option value="Aucune">Aucune</option>
                                <option value="Toiture">Toiture</option>
                                <option value="Plomberie">Plomberie</option>
                                <option value="Electricite">Electricite</option>
                                <option value="Peinture">Peinture</option>
                            </select></td></tr>
                    <tr><td>Tarif (€/h)</td><td><input type="text" id = "tarif" name="tarif" value="${ouvrier.tarif}" /></td></tr>
                        </c:forEach>
            </table>
            <input type="submit" value="Valider" />
        </form>
        
        <br>
        
        <a href="ListeOuvrier"><strong>Voir la liste des ouvriers</strong></a><br>
        <a href="InterfaceAA.jsp"><strong>Retourner à mon interface</strong></a><br>
        <a href="Deconnexion"><strong>Se déconnecter</strong></a>
    </body>
</html>