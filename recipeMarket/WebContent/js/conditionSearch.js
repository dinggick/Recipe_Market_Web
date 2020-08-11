$(() => {
    var $buttonSection = $(".buttonSection");
    $(".dataInput").attr("size", "40");

    var $dataInput = $(".dataInput");
    $dataInput.not(":first").on("focus", function(evt) {
        if (!$(this).prop("readonly"))
            $(this).css("border", "0.5px solid black");
    });

    $dataInput.on("blur change", function(evt) {
        $(this).css("border", "none");
    });

    var $infoWrapper = $(".infoWrapper");
    $infoWrapper.on("focus", "#r_rd_pwd", function(evt) {
        $(this).css("border", "0.5px solid black");
    });
    
    $buttonSection.on("click", ".searchBtn", function(evt) {         
        $.ajax({
            url: "./graph4",
            data: $("form").serialize(),
            success: (responseObj) => {
                if (responseObj.status == "success") {
                	alert("success");
                } else {
                	alert("fail");
                }
            }
        });
        
        return false;
    });

    $(form).on("submit", function() {
        return false;
    });
});
