<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
         <meta charset="UTF-8">
         <title>Title</title>
    </head>
    <body>
        <!-- 상대경로 사용, /로 시작안하면 상대경로, 다른 데서도 재활용하기 위해, [현재 URL이 속한 계층 경로 + /save]
             이 파일을 호출한 MvcMemberFormServlet클래스가 /servlet-mvc/members/new-form이 url에 들어와야 실행되므로
             최종적인 url /servlet-mvc/members/save 마지막 new-form 빼고 save를 넣음,
             form action="save"은 거기로 보내는거니, 실제 개발할때는 상대경로 잘 안씀, 패턴 비슷할 때 씀씀 -->

        <form action="save" method="post">
             username: <input type="text" name="username" />
             age: <input type="text" name="age" />
             <button type="submit">전송</button>
        </form>
    </body>
</html>