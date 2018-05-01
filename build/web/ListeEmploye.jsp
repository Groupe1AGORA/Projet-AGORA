<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agora</title>
    </head>
    <body>
        <h1>Liste des employés actuellement dans la base de données</h1>

        <table id="employeListeTable" border="3">
            <tr >
                <th>ID</th>
                <th>Pseudo</th>
                <th>Mot de passe</th>
                <th>Nom</th>
                <th>Prénom</th>
                <th>Téléphone</th>
                <th>Mail</th>
                <th>Adresse</th>
                <th>Poste</th>
            </tr>
            <c:forEach var="employe" begin="0" items="${requestScope.employeListe}">
                <tr>
                    <td>${employe.idEmploye}&nbsp;&nbsp;</td> 
                    <td>${employe.pseudo}&nbsp;&nbsp;</td>
                    <td>${employe.motdePasse}&nbsp;&nbsp;</td>
                    <td>${employe.nom}&nbsp;&nbsp;</td> 
                    <td>${employe.prenom}&nbsp;&nbsp;</td>
                    <td>${employe.telephone}&nbsp;&nbsp;</td> 
                    <td>${employe.mail}&nbsp;&nbsp;</td> 
                    <td>${employe.adresse}&nbsp;</td>
                    <td>${employe.poste}&nbsp;&nbsp;</td>
                </tr> 
            </c:forEach>
        </table>
        
        <br>
        
        Modifier cet employé
        <form id="modifierEmployeForm" action="ModifierEmploye" method="post">
            <select name="idEmploye">
                <c:forEach var="employe" begin="0" items="${requestScope.employeListe}">
                    <option>${employe.idEmploye}</option>
                </c:forEach>
            </select>       
            <input type="submit" value="Valider" />
        </form>

        <br>
        
        Supprimer cet employé
        <form id="supprimerEmployeForm" action="SupprimerEmploye" method="post">
            <select name="idEmploye">
                <c:forEach var="employe" begin="0" items="${requestScope.employeListe}">
                    <option>${employe.idEmploye}</option>
                </c:forEach>
            </select>       
            <input type="submit" value="Valider" />
        </form>
    
        <br>    
        
        <a href="CreerEmploye.jsp"><strong>Créer un employé</strong></a><br>
        <a href="InterfaceAA.jsp"><strong>Retourner à mon interface</strong></a><br>
    <a href="Deconnexion"><strong>Se déconnecter</strong></a>
</body>
</html>

