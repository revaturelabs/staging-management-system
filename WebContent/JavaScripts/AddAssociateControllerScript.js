
/*
 * JavaScript to hide and show radio buttons
 */
/* 
 * this hide the available radio button in the show associates modal
 * and show the mapped and confirmed radio button.
*/
$(document).on("click", "#available", function() {
	$("#availableRadio").hide();
	$("#mappedRadio").show();
	$("#confirmedRadio").show();
});
/* 
 * this hide the mapped radio button in the show associates modal
 * and show the available and confirmed radio button.
*/
$(document).on("click", "#mapped", function() {
	$("#availableRadio").show();
	$("#mappedRadio").hide();
	$("#confirmedRadio").show();
	
});
/* 
 * this hide the confirmed radio button in the show associates modal
 * and show the available and mapped radio button.
*/
$(document).on("click", "#confirmed", function() {
	$("#availableRadio").show();
	$("#mappedRadio").show();
	$("#confirmedRadio").hide();
	
});

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
						display(e);
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
						display(e);
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
		