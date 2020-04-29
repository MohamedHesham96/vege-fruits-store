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

<title>Insert title here</title>
</head>
<body>


	<form:form metho="POST" action="add-incoming" modelAttribute="incoming">

		<div class="row  my-4">
			<div dir='rtl' class="col-lg-12 col-md-8">
				<div class="table-responsive">
					<table class=" table table-striped table-dark">
						<thead class="thead-inverse">
							<tr>
								<th>الكمية</th>
								<th>الصنف</th>
								<th>السعر تجاري</th>
								<th>سعر القطعة</th>
								<th>المبلغ المدفوع</th>
								<th>المحل</th>
							</tr>
						</thead>

						<tbody>

							<tr>
								<td><form:input style="width: 100px;" type="text"
										path="quantity" class="text-center form-control mb-2 col-xs-3"
										placeholder="ادخل المبلغ"></form:input></td>

								<td style="width:"><form:input type="text" path="item"
										class="text-center form-control mb-2 col-xs-3"
										placeholder="ادخل اسم العميل"></form:input></td>

							</tr>

							<tr style="text-align: center;">
								<td colspan="6"><input type="submit" value="اضف الصنف"
									class="btn badge-info   
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


</body>
</html>