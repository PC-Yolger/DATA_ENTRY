<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page  import="java.io.*"%>
<%@page  import="java.sql.*"%>
<%@page  import="java.util.*"%>

<%@page  import="net.sf.jasperreports.engine.*"%>
<%@page  import="net.sf.jasperreports.view.JasperViewer"%>

<%@page  import="javax.servlet.ServletResponse"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Connection con = null;
            try {
                Class.forName("org.postgresql.Driver");
                con = (Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/BD_Servicios", "postgres", "admin");

                File reportFile = new File(application.getRealPath("Report/report.jasper"));
                Map<String, Object> parameter = new HashMap<String, Object>();
                byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), parameter, con);
                response.setContentType("application/pdf");
                response.setContentLength(bytes.length);
                ServletOutputStream outputStream = response.getOutputStream();
                outputStream.write(bytes, 0, bytes.length);
                outputStream.flush();
                outputStream.close();
            } catch (Exception ex) {
            }
        %>
    </body>
</html>
