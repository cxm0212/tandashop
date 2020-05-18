package com.tanda.tdshop.manager.modules.codegenerator;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * create by
 * 三和智控: cxm on 2020/5/15
 */
public class EntityGenerator {
        // 数据库连接
        private String URL;
        private String DBName;
        private String NAME;
        private String PASS;

        private String authorName = "cxm";// 作者名字
        private String[] colnames; // 列名数组
        private String[] colTypes; // 列名类型数组
        private int[] colSizes; // 列名大小数组
        private boolean f_util = false; // 是否需要导入包java.util.*
        private boolean f_sql = false; // 是否需要导入包java.sql.*

        private SqlHelper sqlHelper = null;

        /*
         * 构造函数
         */
        public EntityGenerator(String url, String dbname, String username, String password) {
            this.URL = url + "/" + dbname+"?serverTimezone=UTC";
            this.DBName = dbname;
            this.NAME = username;
            this.PASS = password;

            sqlHelper = new SqlHelper(this.URL, this.NAME, this.PASS);
        }

        public void Generate() {
            List<String> tableNames = sqlHelper.Get(
                    "SELECT * FROM INFORMATION_SCHEMA.TABLES where TABLE_SCHEMA='" + DBName + "';", "TABLE_NAME");
            Connection con = null;
            try {
                con = sqlHelper.getConnection();
            } catch (ClassNotFoundException | SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            File directory = new File("");
            File dir = new File(directory.getAbsolutePath() + "/src/main/java/" + this.getPackageOutPath().replace(".", "/"));
            if(dir.exists()){
                dir.delete();
            }
            dir.mkdirs();

            for (String table : tableNames) {
                Generate(table, con);
                System.out.println("generated: "+table );
                resetTableInfo();
            }
            try {
                sqlHelper.closeConnection(con);
            } catch (ClassNotFoundException | SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        private void resetTableInfo(){
            colnames = null;
            colTypes=null;
            colSizes=null;
            f_util=false;
            f_sql=false;
        }

        private String getPackageOutPath() {
            return "com.tanda.tdshop.entities." + DBName;
        }

        private void Generate(String tablename, Connection con) {
            if (con == null) {
                System.out.println("------------------Connection to database was not set up------------------");
                return;
            }
            // 查要生成实体类的表
            String sql = "SELECT * FROM " + tablename + " limit 0, 1;";
            PreparedStatement pStemt = null;
            try {
                pStemt = con.prepareStatement(sql);
                ResultSetMetaData rsmd = pStemt.getMetaData();
                int size = rsmd.getColumnCount(); // 统计列
                colnames = new String[size];
                colTypes = new String[size];
                colSizes = new int[size];
                for (int i = 0; i < size; i++) {
                    colnames[i] = rsmd.getColumnName(i + 1).replace(" ", "");
                    colTypes[i] = rsmd.getColumnTypeName(i + 1);

                    if (colTypes[i].equalsIgnoreCase("datetime") || colTypes[i].equalsIgnoreCase("date")) {
                        f_util = true;
                    }
                    if (colTypes[i].equalsIgnoreCase("image") || colTypes[i].equalsIgnoreCase("text")
                            || colTypes[i].equalsIgnoreCase("TIMESTAMP")) {
                        f_sql = true;
                    }
                    colSizes[i] = rsmd.getColumnDisplaySize(i + 1);
                }

                String content = parse(colnames, colTypes, colSizes, tablename);

                try {
                    File directory = new File("");
                    File dir = new File(directory.getAbsolutePath() + "/src/main/java/" + this.getPackageOutPath().replace(".", "/"));
//                    dir.mkdir();

                    String outputPath = dir.getAbsolutePath() + "\\" + initcap(tablename) + "Entity.java";
                    FileWriter fw = new FileWriter(outputPath);
                    PrintWriter pw = new PrintWriter(fw);
                    pw.println(content);
                    pw.flush();
                    pw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (pStemt != null) {
                        pStemt.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        /**
         * 功能：生成实体类主体代码
         *
         * @param colnames
         * @param colTypes
         * @param colSizes
         * @return
         */
        private String parse(String[] colnames, String[] colTypes, int[] colSizes, String tablename) {
            StringBuffer sb = new StringBuffer();
            sb.append("package " + this.getPackageOutPath() + ";\r\n");
            // 判断是否导入工具包
            if (f_util) {
                sb.append("import java.util.Date;\r\n");
            }
            if (f_sql) {
                sb.append("import java.sql.*;\r\n");
            }

            sb.append("import lombok.Data;\r\n");
            sb.append("import com.tanda.tdshop.common.BaseEntity;\r\n");
            sb.append("import com.baomidou.mybatisplus.annotations.TableName;\r\n");
            sb.append("\r\n");
            // 注释部分
            sb.append("/**\r\n");
            sb.append(" * " + tablename);

            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
            sb.append("  generated at " + formater.format(new Date()) + " by: " + this.authorName + "\r\n");

            sb.append(" */\r\n");
            // 实体部分
            sb.append("@Data\r\n");
            sb.append("@TableName(\""+tablename+"\" )");
            sb.append("\r\npublic class " + initcap(tablename) + "Entity extends BaseEntity{\r\n");
            processAllAttrs(sb);// 属性
//            processAllMethod(sb);// get set方法
            sb.append("}");

            return sb.toString();
        }

        /**
         * 功能：生成所有属性
         *
         * @param sb
         */
        private void processAllAttrs(StringBuffer sb) {
            for (int i = 0; i < colnames.length; i++) {
                if(colnames[i].equals("id") || colnames[i].equals("update_time") ||colnames[i].equals("create_time")){
                    continue;
                }
                sb.append("\tprivate " + sqlType2JavaType(colTypes[i]) + " " + propertyToCamelCase(colnames[i]) + ";\r\n");
            }
            sb.append(System.lineSeparator());
        }

        /**
         * 功能：生成所有方法
         *
         * @param sb
         */
        private void processAllMethod(StringBuffer sb) {

            for (int i = 0; i < colnames.length; i++) {
                sb.append("\tpublic void set" + initcap(colnames[i]) + "(" + sqlType2JavaType(colTypes[i]) + " "
                        + colnames[i] + "){\r\n");
                sb.append("\t\tthis." + colnames[i] + "=" + colnames[i] + ";\r\n");
                sb.append("\t}\r\n\r\n");
                sb.append("\tpublic " + sqlType2JavaType(colTypes[i]) + " get" + initcap(colnames[i]) + "(){\r\n");
                sb.append("\t\treturn " + colnames[i] + ";\r\n");
                sb.append("\t}\r\n\r\n");
            }

        }

        /**
         * 功能：将输入字符串的首字母改成大写
         *
         * @param str
         * @return
         */
        private String initcap(String str) {

            StringBuffer buffer = new StringBuffer();
            String[] s = str.split("_");
            for (int i = 1; i < s.length; i++) {
                char[] ch = s[i].toCharArray();

                if (ch[0] >= 'a' && ch[0] <= 'z') {
                    ch[0] = (char) (ch[0] - 32);
                }
                buffer.append(new String(ch));
            }
            return new String(buffer);
        }

    /**
     * 将字符串转换为驼峰命名
     * @param str
     * @return
     */
    private String propertyToCamelCase(String str) {

        StringBuffer buffer = new StringBuffer();
        String[] s = str.split("_");
        buffer.append(s[0]);
        for (int i = 1; i < s.length; i++) {
            char[] ch = s[i].toCharArray();

            if (ch[0] >= 'a' && ch[0] <= 'z') {
                ch[0] = (char) (ch[0] - 32);
            }
            buffer.append(new String(ch));
        }
        return new String(buffer);
    }
        /**
         * 功能：获得列的数据类型
         *
         * @param sqlType
         * @return
         */
        private String sqlType2JavaType(String sqlType) {

            if (sqlType.equalsIgnoreCase("bit")) {
                return "boolean";
            } else if (sqlType.equalsIgnoreCase("tinyint") || sqlType.equalsIgnoreCase("tinyINT UNSIGNED")) {
                return "byte";
            } else if (sqlType.equalsIgnoreCase("smallint")) {
                return "short";
            } else if (sqlType.equalsIgnoreCase("int") || sqlType.equalsIgnoreCase("INT UNSIGNED")) {
                return "int";
            } else if (sqlType.equalsIgnoreCase("bigint")||sqlType.equalsIgnoreCase("Long")) {
                return "long";
            } else if (sqlType.equalsIgnoreCase("float")) {
                return "float";
            } else if (sqlType.equalsIgnoreCase("decimal") || sqlType.equalsIgnoreCase("numeric")
                    || sqlType.equalsIgnoreCase("real") || sqlType.equalsIgnoreCase("money")
                    || sqlType.equalsIgnoreCase("smallmoney")||sqlType.equalsIgnoreCase("DOUBLE") ) {
                return "double";
            } else if (sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("char")
                    || sqlType.equalsIgnoreCase("nvarchar") || sqlType.equalsIgnoreCase("nchar")
                    || sqlType.equalsIgnoreCase("text")) {
                return "String";
            } else if (sqlType.equalsIgnoreCase("datetime") || sqlType.equalsIgnoreCase("date")) {
                return "Date";
            } else if (sqlType.equalsIgnoreCase("image")) {
                return "Blod";
            }else if (sqlType.equalsIgnoreCase("TIMESTAMP")){
                return "Timestamp";
            }

            return null;
        }

    public static void main(String[] args) {
        EntityGenerator entityGenerator = new EntityGenerator("jdbc:mysql://127.0.0.1:3306", "tdshop", "root", "sanhe123456");
        entityGenerator.Generate();
    }
}
