<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${requestScope.lang}"/>
<fmt:setBundle basename="message"/>


<html lang="${requestScope.lang}">
<head>
    <title>All cards</title>
    <link rel="stylesheet" href="index.styl">

</head>
<body>
<div class="line"></div>
<div class="wrapper">
    <header role="banner">
        <nav role="navigation">
            <h1><a href="adminHomepage">Home</a></h1>
            <ul class="nav-ul">
                <dir></dir>
                <dir></dir>
                <dir><a href="allCards"><fmt:message key="header.AllCards"/></a></dir>
                <dir><a href="allUsers"><fmt:message key="header.AllUsers"/></a></dir>
                <dir><a href="logout"><fmt:message key="header.LogOut"/></a></dir>
            </ul>
        </nav>
        <c:choose>
            <c:when test="${requestScope.lang == 'en'}">
                <a href="javascript:settingsLang('uk')"
                   class="nav-link text-secondary"><span
                        class="text-center text-muted">UK</span></a>
            </c:when>
            <c:otherwise>
                <a href="javascript:settingsLang('en')"
                   class="nav-link text-secondary"><span
                        class="text-center text-muted">EN</span></a>
            </c:otherwise>
        </c:choose>
    </header>
    <h2 align="center"><fmt:message key="message.AllCards"/></h2>
    <table width="100%">
        <tr>
            <td><fmt:message key="table.UserID"/></td>
            <td><fmt:message key="table.CardID"/></td>
            <td><fmt:message key="table.Sum"/></td>
            <td><fmt:message key="table.Status"/></td>
            <td><fmt:message key="table.RequestToAdmin"/></td>
            <td><fmt:message key="table.BlockCard"/></td>
            <td><fmt:message key="table.UnblockCard"/></td>
        </tr>
        <p></p>
        <c:forEach items="${requestScope.allCards}" var="card">
            <tr>
                <td>
                    <c:out value="${card.userId}"/>
                </td>
                <td>
                    <c:out value="${card.cardId}"/>
                </td>
                <td>
                    <c:out value="${card.cardSum}"/>
                </td>
                <td>
                    <c:out value="${card.isCardStatus()}"/>
                </td>
                <td>
                    <c:out value="${card.userRequest}"/>
                </td>
                <td>
                    <form action="${pageContext.request.contextPath}/allCards" method="post">
                        <input type="hidden" name="hidden" value="${card.cardId}">
                        <input onclick="setTimeout(function () { window.location.reload(); }, 3)" type="submit"
                               name="button3" value=<fmt:message key="table.BlockCard"/>>
                    </form>
                </td>
                <td>
                    <form action="${pageContext.request.contextPath}/allCards" method="post">
                        <input type="hidden" name="hidden" value="${card.cardId}">
                        <input onclick="setTimeout(function () { window.location.reload(); }, 3)" type="submit"
                               name="button4" value=<fmt:message key="table.UnblockCard"/>>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>


<%--For displaying Previous link except for the 1st page --%>
<c:if test="${currentPage != 1}">
    <td><a href="allCards?page=${currentPage - 1}">Previous</a></td>
</c:if>

<%--For displaying Page numbers.
The when condition does not display a link for the current page--%>
<table border="1" cellpadding="5" cellspacing="5">
    <tr>
        <c:forEach begin="1" end="${noOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <td>${i}</td>
                </c:when>
                <c:otherwise>
                    <td><a href="allCards?page=${i}">${i}</a></td>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </tr>
</table>

<%--For displaying Next link --%>
<c:if test="${currentPage lt noOfPages}">
    <td><a href="allCards?page=${currentPage + 1}">Next</a></td>
</c:if>

<script>
    function settingsLang(lang) {
        document.cookie = "lang=" + lang + "; path=/; max-age=" + (365 * 24 * 60 * 60);
        location.reload();
    }
</script>
<script type="text/javascript">

    initial_sort_id = 0;
    initial_sort_up = 1;
    var sort_case_sensitive = false;

    function _sort(a, b) {
        var a = a[0];
        var b = b[0];
        var _a = (a + '').replace(/,/, '.');
        var _b = (b + '').replace(/,/, '.');
        if (parseInt(_a) && parseInt(_b)) return sort_numbers(parseInt(_a), parseInt(_b));
        else if (!sort_case_sensitive) return sort_insensitive(a, b);
        else return sort_sensitive(a, b);
    }

    function sort_numbers(a, b) {
        return a - b;
    }

    function sort_insensitive(a, b) {
        var anew = a.toLowerCase();
        var bnew = b.toLowerCase();
        if (anew < bnew) return -1;
        if (anew > bnew) return 1;
        return 0;
    }

    function sort_sensitive(a, b) {
        if (a < b) return -1;
        if (a > b) return 1;
        return 0;
    }

    function getConcatenedTextContent(node) {
        var _result = "";
        if (node == null) {
            return _result;
        }
        var childrens = node.childNodes;
        var i = 0;
        while (i < childrens.length) {
            var child = childrens.item(i);
            switch (child.nodeType) {
                case 1: // ELEMENT_NODE
                case 5: // ENTITY_REFERENCE_NODE
                    _result += getConcatenedTextContent(child);
                    break;
                case 3: // TEXT_NODE
                case 2: // ATTRIBUTE_NODE
                case 4: // CDATA_SECTION_NODE
                    _result += child.nodeValue;
                    break;
                case 6: // ENTITY_NODE
                case 7: // PROCESSING_INSTRUCTION_NODE
                case 8: // COMMENT_NODE
                case 9: // DOCUMENT_NODE
                case 10: // DOCUMENT_TYPE_NODE
                case 11: // DOCUMENT_FRAGMENT_NODE
                case 12: // NOTATION_NODE
                    // skip
                    break;
            }
            i++;
        }
        return _result;
    }

    function sort(e) {
        var el = window.event ? window.event.srcElement : e.currentTarget;

        while (el.tagName.toLowerCase() !== "td") el = el.parentNode;

        var a = new Array();
        var name = el.lastChild.nodeValue;
        var dad = el.parentNode;
        var table = dad.parentNode.parentNode;
        var up = table.up; // no set/getAttribute!

        var node, arrow, curcol;
        for (var i = 0; (node = dad.getElementsByTagName("td").item(i)); i++) {
            if (node.lastChild.nodeValue === name) {
                curcol = i;
                if (node.className === "curcol") {
                    arrow = node.firstChild;
                    table.up = Number(!up);
                } else {
                    node.className = "curcol";
                    arrow = node.insertBefore(document.createElement("span"), node.firstChild);
                    arrow.appendChild(document.createTextNode(""));
                    table.up = 0;
                }
                arrow.innerHTML = ((table.up === 0) ? "&#8595;" : "&#8593;") + "&nbsp;";
            } else {
                if (node.className === "curcol") {
                    node.className = "";
                    if (node.firstChild) node.removeChild(node.firstChild);
                }
            }
        }

        var tbody = table.getElementsByTagName("tbody").item(0);
        for (var i = 0; (node = tbody.getElementsByTagName("tr").item(i)); i++) {
            a[i] = new Array();
            a[i][0] = getConcatenedTextContent(node.getElementsByTagName("td").item(curcol));
            a[i][1] = getConcatenedTextContent(node.getElementsByTagName("td").item(1));
            a[i][2] = getConcatenedTextContent(node.getElementsByTagName("td").item(0));
            a[i][3] = node;
        }

        a.sort(_sort);

        if (table.up) a.reverse();

        for (var i = 0; i < a.length; i++) {
            tbody.appendChild(a[i][3]);
        }
    }

    function init(e) {
        if (!document.getElementsByTagName) return;

        if (document.createEvent) function click_elem(elem) {
            var evt = document.createEvent("MouseEvents");
            evt.initMouseEvent("click", false, false, window, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, elem);
            elem.dispatchEvent(evt);
        }

        for (var j = 0; (thead = document.getElementsByTagName("thead").item(j)); j++) {
            var node;
            for (var i = 0; (node = thead.getElementsByTagName("td").item(i)); i++) {
                if (node.addEventListener) node.addEventListener("click", sort, false);
                else if (node.attachEvent) node.attachEvent("onclick", sort);
                node.title = "Нажмите на заголовок, чтобы отсортировать колонку";
            }
            thead.parentNode.up = 0;

            if (typeof (initial_sort_id) != "undefined") {
                td_for_event = thead.getElementsByTagName("td").item(initial_sort_id);
                if (td_for_event.dispatchEvent) click_elem(td_for_event);
                else if (td_for_event.fireEvent) td_for_event.fireEvent("onclick");
                if (typeof (initial_sort_up) != "undefined" && initial_sort_up) {
                    if (td_for_event.dispatchEvent) click_elem(td_for_event);
                    else if (td_for_event.fireEvent) td_for_event.fireEvent("onclick");
                }
            }
        }
    }
    var root = window.addEventListener || window.attachEvent ? window : document.addEventListener ? document : null;
    if (root) {
        if (root.addEventListener) root.addEventListener("load", init, false);
        else if (root.attachEvent) root.attachEvent("onload", init);
    }
</script>
</body>
</html>
