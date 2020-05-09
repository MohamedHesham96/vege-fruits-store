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

			<li style="margin: 5px;" class=""><a
				class="btn bg-warning text-dark  font-weight-bold"
				style="font-size: 20px; font-family: Amiri, sans-serif; padding: 5px;"
				href="balance"> <img
					style="margin-left: 5px; width: 35px; display: inline"
					src="icons/professions-and-jobs.png"> <%=session.getAttribute("loginCasherName")%>
			</a></li>


			<li style="margin-left: 5px; margin-right: 5px">
				<h1 class="text-white">|</h1>
			</li>


			<li style="margin: 5px;" class=""><a
				class="btn bg-success text-white font-weight-bold"
				style="font-family: Amiri, sans-serif; padding: 5px; width: 110px"
				href="balance"> الميـزان <img
					style="margin-right: 10px; width: 35px; display: inline"
					src="icons/fruit(2).png">
			</a></li>


			<li style="margin: 5px;" class=""><a
				class="btn bg-success text-white font-weight-bold"
				style="font-family: Amiri, sans-serif; padding: 5px; width: 110px"
				href="relay"> الترحيـل <img
					style="margin-right: 10px; width: 35px; display: inline"
					src="icons/food-and-restaurant.png">
			</a></li>


			<li style="margin: 5px;" class=""><a
				class="btn bg-success text-white font-weight-bold"
				style="font-family: Amiri, sans-serif; padding: 5px; width: 110px"
				href="collect">الخـزنـة <img
					style="margin-right: 10px; width: 35px; display: inline"
					src="icons/safe.png">
			</a></li>


			<li style="margin: 5px;" class=""><a
				class="btn bg-success text-white font-weight-bold"
				style="font-family: Amiri, sans-serif; padding: 5px; width: 110px"
				href="master">الاستـاذ <img
					style="margin-right: 10px; width: 35px; display: inline"
					src="icons/master.png">
			</a></li>


			<li style="margin-left: 5px; margin-right: 5px">
				<h1 class="text-white">|</h1>
			</li>


			<li style="margin: 5px;" class=""><a
				class="btn bg-success text-white font-weight-bold"
				style="font-family: Amiri, sans-serif; padding: 10px; width: 100px"
				href="clients">العمــلاء</a></li>


			<li style="margin: 5px;" class=""><a
				class="btn bg-success text-white font-weight-bold"
				style="font-family: Amiri, sans-serif; padding: 10px; width: 100px"
				href="sellers">البائعــون</a></li>


			<li style="margin-left: 5px; margin-right: 5px">
				<h1 class="text-white">|</h1>
			</li>


			<li style="margin: 5px;" class=""><a
				class="btn bg-danger text-white font-weight-bold"
				style="font-family: Amiri, sans-serif; padding: 10px;">خروج</a></li>


		</ul>

	</div>
</nav>



<br>
<br>
<br>
<br>