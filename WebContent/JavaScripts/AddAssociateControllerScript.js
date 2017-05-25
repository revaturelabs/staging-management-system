
/*
 * JavaScript to hide and show radio buttons
 */
/* 
 * this hide the available radio button in the show associates modal
 * and show the mapped and confirmed radio button.
*/
$(document).on("click", "#Available", function() {
	$("#availableRadio").hide();
	$("#mappedRadio").show();
	$("#confirmedRadio").hide();
});
/* 
 * this hide the mapped radio button in the show associates modal
 * and show the available and confirmed radio button.
*/
$(document).on("click", "#Mapped", function() {
	$("#availableRadio").show();
	$("#mappedRadio").hide();
	$("#confirmedRadio").show();
	
});
/* 
 * this hide the confirmed radio button in the show associates modal
 * and show the available and mapped radio button.
*/
$(document).on("click", "#Confirmed", function() {
	$("#availableRadio").show();
	$("#mappedRadio").hide();
	$("#confirmedRadio").hide();
	
});

/*
 * post a month on click to the controller
 */


// submit the add batch form
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
			},
			done : function(e) {
				console.log("DONE");
			}
		});
	});
});

// submit the add associate form
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

		// submit the add client form
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
		
		// do datatable things
		$(document).ready(function() {
		    $('#myTable').DataTable();
		} );
