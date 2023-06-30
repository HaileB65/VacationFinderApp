package Capstone.Project.VacationFinderApp.controllers;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadController {

    @GetMapping("/uploadFile")
    public String uploadFile(){
        return "upload-file-page";
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public String submit(@RequestParam("file") MultipartFile file, ModelMap modelMap) {
        modelMap.addAttribute("file", file);
        return "fileUploadView";
    }


}
