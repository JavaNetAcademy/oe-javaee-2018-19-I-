<!DOCTYPE html>
<html>
    <head>
        <title>HOE - �llat</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <form action="modify" method="post">
            <fieldset>
                <legend>M�dos�t�s</legend>
                <div>
                    <label>�llat neve</label>
                    <input name="name" value="${requestScope['item'].name}">
                </div>
                <div>
                    <label>T�pus </label>
                    <input name="type" value="${requestScope['item'].type}" >
                </div>
                <div>
                    <label>Er?ss�g </label>
                    <input name="strength" value="${requestScope['item'].strength}" >
                </div>
                <div><input type="submit"></div>            
        </form>
    </body>
</html>