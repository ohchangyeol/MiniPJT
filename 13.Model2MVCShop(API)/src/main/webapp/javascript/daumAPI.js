$(document).ready(function () {
    function setValueFocus(inputAddrId, detailFocus) {
        new daum.Postcode({
            oncomplete: function(data) { //���ý� �Է°� ����
                document.getElementById(inputAddrId).value = data.address; // �ּ� �ֱ�
                document.querySelector(detailFocus).focus(); //���Է� ��Ŀ��
            }
        }).open();
    }

    $("#addr").on("click", function(){ //�ּ��Է�ĭ�� Ŭ���ϸ�
        //īī�� ���� �߻�
        setValueFocus("addr", "input[name=addrDetail]");
    });
    
    $("#divyAddr").on("click", function(){ //�ּ��Է�ĭ�� Ŭ���ϸ�
        //īī�� ���� �߻�
        setValueFocus("divyAddr", "input[name=divyAddrDetail]");
    });

});
    


