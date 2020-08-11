$(() => {
    var $prevLi = $(".blank");
    $(".menuWrapper > ul > li").on("click", function(evt) {
      $prevLi.find("ul").slideToggle();
      $(this).find("ul").slideToggle();
      $prevLi = $(this);
    });
 });