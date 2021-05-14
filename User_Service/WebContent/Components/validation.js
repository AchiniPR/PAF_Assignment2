$(document).ready(function() 
{  
		$("#alertSuccess").hide();  
	    $("#alertError").hide(); 
}); 
 
 
// SAVE ============================================ 
$(document).on("click", "#btnSave", function(event) 
{  
	// Clear alerts---------------------  
	$("#alertSuccess").text("");  
	$("#alertSuccess").hide();  
	$("#alertError").text("");  
	$("#alertError").hide(); 
 
	// Form validation-------------------  
	var status = validateUserForm();  
	if (status != true)  
	{   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	} 
 
	// If valid------------------------  
	var type = ($("#hidUserIDSave").val() == "") ? "POST" : "PUT"; 

	$.ajax( 
	{  
			url : "UserService",  
			type : type,  
			data : $("#formUser").serialize(),  
			dataType : "text",  
			complete : function(response, status)  
			{   
				onUserSaveComplete(response.responseText, status);  
			} 
	}); 
}); 


function onUserSaveComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			$("#alertSuccess").text("Successfully saved.");    
			$("#alertSuccess").show(); 

			$("#divUserGrid").html(resultSet.data);   
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		} 

	} else if (status == "error")  
	{   
		$("#alertError").text("Error while saving.");   
		$("#alertError").show();  
	} else  
	{   
		$("#alertError").text("Unknown error while saving..");   
		$("#alertError").show();  
	} 

	$("#hidUserIDSave").val("");  
	$("#formUser")[0].reset(); 
} 

 
// UPDATE========================================== 
$(document).on("click", ".btnUpdate", function(event) 
{     
	$("#hidUserIDSave").val($(this).closest("tr").find('#hidUserIDUpdate').val());     
	$("#pmethod").val($(this).closest("tr").find('td:eq(0)').text());     
	$("#pdate").val($(this).closest("tr").find('td:eq(1)').text());     
	$("#pamount").val($(this).closest("tr").find('td:eq(2)').text());     
}); 




//REMOVE===========================================
$(document).on("click", ".btnRemove", function(event) 
{  
	$.ajax(  
	{   
		url : "PaymentService",   
		type : "DELETE",   
		data : "pid=" + $(this).data("paymentid"),   
		dataType : "text",   
		complete : function(response, status)   
		{    
			onPaymentDeleteComplete(response.responseText, status);   
		}  
	}); 
}); 

function onPaymentDeleteComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			
			$("#alertSuccess").text("Successfully deleted.");    
			$("#alertSuccess").show(); 
		
			$("#divPaymentGrid").html(resultSet.data); 
			
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		}
		

	} else if (status == "error")  
	{   
		$("#alertError").text("Error while deleting.");   
		$("#alertError").show();  
	} else  
	{   
		$("#alertError").text("Unknown error while deleting..");   
		$("#alertError").show();  
	}
}
 
// CLIENT-MODEL========================================================================= 
function validatePaymentForm() 
{  
	// NAME  
	if ($("#pmethod").val().trim() == "")  
	{   
		return "Insert Payment Method.";  
	} 

	// DESCRIPTION------------------------  
	if ($("#pdate").val().trim() == "")  
	{   
		return "Insert Date.";  
	} 
	
	
	//AMOUNT-------------------------------
	 var tmpAmount = $("#pamount").val().trim();
	if (!$.isNumeric(tmpAmount)) 
	 {
	 return "Insert Amount.";
	 }

	return true; 
}