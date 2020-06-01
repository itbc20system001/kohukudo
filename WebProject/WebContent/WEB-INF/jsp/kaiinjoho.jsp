<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="javabeans.*"%>
<%
    HappyLife happyLife = (HappyLife) session.getAttribute("happy");
%>
<%
    Register register = (Register) request.getAttribute("register");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>幸福堂｜会員情報の表示</title>
</head>
<body>
<header>
<jsp:include page="/WEB-INF/jsp/header.jsp" />
</header>
<h1><%=happyLife.getUsrName() %>さんの会員情報</h1>
<p>氏名:<%=register.getL_name() %><%=register.getF_name() %></p>
<p>氏名(カナ)：<%=register.getL_name_kana() %><%=register.getF_name_kana() %></p>
<p>誕生日：<%=register.getBirthday() %></p>
<p>メールアドレス：<%=register.getEmail() %></p>
<p>現在の幸福ポイント：<%= happyLife.getHappypoint() %>KP</p>
<p><a href=MyPage>マイページへ戻る</a></p>
<footer>
<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</footer>
</body>
</html>