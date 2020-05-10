<%@page import="org.apache.taglibs.standard.tag.common.xml.IfTag"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.If"%>
<%@page import="java.lang.ProcessBuilder.Redirect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">

<title>كشف البائعين</title>

<link href="webjars/bootstrap/4.4.1/css/bootstrap.min.css"
	rel="stylesheet">

</head>
<body onunload="" background="images/wall1.jpg"
	style="background-attachment: fixed; background-repeat: no-repeat; background-size: cover;">

	<%@ include file="header.jsp"%>

	<div style="text-align: right;" class="container">

		<div class="row  my-4">



			<div style="margin-left: 200px" class="col-sm">

				<div class="card text-white bg-success" style="max-width: 18rem;">
					<div class="card-header">
						<img style="margin-right: 100px; width: 50px; display: inline"
							src="icons/collect.png">
						<h2 class="font-weight-bold"
							style="font-family: Amiri, sans-serif; display: inline;">التحصيل</h2>
					</div>
					<div align="center" class="card-body">


						<h1 class="font-weight-bold card-title">${masterResult.totalCollect}</h1>

					</div>
				</div>

			</div>

			<div class="col-sm">

				<div class="card text-white bg-danger" style="max-width: 18rem;">

					<div class="card-header">
						<img style="margin-right: 110px; width: 50px; display: inline"
							src="icons/fruits.png">
						<h2 class="font-weight-bold"
							style="font-family: Amiri, sans-serif; display: inline">الترحيل</h2>
					</div>

					<div align="center" class="card-body">

						<h1 class="font-weight-bold">${masterResult.totalRelay}</h1>


					</div>
				</div>

			</div>

		</div>

	</div>

</body>
</html>