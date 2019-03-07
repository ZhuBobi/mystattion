$(document).ready(function () {
    if (document.getElementsByClassName('div_title').length > 0) {
        var width = document.getElementsByClassName('div_title')[0].clientWidth;
        if (width < 1135 || width > 1145) {
            document.getElementsByClassName('div_title')[0].style.display = 'none';
        }
        var o = document.getElementById("css_div");
        o.addEventListener("webkitAnimationEnd", function () {
            $('.span_hide_txt').fadeIn();
        });
    }
})