<!DOCTYPE html>
<html>
    <head>
        <title>HOE</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <form action="modify" method="post">
            <fieldset>
                <legend>M�dos�t�s</legend>
                <div>
                    <label>Technol�gia neve</label>
                    <input name="name" value="${requestScope['item'].name}">
                </div>
                <div>
                    <label>Technol�gia le�r�sa</label>
                    <input name="description" value="${requestScope['item'].description}" >
                </div>
                <div>
                    <label>Minim�lis birodalom szint</label>
                    <input name="level" value="${requestScope['item'].empireLevel}" >
                </div>
                <div><input type="submit"></div>            
        </form>
    </body>
</html>