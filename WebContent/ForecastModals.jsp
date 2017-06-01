<!-----------------------
	 Forecasting Available Java Associates
 ------------------------->
 <div ng-controller="infoTable">
<div id="ForecastModal" class="modal fade" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			
			<form ng-submit="submitAssociates()">
			
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title" style="text-align: center">
					<span class="statusheader"></span> <span class="typeheader"></span> Associates
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
							 	<!-- 
							 		 uncomment once you get the company mapping to actually work 
							 		 currently not able to associate an associate to a client 
							 	-->
							 	<!-- <th>Company</th> -->
							 </tr>
							 </thead>
							 <tbody>
								<tr ng-repeat="m in associates |filter:search">
									<td><input ng-model="associateSelected" type="checkbox" ng-checked="exist(m)" ng-click="toggleSelection(m)" ng-true-value="{{m.associateID}}"></td>
									<td>{{m.associateName}}</td>
									<td>{{m.associateID}}</td>
									<td>{{m.status}}</td>
									<td>{{m.batch.startDate}}</td>
									<td>{{m.batch.endDate}}</td>
								<!-- 
							 		 uncomment once you get the company mapping to actually work 
							 		 currently not able to associate an associate to a client 
							 	-->									
							 	<!-- <td>none</td> -->
								</tr>
							 </tbody>
						</table>
						<!-- This div below is just to check the data of the associate selected by showing on this div element -->
					</div>
			</div>
			<div class="modal-footer">
				<div class=pull-left >
					
				<br>
				<!--  The table below is the to how the dropdown button as well as the radio buttons are positioned -->
				<table>
				<tr>
				<td>
				<label id="availableRadio" class="radio-inline">
						<input ng-model="modifyStatus.status" type="radio" value="Available" name="optradio" id="available"> 
						Make Available
					</label>
					<label id="confirmedRadio" class="radio-inline">
						<input ng-model="modifyStatus.status" type="radio" value="Confirmed" name="optradio" id="confirmed">
						Confirm for Client
					</label>
					<label id="mappedRadio" class="radio-inline">
						<input ng-model="modifyStatus.status" type="radio" value="Mapped" name="optradio" id="mapped">
						Map to Client
					</label>
				</td>
				</tr>
				<tr>
					<td style="padding: 5px">

					
						<select ng-model="modifyStatus.clientName" class="form-control" id="sel1" name="clients" required>
		        				<option ng-repeat="t in clientList" value="{{t.clientID}}" selected>{{t.name}}</option>	
	
		      			</select>
      				</td>
      				<td style="padding: 20px" rowspan="2">
						<button type="submit" class="btn btn-primary btn-xs">Change Status</button>

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

