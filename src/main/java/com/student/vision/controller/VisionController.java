package com.student.vision.controller;

import com.student.vision.model.BaseResp;
import com.student.vision.model.Student;
import com.student.vision.model.Vision;
import com.student.vision.service.VisionService;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
            flag = visionService.update(vision);
            if(!flag){
                return BaseResp.fail("更新失败");
            }
        }else {
            flag = visionService.insert(vision);
            if(!flag){
                return BaseResp.fail("新增失败");
            }
        }


        return new BaseResp<>(vision);
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public BaseResp delete(@RequestParam("id") Long id){

        boolean flag=visionService.delete(id);
        if(!flag){
            return BaseResp.fail("删除失败");
        }
        return new BaseResp<>(null);
    }

    @GetMapping("/searchBySId")
    @ResponseBody
    public BaseResp<Student> searchById(@RequestParam("sId")Long sId,
                                        @RequestParam("page")int page,
                                        @RequestParam("pageSize")int pageSize){
        List<Vision> visions= visionService.search(sId,page,pageSize);
        return new BaseResp(visions);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResp handler(MethodArgumentNotValidException exception){
        String message =  exception.getBindingResult().getFieldError().getDefaultMessage();
        return BaseResp.fail(message);
    }


}
