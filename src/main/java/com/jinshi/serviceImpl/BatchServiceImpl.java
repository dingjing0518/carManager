package com.jinshi.serviceImpl;

import com.jinshi.entity.JinshiLincenseGroup;
import com.jinshi.entity.JinshiMemberOrder;
import com.jinshi.entity.JinshiParking;
import com.jinshi.entity.Member;
import com.jinshi.mapper.JinshiLincenseGroupMapper;
import com.jinshi.mapper.JinshiMemberOrderMapper;
import com.jinshi.mapper.MemberMapper;
import com.jinshi.service.BatchService;
import com.jinshi.service.JinshiParkingService;
import com.jinshi.util.ExcelImportUtil;
import com.jinshi.util.GlobalVariable;
import com.jinshi.util.OrderIdForCCB;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class BatchServiceImpl implements BatchService {
    private static Logger logger = Logger.getLogger(BatchServiceImpl.class.getName());

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private JinshiLincenseGroupMapper jinshiLincenseGroupMapper;

    @Autowired
    private JinshiMemberOrderMapper jinshiMemberOrderMapper;

    @Autowired
    private JinshiParkingService jinshiParkingService;

    @Override
    public int batchExport(String parkId, String roleName, HttpServletResponse response) {
        int result = 1;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        HSSFWorkbook wb = new HSSFWorkbook();
        logger.info("开始");
        HSSFSheet sheet = wb.createSheet("会员信息");
        HSSFCellStyle style = wb.createCellStyle();

        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setLocked(true);
        style.setWrapText(true);

        //冻结单元格，参数对应（冻结的列数，冻结的行数，被冻结列第一列的列号，被冻结的最后一行的行号）//（从左往右，从上至下）
        sheet.createFreezePane(1,1,1,1);

        // 左边框的颜色
        style.setLeftBorderColor((short) 8);
        // 右边框的颜色
        style.setRightBorderColor((short) 8);
        // 设置单元格的边框颜色
        style.setBottomBorderColor((short) 8);
        // 设置单元格的背景颜色（单元格的样式会覆盖列或行的样式）
        style.setFillForegroundColor((short) 8);

        style.setBorderBottom(BorderStyle.THIN); //下边框
        style.setBorderLeft(BorderStyle.THIN);//左边框
        style.setBorderTop(BorderStyle.THIN);//上边框
        style.setBorderRight(BorderStyle.THIN);//右边框

        HSSFFont font = wb.createFont();
        font.setFontHeightInPoints((short)10);
        font.setFontName("宋体");
        style.setFont(font);

        /**设置单元格格式为文本格式*/
        HSSFDataFormat format = wb.createDataFormat();
        style.setDataFormat(format.getFormat("@"));

        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("序号");
        row.createCell(1).setCellValue("车牌号");
        row.createCell(2).setCellValue("姓名");
        row.createCell(3).setCellValue("收费类型");
        row.createCell(4).setCellValue("开始日期");
        row.createCell(5).setCellValue("结束日期");
        row.createCell(6).setCellValue("次数");
        row.createCell(7).setCellValue("储值金额");
        row.createCell(8).setCellValue("手机号");
        row.createCell(9).setCellValue("公司名");
        row.createCell(10).setCellValue("部门名");
        row.createCell(11).setCellValue("区域");
        row.createCell(12).setCellValue("到期提醒");
        row.createCell(13).setCellValue("车牌组");
        row.createCell(14).setCellValue("车辆类型");
        row.createCell(15).setCellValue("汽车颜色");
        row.createCell(16).setCellValue("录入时间");
        row.createCell(17).setCellValue("录入账号");
        row.createCell(18).setCellValue("备注");

        //设置单元格列宽
        sheet.setColumnWidth(0,2000);
        sheet.setColumnWidth(1,4000);
        sheet.setColumnWidth(2,5000);
        sheet.setColumnWidth(3,3000);
        sheet.setColumnWidth(4,4000);
        sheet.setColumnWidth(5,4000);
        sheet.setColumnWidth(6,2000);
        sheet.setColumnWidth(7,3000);
        sheet.setColumnWidth(8,6000);
        sheet.setColumnWidth(9,5000);
        sheet.setColumnWidth(10,5000);
        sheet.setColumnWidth(11,3500);
        sheet.setColumnWidth(12,3000);
        sheet.setColumnWidth(13,3000);
        sheet.setColumnWidth(14,4000);
        sheet.setColumnWidth(15,3000);
        sheet.setColumnWidth(16,6000);
        sheet.setColumnWidth(17,3000);
        sheet.setColumnWidth(18,7000);

        List<Member> membersList = new ArrayList<>();
        if (roleName != null) {
            if ("超级管理员".equals(roleName) || "金石管理员".equals(roleName)) {
                membersList = memberMapper.selectMember();
            } else {
                membersList = memberMapper.selectMemberAllByParkId(Integer.valueOf(parkId));
            }
        } else {
            membersList = memberMapper.selectMemberAllByParkId(Integer.valueOf(parkId));
        }
        for (int i = 0; i < membersList.size(); i++) {
            HSSFRow rowX = sheet.createRow(i + 1);
            Member dataX = membersList.get(i);
            /**
             * BatchData的字段设置为String类型则导出数据为文本类型,免得设置cell格式
             */
            rowX.createCell(0).setCellValue(dataX.getId());
            rowX.createCell(1).setCellValue(dataX.getLincensePlateId());
            rowX.createCell(2).setCellValue(dataX.getMemberId());
            rowX.createCell(3).setCellValue(dataX.getServiceType());
            rowX.createCell(4).setCellValue(df.format(dataX.getJoinTime()));
            rowX.createCell(5).setCellValue(df.format(dataX.getExpirationTime()));
            rowX.createCell(6).setCellValue(dataX.getCarName());
            Integer storedMoney = dataX.getStoredMoney();
            if (storedMoney != null) {
                rowX.createCell(7).setCellValue(storedMoney);
            } else {
                rowX.createCell(7).setCellValue("");
            }
            rowX.createCell(8).setCellValue(dataX.getPhoneNumber());
            rowX.createCell(9).setCellValue(dataX.getCompanyName());
            rowX.createCell(10).setCellValue(dataX.getDepartmentName());
            rowX.createCell(11).setCellValue(dataX.getAreaName());
            rowX.createCell(12).setCellValue(dataX.getMemberAddress());
            Integer lgId = dataX.getLgId();
            if (null != lgId) {
                JinshiLincenseGroup jinshiLincenseGroup = jinshiLincenseGroupMapper.selectByPrimaryKey(lgId);
                if (null != jinshiLincenseGroup) {
                    rowX.createCell(13).setCellValue(jinshiLincenseGroup.getJsGroupName());
                }
            } else {
                rowX.createCell(13).setCellValue("");
            }
            rowX.createCell(14).setCellValue(dataX.getCarType());
            rowX.createCell(15).setCellValue(dataX.getCarColor());
//            List<JinshiMemberOrder> jinshiMemberOrder = jinshiMemberOrderMapper.selectByMemberId(dataX.getId());
//            if (jinshiMemberOrder.size() > 0) {
//                JinshiMemberOrder jinshiMemberOrder1 = jinshiMemberOrder.get(0);
//                rowX.createCell(15).setCellValue(String.valueOf(jinshiMemberOrder1.getJmoPayable()));
//                rowX.createCell(16).setCellValue(String.valueOf(jinshiMemberOrder1.getJmoActualCost()));
//            } else {
//                rowX.createCell(15).setCellValue("");
//                rowX.createCell(16).setCellValue("");
//            }
            rowX.createCell(16).setCellValue(dataX.getState());
            rowX.createCell(17).setCellValue(dataX.getEnterUser());
            rowX.createCell(18).setCellValue(dataX.getCompanyAddress());

        }
        for (int i = 0; i <= membersList.size(); i++) {
            HSSFRow row00 = sheet.getRow(i);
            for (Cell cell : row00) {
                cell.setCellStyle(style);
            }
        }
        FileOutputStream out = null;
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        try {
            //创建excel文件
//            File path = new File(filePath);
//            if(!path.exists()){
//                path.mkdirs();
//            }
            Integer random = new Random().nextInt(1000);
            JinshiParking jinshiParking = jinshiParkingService.selectByJpId(Integer.valueOf(parkId));
            String jpName = jinshiParking.getJpName();
            String fileName = jpName + "车主档案" + df.format(new Date());
//            out = new FileOutputStream(filePath+fileName+".xls");
            out = new FileOutputStream(fileName +".xls");
            wb.write(out);
            //压缩为zip文件
//            String fileZipName = "dab"+System.currentTimeMillis()+random;
//            ZipCompress zipCom = new ZipCompress(fileZipPath+fileZipName+".zip",filePath+fileName+".xls");
//            zipCom.zip();
            //下载zip文件
//            File file = new File(filePath+fileName+".xls");
            File file = new File(fileName+".xls");
            if(file.exists()){
                logger.info("下载开始....." + fileName);
                // 设置强制下载不打开
                response.setContentType("application/force-download");
                // 设置文件名
//                response.addHeader("Content-Disposition", "attachment;fileName=" + filePath+".xls");
                response.setHeader("Content-disposition",
                        "attachment; filename=" + new String(fileName.getBytes("GB2312"), "8859_1") + ".xls");

                byte[] buffer = new byte[1024];
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                logger.info("开始读取.."+i);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
//                    logger.info("读取中.."+i);
                }
                logger.info("下载成功....." + fileName);

            }else{
                logger.info("下载失败！" + fileName);
            }
        } catch (Exception e) {
            logger.error("接口内部错误",e);
            e.printStackTrace();
//            throw new MyException("接口内部错误",e);
        }finally {
            logger.info("进入finally代码块");
            if(out != null){
                try {
                    out.close();
                    logger.info("关闭out输出流");
                } catch (IOException e) {
                    logger.error("接口内部错误out",e);
                    e.printStackTrace();
//                    throw new MyException("接口内部错误out",e);
                }
            }
            if (bis != null) {
                try {
                    bis.close();
                    logger.info("关闭bis输出流");
                } catch (IOException e) {
                    logger.error("接口内部错误bis",e);
                    e.printStackTrace();
//                    throw new MyException("接口内部错误bis",e);
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                    logger.info("关闭fis输出流");
                } catch (IOException e) {
                    logger.error("接口内部错误fis",e);
                    e.printStackTrace();
//                    throw new MyException("接口内部错误fis",e);
                }
            }
            //删除数据文件
//            deleteDir(fileZipPath);
//            deleteDir(filePath);
        }
        return result;
    }

    @Override
    public boolean deleteDir(String dir) {
        File file = new File(dir);
        boolean delete ;
        if (file.isDirectory()) {
            String[] children = file.list();
            if(children.length>0){
                /**递归删除目录中的子目录下*/
                for (int i=0; i<children.length; i++) {
                    boolean success = deleteDir(file.getPath()+"/"+children[i]);
                    if (!success) {
                        return false;
                    }
                }
            }
            delete = file.delete();
            logger.info("删除目录"+delete);
        }else {
            delete = file.delete();
            logger.info("删除文件"+delete);
        }
        return delete;
    }

    @Override
    public void batchImport(MultipartFile file,Integer parkId,Integer agentId) {
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat dfa = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if(!file.isEmpty()){
                //获取文件名
                String fileName = file.getOriginalFilename();
                logger.info("上传的文件名为：" + fileName);
                // 获取文件的后缀名
                String suffixName = fileName.substring(fileName.lastIndexOf("."));
                logger.info("文件的后缀名为：" + suffixName);
                // 设置文件存储路径
                String filePath = "C:/carManagerImport/";
                String path = filePath + fileName;
                File dest = new File(path);
                // 检测是否存在目录
                if (!dest.getParentFile().exists()) {
                    dest.getParentFile().mkdirs();// 新建文件夹
                }
                // 文件写入
                file.transferTo(dest);
                logger.info("上传文件成功！");
                boolean excel2003 = ExcelImportUtil.isExcel2003(fileName);
                boolean excel2007 = ExcelImportUtil.isExcel2007(fileName);
                if(!excel2003 && !excel2007){
                    logger.info("上传文件格式不正确");
//                    throw new MyException("上传文件格式不正确");
                }
                FileInputStream fis = new FileInputStream(dest);
                Workbook wb = null;
                if (excel2003) {
                    wb = new HSSFWorkbook(fis);
                } else {

                    wb = new XSSFWorkbook(fis);
                }
                Sheet sheet = wb.getSheetAt(0);
                int countTrue = 1;
                int countFalse = 1;
                for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                    Row row = sheet.getRow(i);
                    if(row == null){
                        continue;
                    }
                    String lincensePlateId = row.getCell(1).getStringCellValue();
                    String memberId = row.getCell(2).getStringCellValue();
                    String serviceType = row.getCell(3).getStringCellValue();
                    row.getCell(4).setCellType(CellType.STRING);
                    String joinTimeStr = row.getCell(4).getStringCellValue();
                    Date joinTime = null;
                    if (null != joinTimeStr && "" != joinTimeStr) {
                        joinTime = sdf.parse(joinTimeStr);
                    }
                    row.getCell(5).setCellType(CellType.STRING);
                    String expirationTimeStr = row.getCell(5).getStringCellValue();
                    Date expirationTime = null;
                    if (null != expirationTimeStr && "" != expirationTimeStr) {
                        expirationTime = sdf.parse(expirationTimeStr);
                    }
                    String carName = row.getCell(6).getStringCellValue();
                    String storedMoney = row.getCell(7).getStringCellValue();
                    row.getCell(8).setCellType(CellType.STRING);
                    String phonenumber = row.getCell(8).getStringCellValue();
                    String companyname = row.getCell(9).getStringCellValue();
                    String departmentname = row.getCell(10).getStringCellValue();
                    String areaName = row.getCell(11).getStringCellValue();
                    String memberAddress = row.getCell(12).getStringCellValue();
                    String lgId = row.getCell(13).getStringCellValue();
                    String carType = row.getCell(14).getStringCellValue();
                    String carColor = row.getCell(15).getStringCellValue();
//                    String payAble = row.getCell(15).getStringCellValue();
//                    String actualCost = row.getCell(16).getStringCellValue();
                    String enterUser = row.getCell(17).getStringCellValue();
                    String companyAddress = row.getCell(18).getStringCellValue();

                    String vipTime = "2099-12-31 23:59:59";
                    Member member = new Member();
                    member.setLincensePlateId(lincensePlateId);
                    member.setMemberId(memberId);
                    member.setState(dfa.format(new Date()));
                    member.setJoinTime(joinTime);
                    if ("贵宾车".equals(serviceType) || "内部车".equals(serviceType)) {
                        member.setExpirationTime(dfa.parse(vipTime));
                    } else {
                        member.setExpirationTime(expirationTime);
                    }
                    member.setServiceType(serviceType);
                    member.setPhoneNumber(phonenumber);
                    member.setCompanyName(companyname);
                    member.setDepartmentName(departmentname);
                    member.setMemberAddress(memberAddress);
                    member.setCompanyAddress(companyAddress);
                    member.setCarType(carType);
                    member.setCarColor(carColor);
                    member.setAreaName(areaName);
                    member.setEnterUser(enterUser);
                    member.setCarName(carName);
                    member.setParkId(parkId);
                    member.setAgentId(agentId);
                    if (null == storedMoney || "".equals(storedMoney)) {
                        member.setStoredMoney(null);
                    } else {
                        member.setStoredMoney(Integer.parseInt(storedMoney));
                    }
                    /*member.setParkId(GlobalVariable.parkId);
                    member.setAgentId(GlobalVariable.agentId);*/
                    if (null == lgId || "".equals(lgId)) {
                        member.setLgId(null);
                    } else {
                        member.setLgId(Integer.parseInt(lgId));
                    }

                    //添加会员订单信息
                    JinshiMemberOrder jinshiMemberOrder = new JinshiMemberOrder();
                    jinshiMemberOrder.setJmoMemberId(memberId);
                    jinshiMemberOrder.setJmoLincensePlate(lincensePlateId);
                    jinshiMemberOrder.setJmoServiceType(serviceType);
                    jinshiMemberOrder.setJmoServiceNumber(carName);
                    jinshiMemberOrder.setJmoJoinTime(joinTime);
                    if ("贵宾车".equals(serviceType) || "内部车".equals(serviceType)) {
                        jinshiMemberOrder.setJmoExpirationTime(dfa.parse(vipTime));
                    } else {
                        jinshiMemberOrder.setJmoExpirationTime(expirationTime);
                    }
                    jinshiMemberOrder.setJmoCreatTime(new Date());
//                    jinshiMemberOrder.setJmoPayable(BigDecimal.valueOf(Integer.valueOf(payAble)));
//                    jinshiMemberOrder.setJmoActualCost(BigDecimal.valueOf(Integer.valueOf(actualCost)));
                    jinshiMemberOrder.setJmoPhoneNumber(phonenumber);
                    jinshiMemberOrder.setJmoAreaName(areaName);
                    jinshiMemberOrder.setJmoParkId(parkId);
                    jinshiMemberOrder.setJmoAgentId(agentId);
                    jinshiMemberOrder.setJmoEnterUser(enterUser);
                    jinshiMemberOrder.setJmoOrderId(OrderIdForCCB.getOrderIdByUUId());

                    List<Member> members = memberMapper.selectMemberByPlateAndArea(lincensePlateId, areaName,parkId);
                    int insert = 0;
                    if(members.size()==0){
                        insert = memberMapper.insert(member);
                        jinshiMemberOrder.setJmoMemberTableId(member.getId());
                        jinshiMemberOrderMapper.insert(jinshiMemberOrder);
                        logger.info("导入Member"+(insert>0));
                        logger.info("导入成功统计：" + countTrue++);
                    }else{
                        logger.info("数据重复"+(insert>0) + lincensePlateId);
                        logger.info("导入重复统计：" + countFalse++);
                    }
//                    int u = batchMapper.addUser(user);
//                    logger.info("导入user"+(u>0));
//                    Car car = Car.builder().uid(id).carName(carName).carPrice(carPrice).build();
//                    int c = batchMapper.addCar(car);
                    logger.info("导入Member"+(insert>0));

                }
            }else{
                logger.info("上传文件为空！");
            }
        } catch (Exception e) {
            logger.info("上传文件接口内部异常",e);
        }
    }
}
