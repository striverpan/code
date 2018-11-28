package com.pan.util.csv;

import com.csvreader.CsvReader;
import com.pan.util.mysql.MysqlSqlHelper;

import java.nio.charset.Charset;
import java.util.ArrayList;

public class ReadCsvFile {

    public static void main(String[] args) {
        try {
            // 用来保存数据
            ArrayList<String[]> csvFileList = new ArrayList<String[]>();
            // 定义一个CSV路径
            String csvFilePath = ReadCsvFile.class.getClassLoader().getResource("tOperator.csv").getPath();
            // 创建CSV读对象 例如:CsvReader(文件路径，分隔符，编码格式);
            CsvReader reader = new CsvReader(csvFilePath, ',', Charset.forName("UTF-8"));
            // 跳过表头 如果需要表头的话，这句可以忽略
            reader.readHeaders();
            // 逐行读入除表头的数据
            while (reader.readRecord()) {
                //System.out.println(reader.getRawRecord());
                csvFileList.add(reader.getValues());
            }
            reader.close();

            // 遍历读取的CSV文件

            for (int row = 0; row < csvFileList.size(); row++) {
                String sql= "insert into TOPERATOR(l_operator_no ,\n" +
                       "  vc_operator_name        ,\n" +
                       "  vc_password             ,\n" +
                       "  vc_domain_name          ,\n" +
                       "  vc_address              ,\n" +
                       "  vc_email                ,\n" +
                       "  vc_tel_no               ,\n" +
                       "  c_card_type             ,\n" +
                       "  vc_card_no              ,\n" +
                       "  c_password_type         ,\n" +
                       "  l_modi_password         ,\n" +
                       "  l_expire_days           ,\n" +
                       "  c_operator_status       ,\n" +
                       "  vc_manage_right         ,\n" +
                       "  l_register_date         ,\n" +
                       "  l_cancel_date           ,\n" +
                       "  c_force_chg_password    ,\n" +
                       "  vc_operator_no          ,\n" +
                       "  c_connect_type          ,\n" +
                       "  vc_busin_classes        ,\n" +
                       "  l_password_minlength    ,\n" +
                       "  l_last_login_date       ,\n" +
                       "  c_stockcode_type        ,\n" +
                       "  vc_fax                  ,\n" +
                       "  vc_stamp                ,\n" +
                       "  l_fund_validity         ,\n" +
                       "  vc_pre_stamp            ,\n" +
                       "  l_transactor_no         ,\n" +
                       "  c_ref_shm               ,\n" +
                       "  vc_teller_code          ,\n" +
                       "  c_login_type            ,\n" +
                       "  vc_fund_def_rights      ,\n" +
                       "  l_org_id                ,\n" +
                       "  c_combi_asset_rights    ,\n" +
                       "  c_first_login           ,\n" +
                       "  vc_add_stockpool_rights ,\n" +
                       "  vc_add_stockpool_type   ,\n" +
                       "  l_dept_id               ,\n" +
                       "  c_isadmin               ,\n" +
                       "  c_carightaddbyf         ,\n" +
                       "  l_organize_id           ,\n" +
                       "  etl_src_tab            ,\n" +
                       "  etl_job       ,\n" +
                       "  etl_load_date         ,\n" +
                       "  etl_upd_date              ) values(";

                for (int i = 0; i < 45; i++) {
                    String cell = csvFileList.get(0)[i];
                    if (cell == null) {
                        cell = " ";
                    }
                    if ("(null)".equals(cell)) {
                        cell = null;
                        sql += cell;
                        sql += ",";
                        continue;
                    }
                    sql += "'";
                    sql += cell;
                    sql += "'";
                    sql += ",";
                }
                sql = sql.substring(0, sql.length() - 1);
                System.out.println(sql);
                try {
                    new MysqlSqlHelper<Object>().insert(sql + ")");

                }catch (Exception e){
                    e.printStackTrace();
                }
            }

        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
