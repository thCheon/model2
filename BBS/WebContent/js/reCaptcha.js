

function chkCaptcha() {
    if (typeof(grecaptcha) != 'undefined') {
       if (grecaptcha.getResponse() == "") {
           alert("reCaptcha를 체크하세요");
           return false;
       }
    }
    else {
         return false;
    }
}