<!DOCTYPE html>
<html>
    <head>
        <title>HOE - Pet Skills</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <form action="modify" method="post">
            <fieldset>
                <legend>M�dos�t�s</legend>
                <div>
                    <label>Pet skill neve</label>
                    <input name="name" value="${requestScope['item'].name}">
                </div>
                <div>
                    <label>Le�r�s </label>
                    <input name="description" value="${requestScope['item'].description}" >
                </div>
                <div>
                    <label>Sz�ks�ges szint </label>
                    <input name="value" value="${requestScope['item'].requiredLevel}" >
                </div>
                <div><input type="submit"></div>            
        </form>
    </body>
</html>