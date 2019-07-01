<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="buildingURL" value="/admin-building?action=LIST" />
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
						<form action="${buildingURL}" method="get">
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
														<input type="text" class="form-control input-sm"
															name="name" value="${model.name}" />
													</div>
												</div>
												<div class="col-sm-6">
													<label>Building Area</label>
													<div class="fg-line">
														<input type="number" class="form-control input-sm"
															name="buildingArea" value="${model.buildingArea}" />
													</div>
												</div>
											</div>

											<div class="form-group">
												<div class="col-sm-4">
													<label>District</label>
													<div class="fg-line">
														<select class="form-control" id="sel1" name="district">
															<option value="">Choose District</option>
															<c:forEach var="item" items="${districts}">
																<option value="${item.key}"${item.key == model.district ? 'selected' : '' }>${item.value}</option>
															</c:forEach>
														</select>
													</div>
												</div>
												<div class="col-sm-4">
													<label>Ward</label>
													<div class="fg-line">
														<input type="text" class="form-control input-sm"
															name="ward" value="${model.ward}" />
													</div>
												</div>
												<div class="col-sm-4">
													<label>Street</label>
													<div class="fg-line">
														<input type="text" class="form-control input-sm"
															name="street" value="${model.street}" />
													</div>
												</div>
											</div>

											<div class="form-group">
												<div class="col-sm-4">
													<label>Number of Basement</label>
													<div class="fg-line">
														<input type="text" class="form-control input-sm"
															name="numberOfBasement" value="${model.numberOfBasement}" />
													</div>
												</div>
												<div class="col-sm-4">
													<label>Direction</label>
													<div class="fg-line">
														<input type="text" class="form-control input-sm"
															name="direction" value="${model.direction}" />
													</div>
												</div>
												<div class="col-sm-4">
													<label>Level</label>
													<div class="fg-line">
														<input type="text" class="form-control input-sm"
															name="level" value="${model.level}" />
													</div>
												</div>
											</div>

											<div class="form-group">
												<div class="col-sm-3">
													<label>Area from</label>
													<div class="fg-line">
														<input type="text" class="form-control input-sm"
															name="areaRentFrom" value="${model.areaRentFrom}" />
													</div>
												</div>
												<div class="col-sm-3">
													<label>Area to</label>
													<div class="fg-line">
														<input type="text" class="form-control input-sm"
															name="areaRentTo" value="${model.areaRentTo}" />
													</div>
												</div>
												<div class="col-sm-3">
													<label>CostRent from</label>
													<div class="fg-line">
														<input type="number" class="form-control input-sm"
															name="costRentFrom" value="${model.costRentFrom}" />
													</div>
												</div>
												<div class="col-sm-3">
													<label>CostRent to</label>
													<div class="fg-line">
														<input type="number" class="form-control input-sm"
															name="costRentTo" value="${model.costRentTo}" />
													</div>
												</div>
											</div>

											<div class="form-group">
												<div class="col-sm-4">
													<label>Manager Name</label>
													<div class="fg-line">
														<input type="text" class="form-control input-sm"
															name="managerName" value="${model.managerName}" />
													</div>
												</div>
												<div class="col-sm-4">
													<label>Manager Phone</label>
													<div class="fg-line">
														<input type="number" class="form-control input-sm"
															name="managerPhone" value="${model.managerPhone}" />
													</div>
												</div>
												<div class="col-sm-4">
													<label>Choose Manager</label>
													<div class="fg-line">
														<select class="form-control" id="sel1">
															<option>Manager 1</option>
															<option>Manager 2</option>
															<option>Manager 3</option>
														</select>
													</div>
												</div>
											</div>

											<div class="form-group">
												<div class="col-sm-12">
													<div class="fg-line">
														<c:forEach var="item" items="${buildingTypes}">
															<label class="checkbox-inline"> <input
																type="checkbox" value="${item.key}" name="buildingTypes" ${fn:contains(fn: join(model.buildingTypes,','),item.key) ? 'checked' : '' }>${item.value}</label>
														</c:forEach>
													</div>
												</div>
											</div>
											<input type="hidden" name="action" value="LIST" />
											<div class="form-group">
												<div class="col-sm-6">
													<button type="submit" class="btn btn-sm btn-success">
														Search<i
															class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
													</button>
												</div>
											</div>

										</div>
									</div>
								</div>
							</div>
						</form>
						<!-- button add, delete -->
						<div class="table-btn-controls">
							<div class="pull-right tableTools-container">
								<div class="dt-buttons btn-overlap btn-group">
									<a flag="info"
										class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
										data-toggle="tooltip" title='Add building'
										href='<c:url value="/admin-building?action=EDIT"/>'> <span><i
											class="fa fa-plus-circle bigger-110 purple"></i></span>
									</a>
									<button type="button"
										class="dt-button buttons-html5 btn btn-white btn-primary btn-bold"
										data-toggle="tooltip" title='Delete building'>
										<span><i class="fa fa-trash-o bigger-110 pink"></i></span>
									</button>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- table -->
				<div class="row">
					<div class="col-xs-12">
						<table class="table table-condensed">
							<thead>
								<tr>
									<th>Building Name</th>
									<th>Address</th>
									<th>Building Area</th>
									<th>Number of basement</th>
									<th>District</th>
									<th>Cost Rent</th>
									<th>Rent Area</th>
									<th>Direction</th>
									<th>Level</th>
									<th>Manager Name</th>
									<th>Manager Phone</th>
									<th>Building Types</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="item" items="${model.listResults}">
									<tr>
										<td>${item.name}</td>
										<td>${item.address}</td>
										<td>${item.buildingArea}</td>
										<td>${item.numberOfBasement}</td>
										<td>${item.district}</td>
										<td>${item.costRent}</td>
										<td>${item.rentArea}</td>
										<td>${item.direction}</td>
										<td>${item.level}</td>
										<td>${item.managerName}</td>
										<td>${item.managerPhone}</td>
										<td>${item.type}</td>
									</tr>
								</c:forEach>

							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>