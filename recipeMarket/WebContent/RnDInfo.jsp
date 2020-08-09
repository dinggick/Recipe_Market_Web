<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@	page import="com.recipe.vo.RnD"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>

<c:set var="rnd" value="${requestScope.rnd}"/>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>RnDInfo</title>

    <link rel="icon" href="${contextPath}/images/titlecon.png">

    <link rel="stylesheet" href="${contextPath}/css/adminCommonSection.css">

    <link rel="stylesheet" href="${contextPath}/css/header.css">
    <link rel="stylesheet" href="${contextPath}/css/footer.css">

    <link rel="stylesheet" href="${contextPath}/css/RnDInfo.css">

    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,400i,500,700,900" rel="stylesheet">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="${contextPath}/js/adminCommonSection.js"></script>
    <script src="${contextPath}/js/RnDInfo.js"></script>

    <script src="${contextPath}/js/header.js"></script>
    <script src="${contextPath}/js/footer.js"></script>
    <script src="${contextPath}/js/dropdownMenu.js"></script>

    <style>
    /* .infoWrapper {
        height: 550px;
        width: 700px;
    } */
    .formWrapper label {
        font-size: 1em;
        font-weight: border;

        padding-right: 20px;
    }
    table {
        height: 400px;
        width: 500px;
        background-color: darkkhaki"
    }
    </style>

    <style>
        input:-webkit-autofill,
        input:-webkit-autofill:hover, 
        input:-webkit-autofill:focus,
        textarea:-webkit-autofill,
        textarea:-webkit-autofill:hover,
        textarea:-webkit-autofill:focus,
        select:-webkit-autofill,
        select:-webkit-autofill:hover,
        select:-webkit-autofill:focus {
            transition: background-color 5000s ease-in-out 0s;
        }
    </style>
    
</head>
<body>
    <header>
        <!-- 왼쪽 영역 -->
        <div class="headerLeftSection">
            <!-- 로고(홈 버튼) -->
            <h1 class="home">RECIPE MARKET</h1>
        </div>
        <!-- 오른쪽 영역 -->
        <div class="headerRightSection">
            <!-- 드롭다운 메뉴 -->
            <div class="dropdown">
                <!-- 로그인 버튼(누르면 드롭다운 메뉴 보이도록) -->
                <h1 class="account">Sign in</h1>
                <!-- 드롭다운 메뉴 구성 (동적 생성 필요) -->
                <div class="dropdown-content">
                    <a href="#">로그인</a>
                    <a href="#">Menu 2</a>
                    <a href="#">Menu 3</a>
                </div>
            </div>
        </div>
    </header>

    <div class="bodySection">
    
    <section class="leftSection">

        <div class="titleWrapper">

            <span>
                RnDInfo
            </span>

        </div>

        <div class="underLineWrapper">
            <hr> 
        </div>

        <div class="menuWrapper">
            <ul>
                <li>
                    <span>RnD management</span>
                    <ul>
                        <li><a href="${contextPath}/static/RnDAdd.html">AddRnD</a></li>
                        <li><a href="${contextPath}/rnd/list?currentPage=">RnDList</a></li>
                    </ul>
                </li>                
                <li>
                    <span>CRM</span>
                    <ul>
                        <li><span>graph1</span></li>
                        <li><span>graph2</span></li>
                    </ul>
                </li>
            </ul>                            
        </div>

    </section>

    <section class="rightSection">                                        
        <div class="contentsWrapper">
            <div class="infoWrapper">
                <form autocomplete="off" class="formWrapper" action="#" method="post">
                    <table>
                        <tr>
                            <td><label for="rd_id">rd_email</label></td><td><input class="dataInput" type="text" id="rd_email" name="rd_email" readonly value="${rnd.rdEmail}"></td>
                        </tr>
                        <tr>
                            <td><label for="rd_pwd">rd_pwd</label></td><td><input class="dataInput" type="password" id="rd_pwd" name="rd_pwd" readonly value="${rnd.rdPwd}"></td>
                        </tr>
                        <tr>
                            <td><label for="rd_manager_name">rd_manager_name</label></td><td><input class="dataInput" type="text" id="rd_manager_name" name="rd_manager_name" readonly value="${rnd.rdManagerName}"></td>
                        </tr>
                        <tr>
                            <td><label for="rd_team_name">rd_team_name</label></td><td><input class="dataInput" type="text" id="rd_team_name" name="rd_team_name" readonly value="${rnd.rdTeamName}"></td>
                        </tr>
                        <tr>
                            <td><label for="rd_phone">rd_phone</label></td><td><input class="dataInput" type="tel" id="rd_phone" name="rd_phone" readonly value="${rnd.rdPhone}"></td>
                        </tr>                    
                    </table>                   
                    <div class="buttonSection">
                        <button class="reviseBtn" type="button">
                            reviseBtn
                        </button>
                        <button class="deleteBtn" type="submit">
                            deleteBtn
                        </button>
                    </div>
                </form>
            </div>
        </div>                             
    </section>

    </div>

    <footer>
        <p>
            © 2020 RECIPE MARKET All rights reserved.
        </p>
        <a class="topBtn">&uarr;TOP</a>
    </footer>

</body>
</html>