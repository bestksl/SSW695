package com.hobbymatcher.controller.file;

import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.hobbymatcher.util.FileUtil;

@CrossOrigin
@SessionAttributes()
@Controller
@RequestMapping("/files")
public class FileController {

	@RequestMapping(value = "/{filename:.+}", method = RequestMethod.GET)
	@ResponseBody
	public Object getHobby(@PathVariable String filename) throws FileNotFoundException {
		File file = new File(FileUtil.getUploadFolder(), filename);

		if (file.exists()) {
			String header = "attachment; filename=\"" + FileUtil.getName(file) + "\"";
			return ok().header(CONTENT_DISPOSITION, header).body(new FileSystemResource(file));
		} else {
			return notFound().build();
		}
	}

}