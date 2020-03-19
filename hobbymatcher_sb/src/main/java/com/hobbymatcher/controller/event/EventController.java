package com.hobbymatcher.controller.event;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.hobbymatcher.entity.Event;
import com.hobbymatcher.service.EventService;
import com.hobbymatcher.util.FileUtil;

@CrossOrigin
@SessionAttributes()
@Controller
@RequestMapping("/event")
public class EventController {

	private final EventService eventService;

	@Autowired
	public EventController(EventService eventsService) {
		this.eventService = eventsService;
	}

	@RequestMapping(value = "/listevent", method = RequestMethod.GET)
	@ResponseBody
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

	@RequestMapping(value = "/addevent", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addEvent(HttpServletResponse response, //
			@ModelAttribute Event event, @RequestPart("file") MultipartFile imageFile) {
		Map<String, Object> resp = new HashMap<String, Object>();
		if (event != null) {
			event.setPhotoId(FileUtil.transferFile(imageFile));
			Boolean inserted = eventService.insertEvent(event);
			resp.put("success", inserted);
			response.setStatus(inserted ? 200 : 400);
			return resp;
		} else {
			resp.put("success", false);
			response.setStatus(400);
			return resp;
		}
	}

	// getevent
	@RequestMapping(value = "/getevent", method = RequestMethod.GET)
	@ResponseBody
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

//	@RequestMapping(value = "/joinevent", method = RequestMethod.POST)
//	@ResponseBody
//	public Map<String, Object> joinEvents(String id, @RequestParam(value = "events_id") String eventsId,
//			HttpServletResponse response, HttpServletRequest request) {
//		Map<String, Object> modelMap = new HashMap<String, Object>();
//		try {
//			List<Event> list = new ArrayList<Event>();
//			List<String> num = new ArrayList<>();
//			list = eventsService.getEventsForUser(id);
//			for (int i = 0; i < list.size(); i++) {
//				num.add(list.get(i).getEventsId());
//			}
//			if (num.contains(eventsId)) {
//				modelMap.put("msg", "User already join this event");
//				modelMap.put("status", false);
//				response.setStatus(400);
//			} else {
//				Boolean result = eventsService.joinEvents(id, eventsId);
//				modelMap.put("status", result);
//				response.setStatus(result ? 200 : 400);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			modelMap.put("msg", "valueError");
//			modelMap.put("status", false);
//			response.setStatus(400);
//		}
//		return modelMap;
//	}
//
//	@RequestMapping(value = "/findpastevent", method = RequestMethod.GET)
//	@ResponseBody
//	public Map<String, Object> findPastEvents(String id, HttpServletResponse response, HttpServletRequest request) {
//		Map<String, Object> modelMap = new HashMap<String, Object>();
//		List<Event> list = new ArrayList<Event>();
//		try {
//			// User user = (User) request.getSession().getAttribute("user");
//			// String userId = user.getId();
//			LocalDateTime timeStamp = LocalDateTime.now();
//			list = eventsService.findPastEvents(id, timeStamp);
//			modelMap.put("list", list);
//			response.setStatus(list == null ? 400 : 200);
//		} catch (Exception e) {
//			e.printStackTrace();
//			modelMap.put("msg", "valueError");
//			modelMap.put("status", false);
//			response.setStatus(400);
//		}
//		return modelMap;
//	}
//
//	@RequestMapping(value = "/findupcomingevents", method = RequestMethod.GET)
//	@ResponseBody
//	public Map<String, Object> findUpcomingEvents(String id, HttpServletResponse response, HttpServletRequest request) {
//		Map<String, Object> modelMap = new HashMap<String, Object>();
//		List<Event> list = new ArrayList<Event>();
//		try {
//			// User user = (User) request.getSession().getAttribute("user");
//			// String userId = user.getId();
//			LocalDateTime timeStamp = LocalDateTime.now();
//			list = eventsService.findUpcomingEvents(id, timeStamp);
//			modelMap.put("events", list);
//		} catch (Exception e) {
//			System.out.println(e.toString());
//			modelMap.put("msg", "valueError");
//			modelMap.put("status", false);
//			response.setStatus(400);
//		}
//		return modelMap;
//	}
//
//	@RequestMapping(value = "/geteventsforuser", method = RequestMethod.GET)
//	@ResponseBody
//	public Map<String, Object> getEventsForUser(String id, HttpServletResponse response, HttpServletRequest request) {
//		Map<String, Object> modelMap = new HashMap<String, Object>();
//		List<Event> list = new ArrayList<Event>();
//		try {
//			// User user = (User) request.getSession().getAttribute("user");
//			// String userId = user.getId();
//			list = eventsService.getEventsForUser(id);
//			modelMap.put("events", list);
//		} catch (Exception e) {
//			System.out.println(e.toString());
//			modelMap.put("msg", "valueError");
//			modelMap.put("status", false);
//			response.setStatus(400);
//		}
//		return modelMap;
//	}
//
//	@RequestMapping(value = "/getevents", method = RequestMethod.GET)
//	@ResponseBody
//	public Map<String, Object> getEvents(@RequestParam(value = "events_id") String id, HttpServletResponse response,
//			HttpServletRequest request) {
//		Map<String, Object> modelMap = new HashMap<String, Object>();
//		try {
//			int id1 = Integer.parseInt(id);
//			System.out.println(id1);
//			Event eventsById = eventsService.findEventsById(id1);
//			int number = eventsService.getNumber(id1);
//			modelMap.put("events", eventsById);
//			modelMap.put("numberOfUser", number);
//			response.setStatus(eventsById == null ? 400 : 200);
//		} catch (Exception e) {
//			System.out.println(e.toString());
//			modelMap.put("msg", "valueError");
//			modelMap.put("status", false);
//			response.setStatus(400);
//		}
//		return modelMap;
//	}
//
//	@RequestMapping(value = "/deleteevents", method = RequestMethod.POST)
//	@ResponseBody
//	public Map<String, Object> deleteEvents(@RequestBody Event events, HttpServletResponse response) {
//		Map<String, Object> modelMap = new HashMap<String, Object>();
//		if (events != null) {
//			Boolean result = eventsService.deleteEvents(events.getEventsId());
//			modelMap.put("status", result);
//			response.setStatus(result ? 200 : 400);
//			return modelMap;
//		} else {
//			modelMap.put("status", false);
//			response.setStatus(400);
//			return modelMap;
//		}
//	}
}
