<!-----------------------
	 Forecasting Available Java Associates
 ------------------------->
 <div ng-controller="infoTable">
<div id="ForecastAvailableJava" class="modal fade" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			
			<form ng-submit="SubmitAssociates()">
			
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title" style="text-align: center">
					Available Associates
				</h4>
			</div>
			<div class="modal-body">

					<div>
						<div class="form-group">
							<label>Search</label>
							<input type="text" ng-model="search" placeholder="search">
						</div>
						<table ng-table="vm.tableParams" show-filter="true" class="table table-striped">
							 <thead>
							 <tr>
							 	<th>Check</th>
							 	<th>Name</th>
							 	<th>EmpID</th>
							 	<th>Status</th>
							 	<th>StartDate</th>
							 	<th>EndDate</th>
							 	<th>Company</th>
							 </tr>
							 </thead>
							 <tbody>
								<tr ng-repeat="m in month |filter:search">
									<td><input ng-model="associateSelected" type="checkbox" ng-checked="exist(a)" ng-click="toggleSelection(a)" ng-true-value="{{m[9].associateID}}"></td>
									<td>{{m[9].associateName}}</td>
									<td>{{month[9].associateID}}</td>
									<td>{{month[9].status}}</td>
									<td>{{month[9].batch.startDate}}</td>
									<td>{{month[9].batch.endDate}}</td>
									<td>none</td>
								</tr>
							 </tbody>
						</table>
						<!-- This div below is just to check the data of the associate selected by showing on this div element -->
						 <div> List: {{associateSelected}}</div>
					</div>
			</div>
			<div class="modal-footer">
				<div class=pull-left >
					
				<br>
				<!--  The table below is the to how the dropdown button as well as the radio buttons are positioned -->
				<table>
				<tr>
					<td> <label> Select a Client:</label> </td>
					<td style="padding: 5px">
						<select ng-model="modifyStatus.clientName" class="form-control" id="sel1" name="clients" required>
		        				<option ng-repeat="t in clientList" value="{{t.name}}" selected>{{t.name}}</option>		
		      			</select>
      				</td>
      				<td style="padding: 20px" rowspan="2">
						<button type="submit">Ok</button>
					</td>
				</tr>
				
				</table>
				</div>
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>

			</div>
			</form>
		</div>

	</div>
</div>

