package com.export.exportfilecsv.services;

import com.export.exportfilecsv.models.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

public interface UserService {
    List<User> findAll();
    void addUser(User user);

    List<User>  uploadFile(MultipartFile file, HttpServletResponse response) throws Exception;


}
