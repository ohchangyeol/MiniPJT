$(document).ready(function () {
    function setValueFocus(inputAddrId, detailFocus) {
        new daum.Postcode({
            oncomplete: function(data) { //선택시 입력값 세팅
                document.getElementById(inputAddrId).value = data.address; // 주소 넣기
                document.querySelector(detailFocus).focus(); //상세입력 포커싱
            }
        }).open();
    }

    $("#addr").on("click", function(){ //주소입력칸을 클릭하면
        //카카오 지도 발생
        setValueFocus("addr", "input[name=addrDetail]");
    });
    
    $("#divyAddr").on("click", function(){ //주소입력칸을 클릭하면
        //카카오 지도 발생
        setValueFocus("divyAddr", "input[name=divyAddrDetail]");
    });

});
    


