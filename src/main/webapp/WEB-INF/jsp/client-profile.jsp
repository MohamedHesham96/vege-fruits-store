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

<title>الميزان</title>

<link href="webjars/bootstrap/4.4.1/css/bootstrap.min.css"
	rel="stylesheet">

</head>
<body onunload="" background="images/wall1.jpg"
	style="background-attachment: fixed; background-repeat: no-repeat; background-size: cover;">

	<%@ include file="header.jsp"%>

	<div style="text-align: right;" class="container">


		<!-- TABLE			TABLE			TABLE			TABLE -->

		<div class="row  my-4">
			<div dir='rtl' class=" col-lg-12 col-md-8">


				<div class="table-responsive">

					<table
						class="table table-bordered table-striped table-sm table-dark">
						<thead class="thead-inverse">


							<tr class="badge-success">
								<td colspan="10" class="font-weight-bold"
									style="font-size: 22px">[ الميزان ] - [ اسم العميل :
									${client.name} ]</td>

							</tr>

							<tr>
								<th>الصنف</th>
								<th>العدد</th>
								<th>الوزن</th>
								<th>الكاشير</th>
								<th>التاريخ</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="tempItem" items="${client.clientBalances}">

								<tr>
									<td>${tempItem.item.name}</td>
									<td>${tempItem.currentCounter}</td>
									<td>${tempItem.currentWeight}</td>
									<td>${tempItem.casher.name}</td>
									<td>${tempItem.date}</td>

									<!-- 										<td style="width: 160px"><a -->
									<!-- 											style="height: 30px; font-size: 14px;" -->
									<!-- 											class="btn btn-danger text-wight font-weight-bold" -->
									<!-- 											onclick="return confirm('هل انت متأكد من حذف هذا الصنف ؟')" -->
									<%-- 											href="delete-balance?id=${tempItem.id}">حذف</a></td> --%>
								</tr>

							</c:forEach>

						</tbody>


					</table>

				</div>
				<br>

			</div>
		</div>
	</div>


</body>
</html>