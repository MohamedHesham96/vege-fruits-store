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
<script type="text/javascript">
	function checkAmount() {

		var amountValue = document.getElementById("amount");
		var drweeTotal = document.getElementById("drweeTotal");

		var alert = document.getElementById("alert");

		if (amountValue.value < drweeTotal.value) {

			alert.style.display = "none"

			return true;

		} else {

			alert.style.display = "block"

			return false;

		}

	}
</script>
</head>
<body background="images/wall1.jpg"
	style="background-attachment: fixed; background-repeat: no-repeat; background-size: cover;">

	<%@ include file="header.jsp"%>

	<input id="drweeTotal" value="${seller.getDrawee()}">


	<div style="text-align: right;" class="container">


		<div style="display: none;" id="alert" class="alert alert-danger"
			role="alert">هذا المبلغ اكبر من حجم الدين</div>


		<div class="card bg-success  text-white"
			style="width: 18rem; margin-left: 820px;">
			<div class="card-header text-white font-weight-bold text-center"
				style="color: #c4c4c4">[ التحصيل ]</div>
			<ul class="list-group list-group-flush">

				<li class="bg-dark list-group-item"><form:form id="myform"
						metho="POST" action="add-collect" modelAttribute="collect">

						<input type="hidden" name="sellerId" value="${seller.id}">

						<form:input id="amount" type="text" path="amount"
							class="text-center form-control mb-2 col-xs-3"
							placeholder="ادخل المبلغ"></form:input>


						<form:input type="text" path="receiver"
							class="text-center form-control mb-2 col-xs-3"
							placeholder="ادخل اسم المستلم"></form:input>


						<input type="submit" ${drweeTotal == 0 ? 'disabled' : '' }
							value="اضف المبلغ للتحصيل"
							class="w-100 btn btn-success   
								 font-weight-bold text-center"
							onsubmit="return checkAmount();">

					</form:form></li>

				<li class="bg-dark list-group-item">



					<form method="GET" action="seller-collect">
						<input type="hidden" name="id" value="${seller.id}"> <input
							type="date" name="date" value="${date}"
							class="w-100 btn badge-light font-weight-bold text-center">

						<input type="submit" style="margin-top: 10px;"
							class="w-100 btn badge-success font-weight-bold text-center"
							value="اذهب لهذا اليوم" />

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
									style="font-size: 22px">[ التحصيل ] - [ اسم البائع :
									${seller.name} ] - [ الدين : ${seller.getDrawee()} ]</td>

							</tr>

							<tr style="font-size: 18px">
								<th>المبلغ</th>
								<th>اسم المستلم</th>
								<th>التاريخ</th>
							</tr>

						</thead>
						<tbody>

							<c:forEach var="tempItem" items="${seller.collects}">

								<tr>
									<td>${tempItem.amount}</td>
									<td>${tempItem.receiver}</td>
									<td>${tempItem.date}</td>
									<td style="width: 160px"><a
										${tempItem.date == today ? '' : 'hidden'}
										style="height: 30px; font-size: 14px;"
										class="btn btn-danger text-wight font-weight-bold"
										onclick="return confirm('هل انت متأكد من حذف هذا التحصيل ؟')"
										href="delete-collect?id=${tempItem.id}">حذف</a></td>
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