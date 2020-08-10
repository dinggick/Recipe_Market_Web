<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@	page import="com.recipe.vo.RnD"%>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>CRM</title>

    <link rel="icon" href="./images/titlecon.png">

    <link rel="stylesheet" href="./css/adminCommonSection.css">

    <link rel="stylesheet" href="./css/header.css">
    <link rel="stylesheet" href="./css/footer.css">

    <link rel="stylesheet" href="./css/CRM.css">
    
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,400i,500,700,900" rel="stylesheet">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="./js/adminCommonSection.js"></script>

    <script src="./js/header.js"></script>
    <script src="./js/footer.js"></script>
    <script src="./js/dropdownMenu.js"></script>

    <script src="./js/CRM.js"></script>

    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
        google.charts.load('current', {'packages':['corechart']});
        google.charts.setOnLoadCallback(drawChart);

        function drawChart() {

        var data = google.visualization.arrayToDataTable([
            ['rd_email', 'total_sales'],
            ['rd1',    28000],
            ['rd2',    19000],
            ['rd3',    39000],
            ['rd4',    3000],
            ['rd5',    12000],
            ['rd6',    13000],
            ['rd7',    16000],
            ['rd8',    17000]
        ]);

        var options = {
            title: '부서별 총 매출 (단위 만)',
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart'));

        chart.draw(data, options);
  }
</script>

</head>
<body>

    <header id="header" style="background-color: #D2302C;">
        <div class="headerLeftSection" style="background-color: #D2302C;">
            <!-- <img src="./images/titlecon.png" style="width: 50px; padding-left: 10px; background-color: #D2302C;"> -->
            <h1 class="home">RECIPE MARKET</h1>
        </div>

        <div class="headerRightSection" style="background-color: #D2302C;">
            <div class="dropdown">
                <img src="./images/user.png" class="account">
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
                CRM
            </span>

        </div>

        <div class="underLineWrapper">
            <hr> 
        </div>

        <div class="menuWrapper">
            <ul>
                <!-- <li>
                    <span>Customer management</span>
                    <ul>
                        <li><span>CustomerList</span></li>
                    </ul>
                </li> -->

                <li>
                    <span>RnD management</span>
                    <ul>
                        <li><a>AddRnD</a></li>
                        <li><a>RnDList</a></li>
                    </ul>
                </li>
                
                <li>
                    <span>CRM</span>
                    <ul>
                        <li><a>graph1</a></li>
                        <li><a>graph2</a></li>
                        <li><a>graph3</a></li>
                    </ul>
                </li>
            </ul>                                 
        </div>

    </section>

    <section class="rightSection">
                                        
        <div class="contentsWrapper">

            <div class="GraphWrapper">

                <section class="selectSection">

                    <select class="selectYear" name="year">
                        <option value="2020" selected>2020</option>
                        <option value="2019">2019</option>
                        <option value="2018">2018</option>
                    </select>

                    <select class="topCount" name="count">
                        <option value="10" selected>상위 10</option>
                        <option value="20">상위 20</option>
                        <option value="30">상위 30</option>
                    </select>
                    
                </section>

                <div class="graphApiWrapper">
                    <!-- <div id="barchart_material" style="width: 100%; height: 100%"></div> -->
                    <div id="piechart" style="width: 81%; height: 100%"></div>
                 </div>
                 
            </div>
            
    	</div>
    	             
    </section>

    </div>

    <footer>
        <p>© 2020 RECIPE MARKET All rights reserved.</p>
        <a class="topBtn">&uarr;TOP</a>
    </footer>

</body>
</html>
