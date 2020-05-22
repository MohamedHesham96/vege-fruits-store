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

<title>الرسائل</title>

<link href="webjars/bootstrap/4.4.1/css/bootstrap.min.css"
	rel="stylesheet">

</head>
<body onunload="" background="images/wall1.jpg"
	style="background-attachment: fixed; background-repeat: no-repeat; background-size: cover;">

	<%@ include file="header.jsp"%>

	<div style="text-align: right;" class="container">

		<div class="row  my-4">
			<div dir='rtl' class=" col-lg-12 col-md-8">

				<div class="table-responsive">

					<table
						class="table table-bordered table-striped table-sm table-dark">
						<thead class="thead-inverse">

							<tr class="badge-success">

								<td colspan="10" class="font-weight-bold"
									style="font-size: 22px">[ الرسائل ] - [ عدد الرسائل - 5 ]</td>

							</tr>

							<tr style="font-size: 18px">
								<th>رقم البائع</th>
								<th>اسم البائع</th>
								<th>الرسالة</th>
							</tr>

						</thead>
						<tbody>

							<c:forEach var="tempItem" items="${sellerList}">
								<c:if test="${!tempItem.checkSeller()}">

									<tr>
										<td style="width: 300px; padding-top: 8px; font-size: 16px"
											class="text-white font-weight-bold">${tempItem.id}</td>

										<td style="padding-top: 8px; width: 350px; font-size: 16px"><a
											class="text-white font-weight-bold"<%-- href="seller-profile?id=${tempItem.id}" --%>>

												${tempItem.name} </a></td>

										<td class="badge-warning">هذا البائع لم يدفع اي تحصيل من
											يوم [ ${tempItem.balances.get(0).date} ]</td>

									</tr>

								</c:if>

							</c:forEach>

						</tbody>

					</table>

				</div>
			</div>
		</div>
	</div>
</body>
</html>