<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="hello.servlet.domain.member.Member"%>
<%@ page import="hello.servlet.domain.member.MemberRepository"%>
<%
    //Scriptlet 영역 안에서 선언하는 변수들은 모두 지역변수. %! % 이라는 선언문에서는 private으로 가능
    MemberRepository memberRepository = MemberRepository.getInstance();

    //jsp도 결국 servlet으로 변환되기 때문에 request, response 사용 가능
    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));

    Member member = new Member(username, age);
    memberRepository.save(member);
%>

<html>
<head>
    <meta charset="UTF-8">
</head>
<body>
성공
<ul>
    <li>id=<%=member.getId()%></li>
    <li>username=<%=member.getUsername()%></li>
    <li>age=<%=member.getAge()%></li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>