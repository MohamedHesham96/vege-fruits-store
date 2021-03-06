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

<title>ميزان البائعين</title>

<link href="webjars/bootstrap/4.4.1/css/bootstrap.min.css"
	rel="stylesheet">
<script type="text/javascript">
	function go(value) {
		location = "/balance?clientId=" + value;
	}

	function ss() {

		var submitBTN = document.getElementById("submitBTN");
		submitBTN.disable = '';
		window.history.replaceState({}, document.tital, "/" + "balance");
	}

	function showForm(btn) {

		var sellerForm = document.getElementById("sellerForm");

		if (sellerForm.style.display === "none") {

			sellerForm.style.display = "block";

		} else {

			sellerForm.style.display = "none";

		}

	}
</script>


</head>
<body onload="ss()" onunload="ss()" background="images/wall1.jpg"
	style="background-attachment: fixed; background-repeat: no-repeat; background-size: cover;">

	<%@ include file="header.jsp"%>

	<div style="text-align: right;" class="container">
		<div id="sellerForm" class="card bg-success"
			style="display: none; width: 18rem; margin-left: 820px;">

			<div class="card-header text-white font-weight-bold text-center"
				style="color: #c4c4c4">اضافة عميل</div>

			<ul class="list-group list-group-flush">


				<li class="bg-dark list-group-item"><form:form
						modelAttribute="seller" method="POST" action="add-seller">

						<form:input type="text" path="name"
							class="text-center form-control mb-2 col-xs-3"
							placeholder="ادخل اسم البائع"></form:input>

						<input type="submit" value="اضافة بائع جديد"
							class="w-100 btn btn-success font-weight-bold text-center"
							onclick="return confirm('هل انت متأكد من اضافة هذا البائع ؟')">

					</form:form></li>
			</ul>

		</div>

		<form:form metho="POST" action="add-balance" modelAttribute="balance">
			<div class="row  my-4">
				<div dir='rtl' class="col-lg-12 col-md-8">
					<div class="table-responsive">
						<table class=" table table-striped table-dark">
							<thead class="thead-inverse">
								<tr>
									<th>اسم العميل</th>
									<th>اسم البائع
										<button id="clientBTN" type="button" onclick="showForm(this)"
											style="height: 30px; font-size: 11px"
											class="btn btn-success text-wight font-weight-bold">اضافة
											بائع جديد</button>
									</th>
									<th>الصنف</th>
									<th>العدد</th>
									<th>الوزن</th>
									<th>سعر الكيلو</th>
									<th>نقدي</th>

								</tr>
							</thead>

							<tbody>

								<tr>
									<td><select onchange="go(this.value)" name="clientId"
										style="width: 175px;" class="text-center form-control ">

											<c:forEach var="tempItem" items="${clientsList}">
												<c:if test="${tempItem.clientBalances.size() != 0}">
													<option
														${tempItem.id == selectedClientId ? 'selected="selected"' : ''}
														value="${tempItem.id}">${tempItem.name}</option>
												</c:if>
											</c:forEach>

									</select></td>

									<td><select name="sellerId" style="width: 175px;"
										class="text-center form-control ">

											<c:forEach var="tempItem" items="${sellersList}">

												<option value="${tempItem.id}">${tempItem.name}</option>

											</c:forEach>

									</select></td>


									<td><select name="clientBalanceId" style="width: 180px;"
										class="text-center form-control">

											<c:forEach var="tempItem" items="${clientBalances}">

												<option value="${tempItem.id}">${tempItem.item.name}
													[${tempItem.date}]</option>

											</c:forEach>

									</select></td>



									<td style="width: 125px"><form:input type="text"
											path="counter" class="text-center form-control"
											placeholder="ادخل العدد"></form:input></td>

									<td style="width: 125px"><form:input type="text"
											path="weight" class="text-center form-control"
											placeholder="ادخل الوزن"></form:input></td>

									<td style="width: 125px"><form:input type="text"
											path="kiloPrice" class="text-center form-control"
											placeholder="ادخل سعر الكيلو"></form:input></td>

									<td style="width: 125px"><form:input type="text"
											path="cash" class="text-center form-control"
											placeholder="ادخل النقدي"></form:input></td>

								</tr>

								<tr style="text-align: center;">
									<td colspan="8"><input id="submitBTN" type="submit"
										value="اضف للميزان"
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


		<!-- TABLE          TABLE          TABLE          TABLE -->

		<div class="row  my-4">
			<div dir='rtl' class=" col-lg-12 col-md-8">

				<c:forEach var="headerTemp" items="${headerResult}">

					<div class="table-responsive">

						<table
							class="table table-bordered table-striped table-sm table-dark">
							<thead class="thead-inverse">

								<tr class="badge-success">

									<td class="font-weight-bold" colspan="10">[ اسم العميل :
										${headerTemp.clientName} ] - [ الصنف : ${headerTemp.itemName}
										] - [ اجمالي العدد : ${headerTemp.totalCount} ]</td>

								</tr>

								<tr>
									<th>العدد</th>
									<th>الوزن</th>
									<th>سعر الكيلو</th>
									<th>آجل</th>
									<th>اجمالي المبلغ</th>
									<th>اسم البائع</th>
									<th>الكاشير</th>
									<th colspan="2">التاريخ</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="tempItem" items="${balanceList}">
									<c:if
										test="${tempItem.client.name == headerTemp.clientName && tempItem.item.name == headerTemp.itemName }">

										<tr>
											<td>${tempItem.counter}</td>
											<td>${tempItem.weight}</td>
											<td>${tempItem.kiloPrice}</td>
											<td>${tempItem.later}</td>
											<td>${tempItem.totalAmount}</td>
											<td>${tempItem.seller.name}</td>
											<td>${tempItem.casher.name}</td>
											<td ${tempItem.date == today ? '' : 'colspan="2"'}>${tempItem.date}</td>

											<td ${tempItem.date == today ? '' : 'hidden '}><a
												style="height: 30px; font-size: 14px;"
												class="btn btn-danger text-wight font-weight-bold "
												onclick="return confirm('هل انت متأكد من حذف هذا الصنف ؟')"
												href="delete-balance?id=${tempItem.id}">حذف</a></td>


										</tr>

									</c:if>

								</c:forEach>

							</tbody>


							<tr class="bg-primary">

								<td>${headerTemp.totalCount}</td>
								<td>${headerTemp.totalWeight}</td>
								<td></td>
								<td>${headerTemp.totalLater}</td>
								<td>${headerTemp.totalAmount}</td>
								<td colspan="6"></td>

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