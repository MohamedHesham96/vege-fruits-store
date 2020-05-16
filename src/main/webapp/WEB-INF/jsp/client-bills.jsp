<%@page import="javax.management.StringValueExp"%>
<%@page import="java.util.ArrayList"%>
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

<title>فواتير</title>

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

					<table style="font-size: 18px"
						class="table table-bordered table-striped table-sm table-dark">
						<thead class="thead-inverse" >


							<tr class="badge-success">
								<td colspan="10" class="font-weight-bold"><h4
										style="display: inline;">

										[ اسم العميل : ${client.name} ] - [ رقم العميل : ${client.id}
										] - [ التاريخ :
										<h4 style="display: inline;"><%=LocalDate.now().toString()%> ]
										</h4>
										
									</h4></td>

							</tr>

							<tr>
								<th>المبلغ</th>
								<th>العدد</th>
								<th>الوزن</th>
								<th style="width: 350px" >سعر الكيلو</th>
								<th style="width: 350px">الصنف</th>
							</tr>
						</thead>
						<tbody style="font-size: 18px">

							<c:set var="i" value="0" scope="page" />


							<c:forEach var="tempItem" items="${clientBalances}">

								<tr>
									<td>${tempItem.weight * avgKiloes.get(i)}</td>
									<td>${tempItem.counter}</td>
									<td>${tempItem.weight}</td>
									<td>${avgKiloes.get(i)}</td>
									<td>${tempItem.item.name}</td>

								</tr>

								<c:set var="i" value="${i + 1}" scope="page" />

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