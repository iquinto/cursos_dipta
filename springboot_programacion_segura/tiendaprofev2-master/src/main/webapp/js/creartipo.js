$(function(){
	var bien=$("#bien");
	var mal=$("#mal");
	var f=$("#formulario");
	
	bien.hide();
	mal.hide();
	
	var token = $("meta[name='_csrf']").attr("content"); 
	var header = $("meta[name='_csrf_header']").attr("content"); 
	
	f.submit(function(e){
		e.preventDefault();
		
		bien.hide();
		mal.hide();
		
		$.ajax({
			url:"../tipo/crear",
			beforeSend: function(request) { 
			     request.setRequestHeader(header, token); 
			},
			//OJO! que no os pase como a mí y se te olvide esto!
			contentType: "application/json",
			method:"post",
			data:JSON.stringify(getFormData(f)),
			dataType:"json",
			success:function() {
				bien.show();
			},
			error:function() {
				mal.show();
			}
		});
		
	});
});


function getFormData($form){
    var unindexed_array = $form.serializeArray();
    var indexed_array = {};

    $.map(unindexed_array, function(n, i){
        indexed_array[n['name']] = n['value'];
    });
	console.log(JSON.stringify(indexed_array))
    return indexed_array;
}