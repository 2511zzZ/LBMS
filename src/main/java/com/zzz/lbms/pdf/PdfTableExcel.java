package com.zzz.lbms.pdf;

import com.itextpdf.text.Font;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cary on 6/15/17.
 */
public class PdfTableExcel {
    protected ExcelObject excelObject;
    protected Excel excel;
    protected boolean setting = false;

    /**
     * <p>Description: Constructor</p>
     *
     * @param excelObject
     */
    public PdfTableExcel(ExcelObject excelObject) {
        this.excelObject = excelObject;
        this.excel = excelObject.getExcel();
    }

    /**
     * <p>Description: 获取转换过的Excel内容Table</p>
     *
     * @return PdfPTable
     * @throws BadElementException
     * @throws MalformedURLException
     * @throws IOException
     */
    public PdfPTable getTable() throws BadElementException, MalformedURLException, IOException {
        Sheet sheet = this.excel.getSheet();
        return toParseContent(sheet);
    }

//    private PdfPCell createPdfPCell(int colIndex, int rowIndex, Cell cell) {
//
//    }

    protected PdfPTable toParseContent(Sheet sheet) throws BadElementException, MalformedURLException, IOException {
        int rows = sheet.getPhysicalNumberOfRows();

        List<PdfPCell> cells = new ArrayList<PdfPCell>();
        float[] widths = null;
        float mw = 0;
        for (int i = 0; i < rows; i++) {
            Row row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            int columns = row.getLastCellNum();

            float[] cws = new float[columns];
            for (int j = 0; j < columns; j++) {
                Cell cell = row.getCell(j);
                if (cell == null) {
                    cell = row.createCell(j);
                }
                float cw = getPOIColumnWidth(cell);
                cws[cell.getColumnIndex()] = cw;

//                if(isUsed(cell.getColumnIndex(), row.getRowNum())){
//                    continue;
//                }

                PdfPCell pdfpCell = new PdfPCell();
                pdfpCell.setBackgroundColor(new BaseColor(POIUtil.getRGB(
                        cell.getCellStyle().getFillForegroundColorColor())));
                pdfpCell.setVerticalAlignment(getVAlignByExcel(cell.getCellStyle().getVerticalAlignmentEnum().getCode()));
                pdfpCell.setHorizontalAlignment(getHAlignByExcel(cell.getCellStyle().getAlignmentEnum().getCode()));

                if (sheet.getDefaultRowHeightInPoints() != row.getHeightInPoints()) {
                    pdfpCell.setFixedHeight(this.getPixelHeight(row.getHeightInPoints()));
                }
                pdfpCell.setPhrase(getPhrase(cell));
                addImageByPOICell(pdfpCell, cell, cw);
                addBorderByExcel(pdfpCell, cell.getCellStyle());
                // 执行此方法在poi导出为 Workbook 是 SXSSFWorkbook的类型时，此方法会导致转换cell 为""
                //cell.setCellType(CellType.STRING);

                CellRangeAddress range = getColspanRowspanByExcel(row.getRowNum(), cell.getColumnIndex());
                int rowspan = 1;
                int colspan = 1;
                if (range != null) {
                    rowspan = range.getLastRow() - range.getFirstRow() + 1;
                    colspan = range.getLastColumn() - range.getFirstColumn() + 1;
                }
                pdfpCell.setColspan(colspan);
                pdfpCell.setRowspan(rowspan);

                cells.add(pdfpCell);
                j += colspan - 1;
            }

            float rw = 0;
            for (int j = 0; j < cws.length; j++) {
                rw += cws[j];
            }
            if (rw > mw || mw == 0) {
                widths = cws;
                mw = rw;
            }
        }

        PdfPTable table = new PdfPTable(widths);
        table.setWidthPercentage(100);
//        table.setLockedWidth(true);
        for (PdfPCell pdfpCell : cells) {
            table.addCell(pdfpCell);
        }
        return table;
    }


    protected Phrase getPhrase(Cell cell) {
        if (this.setting || this.excelObject.getAnchorName() == null) {
            return new Phrase(String.valueOf(getCellValue(cell)), getFontByExcel(cell.getCellStyle()));
        }
        Anchor anchor = new Anchor(String.valueOf(getCellValue(cell)), getFontByExcel(cell.getCellStyle()));
        anchor.setName(this.excelObject.getAnchorName());
        this.setting = true;
        return anchor;
    }


    /**
     * 获取单元格值
     *
     * @return 单元格值
     */
    public Object getCellValue(Cell cell) {
        Object val = "";
        try {
            if (cell != null) {
                if (cell.getCellTypeEnum() == CellType.NUMERIC || cell.getCellTypeEnum() == CellType.FORMULA) {
                    val = cell.getNumericCellValue();
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        // POI Excel 日期格式转换
                        val = DateUtil.getJavaDate((Double) val);
                    } else {
                        if ((Double) val % 1 > 0) {
                            val = new DecimalFormat("0.00").format(val);
                        } else {
                            val = new DecimalFormat("0").format(val);
                        }
                    }
                } else if (cell.getCellTypeEnum() == CellType.STRING) {
                    val = cell.getStringCellValue();
                } else if (cell.getCellTypeEnum() == CellType.BOOLEAN) {
                    val = cell.getBooleanCellValue();
                } else if (cell.getCellTypeEnum() == CellType.ERROR) {
                    val = cell.getErrorCellValue();
                }

            }
        } catch (Exception e) {
            return val;
        }
        return val;
    }


    protected void addImageByPOICell(PdfPCell pdfpCell, Cell cell, float cellWidth) throws BadElementException, MalformedURLException, IOException {
        POIImage poiImage = new POIImage().getCellImage(cell);
        byte[] bytes = poiImage.getBytes();
        if (bytes != null) {
//           double cw = cellWidth;
//           double ch = pdfpCell.getFixedHeight();
//
//           double iw = poiImage.getDimension().getWidth();
//           double ih = poiImage.getDimension().getHeight();
//
//           double scale = cw / ch;
//
//           double nw = iw * scale;
//           double nh = ih - (iw - nw);
//
//           POIUtil.scale(bytes , nw  , nh);
            pdfpCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            pdfpCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            Image image = Image.getInstance(bytes);
            pdfpCell.setImage(image);
        }
    }

    protected float getPixelHeight(float poiHeight) {
        float pixel = poiHeight / 28.6f * 26f;
        return pixel;
    }

    /**
     * <p>Description: 此处获取Excel的列宽像素(无法精确实现,期待有能力的朋友进行改善此处)</p>
     *
     * @param cell
     * @return 像素宽
     */
    protected int getPOIColumnWidth(Cell cell) {
        int poiCWidth = excel.getSheet().getColumnWidth(cell.getColumnIndex());
        // com.itextpdf.text.pdf.PdfPTable.calculateWidths,此方法已经等比例转换了。不知道为什么还需要转换
        // int colWidthpoi = poiCWidth;
        // int widthPixel = 0;
        // if (colWidthpoi >= 416) {
        //     widthPixel = (int) (((colWidthpoi - 416.0) / 256.0) * 8.0 + 13.0 + 0.5);
        // } else {
        //     widthPixel = (int) (colWidthpoi / 416.0 * 13.0 + 0.5);
        // }
        return poiCWidth;
    }

    protected CellRangeAddress getColspanRowspanByExcel(int rowIndex, int colIndex) {
        CellRangeAddress result = null;
        Sheet sheet = excel.getSheet();
        int num = sheet.getNumMergedRegions();
        for (int i = 0; i < num; i++) {
            CellRangeAddress range = sheet.getMergedRegion(i);
            if (range.getFirstColumn() == colIndex && range.getFirstRow() == rowIndex) {
                result = range;
            }
        }
        return result;
    }

    protected boolean isUsed(int colIndex, int rowIndex) {
        boolean result = false;
        Sheet sheet = excel.getSheet();
        int num = sheet.getNumMergedRegions();
        for (int i = 0; i < num; i++) {
            CellRangeAddress range = sheet.getMergedRegion(i);
            int firstRow = range.getFirstRow();
            int lastRow = range.getLastRow();
            int firstColumn = range.getFirstColumn();
            int lastColumn = range.getLastColumn();
            if (firstRow < rowIndex && lastRow >= rowIndex) {
                if (firstColumn <= colIndex && lastColumn >= colIndex) {
                    result = true;
                }
            }
        }
        return result;
    }

    protected Font getFontByExcel(CellStyle style) {
        Font result = new Font(Resource.BASE_FONT_CHINESE, 8, Font.NORMAL);
        Workbook wb = excel.getWorkbook();

        short index = style.getFontIndex();
        org.apache.poi.ss.usermodel.Font font = wb.getFontAt(index);

        if (font.getBold()) {
            result.setStyle(Font.BOLD);
        }

        HSSFColor color = HSSFColor.getIndexHash().get(font.getColor());

        if (color != null) {
            int rbg = POIUtil.getRGB(color);
            result.setColor(new BaseColor(rbg));
        }

        FontUnderline underline = FontUnderline.valueOf(font.getUnderline());
        if (underline == FontUnderline.SINGLE) {
            String ulString = Font.FontStyle.UNDERLINE.getValue();
            result.setStyle(ulString);
        }
        return result;
    }

    protected void addBorderByExcel(PdfPCell cell, CellStyle style) {
        Workbook wb = excel.getWorkbook();
        cell.setBorderColorLeft(new BaseColor(POIUtil.getBorderRBG(wb, style.getLeftBorderColor())));
        cell.setBorderColorRight(new BaseColor(POIUtil.getBorderRBG(wb, style.getRightBorderColor())));
        cell.setBorderColorTop(new BaseColor(POIUtil.getBorderRBG(wb, style.getTopBorderColor())));
        cell.setBorderColorBottom(new BaseColor(POIUtil.getBorderRBG(wb, style.getBottomBorderColor())));
    }

    protected int getVAlignByExcel(short align) {
        int result = 0;
        if (align == VerticalAlignment.BOTTOM.getCode()) {
            result = Element.ALIGN_BOTTOM;
        }
        if (align == VerticalAlignment.CENTER.getCode()) {
            result = Element.ALIGN_MIDDLE;
        }
        if (align == VerticalAlignment.JUSTIFY.getCode()) {
            result = Element.ALIGN_JUSTIFIED;
        }
        if (align == VerticalAlignment.TOP.getCode()) {
            result = Element.ALIGN_TOP;
        }
        return result;
    }

    protected int getHAlignByExcel(short align) {
        int result = 0;
        if (align == HorizontalAlignment.LEFT.getCode()) {
            result = Element.ALIGN_LEFT;
        }
        if (align == HorizontalAlignment.RIGHT.getCode()) {
            result = Element.ALIGN_RIGHT;
        }
        if (align == HorizontalAlignment.JUSTIFY.getCode()) {
            result = Element.ALIGN_JUSTIFIED;
        }
        if (align == HorizontalAlignment.CENTER.getCode()) {
            result = Element.ALIGN_CENTER;
        }
        return result;
    }
}