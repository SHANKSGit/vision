package com.student.vision.controller;

import com.github.pagehelper.Page;
import com.student.vision.model.BaseQuery;
import com.student.vision.model.BaseResp;
import com.student.vision.model.Student;
import com.student.vision.model.Vision;
import com.student.vision.service.StudentService;
import com.student.vision.service.VisionService;
import com.student.vision.util.DocUtil;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    private StudentService studentService;
    @Resource
    private VisionService  visionService;


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
    public void export(HttpServletRequest request,
                           HttpServletResponse response,
                           @RequestParam("id")Long id
                      ){
        Student students= studentService.searchById(id);
        if(students==null){
            return;
        }
        //2、日历类的时间操作
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.get(Calendar.MONTH)+1);
        System.out.println(calendar.get(Calendar.DATE));

        Map<String,String> dataMap = new HashMap<String,String>();
        dataMap.put("name", students.getName());
        dataMap.put("year", calendar.get(Calendar.YEAR)+"");
        dataMap.put("mouth", (calendar.get(Calendar.MONTH)+1)+"");
        dataMap.put("day", calendar.get(Calendar.DATE)+"");
        String newWordName = students.getName()+"配镜信息.doc";
        List<Vision> visionList=visionService.search(students.getId(),1,12);
        for(int i=1;i<=12;i++){
            String date="";
            String eye="";
            String eyeRef="";
            String glass="";
            if(i<=visionList.size()){
                Vision vision=visionList.get(i-1);
                date = new SimpleDateFormat("yyyy-MM-dd").format(vision.getCreateTime());
                String lEye=vision.getlEye()==null?"":vision.getlEye();
                String rEye=vision.getrEye()==null?"":vision.getrEye();
                String lEyeRef=vision.getlEyeRef()==null?"":vision.getlEyeRef();
                String rEyeRef=vision.getrEyeRef()==null?"":vision.getrEyeRef();
                String rGlass=vision.getrGlass()==null?"":vision.getrGlass();
                String lGlass=vision.getlGlass()==null?"":vision.getlGlass();
                eye="左:"+lEye+" 右:"+rEye;
                eyeRef="左:"+lEyeRef+" 右:"+rEyeRef;
                glass="左:"+lGlass+" 右:"+rGlass;
            }
            dataMap.put("date"+i,date);
            dataMap.put("eye"+i,eye);
            dataMap.put("eyeRef"+i,eyeRef);
            dataMap.put("glass"+i,glass);
        }
        //调用打印word的函数
        DocUtil.download(request,response,newWordName, dataMap);
        students.setStatus(1);
        studentService.update(students);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResp handler(MethodArgumentNotValidException exception){
        String message =  exception.getBindingResult().getFieldError().getDefaultMessage();
        return BaseResp.fail(message);
    }


}
