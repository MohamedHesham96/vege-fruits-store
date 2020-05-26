<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.List"%>
<%@page import="com.bluesoft.vegefruitsstore.entity.ClientBalance"%>
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


		<c:if test="${clientBalances.size() == 0}">

			<div style="font-size: 22px" class="alert alert-danger" role="alert">...لا
				يوجد فواتير حاليا لهذا العميل</div>

		</c:if>
		<!-- TABLE			TABLE			TABLE			TABLE -->

		<div class="row  my-4">
			<div dir='rtl' class=" col-lg-12 col-md-8">

				<c:set var="i" value="0" scope="page" />
				<c:set var="total" value="0" scope="page" />

				<%
					double total = 0;
					List<ClientBalance> clientBalances = (ArrayList) request.getAttribute("clientBalances");
					List<Double> avgKiloes = (ArrayList) request.getAttribute("avgKiloes");

					for (int j = 0; j < clientBalances.size(); j++) {
				%>

				<div class="table-responsive">



					<table style="font-size: 18px"
						class="table table-bordered table-striped table-sm table-dark">
						<thead class="thead-inverse">

							<tr class="badge-success">
								<td colspan="10" class="font-weight-bold">


									<div style="font-size: 22px; display: inline;">

										[ اسم العميل : ${client.name} ] - [ رقم العميل : ${client.id}
										] - [ تاريخ النزول :

										<div style="display: inline;">

											<%=clientBalances.get(j).getDate()%>

											]
										</div>

										- [ تاريخ اليوم :
										<div style="display: inline;">
											<%=LocalDate.now().toString()%>
											]
										</div>
									</div>


								</td>

							</tr>

							<tr>
								<th style="width: 150px">المبلغ</th>
								<th style="width: 150px">العدد</th>
								<th style="width: 150px">الوزن</th>
								<th style="width: 150px">سعر الكيلو</th>
								<th style="width: 200px">الصنف</th>
							</tr>
						</thead>
						<tbody style="font-size: 18px">

							<%
								for (int j2 = 0; j2 < clientBalances.size(); j2++) {

										if (clientBalances.get(j).getDate().equals(clientBalances.get(j2).getDate())) {
											j = j2;
							%>


							<tr>
								<td><%=new DecimalFormat("##.##")
								.format(clientBalances.get(j2).getWeight() * avgKiloes.get(j))%></td>
								<td><%=clientBalances.get(j2).getCounter()%></td>
								<td><%=clientBalances.get(j2).getWeight()%></td>
								<td><%=new DecimalFormat("##.##").format(avgKiloes.get(j))%></td>
								<td><%=clientBalances.get(j2).getItem().getName()%></td>

							</tr>


							<%
								total += clientBalances.get(j).getWeight() * avgKiloes.get(j);
										}
									}
							%>

							<tr class="badge-primary">
								<td colspan="10" class=" badge-primary font-weight-bold"><h4
										style="display: inline;">
										[ أجمالي المبلغ :

										<%=new DecimalFormat("##.##").format(total)%>

										]
									</h4></td>
							</tr>
						</tbody>

					</table>
					<%
						total = 0;
					%>

				</div>
				<br>

				<%
					}
				%>
			</div>
		</div>
	</div>


	<%@ include file="footer.jsp"%>


</body>
</html>