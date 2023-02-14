package com.export.exportfilecsv.controllers;

import com.export.exportfilecsv.models.User;
import com.export.exportfilecsv.services.UserService;
import com.export.exportfilecsv.utils.OpenCsvExporter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;


@RestController
@AllArgsConstructor
@RequestMapping("opencsv")
public class UserController {

    final private UserService userService;
    final private OpenCsvExporter<User> exporter;


    @GetMapping("/export")
    public void exportUsingOpenCsv(HttpServletResponse response) throws Exception{
        String fileName = "user.csv";
        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"");
        exporter.export(userService.findAll(), response.getWriter());
        response.getWriter().close();
    }

    @PostMapping("/upload-csv-file")
    public void uploadCSVFile(@RequestParam("file") MultipartFile file, HttpServletResponse response) throws Exception{
//        String fileName = "user-report.csv";
//        response.setContentType("text/csv");
//        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"");
//        exporter.export(userService.uploadFile(file, ), response.getWriter());
//        response.getWriter().close();

        userService.uploadFile(file, response);
    }


}
