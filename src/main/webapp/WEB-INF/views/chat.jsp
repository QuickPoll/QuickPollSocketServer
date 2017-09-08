<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>

<head>

<meta charset="UTF-8">

<title>JSP</title>

<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.10.2.min.js"/>"></script>

<script type="text/javascript" src="<c:url value="/resources/js/sockjs-0.3.4.js"/>"></script>

<script type="text/javascript">

    $(document).ready(function() {
    	var sock;
		var message={};
        //웸소켓을 지정한 url로 연결한다.
		
        
		
        function sendMessage() {
            /*소켓으로 보내겠다.  */
            sock.send($("#message").val());
        }

        //evt 파라미터는 웹소켓을 보내준 데이터다.(자동으로 들어옴)

        function onMessage(evt) {

            var data = evt.data;

            $("#data").append(data + "<br/>");
            //sock.close();
        }
		

        function onClose(evt) {

            $("#data").append("연결 끊김");

        }
       	function create() {
       		sock = new SockJS("<c:url value="/echo"/>");
    		
        	
            //자바스크립트 안에 function을 집어넣을 수 있음.

            //데이터가 나한테 전달되읐을 때 자동으로 실행되는 function

            sock.onmessage = onMessage;


            //데이터를 끊고싶을때 실행하는 메소드

            sock.onclose = onClose;
        	

            sock.onopen = function(){
            	message={};
                message.coure_id = "cs";
                message.type = "create";
                message.id = $("#id").val();
                
                sock.send(JSON.stringify(message));

            };
       	}
       	function connect() {
       		sock = new SockJS("<c:url value="/echo"/>");
    		
        	
            //자바스크립트 안에 function을 집어넣을 수 있음.

            //데이터가 나한테 전달되읐을 때 자동으로 실행되는 function

            sock.onmessage = onMessage;


            //데이터를 끊고싶을때 실행하는 메소드

            sock.onclose = onClose;
        	

            sock.onopen = function(){
            	message={};
                message.coure_id = "cs";
                message.type = "connect";
                message.id = $("#id").val();
                
                sock.send(JSON.stringify(message));

            };
       	}
        function closeMessage() {
        	
        }
        $("#createBtn").click(function() {
        	create();
        })
        $("#connectBtn").click(function() {
        	connect();
        })
        $("#sendBtn").click(function() {

            sendMessage();

        });
		$("#close").click(function() {
			location.href="/QuickPollSocketServer/";
		})
    });

    
	
</script>

</head>

<body>
	<input type="button" id="createBtn" value="만들기"/><br><br>
	<input type="button" id="connectBtn" value="접속"/><br><br>
    <input type="text" id="message" />

    <input type="button" id="sendBtn" value="전송" />
<input type="button" id="close" value="close" />
    <div id="data"></div>
    
	<input type="hidden" id="id" value="${userInfo}">
</body>

</html>


