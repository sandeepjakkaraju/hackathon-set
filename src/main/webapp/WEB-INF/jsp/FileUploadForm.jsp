<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Spring MVC Multiple File Upload</title>
<style>
    body {font-family: "Trebuchet MS";}
    h1 {font-size: 1.5em;}
.error {
    color: #ff0000;
}
.errorblock{
    color: #000;
    background-color: #ffEEEE;
    border: 3px solid #ff0000;
    padding:8px;
    margin:16px;
}

</style>
</head>
<body>
<h1>Upload Questions and Answers in CVS file</h1>

<form:form method="post" action="fileUpload.htm" 
        modelAttribute="uploadForm" enctype="multipart/form-data">
        
    <form:errors path="*" cssClass="errorblock" element="div"/>

    <p>Select file to upload.</p>
        <br/><input name="file" type="file" />
    <br/><input type="submit" value="Upload" />
</form:form>
</body>
</html>


