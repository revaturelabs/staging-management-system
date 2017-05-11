//add associate ajax		
$(document).on("click", "#addAssociate", function(){
		$('#addAssociate').submit(function(e){
	        e.preventDefault();
	        $.ajax({
	            type: 'POST',
	            cache: false,
	            url: '#####',
	            data: $(this).serialize(), 
	            success: function(response, status, xhr) {
	           			console.log(status);
	            },
	            error : function(xhr, status, error) {
					var err = eval("(" + xhr.responseText + ")");
					alert(err);
				}
       		});
    	});
   
	});

//add associate ajax		
$(document).on("click", "#addBatch", function(){
		$('#addBatch').submit(function(e){
	        e.preventDefault();
	        $.ajax({
	            type: 'POST',
	            cache: false,
	            url: '#####',
	            data: $(this).serialize(), 
	            success: function(response, status, xhr) {
	           			console.log(status);
	            },
	            error : function(xhr, status, error) {
					var err = eval("(" + xhr.responseText + ")");
					alert(err);
				}
       		});
    	});
   
	});

//add client ajax		
$(document).on("click", "#addClient", function(){
		$('#addClient').submit(function(e){
	        e.preventDefault();
	        $.ajax({
	            type: 'POST',
	            cache: false,
	            url: '#####',
	            data: $(this).serialize(), 
	            success: function(response, status, xhr) {
	           			console.log(status);
	            },
	            error : function(xhr, status, error) {
					var err = eval("(" + xhr.responseText + ")");
					alert(err);
				}
       		});
    	});
   
	});
