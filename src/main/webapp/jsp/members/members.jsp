<%@ page import="java.util.List" %>
<%@ page import="hello.servlet.domain.member.MemberRepository" %>
<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
     //MemberRepository객체 생성, 안에 있는 member객체 다 꺼내와 배열에 대입
     MemberRepository memberRepository = MemberRepository.getInstance();
     List<Member> members = memberRepository.findAll();
%>

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
                <%
                    //for each문으로 돌려 출력
                     for (Member member : members) {
                     out.write(" <tr>");
                     out.write(" <td>" + member.getId() + "</td>");
                     out.write(" <td>" + member.getUsername() + "</td>");
                     out.write(" <td>" + member.getAge() + "</td>");
                     out.write(" </tr>");
                     }
                %>
             </tbody>
        </table>
    </body>
</html>