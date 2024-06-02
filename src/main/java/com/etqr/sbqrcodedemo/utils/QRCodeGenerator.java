package com.etqr.sbqrcodedemo.utils;

import com.etqr.sbqrcodedemo.model.order.Order;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class QRCodeGenerator {

    public static String generateQRCode(Order order) throws WriterException, IOException {
        String qrCodePath = "E:\\ENGINEERING\\FOE-UOR\\SEM 5\\Software Project\\QR\\4-23\\";
        String qrCodeName = qrCodePath + "Order-" + order.getOrderId() + "-QRCODE.png";
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(
                "NIC: " + order.getUserInfo().getNic() + "\n" +
                        "Phone: " + order.getUserInfo().getPhone() + "\n" +
                        "Email: " + order.getUserInfo().getEmail() + "\n" +
                        "Order ID: " + order.getOrderId(),
                BarcodeFormat.QR_CODE, 400, 400);
        Path path = FileSystems.getDefault().getPath(qrCodeName);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

        String pdfPath = qrCodePath + "Order-" + order.getOrderId() + "-QRCODE.pdf";
        PdfWriter writer = new PdfWriter(pdfPath);
        PdfDocument pdfDoc = new PdfDocument(writer);
        pdfDoc.setDefaultPageSize(PageSize.A4);
        Document document = new Document(pdfDoc);

        // Adding text and QR code to PDF
        document.add(new Paragraph("EasyTicket.LK | Click, Book and Enjoy").setFontSize(26).setTextAlignment(TextAlignment.CENTER));
        document.add(new Paragraph("-----------------------------------------------------------------------------------------------------------").setFontSize(14).setTextAlignment(TextAlignment.CENTER));
        document.add(new Paragraph("This Is Your E-Ticket").setFontSize(16).setTextAlignment(TextAlignment.CENTER));

        // Fetch event details from EventCards entity
        String eventName = order.getEventCards().getEventName();
        String eventDate = order.getEventCards().getEventDate();
        String eventTime = order.getEventCards().getEventTime();

        document.add(new Paragraph(eventName).setFontSize(22).setTextAlignment(TextAlignment.CENTER));

        Image qrCodeImage = new Image(ImageDataFactory.create(qrCodeName)).setWidth(300).setHeight(300);
        qrCodeImage.setHorizontalAlignment(HorizontalAlignment.CENTER);
        document.add(qrCodeImage);

        document.add(new Paragraph("Event Date: " + eventDate).setFontSize(16).setTextAlignment(TextAlignment.CENTER));
        document.add(new Paragraph("Event Time: " + eventTime).setFontSize(16).setTextAlignment(TextAlignment.CENTER));
        //document.add(new Paragraph("Ticket Type: " + order.getTicketType()).setFontSize(16).setTextAlignment(TextAlignment.CENTER));
        document.add(new Paragraph("Amount: " + order.getAmount() + " LKR" ).setFontSize(16).setTextAlignment(TextAlignment.CENTER));


        document.add(new Paragraph(" ").setFontSize(14).setTextAlignment(TextAlignment.CENTER));
        document.add(new Paragraph(" ").setFontSize(14).setTextAlignment(TextAlignment.CENTER));
        document.add(new Paragraph("Enjoy Your Event | EasyTicket.LK").setFontSize(14).setTextAlignment(TextAlignment.CENTER));
        document.add(new Paragraph("-----------------------------------------------------------------------------------------------------------").setFontSize(14).setTextAlignment(TextAlignment.CENTER));
        document.add(new Paragraph("Take Charge: Keep Your E-Ticket QR Code Safe").setFontSize(12).setTextAlignment(TextAlignment.CENTER));

        document.close();

        return pdfPath;
    }
}
