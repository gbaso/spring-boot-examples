<#setting locale="en_GB">
<#function formatDate dateToFormat>
	<#local date = dateToFormat>
	<#if dateToFormat?is_string>
		<#local date = dateToFormat?datetime.iso>
	</#if>
	<#return date>
</#function>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html lang="en" xmlns="http://www.w3.org/1999/xhtml" style="height:100%">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
</head>
<body style="width:100%;margin:0;padding-top:0;padding-bottom:0;padding-left:0;padding-right:0;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;height:100%">
    <img alt="banner" src="${banner}" width="200" height="80" style="display:block;outline:none;border:none;text-decoration:none;width:200px !important"/>
    This is a test email with ${param1} and ${param2}.
</body>
</html>
