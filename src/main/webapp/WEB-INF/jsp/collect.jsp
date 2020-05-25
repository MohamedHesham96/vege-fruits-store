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

<title>الخزنة</title>

<link href="webjars/bootstrap/4.4.1/css/bootstrap.min.css"
	rel="stylesheet">

</head>
<body onunload="" background="images/wall1.jpg"
	style="background-attachment: fixed; background-repeat: no-repeat; background-size: cover;">

	<%@ include file="header.jsp"%>

	<div style="text-align: right;" class="container">

		<div class="card bg-success text-white"
			style="width: 18rem; margin-left: 820px;">
			<div class="card-header text-white font-weight-bold text-center"
				style="color: #c4c4c4">التاريخ</div>
			<ul class="list-group list-group-flush">

				<li class="bg-dark list-group-item">

					<form method="GET" action="collect">

						<input type="date" name="date" value="${date}"
							class="w-100 btn badge-light font-weight-bold text-center">

						<input type="submit" style="margin-top: 10px;"
							class="w-100 btn badge-success font-weight-bold text-center"
							value="اذهب لهذا اليوم" />

					</form>
				</li>

			</ul>
		</div>


		<div class="row  my-4">
			<div dir='rtl' class=" col-lg-12 col-md-8">

				<div class="table-responsive">

					<table
						class="table table-bordered table-striped table-sm table-dark">
						<thead class="thead-inverse">

							<tr class="badge-success">

								<td colspan="10" class="font-weight-bold"
									style="font-size: 22px">[ جدول التحصيل ]</td>

							</tr>

							<tr style="font-size: 18px">
								<th>المبلغ</th>
								<th>اسم البائع</th>
								<th>اسم المستلم</th>
								<th>التاريخ</th>
							</tr>

						</thead>
						<tbody>

							<c:forEach var="tempItem" items="${collectList}">

								<tr>
									<td>${tempItem.amount}</td>
									<td>${tempItem.seller.name}</td>
									<td>${tempItem.receiver}</td>
									<td>${tempItem.date}</td>
								</tr>

							</c:forEach>

						</tbody>

					</table>

				</div>
				<br>

			</div>
		</div>
	</div>

	<%@ include file="footer.jsp"%>

</body>
</html>