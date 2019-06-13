<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Building</title>

</head>
<body>
	<div class="main-content">
		<div class="main-content-inner">
			<div class="breadcrumbs ace-save-state" id="breadcrumbs">
				<ul class="breadcrumb">
					<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">Home</a>
					</li>
					<li>Add Building</li>
				</ul>
				<!-- /.breadcrumb -->
			</div>
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">

						<div class="form-group">
							<div class="col-sm-3">
								<label>Building Name</label>

							</div>
							<div class="col-sm-9">
								<input type="text" class="form-control input-sm" />
							</div>
						</div>
						<br>
						<div class="form-group">
							<div class="col-sm-3">
								<label>Number of Basement</label>

							</div>
							<div class="col-sm-3">
								<input type="number" class="form-control input-sm" />
							</div>
							<div class="col-sm-2">
								<label>District</label>

							</div>
							<div class="col-sm-4">
								<select class="form-control" id="sel1">
									<option>District 1</option>
									<option>District 2</option>
									<option>District 3</option>
								</select>
							</div>
						</div>

						<br>
						<div class="form-group">
							<div class="col-sm-3">
								<label>Building Area</label>

							</div>
							<div class="col-sm-3">
								<input type="number" class="form-control input-sm" />
							</div>
						</div>

						<br>
						<div class="form-group">
							<div class="col-sm-3">
								<label>Ward</label>

							</div>
							<div class="col-sm-3">
								<input type="text" class="form-control input-sm" />
							</div>
							<div class="col-sm-2">
								<label>Street</label>

							</div>
							<div class="col-sm-4">
								<input type="text" class="form-control input-sm" />
							</div>
						</div>

						<br>
						<div class="form-group">
							<div class="col-sm-3">
								<label>Structure</label>

							</div>
							<div class="col-sm-9">
								<input type="text" class="form-control input-sm" />
							</div>
						</div>

						<br>
						<div class="form-group">
							<div class="col-sm-3">
								<label>Cost Rent</label>

							</div>
							<div class="col-sm-3">
								<input type="number" class="form-control input-sm" />
							</div>

							<div class="col-sm-2">
								<label>Cost Description</label>

							</div>
							<div class="col-sm-4">
								<input type="text" class="form-control input-sm" />
							</div>
						</div>

						<br>
						<div class="form-group">
							<div class="col-sm-3">
								<label>Service cost</label>

							</div>
							<div class="col-sm-3">
								<input type="number" class="form-control input-sm" />
							</div>

							<div class="col-sm-2">
								<label>Car cost</label>

							</div>
							<div class="col-sm-4">
								<input type="number" class="form-control input-sm" />
							</div>
						</div>

						<br>
						<div class="form-group">
							<div class="col-sm-3">
								<label>Motorbike cost</label>

							</div>
							<div class="col-sm-3">
								<input type="number" class="form-control input-sm" />
							</div>

							<div class="col-sm-2">
								<label>Overtime cost</label>

							</div>
							<div class="col-sm-4">
								<input type="number" class="form-control input-sm" />
							</div>
						</div>

						<br>
						<div class="form-group">
							<div class="col-sm-3">
								<label>Electric City cost</label>

							</div>
							<div class="col-sm-3">
								<input type="number" class="form-control input-sm" />
							</div>

						</div>

						<br>
						<div class="form-group">
							<div class="col-sm-3">
								<label>Deposit</label>

							</div>
							<div class="col-sm-3">
								<input type="number" class="form-control input-sm" />
							</div>

							<div class="col-sm-2">
								<label>Payment</label>

							</div>
							<div class="col-sm-4">
								<input type="number" class="form-control input-sm" />
							</div>
						</div>

						<br>
						<div class="form-group">
							<div class="col-sm-3">
								<label>Time contract</label>

							</div>
							<div class="col-sm-3">
								<input type="number" class="form-control input-sm" />
							</div>

							<div class="col-sm-2">
								<label>Time decorator</label>

							</div>
							<div class="col-sm-4">
								<input type="text" class="form-control input-sm" />
							</div>
						</div>

						<br>
						<div class="form-group">
							<div class="col-sm-3">
								<label>Service cost</label>

							</div>
							<div class="col-sm-3">
								<input type="number" class="form-control input-sm" />
							</div>

							<div class="col-sm-2">
								<label>Car cost</label>

							</div>
							<div class="col-sm-4">
								<input type="number" class="form-control input-sm" />
							</div>
						</div>

						<br>
						<div class="form-group">
							<div class="col-sm-3">
								<label>Manager</label>

							</div>
							<div class="col-sm-3">
								<select class="form-control" id="sel1">
									<option>Manager 1</option>
									<option>Manager 2</option>
									<option>Manager 3</option>
								</select>
							</div>
						</div>

						<br> <br>
						<div class="form-group">
							<div class="col-sm-3">
								<label>Image</label>

							</div>
							<div class="col-sm-6">
								<img id="uploadPreview" style="width: 100px; height: 100px;" />
								<input id="uploadImage" type="file" name="myPhoto"
									onchange="PreviewImage();" />
								<script type="text/javascript">
									function PreviewImage() {
										var oFReader = new FileReader();
										oFReader
												.readAsDataURL(document
														.getElementById("uploadImage").files[0]);

										oFReader.onload = function(oFREvent) {
											document
													.getElementById("uploadPreview").src = oFREvent.target.result;
										};
									};
								</script>

							</div>
							<div class="col-sm-3">
								<button type="button">Add</button>
							</div>
						</div>
						<br>


					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>