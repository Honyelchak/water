package edu.cug.water.crawler.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRegex {

    public static void main(String[] args) {
        String s = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                "<head>\n" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=gb2312\" />\n" +
                "<title>����ί��ˮ�鹫�ڷ���</title>\n" +
                "<style type=\"text/css\">\n" +
                "<!--\n" +
                "\n" +
                "body {\n" +
                "\tmargin-left: 0px;\n" +
                "\tmargin-top: 0px;\n" +
                "\tmargin-right: 0px;\n" +
                "\tmargin-bottom: 0px;\n" +
                "}\n" +
                "\n" +
                "\n" +
                "\n" +
                "-->\n" +
                "</style>\n" +
                "<SCRIPT LANGUAGE=\"JavaScript\">\n" +
                "\n" +
                "\tfunction cvt(sel){\n" +
                "\t\tsel = sel.replace('��','');\n" +
                "\t\tif(sel){\n" +
                "\t\t\tif(sel != '--'){\n" +
                "\t\t\t    var tmp1 = sel.indexOf('.') + 1;\n" +
                "\t\t\t    var tmp2 = sel.length;\n" +
                "\t\t\t   \n" +
                "\t\t\t    var tmp = tmp2 - tmp1;\n" +
                "\n" +
                "\t\t\t    //alert(tmp1);\n" +
                "\t\t\t    //alert(tmp2);\n" +
                "\t\t\t    //alert(tmp);\n" +
                "\n" +
                "\t\t\t    if(tmp1 == '0'){sel += '.00';}\n" +
                "\t\t\t    if(tmp == '1'){ sel += '0';}\n" +
                "\t\t\t\t\n" +
                "\t\t\t\tdocument.write(sel);\n" +
                "\t\t    }\n" +
                "\t\t\tif(sel == '--'){\n" +
                "\t\t\t\tdocument.write('--');\n" +
                "\t\t\t}\n" +
                "\t\t}else{\n" +
                "\t\t\tdocument.write('--');\n" +
                "\t\t}\n" +
                "\t\t\n" +
                "\t   \n" +
                "\t}\n" +
                "\n" +
                " </SCRIPT>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div style='width:248px;margin-left:2px;margin-top:5px'>\n" +
                "<table width=\"248\" border=\"1\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "<tr>\n" +
                "<td bgcolor=\"#abc2eb\" height=\"25\" align=\"center\" style=\"width: 55px;\"><font style=\"font-size:12px\">վ(��)��</font></td>\n" +
                "<td bgcolor=\"#abc2eb\" height=\"25\" align=\"center\" style=\"width: 50px;\"><font style=\"font-size:12px\">ʱ��</font></td>\n" +
                "<td bgcolor=\"#abc2eb\" height=\"25\" align=\"center\" style=\"width: 45px;\"><font style=\"font-size:12px\">ˮλ(m)</font></td>\n" +
                "<td bgcolor=\"#abc2eb\" height=\"25\" align=\"center\" style=\"width: 70px;\"><font style=\"font-size:12px\">����(m<sup>3</sup>/s)</font></td>\n" +
                "<td bgcolor=\"#abc2eb\" height=\"25\" align=\"center\" style=\"width: 25px;\"><font style=\"font-size:12px\">����</font></td> \n" +
                " </tr>\n" +
                "\n" +
                "\n" +
                "<tr>\n" +
                "         \n" +
                "\t\t\n" +
                "<td bgcolor=\"#ffffff\" height=\"25\"  align='center'><font style=\"font-size:12px\">������                        </font></td>\n" +
                "<td bgcolor=\"#ffffff\" height=\"25\"  align='center'><font style=\"font-size:12px\">17��8ʱ</font></td> \n" +
                "<td bgcolor=\"#ffffff\" height=\"25\"  align='center'><font style=\"font-size:12px\"><SCRIPT>cvt('118.07');</SCRIPT></font></td> \n" +
                "<td bgcolor=\"#ffffff\" height=\"25\"  align='center'><font style=\"font-size:12px\">7480</font></td>\n" +
                "<td bgcolor=\"#ffffff\" height=\"25\"  align='center'><font style=\"font-size:12px\"><img src=http://www.slwr.gov.cn/swjgzfw/images/up.gif></font></td>\n" +
                "\n" +
                "  </tr>\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "<tr>\n" +
                "         \n" +
                "\t\t\n" +
                "<td bgcolor=\"#ffffff\" height=\"25\"  align='center'><font style=\"font-size:12px\">����                          </font></td>\n" +
                "<td bgcolor=\"#ffffff\" height=\"25\"  align='center'><font style=\"font-size:12px\">17��8ʱ</font></td> \n" +
                "<td bgcolor=\"#ffffff\" height=\"25\"  align='center'><font style=\"font-size:12px\"><SCRIPT>cvt('131.48');</SCRIPT></font></td> \n" +
                "<td bgcolor=\"#ffffff\" height=\"25\"  align='center'><font style=\"font-size:12px\">1450</font></td>\n" +
                "<td bgcolor=\"#ffffff\" height=\"25\"  align='center'><font style=\"font-size:12px\"><img src=http://www.slwr.gov.cn/swjgzfw/images/up.gif></font></td>\n" +
                "\n" +
                "  </tr>\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "<tr>\n" +
                "         \n" +
                "\t\t\n" +
                "<td bgcolor=\"#ffffff\" height=\"25\"  align='center'><font style=\"font-size:12px\">����                          </font></td>\n" +
                "<td bgcolor=\"#ffffff\" height=\"25\"  align='center'><font style=\"font-size:12px\">17��8ʱ</font></td> \n" +
                "<td bgcolor=\"#ffffff\" height=\"25\"  align='center'><font style=\"font-size:12px\"><SCRIPT>cvt('140.94');</SCRIPT></font></td> \n" +
                "<td bgcolor=\"#ffffff\" height=\"25\"  align='center'><font style=\"font-size:12px\">6430</font></td>\n" +
                "<td bgcolor=\"#ffffff\" height=\"25\"  align='center'><font style=\"font-size:12px\"><img src=http://www.slwr.gov.cn/swjgzfw/images/equal.gif></font></td>\n" +
                "\n" +
                "  </tr>\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "<tr>\n" +
                "         \n" +
                "\t\t\n" +
                "<td bgcolor=\"#ffffff\" height=\"25\"  align='center'><font style=\"font-size:12px\">����                          </font></td>\n" +
                "<td bgcolor=\"#ffffff\" height=\"25\"  align='center'><font style=\"font-size:12px\">17��8ʱ</font></td> \n" +
                "<td bgcolor=\"#ffffff\" height=\"25\"  align='center'><font style=\"font-size:12px\"><SCRIPT>cvt('132.29');</SCRIPT></font></td> \n" +
                "<td bgcolor=\"#ffffff\" height=\"25\"  align='center'><font style=\"font-size:12px\">7130</font></td>\n" +
                "<td bgcolor=\"#ffffff\" height=\"25\"  align='center'><font style=\"font-size:12px\"><img src=http://www.slwr.gov.cn/swjgzfw/images/up.gif></font></td>\n" +
                "\n" +
                "  </tr>\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "<tr>\n" +
                "         \n" +
                "\t\t\n" +
                "<td bgcolor=\"#ffffff\" height=\"25\"  align='center'><font style=\"font-size:12px\">����                          </font></td>\n" +
                "<td bgcolor=\"#ffffff\" height=\"25\"  align='center'><font style=\"font-size:12px\">17��8ʱ</font></td> \n" +
                "<td bgcolor=\"#ffffff\" height=\"25\"  align='center'><font style=\"font-size:12px\"><SCRIPT>cvt('53.295');</SCRIPT></font></td> \n" +
                "<td bgcolor=\"#ffffff\" height=\"25\"  align='center'><font style=\"font-size:12px\">137</font></td>\n" +
                "<td bgcolor=\"#ffffff\" height=\"25\"  align='center'><font style=\"font-size:12px\"><img src=http://www.slwr.gov.cn/swjgzfw/images/up.gif></font></td>\n" +
                "\n" +
                "  </tr>\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "<tr>\n" +
                "         \n" +
                "\t\t\n" +
                "<td bgcolor=\"#ffffff\" height=\"25\"  align='center'><font style=\"font-size:12px\">����                          </font></td>\n" +
                "<td bgcolor=\"#ffffff\" height=\"25\"  align='center'><font style=\"font-size:12px\">17��8ʱ</font></td> \n" +
                "<td bgcolor=\"#ffffff\" height=\"25\"  align='center'><font style=\"font-size:12px\"><SCRIPT>cvt('37.09');</SCRIPT></font></td> \n" +
                "<td bgcolor=\"#ffffff\" height=\"25\"  align='center'><font style=\"font-size:12px\">27</font></td>\n" +
                "<td bgcolor=\"#ffffff\" height=\"25\"  align='center'><font style=\"font-size:12px\"><img src=http://www.slwr.gov.cn/swjgzfw/images/down.gif></font></td>\n" +
                "\n" +
                "  </tr>\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "<tr>\n" +
                "\t\t\n" +
                "<td bgcolor=\"#ffffff\" height=\"25\"  align='center'><font style=\"font-size:12px\">����</font></td>\n" +
                "<td bgcolor=\"#ffffff\" height=\"25\"  align='center'><font style=\"font-size:12px\">17��8ʱ</font></td> \n" +
                "<td bgcolor=\"#ffffff\" height=\"25\"  align='center'><font style=\"font-size:12px\">253.74</font></td> \n" +
                "<td bgcolor=\"#ffffff\" height=\"25\"  align='center'><font style=\"font-size:12px\">1023(���)</font></td>\n" +
                "<td bgcolor=\"#ffffff\" height=\"25\"  align='center'><font style=\"font-size:12px\"><img src=http://www.slwr.gov.cn/swjgzfw/images/up.gif></font></td>\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "<tr>\n" +
                "\t\t\n" +
                "<td bgcolor=\"#ffffff\" height=\"25\"  align='center'><font style=\"font-size:12px\">�����</font></td>\n" +
                "<td bgcolor=\"#ffffff\" height=\"25\"  align='center'><font style=\"font-size:12px\">17��8ʱ</font></td> \n" +
                "<td bgcolor=\"#ffffff\" height=\"25\"  align='center'><font style=\"font-size:12px\">212.96</font></td> \n" +
                "<td bgcolor=\"#ffffff\" height=\"25\"  align='center'><font style=\"font-size:12px\">1380(���)</font></td>\n" +
                "<td bgcolor=\"#ffffff\" height=\"25\"  align='center'><font style=\"font-size:12px\"><img src=http://www.slwr.gov.cn/swjgzfw/images/equal.gif></font></td>\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "<tr>\n" +
                "\t\t\n" +
                "<td bgcolor=\"#ffffff\" height=\"25\"  align='center'><font style=\"font-size:12px\">ʯ����</font></td>\n" +
                "<td bgcolor=\"#ffffff\" height=\"25\"  align='center'><font style=\"font-size:12px\">17��8ʱ</font></td> \n" +
                "<td bgcolor=\"#ffffff\" height=\"25\"  align='center'><font style=\"font-size:12px\">46.1</font></td> \n" +
                "<td bgcolor=\"#ffffff\" height=\"25\"  align='center'><font style=\"font-size:12px\">173(���)</font></td>\n" +
                "<td bgcolor=\"#ffffff\" height=\"25\"  align='center'><font style=\"font-size:12px\"><img src=http://www.slwr.gov.cn/swjgzfw/images/down.gif></font></td>\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "</table>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>";

        String REGEX =  "<font style=\"font-size:12px\">(.*)</font>";
        String REGEX_FLOW = "<SCRIPT>cvt\\('(.*)'\\);</SCRIPT>";
        Pattern pattern = Pattern.compile(REGEX);
        Pattern patternFlow = Pattern.compile(REGEX_FLOW);
        Matcher matcher = pattern.matcher(s);

        while(matcher.find()){
            System.out.println(matcher.group(1).trim());
        }

    }
}
