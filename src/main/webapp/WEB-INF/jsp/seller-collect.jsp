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

<title>${seller.name}</title>

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
				style="color: #c4c4c4">التحصيل</div>
			<ul class="list-group list-group-flush">

				<li class="bg-dark list-group-item"><form:form metho="POST"
						action="add-collect" modelAttribute="collect">
						<input type="hidden" name="sellerId" value="${seller.id}">
						
						<form:input type="text" path="amount"
							class="text-center form-control mb-2 col-xs-3"
							placeholder="ادخل المبلغ"></form:input>

						<input type="submit" value="اضف المبلغ للتحصيل"
							class="w-100 btn btn-info   
								 font-weight-bold text-center"
							onclick="this.disabled=true; this.parentNode.submit();">

					</form:form></li>
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
									style="font-size: 22px">[ اسم البائع : ${seller.name} ] -
									[ رقم البائع : ${seller.id} ]</td>

							</tr>

							<tr style="font-size: 18px">
								<th>المبلغ</th>
								<th>التاريخ</th>
							</tr>

						</thead>
						<tbody>

							<c:forEach var="tempItem" items="${seller.collects}">

								<tr>
									<td>${tempItem.amount}</td>
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


</body>
</html>