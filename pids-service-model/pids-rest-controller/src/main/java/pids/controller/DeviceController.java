package pids.controller;

import java.net.ConnectException;
import javax.xml.soap.SOAPException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pids.device.service.CameraService;
import pids.data.DataCamera;

@RestController
@RequestMapping("/deviceservice")
public class DeviceController {
	@Autowired
	private CameraService service;
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = {MediaType.TEXT_PLAIN_VALUE})
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String getRTSPURL(@RequestBody DataCamera camera) throws ConnectException, SOAPException {
		return service.getRTSPURL(camera);
	}
	@RequestMapping(value = "/gotopreset/", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void gotoPreset(@RequestBody DataCamera camera, @RequestBody String preset) throws ConnectException, SOAPException {
		service.gotoPreset(camera, preset);
	}
}
