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

		<form:form metho="GET" action="add-balance" modelAttribute="balance">
			<div class="row  my-4">
				<div dir='rtl' class="col-lg-12 col-md-8">
					<div class="table-responsive">
						<table class=" table table-striped table-dark">
							<thead class="thead-inverse">
								<tr>
									<th>العدد</th>
									<th>الصنف</th>
									<th>الوزن</th>
									<th>سعر الكيلو</th>
									<th>نقدي</th>
									<th>آجل</th>
									<th>اسم البائع</th>
								</tr>
							</thead>

							<tbody>

								<tr>
									<td><form:input type="text" path="counter"
											class="text-center form-control mb-2 col-xs-3"
											placeholder="ادخل العدد"></form:input></td>

									<td style="width:"><form:input type="text" path="itemName"
											class="text-center form-control mb-2 col-xs-3"
											placeholder="ادخل اسم الصنف"></form:input></td>


									<td><form:input type="text" path="weight"
											class="text-center form-control mb-2 col-xs-3"
											placeholder="ادخل الوزن"></form:input></td>

									<td style="width:"><form:input type="text"
											path="kiloPrice"
											class="text-center form-control mb-2 col-xs-3"
											placeholder="ادخل سعر الكيلو"></form:input></td>


									<td><form:input type="text" path="cash"
											class="text-center form-control mb-2 col-xs-3"
											placeholder="ادخل النقدي"></form:input></td>

									<td style="width:"><form:input type="text" path="later"
											class="text-center form-control mb-2 col-xs-3"
											placeholder="ادخل الآجل"></form:input></td>

									<td><form:input type="text" path="sellerName"
											class="text-center form-control mb-2 col-xs-3"
											placeholder="ادخل اسم البائع"></form:input></td>


								</tr>

								<tr style="text-align: center;">
									<td colspan="8"><input type="submit" value="اضف الصنف"
										class="btn badge-success  
								 font-weight-bold text-center"
										style="width: 100%; height: 50px;"
										onclick="this.disabled=true; this.parentNode.submit();">
									</td>

								</tr>

							</tbody>

						</table>
					</div>
				</div>
			</div>

		</form:form>


		<!-- TABLE			TABLE			TABLE			TABLE -->

		<div class="row  my-4">
			<div dir='rtl' class=" col-lg-12 col-md-8">

				<c:forEach var="headerTemp" items="${headerResult}">

					<div class="table-responsive">

						<table
							class="table table-bordered table-striped table-sm table-dark">
							<thead class="thead-inverse">


								<tr class="badge-success">

									<td colspan="10">[ اسم العميل : ${headerTemp.sellerName} ]
										- [ الصنف : ${headerTemp.itemName} ] - [ اجمالي العدد :
										${headerTemp.totalCount} ]</td>

								</tr>



								<tr>
									<!-- 									<th>الصنف</th> -->
									<th>العدد</th>
									<th>الوزن</th>
									<th>سعر الكيلو</th>
									<th>نقدي</th>
									<th>آجل</th>
									<th>اجمالي المبلغ</th>
									<!-- 									<th>اسم البائع</th> -->
									<th>التاريخ</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="tempItem" items="${balanceList}">
									<c:if
										test="${tempItem.sellerName == headerTemp.sellerName && tempItem.itemName == headerTemp.itemName }">

										<tr>
											<%-- 											<td>${tempItem.itemName}</td> --%>
											<td>${tempItem.counter}</td>
											<td>${tempItem.weight}</td>
											<td>${tempItem.kiloPrice}</td>
											<td>${tempItem.cash}</td>
											<td>${tempItem.later}</td>
											<td>${tempItem.totalAmount}</td>
											<%-- 											<td>${tempItem.sellerName}</td> --%>
											<td>${tempItem.date}</td>

											<td style="width: 160px"><a
												style="height: 30px; font-size: 14px;"
												class="btn btn-danger text-wight
										font-weight-bold"
												onclick="return confirm('هل انت متأكد من حذف هذا الصنف ؟')"
												href="delete-balance?id=${tempItem.id}">حذف</a></td>
										</tr>

									</c:if>

								</c:forEach>
							</tbody>
						</table>

					</div>
					<br>


				</c:forEach>

			</div>
		</div>
	</div>


</body>
</html>