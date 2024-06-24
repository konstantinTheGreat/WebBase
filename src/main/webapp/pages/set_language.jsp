<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="lang" value="${sessionScope.lang != null ? sessionScope.lang : 'en'}"/>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="${lang eq 'ru' ? 'messages_ru' : 'messages_en'}" />


