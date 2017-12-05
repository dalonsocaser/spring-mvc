<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>



<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tweet list</title>
</head>
<body>
	<c:forEach items="${littleTweetList}" var="tweet">
		<li id="tweet_<c:out value="tweet.id"/>">
			<div class="tweetMessage">
				<c:out value="${tweet.message}" />
			</div>
			<div>
				<span class="tweetTime"><c:out value="${tweet.time}" /></span> <span
					class="tweetLocation"> (<c:out value="${tweet.latitude}" />,
					<c:out value="${tweet.longitude}" />)
				</span>
			</div>
		</li>
	</c:forEach>
</body>
</html>