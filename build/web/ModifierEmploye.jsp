<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agora</title>
    </head>
    <body>
        <h1>Modifier un employe</h1>

        <p>Note : Un champ vide ne sera pas modifié.<p>

        <form action="ModifierEmploye" method="post">
            <table>
                <c:forEach var="employe" begin="0" items="${requestScope.employeListe}">
                    <tr><td>ID</td><td><input type="text" id = "idEmploye" name="idEmploye" value="${employe.idEmploye}" readonly/></td></tr>
                    <tr><td>Pseudo</td><td><input type="text" id = "pseudo" name="pseudo" value="${employe.pseudo}" /></td></tr>
                    <tr><td>Mot de passe</td><td><input type="password" id = "motdepasse" name="motdepasse" /></td></tr>
                    <tr><td>Nom</td><td><input type="text" id = "nom" name="nom" value="${employe.nom}" /></td></tr>
                    <tr><td>Prénom</td><td><input type="text" id = "prenom" name="prenom" value="${employe.prenom}" /></td></tr>
                    <tr><td>Téléphone</td><td><input type="text" id = "telephone" name="telephone" value="${employe.telephone}" /></td></tr>
                    <tr><td>Mail</td><td><input type="text" id = "mail" name="mail" value="${employe.mail}" /></td></tr>
                    <tr><td>Adresse</td><td><input type="text" id = "adresse" name="adresse" value="${employe.adresse}" /></td></tr>
                    <tr><td>Poste</td><td><select id = "poste" name = "poste">
                                <option label="Poste inchangé"></option>
                                <option value="Aucun">Aucun</option>
                                <option value="Service Technique">Service Technique</option>
                                <option value="Agent Administratif">Agent Administratif</option>
                                <option value="Service Marketing">Service Marketing</option>
                                <option value="Service Comptable">Service Comptable</option>
                            </select></td></tr>
                        </c:forEach>
            </table>
            <input type="submit" value="Valider" />
        </form>

        <br>

        <a href="ListeEmploye"><strong>Voir la liste des employés</strong></a><br>
        <a href="InterfaceAA.jsp"><strong>Retourner à mon interface</strong></a><br>
        <a href="Deconnexion"><strong>Se déconnecter</strong></a>
    </body>
</html>