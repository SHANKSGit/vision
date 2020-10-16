package com.student.vision.controller;

import com.github.pagehelper.Page;
import com.student.vision.model.BaseQuery;
import com.student.vision.model.BaseResp;
import com.student.vision.model.Student;
import com.student.vision.service.StudentService;
import com.student.vision.util.DocUtil;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

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
            if(!flag){
                return BaseResp.fail("更新失败");
            }
        }else {
            if(studentService.exist(student)){
                return BaseResp.fail("该学生信息已存在");
            }
            flag = studentService.insert(student);
            if(!flag){
                return BaseResp.fail("添加失败");
            }
        }


        return new BaseResp<>(student);
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public BaseResp delete(@RequestParam("id") Long id){

        boolean flag=studentService.delete(id);
        if(!flag){
            return BaseResp.fail("删除失败");
        }
        return new BaseResp<>(null);
    }

    @GetMapping("/export")
    @ResponseBody
    public void export(HttpServletRequest request, HttpServletResponse response){
        Map<String,String> dataMap = new HashMap<String,String>();
        dataMap.put("workname", "单位名称");
        dataMap.put("name1", "wuhui");
        dataMap.put("name2", "azy");
        dataMap.put("name3", "zyq");
        dataMap.put("year", "2019");
        dataMap.put("month", "08");
        dataMap.put("day", "19");
        String newWordName = "信息.doc";
        //调用打印word的函数
        DocUtil.download(request,response,newWordName, dataMap);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResp handler(MethodArgumentNotValidException exception){
        String message =  exception.getBindingResult().getFieldError().getDefaultMessage();
        return BaseResp.fail(message);
    }


}
