<!DOCTYPE html>
<html>
    <head>
        <title>HOE - Anyagok/title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <form action="modify" method="post">
            <fieldset>
                <legend>M�dos�t�s</legend>
                <div>
                    <label>Anyag neve</label>
                    <input name="name" value="${requestScope['item'].name}">
                </div>
                <div>
                    <label>Anyag le�r�sa</label>
                    <input name="description" value="${requestScope['item'].description}" >
                </div>
                <div><input type="submit"></div>            
        </form>
    </body>
</html>