<%@page import="org.apache.taglibs.standard.tag.common.xml.IfTag"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.If"%>
<%@page import="java.lang.ProcessBuilder.Redirect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

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


		<div class="card bg-secondary text-white"
			style="width: 18rem; margin-left: 820px;">

			<div class="card-header text-white font-weight-bold text-center"
				style="color: #c4c4c4">البحث</div>

			<ul class="list-group list-group-flush">

				<li class="bg-dark list-group-item">

					<form method="GET" action="search-for-clients">

						<input type="text" name="clientName"
							class="text-center form-control mb-2 col-xs-3"
							placeholder="ادخل اسم العميل"> <input type="submit"
							value="ابحث عن الاسم"
							class="w-100 btn btn-info font-weight-bold text-center"
							onclick="this.disabled=true; this.parentNode.submit();">

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
									style="font-size: 22px">[ كشف اسماء العملاء ]</td>

							</tr>

							<tr style="font-size: 18px">
								<th>رقم العميل</th>
								<th>اسم العميل</th>
							</tr>

						</thead>
						<tbody>

							<c:forEach var="tempItem" items="${clientList}">

								<tr>
									<td style="width: 300px; padding-top: 8px; font-size: 16px"
										class="text-white font-weight-bold">${tempItem.id}</td>

									<td style="padding-top: 8px; width: 350px; font-size: 16px"><a
										class="text-white font-weight-bold"<%-- href="seller-profile?id=${tempItem.id}" --%>>

											${tempItem.name} </a></td>

									<td><a style="font-size: 12px"
										href="seller-relay?sellerName=${tempItem.name}"
										class=" btn btn-primary text-white font-weight-bold ">-----</a>

										<a style="font-size: 12px"
										href="seller-collect?id=${tempItem.id}"
										class="btn 
										text-white font-weight-bold btn-success	">-----</a>
										<a style="font-size: 12px"
										href="seller-master?id=${tempItem.id}"
										class="btn btn-danger text-white font-weight-bold ">-----</a>

									</td>

								</tr>

							</c:forEach>

						</tbody>

					</table>

				</div>
			</div>
		</div>
	</div>
</body>
</html>