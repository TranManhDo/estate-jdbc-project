<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List Building</title>
</head>
<body>
	<div class="main-content">
		<div class="main-content-inner">
			<div class="breadcrumbs ace-save-state" id="breadcrumbs">
				<ul class="breadcrumb">
					<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">Home</a>
					</li>
					<li>List Building</li>
				</ul>
				<!-- /.breadcrumb -->
			</div>
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">

						<div class="widget-box table-filter">
							<div class="widget-header">
								<h4 class="widget-title">Search</h4>
								<div class="widget-toolbar">
									<a href="#" data-action="collapse"> <i
										class="ace-icon fa fa-chevron-up"></i>
									</a>
								</div>
							</div>
							<div class="widget-body">
								<div class="widget-main">
									<div class="form-horizontal">
										<div class="form-group">
											<div class="col-sm-6">
												<label>Name Building</label>
												<div class="fg-line">
													<input type="text" class="form-control input-sm" />
												</div>
											</div>
											<div class="col-sm-6">
												<label>Floor Area</label>
												<div class="fg-line">
													<input type="number" class="form-control input-sm" />
												</div>
											</div>
										</div>
										
										<div class="form-group">
											<div class="col-sm-4">
												<label>District</label>
												<div class="fg-line">
													<select class = "form-control" id = "sel1">
														<option>District 1</option>
														<option>District 2</option>
														<option>District 3</option>
													</select>
												</div>
											</div>
											<div class="col-sm-4">
												<label>Ward</label>
												<div class="fg-line">
													<input type="text" class="form-control input-sm" />
												</div>
											</div>
											<div class="col-sm-4">
												<label>Street</label>
												<div class="fg-line">
													<input type="text" class="form-control input-sm" />
												</div>
											</div>
										</div>
										
										<div class="form-group">
											<div class="col-sm-4">
												<label>Number of Basement</label>
												<div class="fg-line">
													<input type="text" class="form-control input-sm" />
												</div>
											</div>
											<div class="col-sm-4">
												<label>Direction</label>
												<div class="fg-line">
													<input type="text" class="form-control input-sm" />
												</div>
											</div>
											<div class="col-sm-4">
												<label>Level</label>
												<div class="fg-line">
													<input type="text" class="form-control input-sm" />
												</div>
											</div>
										</div>
										
										<div class="form-group">
											<div class="col-sm-3">
												<label>Area from</label>
												<div class="fg-line">
													<input type="number" class="form-control input-sm" />
												</div>
											</div>
											<div class="col-sm-3">
												<label>Area to</label>
												<div class="fg-line">
													<input type="number" class="form-control input-sm" />
												</div>
											</div>
											<div class="col-sm-3">
												<label>CostRent from</label>
												<div class="fg-line">
													<input type="text" class="form-control input-sm" />
												</div>
											</div>
											<div class="col-sm-3">
												<label>CostRent to</label>
												<div class="fg-line">
													<input type="text" class="form-control input-sm" />
												</div>
											</div>
										</div>
										
										<div class="form-group">
											<div class="col-sm-4">
												<label>Manager Name</label>
												<div class="fg-line">
													<input type="text" class="form-control input-sm" />
												</div>
											</div>
											<div class="col-sm-4">
												<label>Manager Phone</label>
												<div class="fg-line">
													<input type="number" class="form-control input-sm" />
												</div>
											</div>
											<div class="col-sm-4">
												<label>Choose Manager</label>
												<div class="fg-line">
													<select class = "form-control" id = "sel1">
														<option>Manager 1</option>
														<option>Manager 2</option>
														<option>Manager 3</option>
													</select>
												</div>
											</div>
										</div>
										
										<div class="form-group">
											<div class="col-sm-6">
												<div class="fg-line">
													<label class = "checkbox-inline"><input type ="checkbox" value ="">Option 1</label>
													<label class = "checkbox-inline"><input type ="checkbox" value ="">Option 2</label>
													<label class = "checkbox-inline"><input type ="checkbox" value ="">Option 3</label>
												</div>
											</div>
										</div>
										
										<button type="button">Search</button>
										
									</div>
								</div>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>