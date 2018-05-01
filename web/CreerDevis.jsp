<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agora</title>
    </head>
    <body>
        <h1>Créer un devis</h1>
        
        <form id="creerDevisForm" action="CreerDevis" method="post">
            <table>
                <tr><td>Prestation</td><td><select id = "prestation" name = "prestation">
                            <option value="Toiture">Toiture</option>
                            <option value="Plomberie">Plomberie</option>
                            <option value="Electricite">Electricite</option>
                            <option value="Peinture">Peinture</option>
                        </select></td></tr>
                <tr><td>Commentaire</td><td><input type="text" id = "commentaire" name="commentaire"/></td></tr>
            </table>
            <input type="submit" value="Valider" />
        </form>
        
        <br>
        
        <a href="ListeDevis"><strong>Voir la liste des devis</strong></a><br>
        <a href="Deconnexion"><strong>Se déconnecter</strong></a>
    </body>
</html>
