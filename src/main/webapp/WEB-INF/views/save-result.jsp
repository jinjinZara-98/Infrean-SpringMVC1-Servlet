<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
         <meta charset="UTF-8">
    </head>
    <body>
        성공
        <ul>
         <!-- 프로퍼티 접근법 member.id는 member.getid와 같음
              request.setAttribute, 이전 save파일은 혼잡했지만 깔끔한 코드 -->

         <li>id=${member.id}</li>
         <li>username=${member.username}</li>
         <li>age=${member.age}</li>
        </ul>
        <a href="/index.html">메인</a>
    </body>
</html>