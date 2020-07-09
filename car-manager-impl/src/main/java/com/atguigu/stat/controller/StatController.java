package com.atguigu.stat.controller;

import com.atguigu.bus.bean.BusCustomer;
import com.atguigu.bus.bean.BusRent;
import com.atguigu.bus.service.CustomerService;
import com.atguigu.bus.service.RentCarService;
import com.atguigu.bus.utils.ExportRentExcelUtils;
import com.atguigu.bus.vo.CustomerVo;
import com.atguigu.car.utils.AppFileUtils;
import com.atguigu.car.utils.ExportListExcelUtils;
import com.atguigu.stat.DataEntity;
import com.atguigu.stat.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping(value = "/stat")
@Controller
public class StatController {

    @Autowired
    StatService statPieService;

    @Autowired
    CustomerService customerService;

    @Autowired
    RentCarService rentCarService;

    /**
     * 用于将出租单数据导出到excel
     *
     * @return
     */
    @RequestMapping(value = "/exportRent")
    public ResponseEntity<Object> exportRent(String rentid) {
        // 根据出租单号查找对应的出租单信息
        BusRent rent = rentCarService.selectRentListById(rentid);
        // 根据出租单号查找对应的客户信息
        BusCustomer customer = customerService.queryCustomerById(rent.getIdentity());
        String fileName = customer.getCustname() + "的出租单.xls";
        String sheetName = customer.getCustname() + "的出租单";
        ByteArrayOutputStream bos = ExportRentExcelUtils.exprotRent(rent, customer, fileName, sheetName);
        try {
            fileName = URLEncoder.encode(fileName, "UTF-8");// 处理文件名乱码
            // 创建封装响应头信息的对象
            HttpHeaders header = new HttpHeaders();
            // 创建响应内容类型，APPLICATION_OCTET_STREAM响应的内容不限定
            header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            // 设置下载的文件名称
            header.setContentDispositionFormData("attachment", fileName);
            return new ResponseEntity<Object>(bos.toByteArray(), header, HttpStatus.CREATED);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 导出客户数据到excel表格
     *
     * @param customerVo
     * @return
     */
    @RequestMapping(value = "/exportCustomer")
    public ResponseEntity<Object> exportCustomer(CustomerVo customerVo) {
        List<BusCustomer> list = customerService.queryAllCustomerForList(customerVo);
        ByteArrayOutputStream bos = ExportListExcelUtils.exportList(list, "客户数据列表", "客户数据");
        String fileName = "客户数据.xls";
        return AppFileUtils.downloadFile(bos, fileName);
    }

    /**
     * 用于查询公司年度销售额数据
     *
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/getComSaleLineJson")
    public List<Double> getComSaleLineJson(String year) {
        List<Double> sales = statPieService.getComSaleLineJson(year);
        // 如果该月没有值，将其值设置为0，防止线形图不连续
        for (int i = 0; i < sales.size(); i++) {
            if (null == sales.get(i)) {
                sales.set(i, 0.0);
            }
        }
        return sales;
    }

    /**
     * 用于跳转到柱状图图页面
     *
     * @return
     */
    @RequestMapping(value = "/toComLineStatPage")
    public String toComLineStat() {
        return "stat/CompanyLineStat";
    }

    /**
     * 用于查询员工销售额数据，并返回到页面柱状图
     *
     * @param year
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/getEmpSalesJson")
    public Map<String, Object> getEmpSalesJson(String year) {
        List<DataEntity> dataEntities = statPieService.getEmpSalesJson(year);
        // 将查询到的数据保存到list集合中，然后封装到map集合中，返回到野蛮
        HashMap<String, Object> map = new HashMap<>();
        List<String> names = new ArrayList<>();
        List<Double> values = new ArrayList<>();
        for (DataEntity dataEntity : dataEntities) {
            names.add(dataEntity.getName());
            values.add(dataEntity.getValue());
        }
        map.put("name", names);
        map.put("value", values);
        return map;
    }

    /**
     * 用于跳转到柱状图图页面
     *
     * @return
     */
    @RequestMapping(value = "/toEmpSalesAlignStat")
    public String toEmpSalesAlignStat() {
        return "stat/EmpSalesAlignStat";
    }

    /**
     * 用于查询用户来源地区数据，将数据返回到页面饼状图
     *
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/getStatJson")
    public List<DataEntity> getStatJson() {

        return statPieService.getStatJson();
    }


    /**
     * 用于跳转到饼状图页面
     *
     * @return
     */
    @RequestMapping(value = "/toCustomerPieStatPage")
    public String toCustomerPieStat() {
        return "stat/CustomerPieStat";
    }
}
