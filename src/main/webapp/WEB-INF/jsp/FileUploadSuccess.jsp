<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Spring MVC Multiple File Upload</title>
<style>
body { font-family: "Trebuchet MS"; }
h1 { font-size: 1.5em; }
</style>
</head>
<body>
    <h1>Spring MVC Multiple File Upload example</h1>
    <p>Following files are uploaded successfully.</p>
    <ol>
        <c:forEach items="${files}" var="file">
            <li>${file}</li>
        </c:forEach>
    </ol>
</body>
</html>
