package com.hobbymatcher.controller.event;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.hobbymatcher.entity.Event;
import com.hobbymatcher.entity.Participation;
import com.hobbymatcher.service.EventService;
import com.hobbymatcher.service.impl.AuthUtilService;
import com.hobbymatcher.util.FileUtil;

@CrossOrigin
@SessionAttributes()
@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private AuthUtilService authUtilService;

    // list event
    @GetMapping("/listevent")
    public Map<String, Object> listEvent(String hobbyId, HttpServletResponse response) {
        Map<String, Object> resp = new HashMap<String, Object>();
        try {
            if (hobbyId != null && !hobbyId.isEmpty()) {
                resp.put("list", eventService.listEventByHobbyId(Integer.parseInt(hobbyId)));
            } else {
                resp.put("list", eventService.listEvent());
            }
            resp.put("success", true);
            response.setStatus(200);
        } catch (Exception exp) {
            exp.printStackTrace();
            resp.put("status", false);
            response.setStatus(400);
        }
        return resp;
    }

    // add event
    @PostMapping("/addevent")
    public Map<String, Object> addEvent(HttpServletRequest request, HttpServletResponse response, //
                                        @ModelAttribute Event event, @RequestPart(name = "file", required = true) MultipartFile imageFile) {
        Map<String, Object> resp = new HashMap<String, Object>();
        try {
            event.setPhotoId(FileUtil.transferFile(imageFile));
            event.setCreatedById(authUtilService.getUserId(request));
            Boolean inserted = eventService.insertEvent(event);
            resp.put("success", inserted);
            response.setStatus(inserted ? 200 : 400);
        } catch (Exception exp) {
            exp.printStackTrace();
            resp.put("success", false);
            response.setStatus(400);
        }
        return resp;
    }

    // get event by id
    @GetMapping("/getevent")
    public Map<String, Object> getEvent(String id, HttpServletResponse response) {
        Map<String, Object> resp = new HashMap<String, Object>();
        try {
            Event event = eventService.findEventById(Integer.parseInt(id));
            if (event == null) {
                response.setStatus(404);
            } else {
                resp.put("event", event);
                resp.put("success", true);
                response.setStatus(200);
            }
        } catch (Exception exp) {
            exp.printStackTrace();
            resp.put("success", false);
            response.setStatus(400);
        }
        return resp;
    }

	@PostMapping("/editevent")
	public Map<String, Object> editEvent(HttpServletRequest request, HttpServletResponse response, //
			@ModelAttribute Event event, @RequestPart(name = "file", required = false) MultipartFile imageFile) {
		Map<String, Object> resp = new HashMap<String, Object>();
		try {
			if (imageFile != null && !imageFile.isEmpty()) {
				event.setPhotoId(FileUtil.transferFile(imageFile));
			}
			Boolean updated = eventService.updateEvent(event);
			resp.put("success", updated);
			response.setStatus(updated ? 200 : 400);
		} catch (Exception exp) {
			exp.printStackTrace();
			resp.put("success", false);
			response.setStatus(400);
		}
		return resp;
	}

	@GetMapping("/getparticipation")
	public Map<String, Object> getParticipation(HttpServletRequest request, HttpServletResponse response, //
			@RequestParam("id") Integer eventId) {
		Map<String, Object> resp = new HashMap<String, Object>();
		try {
			int userId = authUtilService.getUserId(request);
			Participation part = eventService.getParticipation(userId, eventId);
			if (part == null) {
				resp.put("status", "");
			} else if (part.getParticipatedOnDatetime() != null) {
				resp.put("status", "participated");
			} else if (part.getApprovedOnDatetime() != null) {
				resp.put("status", "approved");
			} else if (part.getRequestedOnDatetime() != null) {
				resp.put("status", "requested");
			}
			resp.put("success", true);
			response.setStatus(200);
		} catch (Exception exp) {
			exp.printStackTrace();
			resp.put("success", false);
			response.setStatus(400);
		}
		return resp;
	}

	@PostMapping("/participation")
	public Map<String, Object> manageParticipation(HttpServletRequest request, HttpServletResponse response, //
			@RequestParam("id") Integer eventId, @RequestParam("action") String action,
			@RequestParam(name = "userId", required = false) Integer theUserId) {
		Map<String, Object> resp = new HashMap<String, Object>();
		try {
			int userId = authUtilService.getUserId(request);
			Participation part = eventService.getParticipation(userId, eventId);

			if ("request".equals(action)) {
				if (part == null) {
					eventService.requestToParticipateEvent(userId, eventId);
				}
				resp.put("status", "requested");
			} else if ("cancel".equals(action)) {
				if (part != null) {
					eventService.cancelEventParticipationRequest(userId, eventId);
				}
				resp.put("status", "");
			} else if ("participated".equals(action)) {
				if (part != null) {
					eventService.markEventAsParticipated(userId, eventId);
				}
				resp.put("status", "participated");
			} else if ("approve".equals(action)) {
				if (part != null) {
					eventService.approveUser(theUserId, eventId);
				}
				resp.put("status", "approved");
			}

			resp.put("success", true);
			response.setStatus(200);
		} catch (Exception exp) {
			exp.printStackTrace();
			resp.put("success", false);
			response.setStatus(400);
		}
		return resp;
	}
}
