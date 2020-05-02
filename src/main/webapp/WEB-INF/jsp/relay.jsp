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

<title>الترحيل</title>

<link href="webjars/bootstrap/4.4.1/css/bootstrap.min.css"
	rel="stylesheet">

</head>
<body onunload="" background="images/wall1.jpg"
	style="background-attachment: fixed; background-repeat: no-repeat; background-size: cover;">

	<%@ include file="header.jsp"%>

	<div style="text-align: right;" class="container">

		<div class="row  my-4">
			<div dir='rtl' class=" col-lg-12 col-md-8">

				<a style="font-size: 25px;"
					class="btn btn-dark text-wight font-weight-bold"
					href="casher?casherName=محمد عصام"
					>محمد عصام</a> 
					
					<a
					style="font-size: 25px;"
					class="btn btn-dark text-wight font-weight-bold"
					href="casher?casherName=احمد رجب">
					احمد رجب</a>

				<c:forEach var="headerTemp" items="${headerResult}">

					<br>
					<br>


					<div class="table-responsive">

						<table
							class="table table-bordered table-striped table-sm table-dark">
							<thead class="thead-inverse">

								<tr class="badge-success">

									<td colspan="10">[ اسم البائع : ${headerTemp.sellerName} ]</td>

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
									<c:if test="${tempItem.sellerName == headerTemp.sellerName}">

										<tr>
											<td>${tempItem.itemName}</td>
											<td>${tempItem.counter}</td>
											<td>${tempItem.weight}</td>
											<td>${tempItem.kiloPrice}</td>
											<td>${tempItem.totalAmount}</td>
											<td>${tempItem.casherName}</td>
											<td>${tempItem.date}</td>

										</tr>

									</c:if>

								</c:forEach>

							</tbody>

							<tr class="bg-primary">

								<td></td>
								<td>${headerTemp.totalCount}</td>
								<td>${headerTemp.totalWeight}</td>
								<td></td>
								<td>${headerTemp.totalAmount}</td>
								<td colspan="3"></td>

							</tr>

						</table>

					</div>
					<br>

				</c:forEach>

			</div>
		</div>
	</div>


</body>
</html>