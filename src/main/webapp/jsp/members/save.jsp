<%@ page import="hello.servlet.domain.member.MemberRepository" %>
<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // 로직 자바 코드 삽입 가능
    // jsp도 결국 서블릿으로 바뀌기 때문에 request, response 사용 가능
     MemberRepository memberRepository = MemberRepository.getInstance();

     System.out.println("save.jsp");
     //username와 age라는 파라미터 이름을 가진 값들을 추출해 Member객체 파라미터에 세팅하고 저장소에 저장
     String username = request.getParameter("username");
     int age = Integer.parseInt(request.getParameter("age"));
     Member member = new Member(username, age);
     System.out.println("member = " + member);
     memberRepository.save(member);
%>
<html>
    <head>
         <meta charset="UTF-8">
    </head>
    <body>
        성공
        <ul>
        <!-- id, username, age 출력 -->
         <li>id = <%= member.getId() %></li>
         <li>username = <%= member.getUsername() %></li>
         <li>age = <%= member.getAge() %></li>
        </ul>
        <a href="/index.html">메인</a>
    </body>
</html>