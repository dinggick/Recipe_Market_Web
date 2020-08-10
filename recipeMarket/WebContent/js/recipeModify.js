$(() => {
    
    $(".dataInput").on("focus", function(evt) {
        $(this).css("border", "0.5px solid black");
        $(this).css("background-color", "ghostwhite");
    });
   ////////////////////////////////////////////////////////////////////////
	$('.ingredientsWrapper').on("focus",'.dataInput', function(evt) {
        $(this).css("border", "0.5px solid black");
        $(this).css("background-color", "ghostwhite");
    });
   
    $('.ingredientsWrapper').on('blur change','.dataInput', function(evt) {
        if ($(this).val() == "") {
            $(this).css("background-color", "#ddd");
            $(this).css("border", "none");
        }
        else {
            $(this).css("background-color", "ghostwhite");
            $(this).css("border", "none");
        }
    });
    ////////////////////////////////////////////////////////////
       ////////////////////////////////////////////////////////////////////////
	$('.recipeSummWrapper').on("focus",'.dataInput', function(evt) {
        $(this).css("border", "0.5px solid black");
        $(this).css("background-color", "ghostwhite");
    });
   
    $('.recipeSummWrapper').on('blur change','.dataInput', function(evt) {
        if ($(this).val() == "") {
            $(this).css("background-color", "#ddd");
            $(this).css("border", "none");
        }
        else {
            $(this).css("background-color", "ghostwhite");
            $(this).css("border", "none");
        }
    });
    ////////////////////////////////////////////////////////////
        $(".dataInput").on("blur change", function(evt) {
        if ($(this).val() == "") {
            $(this).css("background-color", "#ddd");
            $(this).css("border", "none");
        }
        else {
            $(this).css("background-color", "ghostwhite");
            $(this).css("border", "none");
        }
    });

});
