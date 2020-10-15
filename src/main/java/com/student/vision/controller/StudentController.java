package com.student.vision.controller;

import com.github.pagehelper.Page;
import com.student.vision.model.BaseQuery;
import com.student.vision.model.BaseResp;
import com.student.vision.model.Student;
import com.student.vision.service.StudentService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    private StudentService studentService;


    @PostMapping("/search")
    @ResponseBody
    public BaseResp<Page<Student>> search(@RequestBody BaseQuery baseQuery){
        Page<Student> students= studentService.search(baseQuery.getSearch(),baseQuery.getPage(),baseQuery.getPageSize());
        return new BaseResp<>(students);
    }

    @GetMapping("/searchById")
    @ResponseBody
    public BaseResp<Student> searchById(@RequestParam("id")Long id){
        Student students= studentService.searchById(id);
        return new BaseResp(students);
    }

    @PostMapping("/insert")
    @ResponseBody
    public BaseResp<Student> insert(@RequestBody @Validated Student student){
        boolean flag;
        if(student.getId()!=null){
            flag=studentService.update(student);
        }else {
            flag = studentService.insert(student);
        }

        if(!flag){
            return BaseResp.fail("添加失败");
        }
        return new BaseResp<>(student);
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public BaseResp delete(@RequestParam("id") Long id){

        boolean flag=studentService.delete(id);
        if(!flag){
            return BaseResp.fail("更新失败");
        }
        return new BaseResp<>(null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResp handler(MethodArgumentNotValidException exception){
        String message =  exception.getBindingResult().getFieldError().getDefaultMessage();
        return BaseResp.fail(message);
    }


}
