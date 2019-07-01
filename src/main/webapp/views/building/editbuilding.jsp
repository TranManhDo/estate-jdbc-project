<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="/common/taglib.jsp"%>
<c:url var ="buildingAPI" value = "/api-admin-building"/>
<c:url var="buildingURL" value="/admin-building" />
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Building</title>

</head>
<body>
	<div class="main-content">
		<div class="main-content-inner">
			<div class="breadcrumbs ace-save-state" id="breadcrumbs">
				<ul class="breadcrumb">
					<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">Home</a>
					</li>
					<li>Edit Building</li>
				</ul>
				<!-- /.breadcrumb -->
			</div>
			<div class="page-content">
				<form action="" id="formEdit">
					<div class="row">
						<div class="col-xs-12">

							<div class="form-group">
								<div class="col-sm-3">
									<label>Building Name</label>

								</div>
								<div class="col-sm-9">
									<input type="text" class="form-control input-sm" name="name"
										value="${model.name}" />
								</div>
							</div>
							<br>
							<div class="form-group">
								<div class="col-sm-3">
									<label>Number of Basement</label>

								</div>
								<div class="col-sm-9">
									<input type="number" class="form-control input-sm"
										name="numberOfBasement" value="${model.numberOfBasement}" />
								</div>

							</div>
							</br>
							<div class="form-group">
								<div class="col-sm-3">
									<label>District</label>
								</div>
								<div class="col-sm-3">
									<select class="form-control" id="sel1" name="district">
										<option value="">Choose District</option>
										<c:forEach var="item" items="${districts}">
											<option value="${item.key}"
												${item.key == model.district ? 'selected' : '' }>${item.value}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<br>
							<div class="form-group">
								<div class="col-sm-3">
									<label>Building Area</label>

								</div>
								<div class="col-sm-3">
									<input type="number" class="form-control input-sm"
										name="buildingArea" value="${model.buildingArea}" />
								</div>
							</div>

							<br>
							<div class="form-group">
								<div class="col-sm-3">
									<label>Ward</label>

								</div>
								<div class="col-sm-3">
									<input type="text" class="form-control input-sm" name="ward"
										value="${model.ward}" />
								</div>
								<div class="col-sm-2">
									<label>Street</label>

								</div>
								<div class="col-sm-4">
									<input type="text" class="form-control input-sm" name="street"
										value="${model.street}" />
								</div>
							</div>

							<br>
							<div class="form-group">
								<div class="col-sm-3">
									<label>RentArea</label>

								</div>
								<div class="col-sm-9">
									<input type="text" class="form-control input-sm"
										name="rentArea" value="${model.rentArea}" />
								</div>
							</div>

							<br>
							<div class="form-group">
								<div class="col-sm-3">
									<label>Cost Rent</label>

								</div>
								<div class="col-sm-3">
									<input type="number" class="form-control input-sm"
										name="costRent" value="${model.costRent}" />
								</div>

							</div>

							<br>

							<div class="form-group">
								<div class="col-sm-3">
									<label>Manager Name</label>

								</div>
								<div class="col-sm-3">
									<input type="text" class="form-control input-sm"
										name="managerName" value="${model.managerName}" />
								</div>

								<div class="col-sm-2">
									<label>Manager Phone</label>

								</div>
								<div class="col-sm-4">
									<input type="text" class="form-control input-sm"
										name="managerPhone" value="${model.managerPhone}" />
								</div>
							</div>

							<br> <br>
							<div class="form-group">
								<div class="col-sm-3">
									<label>Type</label>
								</div>
								<div class="col-sm-9">
									<c:forEach var="item" items="${buildingTypes}">
										<label class="checkbox-inline"> <input type="checkbox"
											value="${item.key}" name="buildingTypes"
											${fn:contains(fn: join(model.buildingTypes,','),item.key) ? 'checked' : '' }>${item.value}</label>
									</c:forEach>
								</div>
							</div>
							<br> <br>
							<!-- 							<div class="form-group"> -->
							<!-- 								<div class="col-sm-3"> -->
							<!-- 									<label>Image</label> -->

							<!-- 								</div> -->
							<!-- 								<div class="col-sm-6"> -->
							<!-- 									<img id="uploadPreview" style="width: 100px; height: 100px;" /> -->
							<!-- 									<input id="uploadImage" type="file" name="myPhoto" -->
							<!-- 										onchange="PreviewImage();" /> -->
							<!-- 									<script type="text/javascript"> -->
							<!-- 							// function PreviewImage() { // var oFReader = new FileReader(); -->
							<!-- 							// oFReader // .readAsDataURL(document // -->
							<!-- 							.getElementById("uploadImage").files[0]); // oFReader.onload = -->
							<!-- 							function(oFREvent) { // document // -->
							<!-- 							.getElementById("uploadPreview").src = oFREvent.target.result; // -->
							<!-- 							}; // }; -->
							<!-- 									</script> -->

							<!-- 								</div> -->

							<!-- 							</div> -->
							<!-- 							<br> -->


						</div>
					</div>
					<input type="hidden" name="id" value="${model.id}" id="buildingId" />
				</form>
				<div class="form-group">
					<c:if test="${empty model.id}">
						<button type="submit" class="btn btn-sm btn-success"
							id="btnAddOrUpdateBuilding">
							Add Building<i
								class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
						</button>
					</c:if>
					<c:if test="${not empty model.id}">
						<button type="submit" class="btn btn-sm btn-success"
							id="btnAddOrUpdateBuilding">
							Edit Building<i
								class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
						</button>
					</c:if>

				</div>
			</div>

		</div>
	</div>
	<script type="text/javascript">
		$("#btnAddOrUpdateBuilding").click(function() {
			addOrUpdateBuilding();
		});
		
		function addOrUpdateBuilding(){
			var buildingId = $('#buildingId').val();
			var formData =$('#formEdit').serializeArray();
			var data ={};
			var buildingTypes =[];
			$.each(formData,function(index,v){
				if(v.name == 'buildingTypes'){
					buildingTypes.push(v.value);
				}else {
					data[""+v.name+""] = v.value;
				}
			  });
			data['buildingTypes'] = buildingTypes;
			if(buildingId==''){
				addBuilding(data);
			}else {
				editBuilding(data,buildingId);
			}
		}
		function addBuilding(data){
			$.ajax({
				url: '${buildingAPI}',
				data: JSON.stringify(data),
				type: 'POST',
				contentType: 'application/json',
				dataType: 'json',
				success: function(data) {
					window.location.href = "${buildingURL}?action=EDIT&id="+data.id+"&message=insert_success";
				},
				error: function() {
					window.location.href = "${buildingURL}?action=LIST&message=error_system";
				}
				
				});
		}
		function editBuilding(data,id){
			
		}
	</script>
</body>
</html>