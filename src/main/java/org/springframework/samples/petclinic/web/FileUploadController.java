package org.springframework.samples.petclinic.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.samples.petclinic.model.FileUpload;
import org.springframework.samples.petclinic.web.FileUploadValidator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

    private static final Logger logger =
    		LoggerFactory.getLogger(FileUploadController.class);

    
    @Autowired  
    FileUploadValidator fileValidator;

    public FileUploadValidator getFileValidator() {
		return fileValidator;
	}

	public void setFileValidator(FileUploadValidator fileValidator) {
		this.fileValidator = fileValidator;
	}

	@RequestMapping(value = "/fileUploadForm", method = RequestMethod.GET)
    public String displayForm() {
        return "FileUploadForm";
    }

	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
	public String save(@ModelAttribute("uploadForm") FileUpload uploadedfile, BindingResult result, Model map) throws IllegalStateException, IOException {
		System.out.println(uploadedfile);
		fileValidator.validate(uploadedfile, result);

		if (result.hasErrors()) {
			return "FileUploadForm";
		}
		MultipartFile multipartFile = uploadedfile.getFile();
		InputStreamReader inputStreamReader = new InputStreamReader(multipartFile.getInputStream());
		BufferedReader br = new BufferedReader(inputStreamReader);
		String line = "";
		while ((line = br.readLine()) != null) {
			String[] token = line.split(",");
			System.out.println("Question : " + token[0] + "\n" + "Option 1:"
					+ token[1] + "\n" + "Option 2:" + token[2]);
		}
		br.close();
		inputStreamReader.close();

		map.addAttribute("files", multipartFile.getOriginalFilename());
		return "FileUploadSuccess";
	}
}