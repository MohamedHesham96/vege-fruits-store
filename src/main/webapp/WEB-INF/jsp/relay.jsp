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

<script type="text/javascript">
	function showForm(btn, id, name) {

		var form = document.getElementById("casherForm");
		var updateForm = document.getElementById("casherUpdateForm");
		var casherId = document.getElementById("casherId");
		var casherName = document.getElementById("casherName");

		if (btn.id === "addBtn") {

			if (form.style.display === "block")
				form.style.display = "none";
			else {
				form.style.display = "block";
				updateForm.style.display = "none";
			}

		}

		else {

			casherId.value = id;
			casherName.value = name;

			if (updateForm.style.display === "none") {

				updateForm.style.display = "block";
				form.style.display = "none";
			}

		}

	}
</script>
</head>
<body onunload="" background="images/wall1.jpg"
	style="background-attachment: fixed; background-repeat: no-repeat; background-size: cover;">

	<%@ include file="header.jsp"%>

	<div style="text-align: right;" class="container">

		<div id="casherForm" class="card bg-success"
			style="display: none; width: 18rem; margin-left: 820px;">

			<div class="card-header text-white font-weight-bold text-center"
				style="color: #c4c4c4">اضافة كاشير</div>

			<ul class="list-group list-group-flush">

				<li class="bg-dark list-group-item"><form:form
						modelAttribute="casher" method="POST" action="add-casher">

						<form:input type="text" path="name"
							class="text-center form-control mb-2 col-xs-3"
							placeholder="ادخل اسم الكاشير"></form:input>

						<form:password path="password"
							class="text-center form-control mb-2 col-xs-3"
							placeholder="ادخل الرقم السري"></form:password>

						<input type="submit" value="اضافة كاشير جديد"
							class="w-100 btn btn-success font-weight-bold text-center"
							onclick="return confirm('هل انت متأكد من اضافة هذا الكاشير ؟')">

					</form:form></li>
			</ul>

		</div>


		<div id="casherUpdateForm" class="card bg-success"
			style="display: none; width: 18rem; margin-left: 820px;">

			<div class="card-header text-white font-weight-bold text-center"
				style="color: #c4c4c4">تعديل كاشير</div>

			<ul class="list-group list-group-flush">

				<li class="bg-dark list-group-item"><form:form
						modelAttribute="casher" method="POST"
						action="update-casher-password">

						<form:hidden id="casherId" path="id"></form:hidden>

						<input
							class="w-100 btn btn-warning font-weight-bold text-center form-control mb-2 col-xs-3"
							id="casherName"></input>


						<form:password path="password"
							class="text-center form-control mb-2 col-xs-3"
							placeholder="ادخل الرقم السري الجديد"></form:password>

						<input type="submit" value="تعديل"
							class="w-100 btn btn-success font-weight-bold text-center"
							onclick="return confirm('هل انت متأكد من تعديل هذا الكاشير ؟')">

					</form:form></li>
			</ul>

		</div>

		<div class="row my-4">
			<div dir='rtl' class=" col-lg-12 col-md-8">

				<%
					if (request.getSession().getAttribute("loginCasherIsAdmin").equals(true)) {
				%>
				<div class="table-responsive">

					<table
						class="table table-bordered table-striped table-sm table-dark">
						<thead class="thead-inverse">

							<tr class="badge-info">

								<td class="font-weight-bold" colspan="10">[ كشف أسماء
									الكاشير ]


									<button id="addBtn" onclick="showForm(this)"
										style="height: 30px; font-size: 12px"
										class="btn btn-dark text-wight font-weight-bold">+
										اضافة كاشير جديد</button>
								</td>

							</tr>

							<tr>
								<th>رقم الكاشير</th>
								<th  colspan="2">اسم الكاشير</th>

							</tr>

						</thead>
						<tbody>

							<c:forEach var="tempItem" items="${casherList}">

								<tr>
									<td>${tempItem.id}</td>
									<td style="width: 300px">${tempItem.name}</td>
									<td style="width: 600px"><a
										style="height: 30px; font-size: 12px"
										class="btn btn-primary text-wight font-weight-bold"
										href="casher-sellers?casherId=${tempItem.id}">يومية
											البائعين</a> <a style="height: 30px; font-size: 12px"
										class="btn btn-success text-wight font-weight-bold"
										href="casher-clients?casherId=${tempItem.id}">يومية
											العملاء</a> |

										<button id="updateBtn"
											onclick="showForm( this , '${tempItem.id}' , '${tempItem.name}' )"
											style="height: 30px; font-size: 12px"
											class="btn btn-warning text-wight font-weight-bold">تغيير
											الرقم السري</button></td>
								</tr>


							</c:forEach>

						</tbody>

					</table>

				</div>
				<br>

				<%
					}
				%>
				<c:forEach var="headerTemp" items="${headerResult}">



					<div class="table-responsive">

						<table
							class="table table-bordered table-striped table-sm table-dark">
							<thead class="thead-inverse">

								<tr class="badge-success">

									<td style="font-size: 20px" class="font-weight-bold"
										colspan="10">[ ترحيل ] - [ اسم البائع :
										${headerTemp.sellerName} ]</td>

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
									<c:if test="${tempItem.seller.name == headerTemp.sellerName}">

										<tr>
											<td>${tempItem.item.name}</td>
											<td>${tempItem.counter}</td>
											<td>${tempItem.weight}</td>
											<td>${tempItem.kiloPrice}</td>
											<td>${tempItem.totalAmount}</td>
											<td>${tempItem.casher.name}</td>
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

	<%@ include file="footer.jsp"%>

</body>
</html>