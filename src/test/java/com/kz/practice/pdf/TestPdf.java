package com.kz.practice.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.exceptions.UnsupportedPdfException;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.LineSeparator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Description TODO
 * @Author KatieZ
 * @Date Created in 14:02  14:02
 */
public class TestPdf {

    public static final String DEST = "results/tables/simple_table6.pdf";

    BaseFont baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", false);

    Font font = new Font(baseFont, 12, Font.NORMAL);

    public TestPdf() throws IOException, DocumentException {
    }

    public static void main(String[] args) throws IOException,
            DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new TestPdf().splintingTables(DEST);
    }

    /**
     * 字体下划线 删除线
     * @param dest
     * @throws IOException
     * @throws DocumentException
     */
    public void test1(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfPTable table = new PdfPTable(2);
        table.addCell(new Phrase("0123456789"));
        Font font = new Font(Font.FontFamily.HELVETICA, 12f, Font.STRIKETHRU);
        table.addCell(new Phrase("0123456789", font));
        Chunk chunk1 = new Chunk("0123456789");
        chunk1.setUnderline(1.5f, -1);
        table.addCell(new Phrase(chunk1));
        Chunk chunk2 = new Chunk("0123456789");
        chunk2.setUnderline(1.5f, 3.5f);
        table.addCell(new Phrase(chunk2));
        document.add(table);
        document.close();
    }

    /**
     * 下划线
     * @param dest
     * @throws IOException
     * @throws DocumentException
     */
    public void test2(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfContentByte canvas = writer.getDirectContent();
        canvas.moveTo(10,800);
        canvas.lineTo(585, 800);
        canvas.closePathStroke();
        document.close();
    }

    /**
     * 生成显示条形码
     * @param dest
     * @throws IOException
     * @throws DocumentException
     */
    public void test3(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        for (int i = 0; i < 12; i++) {
            table.addCell(createBarcode(writer, String.format("%08d", i)));
        }
        document.add(table);
        document.close();
    }

    public static PdfPCell createBarcode(PdfWriter writer, String code) throws DocumentException, IOException {
        BarcodeEAN barcode = new BarcodeEAN();
        barcode.setCodeType(Barcode.EAN8);
        barcode.setCode(code);
        PdfPCell cell = new PdfPCell(barcode.createImageWithBarcode(writer.getDirectContent(), BaseColor.BLACK, BaseColor.GRAY), true);
        cell.setPadding(10);
        return cell;
    }

    /**
     * 大表格
     * @param dest
     * @throws IOException
     * @throws DocumentException
     */
    public void testTable(String dest) throws IOException, DocumentException {
        Document document = new Document(PageSize.LETTER);
        PdfWriter.getInstance(document, new FileOutputStream(dest));

        document.open();
        PdfPTable table = new PdfPTable(5);
        table.setHeaderRows(1);
        table.setSplitRows(true);
        table.setComplete(false);

        for (int i = 0; i < 5; i++) {table.addCell("Header " + i);}

        for (int i = 0; i < 500; i++) {
            if (i%5 == 0) {
                document.add(table);
            }
            table.addCell("Test " + i);
        }

        table.setComplete(true);
        document.add(table);
        document.close();
    }

    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        // 定义列宽度
        float[] columnWidths = {2, 5, 5,5,5,5,5,5};
        PdfPTable table = new PdfPTable(columnWidths);
        table.setWidthPercentage(80);
        table.getDefaultCell().setUseAscender(true);
        table.getDefaultCell().setUseDescender(true);
        // 表头
        Chunk titleChunk = new Chunk("配送出库单", font);
        // 单元格内容加下划线
        titleChunk.setUnderline(2f, -1);
        PdfPCell cell = new PdfPCell(new Phrase(titleChunk));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(8);
        cell.setFixedHeight(30f);
        cell.setBorderColor(BaseColor.WHITE);
        table.addCell(cell);
        table.getDefaultCell().setBackgroundColor(new GrayColor(0.75f));

        // 表头头部说明
        PdfPCell leftCenter = new PdfPCell(new Phrase("申购人：柳絮影", font));
        leftCenter.setColspan(2);
        leftCenter.setFixedHeight(30f);
        leftCenter.setBorderColor(BaseColor.WHITE);
        table.addCell(leftCenter);
        PdfPCell centerCell = new PdfPCell(new Phrase("导师：周兴平", font));
        centerCell.setColspan(2);
        centerCell.setFixedHeight(30f);
        centerCell.setBorderColor(BaseColor.WHITE);
        table.addCell(centerCell);
        PdfPCell rightCell = new PdfPCell(new Phrase("收货信息：上海市松江区人民北路东华大学生物所5123室，柳絮影，15673723400", font));
        rightCell.setColspan(4);
        rightCell.setFixedHeight(30f);
        rightCell.setBorderColor(BaseColor.WHITE);
        table.addCell(rightCell);

        // table表头
        table.addCell(new Phrase("序号", font));
        table.addCell(new Phrase("订单编号", font));
        table.addCell(new Phrase("货号", font));
        table.addCell(new Phrase("物品名称", font));
        table.addCell(new Phrase("CAS号", font));
        table.addCell(new Phrase("供应商", font));
        table.addCell(new Phrase("规格", font));
        table.addCell(new Phrase("数量单位", font));
        table.setHeaderRows(3);
        // 居中
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        // 内容
        for (int counter = 1; counter < 101; counter++) {
            table.addCell(String.valueOf(counter));
            table.addCell("key " + counter);
            table.addCell("key " + counter);
            table.addCell("key " + counter);
            table.addCell("key " + counter);
            table.addCell("key " + counter);
            table.addCell("key " + counter);
        }
        document.add(table);
        document.close();
    }

    public void createPdfTest(String dest) throws DocumentException, IOException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.setMargins(15, 15, 55, 35);
        document.open();
        String[] header = new String[] { "Header1", "Header2", "Header3",
                "Header4", "Header5" };
        String[] content = new String[] { "column 1", "column 2",
                "some Text in column 3", "Test data ", "column 5" };
        PdfPTable table = new PdfPTable(header.length);
        table.setHeaderRows(1);
        table.setWidths(new int[] { 3, 2, 4, 3, 2 });
        table.setWidthPercentage(98);
        table.setSpacingBefore(15);
        table.setSplitLate(false);
        for (String columnHeader : header) {
            PdfPCell headerCell = new PdfPCell();
            headerCell.addElement(new Phrase(columnHeader, FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            headerCell.setBorderColor(BaseColor.LIGHT_GRAY);
            headerCell.setPadding(8);
            table.addCell(headerCell);
        }
        for (int i = 0; i < 15; i++) {
            int j = 0;
            for (String text : content) {
                if (i == 13 && j == 3) {
                    text = "Test data \n Test data \n Test data";
                }
                j++;
                PdfPCell cell = new PdfPCell();
                cell.addElement(new Phrase(text, FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
                cell.setBorderColor(BaseColor.LIGHT_GRAY);
                cell.setPadding(5);
                table.addCell(cell);
            }
        }
        document.add(table);
        document.add(new Phrase("\n"));
        LineSeparator separator = new LineSeparator();
        separator.setPercentage(98);
        separator.setLineColor(BaseColor.LIGHT_GRAY);
        Chunk linebreak = new Chunk(separator);
        document.add(linebreak);
        for (int k = 0; k < 5; k++) {
            Paragraph info = new Paragraph("Some title", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL));
            info.setSpacingBefore(12f);
            document.add(info);
            table = new PdfPTable(header.length);
            table.setHeaderRows(1);
            table.setWidths(new int[] { 3, 2, 4, 3, 2 });
            table.setWidthPercentage(98);
            table.setSpacingBefore(15);
            table.setSplitLate(false);
            for (String columnHeader : header) {
                PdfPCell headerCell = new PdfPCell();
                headerCell.addElement(new Phrase(columnHeader, FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
                headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                headerCell.setBorderColor(BaseColor.LIGHT_GRAY);
                headerCell.setPadding(8);
                table.addCell(headerCell);
            }
            for (String text : content) {
                PdfPCell cell = new PdfPCell();
                cell.addElement(new Phrase(text, FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
                cell.setBorderColor(BaseColor.LIGHT_GRAY);
                cell.setPadding(5);
                table.addCell(cell);
            }
            document.add(table);
            separator = new LineSeparator();
            separator.setPercentage(98);
            separator.setLineColor(BaseColor.LIGHT_GRAY);
            linebreak = new Chunk(separator);
            document.add(linebreak);
        }
        document.close();
    }

    public void parse(String src) throws IOException {
        PdfReader reader = new PdfReader(src);
        PdfObject obj;
        for (int i = 1; i <= reader.getXrefSize(); i++) {
            obj = reader.getPdfObject(i);
            if (obj != null && obj.isStream()) {
                PRStream stream = (PRStream)obj;
                byte[] b;
                try {
                    b = PdfReader.getStreamBytes(stream);
                }
                catch(UnsupportedPdfException e) {
                    b = PdfReader.getStreamBytesRaw(stream);
                }
            }
        }
    }

    public void splintingTables(String dest) throws DocumentException, IOException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.setMargins(15, 15, 55, 35);
        document.open();
        String[] header = new String[] { "Header1", "Header2", "Header3",
                "Header4", "Header5" };
        String[] content = new String[] { "column 1", "column 2",
                "some Text in column 3", "Test data ", "column 5" };
        PdfPTable table = new PdfPTable(header.length);
        table.setHeaderRows(1);
        table.setWidths(new int[] { 3, 2, 4, 3, 2 });
        table.setWidthPercentage(98);
        table.setSpacingBefore(15);
        table.setSplitLate(false);
        for (String columnHeader : header) {
            PdfPCell headerCell = new PdfPCell();
            headerCell.addElement(new Phrase(columnHeader, FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            headerCell.setBorderColor(BaseColor.LIGHT_GRAY);
            headerCell.setPadding(8);
            table.addCell(headerCell);
        }
        for (int i = 0; i < 15; i++) {
            int j = 0;
            for (String text : content) {
                if (i == 13 && j == 3) {
                    text = "Test data \n Test data \n Test data";
                }
                j++;
                PdfPCell cell = new PdfPCell();
                cell.addElement(new Phrase(text, FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
                cell.setBorderColor(BaseColor.LIGHT_GRAY);
                cell.setPadding(5);
                table.addCell(cell);
            }
        }
        document.add(table);
        document.add(new Phrase("\n"));
        LineSeparator separator = new LineSeparator();
        separator.setPercentage(98);
        separator.setLineColor(BaseColor.LIGHT_GRAY);
        Chunk linebreak = new Chunk(separator);
        document.add(linebreak);
        for (int k = 0; k < 5; k++) {
            Paragraph info = new Paragraph("Some title", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL));
            info.setSpacingBefore(12f);
            document.add(info);
            table = new PdfPTable(header.length);
            table.setHeaderRows(1);
            table.setWidths(new int[] { 3, 2, 4, 3, 2 });
            table.setWidthPercentage(98);
            table.setSpacingBefore(15);
            table.setSplitLate(false);
            for (String columnHeader : header) {
                PdfPCell headerCell = new PdfPCell();
                headerCell.addElement(new Phrase(columnHeader, FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
                headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                headerCell.setBorderColor(BaseColor.LIGHT_GRAY);
                headerCell.setPadding(8);
                table.addCell(headerCell);
            }
            for (String text : content) {
                PdfPCell cell = new PdfPCell();
                cell.addElement(new Phrase(text, FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
                cell.setBorderColor(BaseColor.LIGHT_GRAY);
                cell.setPadding(5);
                table.addCell(cell);
            }
            document.add(table);
            separator = new LineSeparator();
            separator.setPercentage(98);
            separator.setLineColor(BaseColor.LIGHT_GRAY);
            linebreak = new Chunk(separator);
            document.add(linebreak);
        }
        document.close();
    }
}
