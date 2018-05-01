<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agora</title>
    </head>
    <body>
        <h1>S'authentifier</h1>
        ${requestScope.message}
        <form id="authentificationForm" action="Authentification" method="post" onsubmit="return confirm('Êtes-vous sûr de votre choix ?');">
            <table>
                <tr><td>Pseudo : </td><td><input type="text" id = "pseudo" name="pseudo" required/></td></tr>
                <tr><td>Mot de passe : </td><td><input type="password" id = "motdepasse" name="motdepasse" required/></td></tr>
                <tr><td>Vous êtes un </td>
                    <td>  
                    <input type="radio" id="typeChoix1"
                           name="type" value="Employe" required>
                    <label for="typeChoix1">Employé</label>

                    <input type="radio" id="typeChoix2"
                           name="type" value="Ouvrier">
                    <label for="typeChoix2">Ouvrier</label>

                    <input type="radio" id="typeChoix3"
                           name="type" value="Client">
                    <label for="typeChoix3">Client</label>
                    </td>
                </tr>
            </table>
            <input type="submit" id="Login" value="Valider" />
        </form>
        <br>
        <a href="CreerClient.jsp"><strong>Je suis un client et je n'ai pas de compte</strong></a>
    </body>
</html>

