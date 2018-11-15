
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>J�rmuvek</h1>
<h2>M�dos�t�s/T�rl�s</h2>
<table>
    <tr><td></td><td>J�rmu neve</td><td>J�rmu leir�sa</td></tr>
    <c:forEach var="onevehicle" items="${vehicle}">
    <form method="get" action="vehiclemod" >
        <tr><td><input type="hidden" value="${onevehicle.id}" name="modid"</td><td><input type="text" value="${onevehicle.name}" name="modname"/></td><td><input type="text" value="${onevehicle.description}" name="moddesc"/></td>
        <td><input type="submit" value="modositas"></td>
    </form>
    <form method="get" action="vehicledelete" >
        <td><input type="hidden" value="${onevehicle.id}" name="delid"</td>
        <td><input type="submit" value="torles"</td>
    </form>
    </tr>
    
    </c:forEach>  
</table>

<h2>�j hozz�ad�s</h2>
        <form method="post" action="vehicle">
            <div>
                <span>Neve</span>
                <input type="text" name="vname">
            </div>
            <div>
                <span>Le�r�s</span>
                <textarea name="vdesc"></textarea>
            </div>
            <div>
                <input type="submit" value="felvitel" name="add">
            </div>
        </form>

