$(() => {
	var $prevLi = $(".blank");
    $(".menuWrapper > ul > li > span").on("click", function(evt) {
    	$prevLi.slideToggle();
    	$(this).next().slideToggle();
    	$prevLi = $(this).next();
    });
 });