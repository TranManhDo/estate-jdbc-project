package com.laptrinhjavaweb.api;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.service.impl.BuildingService;
import com.laptrinhjavaweb.utils.HttpUtil;

/**
 * Servlet implementation class BuildingAPI
 */
@WebServlet(urlPatterns = {"/api-admin-building"})
public class BuildingAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public IBuildingService buildingService;
	
	public BuildingAPI() {
		buildingService = new BuildingService();
	}
    
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		BuildingDTO buildingDTO = HttpUtil.of(req.getReader()).toModel(BuildingDTO.class);
		//BuildingEntity building = buildingService.findById(buildingDTO.getId());
		//List<BuildingEntity> listBuilding = buildingService.findAll(buildingDTO);
		
		mapper.writeValue(resp.getOutputStream(), buildingDTO);
	}

	
	/*protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		BuildingDTO buildingDTO = HttpUtil.of(request.getReader()).toModel(BuildingDTO.class);
		buildingDTO = buildingService.save(buildingDTO);
		mapper.writeValue(response.getOutputStream(), buildingDTO);
	}*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		BuildingDTO buildingDTO = HttpUtil.of(request.getReader()).toModel(BuildingDTO.class);
		buildingDTO = buildingService.save(buildingDTO);
		mapper.writeValue(response.getOutputStream(), buildingDTO);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		BuildingDTO buildingDTO = HttpUtil.of(req.getReader()).toModel(BuildingDTO.class);
		buildingDTO = buildingService.update(buildingDTO);
		mapper.writeValue(resp.getOutputStream(), buildingDTO);
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		BuildingDTO buildingDTO = HttpUtil.of(req.getReader()).toModel(BuildingDTO.class);
		buildingService.delete(buildingDTO.getId());
		mapper.writeValue(resp.getOutputStream(), buildingDTO);
	}

}
