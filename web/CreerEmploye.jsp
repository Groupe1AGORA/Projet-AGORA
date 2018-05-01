<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agora</title>
    </head>
    <body>
        <h1>Créer un employé</h1>
        
        
        <form id="creerEmployeForm" action="CreerEmploye" method="post">
            <table>
                <tr><td>Pseudo</td><td><input type="text" id = "pseudo" name="pseudo" required/></td></tr>
                <tr><td>Mot de passe</td><td><input type="password" id = "motdepasse" name="motdepasse" required/></td></tr>
                <tr><td>Nom</td><td><input type="text" id = "nom" name="nom" required/></td></tr>
                <tr><td>Prenom</td><td><input type="text" id = "prenom" name="prenom" required/></td></tr>
                <tr><td>Telephone</td><td><input type="text" id = "telephone" name="telephone" required/></td></tr>
                <tr><td>Mail</td><td><input type="text" id = "mail" name="mail" required/></td></tr>
                <tr><td>Adresse</td><td><input type="text" id = "adresse" name="adresse" required/></td></tr>
                <tr><td>Poste</td><td><select id = "poste" name = "poste" required>
                            <option value="Aucun">Aucun</option>
                            <option value="Service Technique">Service Technique</option>
                            <option value="Agent Administratif">Agent Administratif</option>
                            <option value="Service Marketing">Service Marketing</option>
                            <option value="Service Comptable">Service Comptable</option>
                        </select></td></tr>
            </table>
            <input type="submit" id="CreateRecord" value="Valider" />
        </form>
        <a href="ListeEmploye"><strong>Voir la liste des employés</strong></a>
    </body>
</html>
