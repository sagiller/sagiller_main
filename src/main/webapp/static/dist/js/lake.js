/*
 * Author: Perry Hu
 * Date: 2016年10月17日
 * Description:
 *
 **/
$(function () {
    markActive();
});
function markActive()
{
    var path = window.location.href;

    $('a[href="'+ path + '"]').parent().addClass('active');
    $('a[href="'+ path + '"]').parent().parent().parent().addClass('active');
}
