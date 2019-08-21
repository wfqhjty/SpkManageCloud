package cn.spk.data.controller;

import cn.spk.data.entity.Employee;
import cn.spk.data.entity.FrameUser;
import cn.spk.data.service.impl.ExportExcel;
import cn.spk.data.service.impl.ExportExcelFrameUser;
import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ExcelExportController {

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/excelExport")
    public void excelExport(HttpServletRequest request, HttpServletResponse response) {
        /**模拟数据开始*/
        List<Employee> staffs = new ArrayList<Employee>();
        staffs.add(new Employee("test01", "一组", 2017, 9, 20, 20000));
        staffs.add(new Employee("test02", "一组", 2017, 9, 20, 20000));
        staffs.add(new Employee("test03", "一组", 2017, 9, 20, 20000));
        staffs.add(new Employee("test04", "一组", 2017, 9, 20, 20000));
        staffs.add(new Employee("test05", "一组", 2017, 9, 20, 20000));

        Map<String, String> titleMap = new LinkedHashMap<String, String>();
        titleMap.put("name", "姓名");
        titleMap.put("clazz", "组号");
        titleMap.put("year", "年份");
        titleMap.put("month", "月份");
        titleMap.put("day", "天");
        titleMap.put("salary", "薪资");
        String sheetName = "信息导出";
        /**模拟数据结束*/
        System.out.println("start导出");
        long start = System.currentTimeMillis();
        ExportExcel.excelExport(staffs, titleMap, sheetName, response);
        long end = System.currentTimeMillis();
        System.out.println("end导出");
        System.out.println("耗时：" + (end - start) + "ms");
    }

    @GetMapping("/excelExportUser")
    public void excelExportUser(HttpServletRequest request, HttpServletResponse response) {
        String url = "http://localhost:8764/spkuser/frameUserController/getUsers";
        //headers
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOiJ1aWRfOSIsImV4cCI6MTU2NjI4MTgzOCwidXNlcm5hbWUiOiJhZG1pbiJ9.Dl-DKcMToSehJTZOdcJLq7jMn-m7pmyYaGqWi4Wb924");
        //HttpEntity
        HttpEntity<Object> requestEntity = new HttpEntity<>(requestHeaders);
        ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        List<FrameUser> staffs = JSON.parseArray(result.getBody(), FrameUser.class);
        Map<String, String> titleMap = new LinkedHashMap<>();
        titleMap.put("userid", "用户ID");
        titleMap.put("username", "用户名");
        titleMap.put("passwd", "密码");
        titleMap.put("createdate", "创建时间");
        titleMap.put("phone", "手机");
        titleMap.put("deptname", "部门");
        String sheetName = "人员信息导出";
        System.out.println("start导出");
        long start = System.currentTimeMillis();
        ExportExcelFrameUser.excelExport(staffs, titleMap, sheetName, response);
        long end = System.currentTimeMillis();
        System.out.println("end导出");
        System.out.println("耗时：" + (end - start) + "ms");
    }

}