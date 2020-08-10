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
      google.charts.load('current', {'packages':['bar']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Year', 'Sales', 'Expenses', 'Profit'],
          ['2014', 1000, 400, 200]
        ]);

        var options = {
          chart: {
            title: 'Company Performance',
            subtitle: 'Sales, Expenses, and Profit: 2014-2017',
          },
          bars: 'horizontal' // Required for Material Bar Charts.
        };

        var chart = new google.charts.Bar(document.getElementById('barchart_material'));

        chart.draw(data, google.charts.Bar.convertOptions(options));
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

                    <select class="selectTerm" name="term">
                        <option value="202006/202008" selected>2020 Summer</option>
                        <option value="202003/202005">2020 Spring</option>
                        <option value="201912/202002">2019 Winter</option>
                        <option value="201909/201911">2019 Fall</option>
                        <option value="201906/201908">2019 Summer</option>
                    </select>

                    <select class="topCount" name="count">
                        <option value="10" selected>상위 10</option>
                        <option value="20">상위 20</option>
                        <option value="30">상위 30</option>
                    </select>
                    
                </section>

                <div class="graphApiWrapper">
                    <div id="barchart_material" style="width: 100%; height: 100%;"></div>
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
