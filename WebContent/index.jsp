<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="./activity/addSubActivity" method="post">
		activityId<input type="text" name="PitchActivityId"></br>
		date<input type="text" name="Date"></br>
		lession<input type="text" name="Lession"></br>
		day<input type="text" name="Day"></br>
		header<input type="text" name="Header"></br>
		needDepartmentId<input type="text" name="NeedDepartmentId"></br>
		boyFirst<input type="text" name="BoyFirst"></br>
		needNumber<input type="text" name="NeedNumber"></br>
		<input type="submit" value="提交">
	</form>
</body>
</html>