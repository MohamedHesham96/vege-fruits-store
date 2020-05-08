<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>



<%-- <%
	if (session.getAttribute("username") == null) {
%>

<jsp:forward page="login.jsp" />

<%
	}
%> --%>


<nav style="box-shadow: 0 6px 10px -1px rgba(0, 0, 0, 0.9);" dir="rtl"
	class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top scrolling-navbar">
	<a class="navbar-brand" href="#">المعلم عمرو ابو العيون</a>

	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">

			<!-- 			<li style="margin: 5px;" class=""><a -->
			<!-- 				class="btn bg-light text-dark font-weight-bold" -->
			<%-- 				style="padding: 10px;" href="today">اليوم | <%=LocalDate.now().toString()%> --%>
			<!-- 			</a></li> -->


			<li style="margin-left: 5px; margin-right: 5px">
				<h1 class="text-white">|</h1>
			</li>


			<li style="margin: 5px;" class=""><a
				class="btn bg-success text-white font-weight-bold"
				style="font-family: Amiri, sans-sans-serif; padding: 5px; width: 110px"
				href="balance"> الميزان <img
					style="margin-right: 10px; width: 35px; display: inline"
					src="icons/fruit(2).png">
			</a></li>


			<li style="margin: 5px;" class=""><a
				class="btn bg-success text-white font-weight-bold"
				style="font-family: Amiri, sans-serif; padding: 5px; width: 110px"
				href="relay"> الترحيل <img
					style="margin-right: 10px; width: 35px; display: inline"
					src="icons/food-and-restaurant.png">
			</a></li>


			<li style="margin: 5px;" class=""><a
				class="btn bg-success text-white font-weight-bold"
				style="font-family: Amiri, sans-serif; padding: 5px; width: 110px"
				href="collect">الخزنة <img
					style="margin-right: 10px; width: 35px; display: inline"
					src="icons/safe.png">
			</a></li>


			<li style="margin: 5px;" class=""><a
				class="btn bg-success text-white font-weight-bold"
				style="font-family: Amiri, sans-serif; padding: 5px; width: 110px"
				href="master">الاستاذ <img
					style="margin-right: 10px; width: 35px; display: inline"
					src="icons/master.png">
			</a></li>


			<li style="margin: 5px;" class=""><a
				class="btn bg-success text-white font-weight-bold"
				style="font-family: Amiri, sans-serif; padding-top: 5px; padding-buttom: 5px; width: 110px"
				href="clients">الطماطم <img
					style="margin-right:; width: 35px; display: inline"
					src="icons/tomatoes.png">
			</a></li>

			<li style="margin-left: 5px; margin-right: 5px">
				<h1 class="text-white">|</h1>
			</li>


			<li style="margin: 5px;" class=""><a
				class="btn bg-success text-white font-weight-bold"
				style="font-family: Amiri, sans-serif; padding: 10px; width: 100px"
				href="sellers">البائعون</a></li>


			<li style="margin-left: 5px; margin-right: 5px">
				<h1 class="text-white">|</h1>
			</li>


			<li style="margin: 5px;"><a
				class="btn bg-danger text-white font-weight-bold "
				style="padding: 10px; width: 100px" href="logout">خروج</a></li>

		</ul>

	</div>
</nav>



<br>
<br>
<br>
<br>




