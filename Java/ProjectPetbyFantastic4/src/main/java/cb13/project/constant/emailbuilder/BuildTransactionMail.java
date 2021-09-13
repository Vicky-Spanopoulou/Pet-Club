package cb13.project.constant.emailbuilder;

import java.util.Date;

public class BuildTransactionMail {

    public static final String transactionMail(Date date, String companyName, String address, Long id,
                                               String phone , String type, String email, String financialService,
                                               String vatNumber, String paid, String sub_name, String sub_price,
                                               int sub_duration, String sub_role, boolean advForEver){
        
   
       
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Document</title>\n" +
                "    <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\n" +
                "<link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\n" +
                "<link href=\"https://fonts.googleapis.com/css2?family=Roboto:wght@700&display=swap\" rel=\"stylesheet\">\n" +
                "    <style>\n" +
                "        .paymentSelected{\n" +
                "    width: 100%;\n" +
                "    color: green; /* var(--first-color) */\n" +
                "  \n" +
                "  }\n" +
                " \n" +
                "  \n" +
                "  #invoice{\n" +
                "      margin-top: 50px;\n" +
                "      margin-left: 250px;\n" +
                "      width: calc(100% - 500px) ;\n" +
                "      align-items: center;\n" +
                "      height: 100%;\n" +
                "      font-family: 'Times New Roman', Times, serif;\n" +
                "      font-size: x-large;\n" +
                "      \n" +
                "  \n" +
                "  }\n" +
                "  #invoice #date-invoice{\n" +
                "      width: 100%;\n" +
                "      display: inline-block;\n" +
                "  }\n" +
                " #invoice img{\n" +
                "      float: left;\n" +
                "      margin-top: 75px;\n" +
                "      width: 300px;\n" +
                "      height: 150px;\n" +
                "  }\n" +
                " #invoice #date-invoice #container{\n" +
                "      margin-top: 100px;\n" +
                "      float: right;\n" +
                "  }\n" +
                "  \n" +
                " #invoice #date-invoice h3{\n" +
                "      border-bottom: 2px solid black;\n" +
                "  }\n" +
                " #invoice #date-invoice span{\n" +
                "      font-weight: bold;\n" +
                "      margin-right: 30px;\n" +
                "  }\n" +
                " #invoice #info-invoice{\n" +
                "      width: 100%;\n" +
                "      height: 500px;\n" +
                "      border-top: 1px solid black;\n" +
                "      border-bottom: 1px dashed black;\n" +
                "  }\n" +
                " #invoice #info-invoice p{\n" +
                "      padding-bottom: 50px;\n" +
                "  }\n" +
                "  #invoice #info-invoice #left-info{\n" +
                "      width: 50%;\n" +
                "      float: left;\n" +
                "  }\n" +
                "#invoice #info-invoice #right-info{\n" +
                "      width: 50%;\n" +
                "      float: right;\n" +
                "  }\n" +
                " #invoice #amount-invoice{\n" +
                "      width: 100%;\n" +
                "  }\n" +
                "  \n" +
                " #invoice #amount-invoice table{\n" +
                "      margin-top: 30px;\n" +
                "      /* margin-left: 200px; */\n" +
                "      vertical-align: center;\n" +
                "      text-align: center;\n" +
                "      font-family: 'Times New Roman', Times, serif;;\n" +
                "      font-size: large;\n" +
                "      width: 100%;\n" +
                "      height: 100%;\n" +
                "      border-bottom: 1px solid gray ;\n" +
                "      border-spacing: 0px;\n" +
                "    }\n" +
                "  \n" +
                " #invoice #amount-invoice table th:first-child{\n" +
                "      border-top-left-radius: 8px ;\n" +
                "      border-bottom-left-radius: 8px ;\n" +
                "  }\n" +
                "#invoice #amount-invoice table  th:last-child{\n" +
                "    border-top-right-radius: 8px ;\n" +
                "    border-bottom-right-radius: 8px ;\n" +
                "  }\n" +
                "  \n" +
                " #invoice #amount-invoice table tbody{\n" +
                "    vertical-align: top;\n" +
                "  }\n" +
                " #invoice #amount-invoice table td,th{\n" +
                "    line-height: 40px;\n" +
                "    padding: 5px;\n" +
                "  }\n" +
                "  \n" +
                " #invoice #amount-invoice table thead {\n" +
                "    width: auto;\n" +
                "    height: 50px;\n" +
                "    background-color: burlywood;\n" +
                "    color: white;\n" +
                "  }\n" +
                "  \n" +
                " #invoice #amount-invoice #total{\n" +
                "      font-size: x-large;\n" +
                "      float: right;\n" +
                "  }\n" +
                "\n" +
                "  #invoice #info-invoice #right-info #paid{\n" +
                "    font-family: 'Times New Roman', Times, serif;;\n" +
                "    border: none;\n" +
                "    width: 35%;\n" +
                "    font-size: large;\n" +
                "  }\n" +
                "\n" +
                "  h5{\n" +
                "      font-family: 'Roboto';\n" +
                "  }\n" +
                "  \n" +
                "  \n" +
                "  \n" +
                "  @media screen and (max-width:1400px)  {\n" +
                "    #mid-down{\n" +
                "      margin: 0;\n" +
                "    }\n" +
                " #invoice{\n" +
                "      width: calc(100% - 500px);\n" +
                "    }\n" +
                "  #invoice #date-invoice {\n" +
                "      width: 100%;\n" +
                "    }\n" +
                "   \n" +
                " #invoice #info-invoice{\n" +
                "      width: 100%;\n" +
                "      height: 100%;\n" +
                "    }\n" +
                " #invoice #info-invoice #left-info{\n" +
                "      width: 100%;\n" +
                "    }\n" +
                "    #invoice #info-invoice #right-info{\n" +
                "      width: 100%;\n" +
                "      float: left;\n" +
                "    }\n" +
                " #invoice #amount-invoice{\n" +
                "      position: relative;\n" +
                "    }\n" +
                "#invoice #amount-invoice table{\n" +
                "      margin: 0;\n" +
                "    }\n" +
                " #invoice #amount-invoice #total{\n" +
                "      margin-right:0px;\n" +
                "      /* float: right; */\n" +
                "    }\n" +
                " #invoice #amount-invoice #total span{\n" +
                "      margin:0;\n" +
                "    }\n" +
                "  }\n" +
                "  @media screen and (max-width: 850px){\n" +
                "    #mid{\n" +
                "      margin: 0;\n" +
                "    }\n" +
                "   \n" +
                "    #mid #mid-table table thead tr th {\n" +
                "      font-size: small;\n" +
                "      \n" +
                "    }\n" +
                "    #dashboard-pages-container  #mid{\n" +
                "      width: 500px;\n" +
                "    }\n" +
                "    #dashboard-pages-container  #mid #mid-table table{\n" +
                "      width: 500px;\n" +
                "    }\n" +
                "    #mid-down{\n" +
                "      margin-left: 0px;\n" +
                "      padding:0;\n" +
                "      width: 200px;\n" +
                "      \n" +
                "    }\n" +
                " #invoice{\n" +
                "      margin-left: 0;\n" +
                "      margin-top: 20px;\n" +
                "    }\n" +
                " #invoice #date-invoice {\n" +
                "      width: 100%;\n" +
                "    }\n" +
                "#invoice #date-invoice img{\n" +
                "      margin-top: 10px;\n" +
                "    }\n" +
                " #invoice #date-invoice #container{\n" +
                "      margin-top: 0;\n" +
                "      margin-left: 2px;\n" +
                "      display: block;\n" +
                "      width: 100%;\n" +
                "    }\n" +
                " #invoice #date-invoice h3{\n" +
                "      width: 100px;\n" +
                "    }\n" +
                " #invoice #info-invoice{\n" +
                "      width: 300px;\n" +
                "    }\n" +
                " #invoice #amount-invoice table{\n" +
                "      margin: 0;\n" +
                "    }\n" +
                " #invoice #amount-invoice #total{\n" +
                "      margin:0;\n" +
                "      float: left;\n" +
                "    }\n" +
                " #invoice #amount-invoice #total span{\n" +
                "      margin: 0;\n" +
                "    }\n" +
                "  }\n" +
                "  @media screen and (max-width: 500px){\n" +
                "\n" +
                "    #invoice #date-invoice {\n" +
                "      display: block;\n" +
                "      float: left;\n" +
                "    }\n" +
                "    #invoice #info-invoice{\n" +
                "      padding-top: 450px;\n" +
                "      display: block;\n" +
                "      float: left;\n" +
                "    }\n" +
                "\n" +
                "  }\n" +
                "  \n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div id=\"invoice\">\n" +
                "        <div id=\"date-invoice\">\n" +
                "            <a href=\"https://ibb.co/ysmgFFH\"><img src=\"https://i.ibb.co/f0Vrddj/logo.png\" alt=\"logo\" border=\"0\"></a>\n" +
                "            <div id=\"container\">\n" +
                "                <h3>Invoice</h3>\n" +
                "                <p>"+date+"</p>\n" +
                "                <p><span>Invoice Number :</span>"+id+"</p>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div id=\"info-invoice\" >\n" +
                "            <div id=\"left-info\">\n" +
                "                <h5 >Company's Name :</h5>\n" +
                "                <p>"+companyName+"</p>\n" +
                "                <h5>Address :</h5>\n" +
                "                <p>"+address+"</p>\n" +
                "                <h5>Phone :</h5>\n" +
                "                <p>"+phone+"</p> \n" +
                "                <h5>Type Of Trasanction :</h5>\n" +
                "                <p>"+type+"</p>\n" +
                "    \n" +
                "            </div>\n" +
                "            <div id=\"right-info\">\n" +
                "                <h5>Email :</h5>\n" +
                "                <p>"+email+"</p>\n" +
                "                <h5>Public Financial Service :</h5>\n" +
                "                <p>"+financialService+"</p>\n" +
                "                <h5>VAT Number :</h5>\n" +
                "                <p>"+vatNumber+"</p>\n" +
                "                <h5>Ιnvoicing Statement</h5>\n" +
                "                <p>Status</p>\n" +
                "                <h5>"+paid+"</h5>\n" +
                "                \n" +
                "        \n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div id=\"amount-invoice\" >\n" +
                "            <table>\n" +
                "                <thead>\n" +
                "                    <th>Description</th>\n" +
                "                    <th>Duration Days</th>\n" +
                "                    <th>Adv. Forever</th>\n" +
                "                    <th>Role</th>\n" +
                "                    <th>Price</th>\n" +
                "                </thead>\n" +
                "                <tbody>\n" +
                "                    <td>"+sub_name+"</td>\n" +
                "                    <td>"+sub_duration+"</td>\n" +
                "                    <td >"+advForEver+"</td>\n" +
                "                    <td>"+sub_role+"</td> \n" +
                "                    <td>"+sub_price+" $</td>\n" +
                "                </tbody>\n" +
                "            </table>\n" +
                "            <div id=\"total\">\n" +
                "                <p>Total : <span>"+sub_price+" $</span></p>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    \n" +
                "</body>\n" +
                "</html>";
    }
}
