<!DOCTYPE html>
<html>
    <head>
        <title>HOE - Sz�vets�g m�dos�t�s</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <form action="modify" method="post">
            <fieldset>
                <legend>M�dos�t�s</legend>
                <div>
                    <label>Sz�vets�g neve</label>
                    <input name="name" value="${requestScope['item'].name}">
                </div>
                <div>
                    <label>le�r�sa </label>
                    <input name="description" value="${requestScope['item'].description}" >
                </div>
                <div><input type="submit"></div>            
        </form>
    </body>
</html>