package com.student.vision.controller;

import com.github.pagehelper.Page;
import com.student.vision.model.BaseQuery;
import com.student.vision.model.BaseResp;
import com.student.vision.model.Student;
import com.student.vision.model.Vision;
import com.student.vision.service.StudentService;
import com.student.vision.service.VisionService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/vision")
public class VisionController {

    @Resource
    private VisionService visionService;

    @PostMapping("/insert")
    @ResponseBody
    public BaseResp<Vision> insert(@RequestBody Vision vision){
        boolean flag;
        if(vision.getId()!=null){
            flag=visionService.update(vision);
        }else {
            flag = visionService.insert(vision);
        }

        if(!flag){
            return BaseResp.fail("添加失败");
        }
        return new BaseResp<>(vision);
    }

//    @RequestMapping("/update")
//    @ResponseBody
//    public BaseResp<Student> update(@RequestBody Student student){
//
//        boolean flag=studentService.update(student);
//        if(!flag){
//            return BaseResp.fail("更新失败");
//        }
//        return new BaseResp<>(student);
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public BaseResp handler(MethodArgumentNotValidException exception){
//        String message =  exception.getBindingResult().getFieldError().getDefaultMessage();
//        return BaseResp.fail(message);
//    }


}
