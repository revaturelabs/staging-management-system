$(document).on("click", "#addBatch", function() {

			$("#addBatchForm").unbind('submit').bind('submit', function(e) {
				e.preventDefault();

				$.ajax({
					type : 'POST',
					url : '/StagingManagementSystem/addBatch',
					data : $(this).serialize(),
					success : function(data) {
						console.log("SUCCESS: ", data);
					    $('#addBatch').modal('hide');

					},
					error : function(e) {
						console.log("ERROR: ", e);
						display(e);
					},
					done : function(e) {
						console.log("DONE");
					}
				});
			});
		});


