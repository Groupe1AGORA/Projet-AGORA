<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agora</title>
    </head>
    <body>
        <h1>Créer un ouvrier</h1>
        
        <form id="creerOuvrierForm" action="CreerOuvrier" method="post">
            <table>
                <tr><td>Pseudo</td><td><input type="text" id = "pseudo" name="pseudo" required/></td></tr>
                <tr><td>Mot de passe</td><td><input type="password" id = "motdepasse" name="motdepasse" required/></td></tr>
                <tr><td>Nom</td><td><input type="text" id = "nom" name="nom" required/></td></tr>
                <tr><td>Prenom</td><td><input type="text" id = "prenom" name="prenom" required/></td></tr>
                <tr><td>Telephone</td><td><input type="text" id = "telephone" name="telephone" required/></td></tr>
                <tr><td>Mail</td><td><input type="text" id = "mail" name="mail" required/></td></tr>
                <tr><td>Adresse</td><td><input type="text" id = "adresse" name="adresse" required/></td></tr>
                <tr><td>Prestation(s) proposée(s)</td><td><select id = "prestation" name = "prestation" >
                            <option value="Aucune">Aucune</option>
                            <option value="Toiture">Toiture</option>
                            <option value="Plomberie">Plomberie</option>
                            <option value="Electricite">Electricite</option>
                            <option value="Peinture">Peinture</option>
                        </select></td></tr>
            </table>
            <input type="submit" value="Valider" />
        </form>
        
        <br>
        <a href="ListeOuvrier"><strong>Voir la liste des ouvriers</strong></a>
        <a href="InterfaceAA.jsp"><strong>Retourner à mon interface</strong></a>
    </body>
</html>
