package com.kz.practice.controller;

import com.google.zxing.WriterException;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.kz.practice.DesignPattern.springCoreDemo.LoginEventStateEnum;
import com.kz.practice.DesignPattern.springCoreDemo.LoginStateEnum;
import com.kz.practice.pdf.build.FooterHandler;
import com.kz.practice.pdf.build.HeaderHandler;
import com.kz.practice.pdf.build.TableFooterEventHandler;
import com.kz.practice.pdf.data.DeliveryData;
import com.kz.practice.util.BarCodeUtil;
import com.xxl.job.core.util.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ Author     ：KatieZ
 * @ Date       ：Created in 16:52 2019/12/23
 * @ Description：示例
 * @ Modified By：KatieZ
 * @Version: V1.0
 */
@Api(tags = {"Example"})
@RestController
@RequestMapping("/kz/{version}/example")
@Slf4j
public class ExampleController {
    BaseFont baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", false);

    Font ffont = new Font(baseFont, 12, Font.NORMAL);

    Font titleFont = new Font(baseFont, 24, Font.NORMAL);

    @Autowired
    private StateMachine<LoginStateEnum,LoginEventStateEnum> stateMachine;

    private static ExecutorService executorService = (ThreadPoolExecutor)Executors.newFixedThreadPool(10);

    public ExampleController() throws IOException, DocumentException {
    }


    @ApiOperation(value = "hello world")
    @GetMapping("/hello")
    public String getHello(){
        return "hello world";
    }


    @GetMapping("/pdf")
    public void projectExport(HttpServletRequest request, HttpServletResponse response) throws IOException, DocumentException, WriterException {
        // 创建pdf文档(A4)
        Document document = new Document(PageSize.A4);
        // 填充内容
        PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
        // 页眉
//        HeaderHandler headerHandler = new HeaderHandler();
//        headerHandler.setHasLine(true);
//        writer.setPageEvent(headerHandler);
        document.open();
        List<DeliveryData> list = new ArrayList<>();
        DeliveryData deliveryData = new DeliveryData();
        deliveryData.setDeliveryNo("S0001");
        deliveryData.setDeliveryDate(new Date());
        DeliveryData deliveryData2 = new DeliveryData();
        deliveryData2.setDeliveryNo("S0002");
        deliveryData2.setDeliveryDate(new Date());
        list.add(deliveryData);
        list.add(deliveryData2);
        // 内容table
        PdfPTable table = new PdfPTable(5);
        table.setHeaderRows(1);
        table.setPaddingTop(20);
        table.setSplitRows(false);
        table.setComplete(false);
        // 组装表头
        for (int i = 0; i < 5; i++) {table.addCell("Header " + i);}
        // 组装内容
        for (DeliveryData data:list) {
//            headerHandler.setLeft(new Phrase(String.format("单号：%s",data.getDeliveryNo())));
//            headerHandler.setCenter(new Phrase(new Chunk(Image.getInstance(BarCodeUtil.getBytes(data.getDeliveryNo())),60,80)));
//            headerHandler.setRight(new Phrase(String.format("单据日期：%s",DateUtil.format(data.getDeliveryDate(),"yyyy-MM-dd"))));
            // 表格内容
            for (int i = 0; i < 500; i++) {
                if (i%5 == 0) {
                    document.add(table);
                }
                table.addCell("Test " + i);
            }
        }
        table.setComplete(true);
        document.add(table);
        // step 5
        document.close();
    }

    @GetMapping("/exportPdf")
    public void exportPdf(HttpServletRequest request, HttpServletResponse response) throws IOException, DocumentException, WriterException {
        // 1、创建pdf文档(A4)
        Document document = new Document(PageSize.A4.rotate(),30,30,10,20);
        // 2、开始填充内容
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        // 页眉
        PdfPTable headerTable = new PdfPTable(3);
        //设置每列宽度比例10,20
        int[] headerWidth = {50,50,50};
        headerTable.setWidths(headerWidth);
        headerTable.setWidthPercentage(98);
        PdfPCell headerCell = new PdfPCell(new Paragraph(String.format("单号：%s","PS-DH-20191314"),ffont));
        headerCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        headerCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
        headerCell.disableBorderSide(PdfPCell.BOX);
        headerCell.setFixedHeight(40);
        headerCell.setPaddingBottom(5);
        headerTable.addCell(headerCell);
        // 条形码
        Image barCodeImg = Image.getInstance(BarCodeUtil.getBytes("PSD111"));
        barCodeImg.scaleAbsolute(120,40);
        PdfPCell imgCell = new PdfPCell(barCodeImg,false);
        imgCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        imgCell.disableBorderSide(PdfPCell.BOX);
        imgCell.setPaddingBottom(5);
        headerTable.addCell(imgCell);
        headerCell = new PdfPCell(new Paragraph(String.format("单据日期；%s",DateUtil.format(new Date(),"yyyy-MM-dd"))));
        headerCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        headerCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
        headerCell.disableBorderSide(PdfPCell.BOX);
        headerCell.setPaddingBottom(5);
        headerTable.addCell(headerCell);
        document.add(headerTable);
        // 线条
        LineSeparator separator = new LineSeparator();
        separator.setPercentage(98);
        separator.setLineColor(BaseColor.BLACK);
        Chunk linebreak = new Chunk(separator);
        document.add(linebreak);
        // 标题
        PdfPTable titleTable = new PdfPTable(1);
        titleTable.setWidthPercentage(98);
        // 下划线
        Chunk titleChunk = new Chunk("配送单（配送员联）",titleFont);
        titleChunk.setUnderline(1.5f, -6f);
        PdfPCell titleCell = new PdfPCell(new Phrase(titleChunk));
        titleCell.setFixedHeight(40);
        titleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        titleCell.setVerticalAlignment(Element.ALIGN_CENTER);
        titleCell.disableBorderSide(PdfPCell.BOX);
        titleTable.addCell(titleCell);
        document.add(titleTable);
        // 表前置
        PdfPTable preTable = new PdfPTable(3);
        int[] titleWidth  = {20,20,50};
        preTable.setWidths(titleWidth);
        preTable.setWidthPercentage(98);
        PdfPCell preCell = new PdfPCell(new Phrase(String.format("申购人：%s","柳絮影"),ffont));
        preCell.setFixedHeight(40);
        preCell.disableBorderSide(PdfPCell.BOX);
        preCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        preCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        preTable.addCell(preCell);
        preCell = new PdfPCell(new Phrase(String.format("导师：%s","周兴"),ffont));
        preCell.setFixedHeight(40);
        preCell.disableBorderSide(PdfPCell.BOX);
        preCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        preCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        preTable.addCell(preCell);
        preCell = new PdfPCell(new Phrase(String.format("收货信息：%s %s %s","上海市松江区人民北路东华大学生物所5123室","柳絮影","15673723400"),ffont));
        preCell.setFixedHeight(40);
        preCell.disableBorderSide(PdfPCell.BOX);
        preCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        preCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        preTable.addCell(preCell);
        document.add(preTable);
        // 数据表格
        PdfPTable dataTable = new PdfPTable(8);
        int[] columnWidth = {2,4,4,4,3,4,8,3};
        dataTable.setWidths(columnWidth);
        dataTable.setWidthPercentage(98);
        dataTable.setHeaderRows(1);
        dataTable.setSplitRows(false);
        // 后续会继续添加内容
        dataTable.setComplete(false);
        // table头部
        PdfPCell cell = new PdfPCell();
        cell.setFixedHeight(30);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPhrase(new Phrase("序号",ffont));
        dataTable.addCell(cell);
        cell.setPhrase(new Phrase("订单编号",ffont));
        dataTable.addCell(cell);
        cell.setPhrase(new Phrase("货号",ffont));
        dataTable.addCell(cell);
        cell.setPhrase(new Phrase("物品名称",ffont));
        dataTable.addCell(cell);
        cell.setPhrase(new Phrase("CAS号",ffont));
        dataTable.addCell(cell);
        cell.setPhrase(new Phrase("供应商",ffont));
        dataTable.addCell(cell);
        cell.setPhrase(new Phrase("规格",ffont));
        dataTable.addCell(cell);
        cell.setPhrase(new Phrase("数量单位",ffont));
        dataTable.addCell(cell);
        for (int i = 0; i < 50; i++) {
//            table.addCell(new Phrase("CGD20042100102",font));
//            table.addCell(new Phrase("40066480",font));
//            table.addCell(new Phrase("甲基叔丁基醚（沃凯牌）",font));
//            table.addCell(new Phrase("1634-04-4",font));
//            table.addCell(new Phrase("国药集团化学试剂有限公司",font));
//            table.addCell(new Phrase("4.00l",font));
//            table.addCell(new Phrase("1盒子/15瓶",font));
            // 每10个为一个table
            if (i%10 == 0) {
                document.add(dataTable);
            }
            dataTable.addCell("Test " + i);
        }
        dataTable.setComplete(true);
        document.add(dataTable);
        PdfPTable footerTable = new PdfPTable(7);
        int[] footerWidth = {35,40,50,35,40,50,50};
        footerTable.setWidths(footerWidth);
        footerTable.setFooterRows(1);
        footerTable.setWidthPercentage(98);
        PdfPCell footerCell = new PdfPCell();
        footerCell.setPhrase(new Phrase("申购人签字：",ffont));
        footerCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        footerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        footerCell.disableBorderSide(PdfPCell.BOX);
        footerTable.addCell(footerCell);
        footerCell = new PdfPCell(new Phrase(""));
        footerCell.disableBorderSide(13);
        footerTable.addCell(footerCell);
        footerCell = new PdfPCell();
        footerCell.disableBorderSide(PdfPCell.BOX);
        footerTable.addCell(footerCell);
        footerCell.setPhrase(new Phrase(String.format("配送员签字："),ffont));
        footerCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        footerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        footerCell.disableBorderSide(PdfPCell.BOX);
        footerTable.addCell(footerCell);
        footerCell = new PdfPCell(new Phrase(""));
        footerCell.disableBorderSide(13);
        footerTable.addCell(footerCell);
        footerCell = new PdfPCell();
        footerCell.disableBorderSide(PdfPCell.BOX);
        footerTable.addCell(footerCell);
        footerCell.setPhrase(new Phrase(String.format("第 %d 页/共 %d 页",document.getPageNumber(),document.getPageNumber()),ffont));
        footerCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        footerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        footerCell.disableBorderSide(PdfPCell.BOX);
        footerTable.addCell(footerCell);
        document.add(footerTable);
        // 内容表尾部
        document.close();
    }

    @GetMapping(value = "/testStateMachine")
    public void testStateMachine()
    {
        for(int i=0;i<20;i++){
            if(i%2 == 0){
                // 创建线程池进行状态机的测试
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        testStateMachine1();
                    }
                });
            }else{
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        testStateMachine3();
                    }
                });
            }
        }
    }

    public void testStateMachine1()
    {
        stateMachine.start();
        stateMachine.sendEvent(LoginEventStateEnum.CONNECT);
        stateMachine.sendEvent(LoginEventStateEnum.BEGIN_TO_LOGIN);
        stateMachine.sendEvent(LoginEventStateEnum.LOGIN_FAILURE);
        stateMachine.sendEvent(LoginEventStateEnum.LOGOUT);
    }

    public void testStateMachine3()
    {
        stateMachine.start();
        stateMachine.sendEvent(LoginEventStateEnum.BEGIN_TO_LOGIN);
        stateMachine.sendEvent(LoginEventStateEnum.LOGIN_FAILURE);
        stateMachine.sendEvent(LoginEventStateEnum.LOGOUT);
    }

}
