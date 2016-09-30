package org.springframework.samples.petclinic.web;



import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.samples.petclinic.model.FileUpload;

@Component
@Scope("prototype")
public class FileUploadValidator implements Validator{

    public boolean supports(Class clazz) {
        //just validate the FileUpload instances
        return FileUpload.class.isAssignableFrom(clazz);

    }

	public void validate(Object target, Errors errors) {

		FileUpload uploadedfile= (FileUpload) target;
		System.out.println(uploadedfile);
		MultipartFile multipartFile = uploadedfile.getFile();

		String fileName = multipartFile.getOriginalFilename();

		if ("".equalsIgnoreCase(fileName.trim())) {
			errors.rejectValue("file", "required.fileUpload");
		}

		if (multipartFile.isEmpty()) {
			errors.rejectValue("file", "required.emptyFile");
		}
	}
} 
