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

<title dir="ltr">[ الترحيل ] - [ ${headerResult.sellerName} ]</title>

<link href="webjars/bootstrap/4.4.1/css/bootstrap.min.css"
	rel="stylesheet">

</head>
<body onunload="" background="images/wall1.jpg"
	style="background-attachment: fixed; background-repeat: no-repeat; background-size: cover;">

	<%@ include file="header.jsp"%>

	<div style="text-align: right;" class="container">

		<div class="row my-4">
			<div dir='rtl' class=" col-lg-12 col-md-8">

				<br> <br>


				<div class="table-responsive">

					<table
						class="table table-bordered table-striped table-sm table-dark">
						<thead class="thead-inverse">

							<tr class="badge-success">

								<td colspan="10" class="font-weight-bold"
									style="font-size: 22px">[ الترحيل ] - [ اسم البائع :
									${headerResult.sellerName} ]</td>

							</tr>

							<tr>
								<th>الصنف</th>
								<th>العدد</th>
								<th>الوزن</th>
								<th>سعر الكيلو</th>
								<th>اجمالي المبلغ</th>
								<th>الكاشير</th>
								<th>التاريخ</th>
							</tr>

						</thead>
						<tbody>
							<c:forEach var="tempItem" items="${relayList}">

								<tr>
									<td>${tempItem.item.name}</td>
									<td>${tempItem.counter}</td>
									<td>${tempItem.weight}</td>
									<td>${tempItem.kiloPrice}</td>
									<td>${tempItem.totalAmount}</td>
									<td>${tempItem.casher.name}</td>
									<td>${tempItem.date}</td>

								</tr>


							</c:forEach>

						</tbody>

						<tr class="bg-primary">

							<td></td>
							<td>${headerResult.totalCount}</td>
							<td>${headerResult.totalWeight}</td>
							<td></td>
							<td>${headerResult.totalAmount}</td>
							<td colspan="3"></td>

						</tr>

					</table>

				</div>
				<br>


			</div>
		</div>
	</div>

	<%@ include file="footer.jsp"%>

</body>
</html>