<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- jstl 사용-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
         <meta charset="UTF-8">
         <title>Title</title>
    </head>
    <body>
        <a href="/index.html">메인</a>
        <table>
             <thead>
                 <th>id</th>
                 <th>username</th>
                 <th>age</th>
             </thead>
             <tbody>
                <!--이 파일로 보낸 MvcMemberListServlet클래스에서 request.setAttribute로 members라는 이름으로 저장한 값 -->
                 <c:forEach var="item" items="${members}">
                     <tr>
                         <td>${item.id}</td>
                         <td>${item.username}</td>
                         <td>${item.age}</td>
                     </tr>
                 </c:forEach>
             </tbody>
        </table>
    </body>
</html>