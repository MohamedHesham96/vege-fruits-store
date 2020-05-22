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

<script type="text/javascript">
	function showForm(btn) {

		var clientForm = document.getElementById("clientForm");
		var itemForm = document.getElementById("itemForm");

		if (btn.id === "clientBTN") {

			if (clientForm.style.display === "none") {

				clientForm.style.display = "block";
				itemForm.style.display = "none";

			} else {

				clientForm.style.display = "none";

			}

		}

		else {

			if (itemForm.style.display === "none") {

				itemForm.style.display = "block";
				clientForm.style.display = "none";

			} else {

				itemForm.style.display = "none";

			}

		}
	}
</script>

</head>
<body onunload="" background="images/wall1.jpg"
	style="background-attachment: fixed; background-repeat: no-repeat; background-size: cover;">

	<%@ include file="header.jsp"%>

	<div style="text-align: right;" class="container">

		<div id="clientForm" class="card bg-success"
			style="display: none; width: 18rem; margin-left: 820px;">

			<div class="card-header text-white font-weight-bold text-center"
				style="color: #c4c4c4">اضافة عميل</div>

			<ul class="list-group list-group-flush">

				<li class="bg-dark list-group-item"><form:form
						modelAttribute="client" method="POST" action="add-client">

						<form:input type="text" path="name"
							class="text-center form-control mb-2 col-xs-3"
							placeholder="ادخل اسم العميل"></form:input>

						<input type="submit" value="اضافة عميل جديد"
							class="w-100 btn btn-success font-weight-bold text-center"
							onclick="return confirm('هل انت متأكد من اضافة هذا العميل ؟')">
					</form:form></li>
			</ul>

		</div>


		<div id="itemForm" class="card bg-success"
			style="display: none; width: 18rem; margin-left: 820px;">

			<div class="card-header text-white font-weight-bold text-center"
				style="color: #c4c4c4">اضافة صنف</div>

			<ul class="list-group list-group-flush">

				<li class="bg-dark list-group-item"><form:form
						modelAttribute="item" method="POST" action="add-item">

						<form:input type="text" path="name"
							class="text-center form-control mb-2 col-xs-3"
							placeholder="ادخل اسم الصنف"></form:input>

						<input type="submit" value="اضافة صنف جديد"
							class="w-100 btn btn-success font-weight-bold text-center"
							onclick="return confirm('هل انت متأكد من اضافة هذا الصنف ؟')">

					</form:form></li>
			</ul>

		</div>


		<form:form metho="POST" action="add-client-balance"
			modelAttribute="clientBalance">


			<div class="row  my-4">
				<div dir='rtl' class="col-lg-12 col-md-8">
					<div class="table-responsive">
						<table class=" table table-striped table-dark">
							<thead class="thead-inverse">
								<tr>
									<th>اسم العميل
										<button id="clientBTN" type="button" onclick="showForm(this)"
											style="height: 30px; font-size: 11px"
											class="btn btn-success text-wight font-weight-bold">اضافة
											عميل جديد</button>
									</th>
									<th>الصنف
										<button id="itemBTN" type="button" onclick="showForm(this)"
											style="height: 30px; font-size: 11px"
											class="btn btn-success text-wight font-weight-bold">اضافة
											صنف جديد</button> <a id="itemBTN" type="button" href="items"
										style="height: 30px; font-size: 11px"
										class="btn btn-success text-wight font-weight-bold">
											الاصناف</a>
									</th>
									<th>العدد</th>
									<th>الوزن</th>

								</tr>
							</thead>

							<tbody>

								<tr>

									<td><select name="clientId" style="width: 250px;"
										class="text-center form-control ">

											<c:forEach var="tempItem" items="${clientsList}">

												<option value="${tempItem.id}">${tempItem.name}</option>

											</c:forEach>

									</select></td>


									<td><select name="itemId" style="width: 250px;"
										class="text-center form-control ">

											<c:forEach var="tempItem" items="${itemsList}">

												<option value="${tempItem.id}">${tempItem.name}</option>

											</c:forEach>

									</select></td>



									<td style="width:"><form:input type="text" path="counter"
											class="text-center form-control  " placeholder="ادخل العدد"></form:input></td>

									<td style="width:"><form:input type="text" path="weight"
											class="text-center form-control  " placeholder="ادخل الوزن"></form:input></td>



								</tr>

								<tr style="text-align: center;">
									<td colspan="8"><input type="submit" value="اضف للميزان"
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

		<div class="row">
			<div dir='rtl' class=" col-lg-12 col-md-8">

				<c:forEach var="itemTemp" items="${clientsList}">

					<div class="table-responsive">

						<c:if test="${itemTemp.clientBalances.size() > 0}">

							<table style="margin-top: 25px"
								class="table table-bordered table-striped table-sm table-dark">
								<thead class="thead-inverse">


									<tr class="badge-success">

										<td class="font-weight-bold" colspan="10">[ اسم العميل :
											${itemTemp.name} ]</td>

									</tr>

									<tr>
										<th>الصنف</th>
										<th>العدد الحالي</th>
										<th>الوزن الحالي</th>
										<th>الكاشير</th>
										<th>التاريخ</th>
									</tr>
								</thead>

								<tbody>
									<c:forEach var="tempItem" items="${itemTemp.clientBalances}">

										<tr>
											<td>${tempItem.item.name}</td>
											<td><h5 style="display: inline;">${tempItem.currentCounter}</h5>
												-
												<h6 class="font-weight-bold"
													style="display: inline; font-size: 14px;">(من
													${tempItem.counter})</h6>
											<td><h5 style="display: inline;">${tempItem.currentWeight}</h5>
												-
												<h6 class="font-weight-bold"
													style="display: inline; font-size: 14px;">(من
													${tempItem.weight})</h6></td>


											<td>${tempItem.casher.name}</td>
											<td>${tempItem.date}</td>

											<td><a style="font-size: 12px"
												href="delete-client-balance?id=${tempItem.id}"
												class=" btn btn-danger text-white font-weight-bold  ${ tempItem.currentCounter ==  tempItem.counter ? '' : 'disabled'}"
												onclick="return confirm('هل انت متأكد من حذف هذا الصنف ؟')">
												حذف</a>

											</td>

											<!-- 										<td style="width: 160px"><a -->
											<!-- 											style="height: 30px; font-size: 14px;" -->
											<!-- 											class="btn btn-danger text-wight font-weight-bold" -->
											<!-- 											onclick="return confirm('هل انت متأكد من حذف هذا الصنف ؟')" -->
											<%-- 											href="delete-balance?id=${tempItem.id}">حذف</a></td> --%>
										</tr>

									</c:forEach>

								</tbody>

							</table>

						</c:if>
					</div>


				</c:forEach>

			</div>
		</div>
	</div>


</body>
</html>