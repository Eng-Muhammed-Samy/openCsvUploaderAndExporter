package com.export.exportfilecsv.services.impl;

import com.export.exportfilecsv.converter.CsvConverter;
import com.export.exportfilecsv.mapper.UserMapper;
import com.export.exportfilecsv.models.PairResult;
import com.export.exportfilecsv.models.User;
import com.export.exportfilecsv.repository.UserRepo;
import com.export.exportfilecsv.services.UserService;
import com.export.exportfilecsv.utils.*;
import com.opencsv.exceptions.CsvException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

   @Autowired
   private UserRepo userRepo;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CSVHandler<User> uploader;

    @Autowired private OpenCsvExporter<User> exporter;
    @Autowired private CsvConverter<User> converter;


    @Override
    public List<User> findAll() {
        return userRepo.findAll().stream().map(userMapper::toModel).collect(Collectors.toList());
    }

    @Override
    public void addUser(User user) {
        userRepo.save(userMapper.toEntity(user));
    }

    @Override
    public List<User> uploadFile(MultipartFile file, HttpServletResponse response) throws Exception {
        PairResult<User, CsvException> result = uploader.getValidData(file, User.class);
        List<User> users = result.getSuccessRows();
        List<CsvException> exceptions = result.getFailureRows();
        List<User> invalid =  exceptions.stream().map(exception -> converter.convert(exception.getLine())).collect(Collectors.toList());
        System.out.println("--------------------------------");
        List<User> invalidUsers = getInvalidData(exceptions, invalid);
        exporter.export(uploader.wrapper(users, invalidUsers), response.getWriter());
        return  null;
    }

    private List<User> getInvalidData(List<CsvException> exceptions, List<User> invalid){
        final int[] i = {0};
        return invalid.stream().map(user -> {
        user.setIsValid(Boolean.FALSE);
        user.setRaison(exceptions.get(i[0]++).getMessage());
            return user;
        }).collect(Collectors.toList());
    }

}
