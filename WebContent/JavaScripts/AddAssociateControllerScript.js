$(document).on("click", "#addBatchBtn", function() {

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
			},
			done : function(e) {
				console.log("DONE");
			}
		});
	});
});


		$(document).on("click", "#addAssociate", function() {

			$("#addAssociateForm").unbind('submit').bind('submit', function(e) {
				e.preventDefault();

				$.ajax({
					type : 'POST',
					url : '/StagingManagementSystem/addAssociate',
					data : $(this).serialize(),
					success : function(data) {
						console.log("SUCCESS: ", data);
						$('#addAssociate').modal('hide');

					},
					error : function(e) {
						console.log("ERROR: ", e);
					},
					done : function(e) {
						console.log("DONE");
					}
				});
			});
		});

		$(document).on("click", "#addClient", function() {

			$("#addClientForm").unbind('submit').bind('submit', function(e) {
				e.preventDefault();

				$.ajax({
					type : 'POST',
					url : '/StagingManagementSystem/addClient',
					data : $(this).serialize(),
					success : function(data) {
						console.log("SUCCESS: ", data);
						$('#addClient').modal('hide');

					},
					error : function(e) {
						console.log("ERROR: ", e);
					},
					done : function(e) {
						console.log("DONE");
					}
				});
			});
		});
		
		$(document).ready(function() {
		    $('#myTable').DataTable();
		} );
